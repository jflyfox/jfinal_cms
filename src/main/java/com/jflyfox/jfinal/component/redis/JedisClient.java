package com.jflyfox.jfinal.component.redis;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.util.SafeEncoder;

import com.jfinal.log.Log;
import com.jflyfox.util.Config;
import com.jflyfox.util.StrUtils;
import com.jflyfox.util.serializable.SerializerManage;

/**
 * redis瀹㈡埛绔�
 * 
 * 2016骞�5鏈�10鏃� 涓嬪崍2:34:30 flyfox 330627517@qq.com
 */
public class JedisClient {

	private static Log logger = Log.getLog(JedisClient.class);

	private static volatile JedisClient instance = null;

	public static JedisClient getInstance() {
		if (instance == null) {
			synchronized (JedisClient.class) {
				if (instance == null) {
					String password = Config.getStr("redis.password");
					password = StrUtils.isEmpty(password) ? null : password;
					instance = new JedisClient(Config.getStr("redis.host"), Config.getToInt("redis.port"),
							Config.getToInt("redis.maxIdel"), Config.getToInt("redis.maxWait"),
							Config.getToInt("redis.poolTimeWait"), password);
				}
			}
		}
		return instance;
	}

	public static void resetInstance() {
		instance = null;
	}

	private JedisPoolConfig config;
	private JedisPool pool;

	private JedisClient(String host, int port, int maxIdel, int maxWait, int poolTimeWait, String password) {
		config = new JedisPoolConfig();
		config.setMaxIdle(maxIdel);
		config.setMaxWaitMillis(maxWait);

		if (null == password)
			pool = new JedisPool(config, host, port, poolTimeWait);
		else {
			pool = new JedisPool(config, host, port, poolTimeWait, password);
		}
	}
	
	public Long expire(String key, int seconds) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.expire(key, seconds);
		}
	}
	
	public Long ttl(String key) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.ttl(key);
		}
	}

	public byte[] get(byte[] key) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.get(key);
		}
	}

	public void set(byte[] key, byte[] val) {
		try (Jedis jedis = pool.getResource()) {
			jedis.set(key, val);
		}
	}

	public void set(byte[] key, int seconds, byte[] val) {
		try (Jedis jedis = pool.getResource()) {
			jedis.setex(key, seconds, val);
		}
	}

	public String get(String key) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.get(key);
		}
	}

	public void set(String key, String val) {
		try (Jedis jedis = pool.getResource()) {
			jedis.set(key, val);
		}
	}

	public void set(String key, int seconds, String val) {
		try (Jedis jedis = pool.getResource()) {
			jedis.setex(key, seconds, val);
		}
	}

	public boolean setnx(String key) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.setnx(key, "setnx") == 1;
		}
	}
	
	public long setnx(String key, String val) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.setnx(key, val);
		}
	}
	
	public Long incr(String key) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.incr(key);
		}
	}

	public Long incrBy(String key, long value) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.incrBy(key, value);
		}
	}

	public Long decr(String key) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.decr(key);
		}
	}

	public Long decrBy(String key, long value) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.decrBy(key, value);
		}
	}

	public void append(String key, String str) {
		try (Jedis jedis = pool.getResource()) {
			jedis.append(key, str);
		}
	}

	public String setObj(String key, Object data) {
		byte[] vals = null;
		try {
			vals = SerializerManage.serialize(data);
		} catch (Exception e) {
			logger.error("SerializerManage error", e);
			return null;
		}

		if (vals == null) {
			return null;
		}

		try (Jedis jedis = pool.getResource()) {
			return jedis.set(SafeEncoder.encode(key), vals);
		}

	}

	public String setObj(String key, int seconds, Object data) {
		byte[] vals = null;
		try {
			vals = SerializerManage.serialize(data);
		} catch (Exception e) {
			logger.error("SerializerManage error", e);
			return null;
		}

		if (vals == null) {
			return null;
		}

		try (Jedis jedis = pool.getResource()) {
			return jedis.setex(SafeEncoder.encode(key), seconds, vals);
		}

	}

	@SuppressWarnings("unchecked")
	public <T> T getObj(String key) {
		byte[] vals = null;
		try (Jedis jedis = pool.getResource()) {
			vals = jedis.get(SafeEncoder.encode(key));
		}

		if (vals == null) {
			return null;
		}

		try {
			return (T) SerializerManage.deserialize(vals);
		} catch (Exception e) {
			logger.error("SerializerManage error", e);
			return null;
		}

	}

	/**
	 * 绉婚櫎缁欏畾鐨刱ey
	 * 
	 * @param key
	 */
	public void del(String key) {
		try (Jedis jedis = pool.getResource()) {
			jedis.del(key);
		}
	}
	
	/**
	 * 绉婚櫎缁欏畾鐨刱ey
	 * 
	 * @param key
	 */
	public void del(byte[] key) {
		try (Jedis jedis = pool.getResource()) {
			jedis.del(key);
		}
	}

	public void flushAll() {
		try (Jedis jedis = pool.getResource()) {
			jedis.flushAll();
		}
	}
	
	public long dbSize() {
		try (Jedis jedis = pool.getResource()) {
			return jedis.dbSize();
		}
	}
	
	/**
	 * 绉婚櫎缁欏畾key鐨勭敓瀛樻椂闂� 灏嗚繖涓� key 浠庛�庢槗澶辩殑銆�(甯︾敓瀛樻椂闂� key )杞崲鎴愩�庢寔涔呯殑銆�(涓�涓笉甯︾敓瀛樻椂闂淬�佹案涓嶈繃鏈熺殑 key )
	 * 
	 * @param key
	 */
	public void persist(String key) {
		try (Jedis jedis = pool.getResource()) {
			jedis.persist(key);
		}
	}

	/**
	 * 鎵归噺灏嗗�兼彃鍏orted sets涓紝濡傛灉member瀵瑰簲鐨剉alue涓嶅瓨鍦紝鍒欏垵濮嬪寲锛屽惁鍒欐洿鏂版member瀵瑰簲value
	 * 
	 * @param key
	 *            jedis涓璳ey
	 * @param scoreMembers
	 *            娣诲姞(淇敼鐨�)sets , key:score,value:member
	 */
	public void zadd(String key, Map<String, Double> scoreMembers, int validSecond) {
		try (Jedis jedis = pool.getResource()) {

			jedis.zadd(key, scoreMembers);

			jedis.expire(key, validSecond);
			// jedis.zremrangeByRank(key, rankSize, -1);
			// jedis.ltrim(key, 0, rankSize);
		}
	}

	public void zremrangeByRank(String key, int limit) {
		try (Jedis jedis = pool.getResource()) {
			jedis.zremrangeByRank(key, 0, -limit - 1);
		}
	}

	/**
	 * 灏嗕竴涓垨澶氫釜member鍏冪礌鍙婂叾score鍊煎姞鍏ュ埌鏈夊簭闆唊ey褰撲腑銆�
	 * 濡傛灉鏌愪釜member宸茬粡鏄湁搴忛泦鐨勬垚鍛橈紝閭ｄ箞鏇存柊杩欎釜member鐨剆core鍊�
	 * 锛屽苟閫氳繃閲嶆柊鎻掑叆杩欎釜member鍏冪礌锛屾潵淇濊瘉璇ember鍦ㄦ纭殑浣嶇疆涓娿�� score鍊煎彲浠ユ槸鏁存暟鍊兼垨鍙岀簿搴︽诞鐐规暟銆�
	 * 濡傛灉key涓嶅瓨鍦紝鍒欏垱寤轰竴涓┖鐨勬湁搴忛泦骞舵墽琛孼ADD鎿嶄綔銆� 褰搆ey瀛樺湪浣嗕笉鏄湁搴忛泦绫诲瀷鏃讹紝杩斿洖涓�涓敊璇��
	 * 
	 * @param key
	 *            jedis涓搴旂殑key
	 * @param score
	 *            鍒嗘暟锛宒ouble
	 * @param member
	 *            褰撳墠鍒嗘暟瀵瑰簲鐨勫璞�
	 */
	public void zadd(String key, double score, String member, int validSecond) {
		try (Jedis jedis = pool.getResource()) {
			jedis.zadd(key, score, member);
			jedis.expire(key, validSecond);
		}
	}

	/**
	 * 灏嗕竴涓垨澶氫釜member鍏冪礌鍙婂叾score鍊煎姞鍏ュ埌鏈夊簭闆唊ey褰撲腑銆�
	 * 濡傛灉鏌愪釜member宸茬粡鏄湁搴忛泦鐨勬垚鍛橈紝閭ｄ箞鏇存柊杩欎釜member鐨剆core鍊�
	 * 锛屽苟閫氳繃閲嶆柊鎻掑叆杩欎釜member鍏冪礌锛屾潵淇濊瘉璇ember鍦ㄦ纭殑浣嶇疆涓娿�� score鍊煎彲浠ユ槸鏁存暟鍊兼垨鍙岀簿搴︽诞鐐规暟銆�
	 * 濡傛灉key涓嶅瓨鍦紝鍒欏垱寤轰竴涓┖鐨勬湁搴忛泦骞舵墽琛孼ADD鎿嶄綔銆� 褰搆ey瀛樺湪浣嗕笉鏄湁搴忛泦绫诲瀷鏃讹紝杩斿洖涓�涓敊璇��
	 * 
	 * @param key
	 *            jedis涓搴旂殑key
	 * @param score
	 *            鍒嗘暟锛宒ouble
	 * @param member
	 *            褰撳墠鍒嗘暟瀵瑰簲鐨勫璞�
	 */
	public void zadd(String key, double score, String member) {
		try (Jedis jedis = pool.getResource()) {
			jedis.zadd(key, score, member);
		}
	}
	
	/**
	 * 杩斿洖鍗囧簭鎺掑悕鐨勭敤鎴峰悕鍜屽垎鏁扮殑闆嗗悎,鍗冲垎鏁颁粠鏈�浣庡埌鏈�楂樼殑鎺掑垪锛屽垎鏁扮浉绛夌殑涓や釜member鏍规嵁瀛楀吀鎺掑簭椤哄簭杩斿洖銆�
	 * 濡傛灉瑕佽幏鍙栭檷搴忔帓鍒楋紝璇蜂娇鐢╖revrange鎺ュ彛銆� start鍜宻top鐨勫悕娆￠兘鏄熀浜�0鐨勶紝涔熷氨鏄�0鏄涓�涓厓绱狅紝1鏄浜屼釜鍏冪礌锛屼互姝ょ被鎺ㄣ��
	 * 浠栦滑涔熷彲浠ユ槸璐熸暟锛岃〃绀轰粠sorted set鏈�鍚庡紑濮嬪畾浣嶏紝-1琛ㄧず鏈�鍚庝竴涓厓绱狅紝-2琛ㄧず鍊掓暟绗簩涓紝浠ユ绫绘帹
	 * 鍋囧璇磋秺鐣屼簡锛屼笉浼氫骇鐢熼敊璇�傚鏋滃紑濮嬩綅锛坰tart锛夋瘮鏁翠釜sorted set閮藉ぇ锛屾垨鑰卻tart澶т簬stop锛岄偅涔堜細杩斿洖涓�涓┖鐨刲ist銆�
	 * 濡傛灉stop姣旂湡涓猻orted sets閮藉ぇ锛宺edis浼氭妸stop璁や负鏄sorted set涓殑鏈�鍚庝竴涓厓绱犮��
	 * 
	 * @param key
	 *            jedis涓搴旂殑key
	 * @param start
	 *            start index锛宻tart with 0,
	 * @param stop
	 */
	public LinkedHashSet<String> zrange(String key, long start, long stop) {
		try (Jedis jedis = pool.getResource()) {
			LinkedHashSet<String> result = (LinkedHashSet<String>) jedis.zrange(key, start, stop);
			return result;
		}
	}

	/**
	 * 杩斿洖mem瀵瑰簲鐨勬帓鍚�(浠庝綆鍒伴珮鎺掑悕锛屽亣濡傝繖涓摜浠垎鏁版渶浣庯紝鍒欒繑鍥炵殑鏄�0鑰屼笉鏄�1) 鍋囧闇�瑕佽幏鍙栧垎鏁颁粠澶у埌灏忕殑鎺掑悕锛岃浣跨敤鏂规硶ZREVRANK
	 * 鍋囧瀵瑰簲member鐨勫湪sorted set涓瓨鍦紝杩斿洖瀵瑰簲鐨剅ank銆� 濡傛灉member涓嶅瓨鍦紝鎴栬�卥ey瀵瑰簲鐨剆orted
	 * set涓嶅瓨鍦紝杩斿洖null
	 * 
	 * @param key
	 * @param member
	 */
	public Long zrank(String key, String member) {
		try (Jedis jedis = pool.getResource()) {
			Long index = jedis.zrank(key, member);
			return index;
		}
	}

	/**
	 * 杩斿洖mem瀵瑰簲鐨勬帓鍚�(浠庨珮鍒颁綆鎺掑悕锛屽亣濡傝繖涓摜浠垎鏁版渶楂橈紝鍒欒繑鍥炵殑鏄�0鑰屼笉鏄�1) 鍋囧闇�瑕佽幏鍙栧垎鏁颁粠灏忓埌澶х殑鎺掑悕锛岃浣跨敤鏂规硶ZRANK
	 * 鍋囧瀵瑰簲member鐨勫湪sorted set涓瓨鍦紝杩斿洖瀵瑰簲鐨剅ank銆� 濡傛灉member涓嶅瓨鍦紝鎴栬�卥ey瀵瑰簲鐨剆orted
	 * set涓嶅瓨鍦紝杩斿洖null
	 * 
	 * @param key
	 * @param member
	 * @return
	 */
	public Long zrevrank(String key, String member) {
		try (Jedis jedis = pool.getResource()) {
			Long index = jedis.zrevrank(key, member);
			return index;
		}
	}

	/**
	 * 杩斿洖闄嶅簭鎺掑悕鐨勭敤鎴峰悕鍜屽垎鏁扮殑闆嗗悎,鍗冲垎鏁颁粠鏈�楂樺埌鏈�浣庣殑鎺掑垪锛屽垎鏁扮浉绛夌殑涓や釜member鏍规嵁瀛楀吀鎺掑簭椤哄簭杩斿洖銆�
	 * 濡傛灉瑕佽幏鍙栧崌搴忔帓鍒楋紝璇蜂娇鐢╖revrange鎺ュ彛銆� start鍜宻top鐨勫悕娆￠兘鏄熀浜�0鐨勶紝涔熷氨鏄�0鏄涓�涓厓绱狅紝1鏄浜屼釜鍏冪礌锛屼互姝ょ被鎺ㄣ��
	 * 浠栦滑涔熷彲浠ユ槸璐熸暟锛岃〃绀轰粠sorted set鏈�鍚庡紑濮嬪畾浣嶏紝-1琛ㄧず鏈�鍚庝竴涓厓绱狅紝-2琛ㄧず鍊掓暟绗簩涓紝浠ユ绫绘帹
	 * 鍋囧璇磋秺鐣屼簡锛屼笉浼氫骇鐢熼敊璇�傚鏋滃紑濮嬩綅锛坰tart锛夋瘮鏁翠釜sorted set閮藉ぇ锛屾垨鑰卻tart澶т簬stop锛岄偅涔堜細杩斿洖涓�涓┖鐨刲ist銆�
	 * 濡傛灉stop姣旂湡涓猻orted sets閮藉ぇ锛宺edis浼氭妸stop璁や负鏄sorted set涓殑鏈�鍚庝竴涓厓绱狅紝鍗冲垎鏁版渶浣庣殑鍏冪礌銆�
	 * 
	 * @param key
	 * @param start
	 * @param stop
	 * @return
	 */
	public LinkedHashSet<String> zrevrange(String key, long start, long end) {
		try (Jedis jedis = pool.getResource()) {
			LinkedHashSet<String> result = (LinkedHashSet<String>) jedis.zrevrange(key, start, end);
			return result;
		}
	}

	/**
	 * 杩斿洖鎸囧畾member瀵瑰簲鐨勫垎鏁� 濡傛灉member涓嶅瓨鍦紝鎴栬�卥ey瀵瑰簲鐨剆orted set涓嶅瓨鍦紝鍒欒繑鍥瀗ull
	 * 
	 * @param key
	 * @param member
	 * @return
	 */
	public Double zscore(String key, String member) {
		try (Jedis jedis = pool.getResource()) {
			Double result = jedis.zscore(key, member);
			return result;
		}
	}

	/**
	 * 鍒犻櫎key瀵瑰簲鐨剆orted set涓殑鎸囧畾members, 濡傛灉member涓嶅瓨鍦�,鍒欎笉杩涜浠讳綍鎿嶄綔,
	 * 濡傛灉key鎵�瀵瑰簲value涓嶆槸set,杩斿洖閿欒<br>
	 * 
	 * 杩斿洖锛氬疄闄呭垹闄ょ殑member涓暟.P.S:1琛ㄧずmember琚垹闄�;0琛ㄧずmember涓嶅瓨鍦�
	 * 
	 * @param key
	 * @param member
	 * @return
	 */
	public Long zrem(String key, String... members) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.zrem(key, members);
		}
	}

	/**
	 * 鍒犻櫎鎸囧畾key鐨剆orted set涓璻ank浣嶄簬start鍜宔nd涔嬮棿鐨勬墍鏈夊厓绱�.start鍜宔nd閮芥槸鍩轰簬0鐨勬暟瀛�,鍏朵腑rank=0
	 * 鏄渶浣庣殑鍒嗘暟. start鍜宔nd閮藉彲浠ヤ负璐熸暟,琛ㄧず浠庢渶楂榬ank璧峰鐨勫厓绱�,渚嬪:-1涓烘渶楂樺垎,-2涓虹浜岄珮鍒嗙瓑.杩斿洖鍒犻櫎鍏冪礌鐨勪釜鏁�.
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public long zremrangeByRank(String key, long start, long end) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.zremrangeByRank(key, start, end);
		}
	}

	/**
	 * 鍚戝瓨浜� key 鐨勫垪琛ㄧ殑灏鹃儴鎻掑叆鎵�鏈夋寚瀹氱殑鍊笺�� 濡傛灉 key 涓嶅瓨鍦紝閭ｄ箞浼氬垱寤轰竴涓┖鐨勫垪琛ㄧ劧鍚庡啀杩涜 push 鎿嶄綔銆� 褰� key
	 * 淇濆瓨鐨勪笉鏄竴涓垪琛紝閭ｄ箞浼氳繑鍥炰竴涓敊璇��
	 * 
	 * 鍙互浣跨敤涓�涓懡浠ゆ妸澶氫釜鍏冪礌鎵撳叆闃熷垪锛屽彧闇�瑕佸湪鍛戒护鍚庨潰鎸囧畾澶氫釜鍙傛暟銆� 鍏冪礌鏄粠宸﹀埌鍙充竴涓帴涓�涓粠鍒楄〃灏鹃儴鎻掑叆銆� 姣斿鍛戒护 RPUSH mylist
	 * a b c 浼氳繑鍥炰竴涓垪琛紝鍏剁涓�涓厓绱犳槸 a 锛岀浜屼釜鍏冪礌鏄� b 锛岀涓変釜鍏冪礌鏄� c銆�
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public long rpush(String key, String... values) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.rpush(key, values);
		}
	}

	/**
	 * 杩斿洖瀛樺偍鍦� key 鐨勫垪琛ㄩ噷鎸囧畾鑼冨洿鍐呯殑鍏冪礌銆� start 鍜� end
	 * 鍋忕Щ閲忛兘鏄熀浜�0鐨勪笅鏍囷紝鍗砽ist鐨勭涓�涓厓绱犱笅鏍囨槸0锛坙ist鐨勮〃澶达級锛岀浜屼釜鍏冪礌涓嬫爣鏄�1锛屼互姝ょ被鎺ㄣ��
	 * 鍋忕Щ閲忎篃鍙互鏄礋鏁帮紝琛ㄧず鍋忕Щ閲忔槸浠巐ist灏鹃儴寮�濮嬭鏁般�� 渚嬪锛� -1 琛ㄧず鍒楄〃鐨勬渶鍚庝竴涓厓绱狅紝-2 鏄�掓暟绗簩涓紝浠ユ绫绘帹銆�
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<String> lrange(String key, int start, int end) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.lrange(key, start, end);
		}
	}

	/**
	 * 淇壀(trim)涓�涓凡瀛樺湪鐨� list锛岃繖鏍� list 灏变細鍙寘鍚寚瀹氳寖鍥寸殑鎸囧畾鍏冪礌銆� start 鍜� stop 閮芥槸鐢�0寮�濮嬭鏁扮殑锛� 杩欓噷鐨� 0
	 * 鏄垪琛ㄩ噷鐨勭涓�涓厓绱狅紙琛ㄥご锛夛紝1 鏄浜屼釜鍏冪礌锛屼互姝ょ被鎺ㄣ��
	 * 
	 * 渚嬪锛� LTRIM foobar 0 2 灏嗕細瀵瑰瓨鍌ㄥ湪 foobar 鐨勫垪琛ㄨ繘琛屼慨鍓紝鍙繚鐣欏垪琛ㄩ噷鐨勫墠3涓厓绱犮��
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public String ltrim(String key, int start, int end) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.ltrim(key, start, end);
		}
	}

	/**
	 * 杩斿洖瀛樺偍鍦� key 閲岀殑list鐨勯暱搴︺�� 濡傛灉 key 涓嶅瓨鍦紝閭ｄ箞灏辫鐪嬩綔鏄┖list锛屽苟涓旇繑鍥為暱搴︿负 0銆� 褰撳瓨鍌ㄥ湪 key
	 * 閲岀殑鍊间笉鏄竴涓猯ist鐨勮瘽锛屼細杩斿洖error銆�
	 * 
	 * @param key
	 * @return
	 */
	public long llen(String key) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.llen(key);
		}
	}

	/**
	 * 涓烘湁搴忛泦key鐨勬垚鍛榤ember鐨剆core鍊煎姞涓婂閲廼ncrement銆�
	 * 濡傛灉key涓笉瀛樺湪member锛屽氨鍦╧ey涓坊鍔犱竴涓猰ember锛宻core鏄痠ncrement锛堝氨濂藉儚瀹冧箣鍓嶇殑score鏄�0.0锛夈��
	 * 濡傛灉key涓嶅瓨鍦紝灏卞垱寤轰竴涓彧鍚湁鎸囧畾member鎴愬憳鐨勬湁搴忛泦鍚堛��
	 * score鍊煎繀椤绘槸瀛楃涓茶〃绀虹殑鏁存暟鍊兼垨鍙岀簿搴︽诞鐐规暟锛屽苟涓旇兘鎺ュ彈double绮惧害鐨勬诞鐐规暟銆備篃鏈夊彲鑳界粰涓�涓礋鏁版潵鍑忓皯score鐨勫�笺��
	 * 
	 * 褰搆ey涓嶆槸鏈夊簭闆嗙被鍨嬫椂锛岃繑鍥炰竴涓敊璇��
	 * 
	 * @param key
	 * @param score
	 * @param member
	 * @return
	 */
	public double zincrBy(String key, double score, String member) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.zincrby(key, score, member);
		}
	}


	/********************** hashmap *******************************/
	public Map<String, String> hgetAll(String key) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.hgetAll(key);
		}
	}

	public List<String> mget(String... keys) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.mget(keys);
		}
	}
	
	public String hget(String key, String field) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.hget(key, field);
		}
	}

	public Long hset(String key, String field, String value) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.hset(key, field, value);
		}
	}

	public Long hdel(String key, String field) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.hdel(key, field);
		}
	}

	public List<byte[]> mget(byte[]... keys) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.mget(keys);
		}
	}
	
	public Map<byte[], byte[]> hgetAll(byte[] key) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.hgetAll(key);
		}
	}
	
	public byte[] hget(byte[] key, byte[] field) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.hget(key, field);
		}
	}

	public Long hset(byte[] key, byte[] field, byte[] value) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.hset(key, field, value);
		}
	}

	public Long hdel(byte[] key, byte[] field) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.hdel(key, field);
		}
	}
	
	///////////////////////// hashmap object //////////////////////////////
	@SuppressWarnings("unchecked")
	public <T> Map<String, T> hgetAllObj(String key) {
		Map<byte[], byte[]> map = null;
		Map<String, T> mapObj = new HashMap<String, T>();
		try (Jedis jedis = pool.getResource()) {
			map = jedis.hgetAll(SafeEncoder.encode(key));
		}

		if (map == null) {
			return null;
		}

		for (Map.Entry<byte[], byte[]> entry : map.entrySet()) {
			String objKey = SafeEncoder.encode(entry.getKey());
			T obj = null;
			try {
				obj = (T) SerializerManage.deserialize(entry.getValue());
			} catch (Exception e) {
				logger.error("SerializerManage error", e);
			}
			mapObj.put(objKey, obj);
		}

		return mapObj;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T hgetObj(String key, String field) {
		byte[] vals = null;
		try (Jedis jedis = pool.getResource()) {
			vals = jedis.hget(SafeEncoder.encode(key), SafeEncoder.encode(field));
		}

		if (vals == null) {
			return null;
		}

		try {
			return (T) SerializerManage.deserialize(vals);
		} catch (Exception e) {
			logger.error("SerializerManage error", e);
			return null;
		}
	}

	public Long hsetObj(String key, String field, Object value) {
		byte[] vals = null;
		try {
			vals = SerializerManage.serialize(value);
		} catch (Exception e) {
			logger.error("SerializerManage error", e);
			return null;
		}

		if (vals == null) {
			return null;
		}
		
		try (Jedis jedis = pool.getResource()) {
			return jedis.hset(SafeEncoder.encode(key), SafeEncoder.encode(field), vals);
		}
	}

	public Long hdelObj(String key, String field) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.hdel(SafeEncoder.encode(key), SafeEncoder.encode(field));
		}
	}
	
	/**
	 * 鑾峰彇鏁伴噺
	 * 
	 * @param key
	 */
	public long hlenObj(String key) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.hlen(SafeEncoder.encode(key));
		}
	}
	
	/**
	 * 绉婚櫎hashmap鎵�鏈塊ey
	 * 
	 * @param key
	 */
	public void hdelObjAll(String key) {
		try (Jedis jedis = pool.getResource()) {
			jedis.del(SafeEncoder.encode(key));
		}
	}
	
}
