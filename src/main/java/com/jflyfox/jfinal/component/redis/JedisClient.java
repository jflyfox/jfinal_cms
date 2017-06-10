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
 * redis客户端
 * 
 * 2016年5月10日 下午2:34:30 flyfox 330627517@qq.com
 */
public class JedisClient {

	private static Log logger = Log.getLog(JedisClient.class);

	private static JedisClient instance = null;

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
	 * 移除给定的key
	 * 
	 * @param key
	 */
	public void del(String key) {
		try (Jedis jedis = pool.getResource()) {
			jedis.del(key);
		}
	}
	
	/**
	 * 移除给定的key
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
	 * 移除给定key的生存时间 将这个 key 从『易失的』(带生存时间 key )转换成『持久的』(一个不带生存时间、永不过期的 key )
	 * 
	 * @param key
	 */
	public void persist(String key) {
		try (Jedis jedis = pool.getResource()) {
			jedis.persist(key);
		}
	}

	/**
	 * 批量将值插入sorted sets中，如果member对应的value不存在，则初始化，否则更新此member对应value
	 * 
	 * @param key
	 *            jedis中key
	 * @param scoreMembers
	 *            添加(修改的)sets , key:score,value:member
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
	 * 将一个或多个member元素及其score值加入到有序集key当中。
	 * 如果某个member已经是有序集的成员，那么更新这个member的score值
	 * ，并通过重新插入这个member元素，来保证该member在正确的位置上。 score值可以是整数值或双精度浮点数。
	 * 如果key不存在，则创建一个空的有序集并执行ZADD操作。 当key存在但不是有序集类型时，返回一个错误。
	 * 
	 * @param key
	 *            jedis中对应的key
	 * @param score
	 *            分数，double
	 * @param member
	 *            当前分数对应的对象
	 */
	public void zadd(String key, double score, String member, int validSecond) {
		try (Jedis jedis = pool.getResource()) {
			jedis.zadd(key, score, member);
			jedis.expire(key, validSecond);
		}
	}

	/**
	 * 将一个或多个member元素及其score值加入到有序集key当中。
	 * 如果某个member已经是有序集的成员，那么更新这个member的score值
	 * ，并通过重新插入这个member元素，来保证该member在正确的位置上。 score值可以是整数值或双精度浮点数。
	 * 如果key不存在，则创建一个空的有序集并执行ZADD操作。 当key存在但不是有序集类型时，返回一个错误。
	 * 
	 * @param key
	 *            jedis中对应的key
	 * @param score
	 *            分数，double
	 * @param member
	 *            当前分数对应的对象
	 */
	public void zadd(String key, double score, String member) {
		try (Jedis jedis = pool.getResource()) {
			jedis.zadd(key, score, member);
		}
	}
	
	/**
	 * 返回升序排名的用户名和分数的集合,即分数从最低到最高的排列，分数相等的两个member根据字典排序顺序返回。
	 * 如果要获取降序排列，请使用Zrevrange接口。 start和stop的名次都是基于0的，也就是0是第一个元素，1是第二个元素，以此类推。
	 * 他们也可以是负数，表示从sorted set最后开始定位，-1表示最后一个元素，-2表示倒数第二个，以此类推
	 * 假如说越界了，不会产生错误。如果开始位（start）比整个sorted set都大，或者start大于stop，那么会返回一个空的list。
	 * 如果stop比真个sorted sets都大，redis会把stop认为是此sorted set中的最后一个元素。
	 * 
	 * @param key
	 *            jedis中对应的key
	 * @param start
	 *            start index，start with 0,
	 * @param stop
	 */
	public LinkedHashSet<String> zrange(String key, long start, long stop) {
		try (Jedis jedis = pool.getResource()) {
			LinkedHashSet<String> result = (LinkedHashSet<String>) jedis.zrange(key, start, stop);
			return result;
		}
	}

	/**
	 * 返回mem对应的排名(从低到高排名，假如这个哥们分数最低，则返回的是0而不是1) 假如需要获取分数从大到小的排名，请使用方法ZREVRANK
	 * 假如对应member的在sorted set中存在，返回对应的rank。 如果member不存在，或者key对应的sorted
	 * set不存在，返回null
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
	 * 返回mem对应的排名(从高到低排名，假如这个哥们分数最高，则返回的是0而不是1) 假如需要获取分数从小到大的排名，请使用方法ZRANK
	 * 假如对应member的在sorted set中存在，返回对应的rank。 如果member不存在，或者key对应的sorted
	 * set不存在，返回null
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
	 * 返回降序排名的用户名和分数的集合,即分数从最高到最低的排列，分数相等的两个member根据字典排序顺序返回。
	 * 如果要获取升序排列，请使用Zrevrange接口。 start和stop的名次都是基于0的，也就是0是第一个元素，1是第二个元素，以此类推。
	 * 他们也可以是负数，表示从sorted set最后开始定位，-1表示最后一个元素，-2表示倒数第二个，以此类推
	 * 假如说越界了，不会产生错误。如果开始位（start）比整个sorted set都大，或者start大于stop，那么会返回一个空的list。
	 * 如果stop比真个sorted sets都大，redis会把stop认为是此sorted set中的最后一个元素，即分数最低的元素。
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
	 * 返回指定member对应的分数 如果member不存在，或者key对应的sorted set不存在，则返回null
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
	 * 删除key对应的sorted set中的指定members, 如果member不存在,则不进行任何操作,
	 * 如果key所对应value不是set,返回错误<br>
	 * 
	 * 返回：实际删除的member个数.P.S:1表示member被删除;0表示member不存在
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
	 * 删除指定key的sorted set中rank位于start和end之间的所有元素.start和end都是基于0的数字,其中rank=0
	 * 是最低的分数. start和end都可以为负数,表示从最高rank起始的元素,例如:-1为最高分,-2为第二高分等.返回删除元素的个数.
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
	 * 向存于 key 的列表的尾部插入所有指定的值。 如果 key 不存在，那么会创建一个空的列表然后再进行 push 操作。 当 key
	 * 保存的不是一个列表，那么会返回一个错误。
	 * 
	 * 可以使用一个命令把多个元素打入队列，只需要在命令后面指定多个参数。 元素是从左到右一个接一个从列表尾部插入。 比如命令 RPUSH mylist
	 * a b c 会返回一个列表，其第一个元素是 a ，第二个元素是 b ，第三个元素是 c。
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
	 * 返回存储在 key 的列表里指定范围内的元素。 start 和 end
	 * 偏移量都是基于0的下标，即list的第一个元素下标是0（list的表头），第二个元素下标是1，以此类推。
	 * 偏移量也可以是负数，表示偏移量是从list尾部开始计数。 例如， -1 表示列表的最后一个元素，-2 是倒数第二个，以此类推。
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
	 * 修剪(trim)一个已存在的 list，这样 list 就会只包含指定范围的指定元素。 start 和 stop 都是由0开始计数的， 这里的 0
	 * 是列表里的第一个元素（表头），1 是第二个元素，以此类推。
	 * 
	 * 例如： LTRIM foobar 0 2 将会对存储在 foobar 的列表进行修剪，只保留列表里的前3个元素。
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
	 * 返回存储在 key 里的list的长度。 如果 key 不存在，那么就被看作是空list，并且返回长度为 0。 当存储在 key
	 * 里的值不是一个list的话，会返回error。
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
	 * 为有序集key的成员member的score值加上增量increment。
	 * 如果key中不存在member，就在key中添加一个member，score是increment（就好像它之前的score是0.0）。
	 * 如果key不存在，就创建一个只含有指定member成员的有序集合。
	 * score值必须是字符串表示的整数值或双精度浮点数，并且能接受double精度的浮点数。也有可能给一个负数来减少score的值。
	 * 
	 * 当key不是有序集类型时，返回一个错误。
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
	 * 获取数量
	 * 
	 * @param key
	 */
	public long hlenObj(String key) {
		try (Jedis jedis = pool.getResource()) {
			return jedis.hlen(SafeEncoder.encode(key));
		}
	}
	
	/**
	 * 移除hashmap所有Key
	 * 
	 * @param key
	 */
	public void hdelObjAll(String key) {
		try (Jedis jedis = pool.getResource()) {
			jedis.del(SafeEncoder.encode(key));
		}
	}
	
}
