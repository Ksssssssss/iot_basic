package com.htjc.iot.redis;

import com.alibaba.fastjson.JSON;
import com.htjc.iot.util.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description:redis服务类
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-04-19 16:43
 */

@Component
public class RedisService {
    @Autowired
    private JedisProvider provider;

    @Autowired
    private TimeService timeService;

    /**
     * @param key
     * @param clazz
     * @param <T>
     * @return
     * @description getkey
     */
    public <T> T hget(String key, String key2, Class<T> clazz) {
        T ret = null;
        try (Jedis jedis = provider.provider()) {
            String target = jedis.hget(key, key2);
            if (StringUtils.isEmpty(target)) {
                return ret;
            }
            ret = parseObject(target, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public void zadd(String key, long score, String msg) {
        try (Jedis jedis = provider.provider()) {
            jedis.zadd(key, score, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long zcard(String key) {
        long count = 0;
        try (Jedis jedis = provider.provider()) {
            count = jedis.zcard(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public <T> T get(String key, Class<T> clazz) {
        String target = "";
        try (Jedis jedis = provider.provider()) {
            target = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parseObject(target, clazz);
    }

    public Set<String> zrevrangeByScore(String key, long maxTime, int count) {
        Set<String> ret = null;
        try (Jedis jedis = provider.provider()) {
            ret = jedis.zrevrangeByScore(key, maxTime, 0, 0, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public void hset(String key, String key2, String msg) {
        try (Jedis jedis = provider.provider()) {
            jedis.hset(key, key2, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hdel(String key, String... key2) {
        try (Jedis jedis = provider.provider()) {
            if (key2.length > 0) {
                jedis.hdel(key, key2);
                return;
            }
            jedis.hdel(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void zrem(String key) {
        try (Jedis jedis = provider.provider()) {
            jedis.zrem(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> hgetAll(String key) {
        Map<String, String> ret = null;
        try (Jedis jedis = provider.provider()) {
            ret = jedis.hgetAll(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public <T> List<T> hgetAll(String firstKey, Set<String> keys, Class<T> clazz) {
        List<T> objs = new ArrayList<>(keys.size());
        List<Response<String>> target = new ArrayList<>(keys.size());

        try (Jedis jedis = provider.provider();
             Pipeline pipeline = jedis.pipelined()) {
            pipeline.multi();
            for (String key : keys) {
                Response<String> response = pipeline.hget(firstKey, key);
                target.add(response);
            }
            pipeline.exec();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Response<String> response : target) {
            T obj = JSON.parseObject(response.get(), clazz);
            objs.add(obj);
        }
        return objs;
    }

    /**
     * @param key
     * @param obj
     * @param <T>
     * @return
     */
    public <T> boolean setex(String key, T obj) {
        String value = toString(obj);
        if (StringUtils.isEmpty(value)) {
            return false;
        }

        try (Jedis jedis = provider.provider()) {
            jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 加载lua脚本
     *
     * @param lua
     * @return
     */
    public String loadLua(String lua) {
        String sha1 = "";
        try (Jedis jedis = provider.provider();) {
            sha1 = jedis.scriptLoad(lua);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sha1;
    }

    /**
     * 执行lua脚本
     *
     * @param keys
     * @param params
     * @param lua
     * @return
     */
    public Object scriptLua(String lua, List<String> keys, List<String> params) {
        Object obj = null;
        try (Jedis jedis = provider.provider();) {
            obj = jedis.evalsha(lua, keys, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * obj转Stirng
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String toString(T obj) {
        if (obj == null) {
            return null;
        }

        String result = "";
        try {
            result = JSON.toJSONString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * String转class
     *
     * @param target
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String target, Class<T> clazz) {
        if (StringUtils.isEmpty(target)) {
            return null;
        }
        return JSON.parseObject(target, clazz);
    }

    public static void main(String[] args) {
    }
}
