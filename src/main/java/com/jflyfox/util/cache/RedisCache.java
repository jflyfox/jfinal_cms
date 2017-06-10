package com.jflyfox.util.cache;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.jflyfox.jfinal.component.redis.JedisClient;

public class RedisCache implements Cache {

	protected String name;
	protected JedisClient client = null;

	public RedisCache() {
		client = JedisClient.getInstance();
	}

	public String name() {
		return name;
	}

	public RedisCache name(String name) {
		this.name = name;
		return this;
	}

	public RedisCache add(String key, Object value) {
		client.hsetObj(this.name, key, value);
		return this;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		return (T) client.hgetObj(this.name, key);
	}

	public Object remove(String key) {
		client.hdel(this.name, key);
		return null;
	}

	public void clear() {
		client.hdelObjAll(this.name);
	}
	
	public int size() {
		return (int) client.hlenObj(this.name);
	}

	public Set<String> keys() {
		Map<String, Object> map = client.hgetAllObj(this.name);
		if (map.size() == 0) {
			return null;
		}
		
		return map.keySet();
	}

	public <T> Collection<T> values() {
		Map<String, T> map = client.hgetAllObj(this.name);
		if (map.size() == 0) {
			return null;
		}

		return map.values();
	}

}
