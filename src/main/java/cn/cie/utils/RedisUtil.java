package cn.cie.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates redis operations and uses protostuff for serialization and deserialization
 */
@Component
public class RedisUtil<T> implements InitializingBean {

    private JedisPool jedisPool;

    private static final String REDIS_URL = "redis://localhost:6379/6";

    public static final String EVERYDAY = "everyday";

    public static final String KINDS = "kinds";

    public static final String NEWESTGAME = "newestgame";

    public static final String PRE_UP_GAMES = "preupgames";

    /**
     * tore a piece of data
     *
     * @param key
     * @param value
     * @return
     */
    public String put(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Store a piece of data that expires at a fixed time
     *
     * @param key
     * @param value
     * @param timeout
     * @return
     */
    public String putEx(String key, String value, int timeout) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.setex(key, timeout, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * get key by value
     *
     * @param key
     * @return
     */
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Storing an object
     *
     * @param key
     * @param value
     * @return
     */
    public String putObject(String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key, JSON.toJSONString(value));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Stores an object with a timed expiration date
     *
     * @param key
     * @param value
     * @param timeout
     * @return
     */
    public String putObjectEx(String key, T value, int timeout) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.setex(key, timeout, JSON.toJSONString(value));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Get the corresponding object according to the key
     *
     * @param key
     * @return
     */
    public T getObject(String key, Class clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return (T) JSON.parseObject(jedis.get(key), clazz);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Delete data based on key
     *
     * @param key
     * @return
     */
    public long delete(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.del(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Dequeues an element from the head of the queue. If the queue is empty, it will block for the specified timeout in seconds and then return null.
     * If the timeout is 0, it will block indefinitely until an element is available.
     *
     * @param timeout The blocking time in seconds
     * @param key
     * @param clazz
     * @return
     */
    public T blpopObject(int timeout, String key, Class clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            List<String> list = jedis.blpop(timeout, key);
            return (T) JSON.parseObject(list.get(0), clazz);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Pop an element from the left side of the queue
     *
     * @param key
     * @param clazz
     * @return
     */
    public T lpopObject(String key, Class clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return (T) JSON.parseObject(jedis.lpop(key), clazz);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Add data to the end of the list
     *
     * @param key
     * @param values
     * @return
     */
    public long rpushObject(String key, Class clazz, Object... values) {
        if (values == null || values.length == 0) {
            return 0;
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String[] jsonStrs = new String[values.length];
            int index = 0;
            for (Object value : values) {
                jsonStrs[index] = JSON.toJSONString(value);
                ++index;
            }
            return jedis.rpush(key, jsonStrs);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Adding data deleted at a certain point in time to the end of the list
     *
     * @param key
     * @param time   unix timestamp
     * @param values
     * @return
     */
    public long rpushObjectExAtTime(String key, Class clazz, long time, Object... values) {
        if (values.length == 0) {
            return 0;
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String[] jsonStrs = new String[values.length];
            int index = 0;
            for (Object value : values) {
                jsonStrs[index] = JSON.toJSONString(value);
                ++index;
            }
            long res = jedis.rpush(key, jsonStrs);
            jedis.expireAt(key.getBytes(), time);      // Manually set the expiration time
            return res;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Add a periodic deletion of data at the end of the list
     *
     * @param key
     * @param clazz
     * @param timeout
     * @param values
     * @return
     */
    public long rpushObjectEx(String key, Class clazz, int timeout, Object... values) {
        if (values.length == 0) {
            return 0;
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String[] jsonStrs = new String[values.length];
            int index = 0;
            for (Object value : values) {
                jsonStrs[index] = JSON.toJSONString(value);
                ++index;
            }
            long res = jedis.rpush(key, jsonStrs);
            jedis.expire(key, timeout);
            return res;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Get all the data in the list
     *
     * @param key
     * @return
     */
    public List<T> lall(String key, Class clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            List<String> list = jedis.lrange(key, 0, -1);
            List<T> res = new ArrayList<T>();
            if (list == null || list.size() == 0) {
                return res;
            }
            for (String str : list) {
                res.add((T) JSON.parseObject(str, clazz));
            }
            return res;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * This method is called automatically after spring injection.
     *
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {
        System.out.println("Creating a Redis Connection");
        jedisPool = new JedisPool(REDIS_URL);
        System.out.println("jedisPool:"+jedisPool.toString());
        System.out.println("Creating a Redis Connection Succeeded");
    }
}
