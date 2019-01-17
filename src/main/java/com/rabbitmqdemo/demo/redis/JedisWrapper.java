package com.rabbitmqdemo.demo.redis;

import com.yundasys.common.logger.LoggerUtil;
import com.yundasys.common.logger.LoggerUtil.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;
import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.util.Slowlog;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author jingongmei
 * @ClassName: JedisWrapper
 * @Description: redis针对连接池的代理 防止连接池用光
 * @date 2018年4月23日 下午3:05:40
 */
@Component
public class JedisWrapper extends Jedis {

    // 缓存连接工厂
    @Autowired
    @Qualifier("factory")
    private JedisConnectionFactory factory;

    /**
     * 注入工厂类
     */
    public JedisWrapper(JedisConnectionFactory factory) {
        super(factory.getHostName(), factory.getPort(), factory.getTimeout());
        this.factory = factory;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param seconds
     * @param value
     * @return
     * @see redis.clients.jedis.Jedis#setex(String, int, String)
     */
    @Override
    public String setex(String key, int seconds, String value) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.setex(key, seconds, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令setex异常");
        }
        return result;
    }

    /**
     * (non-Javadoc)
     *
     * @return
     * @see redis.clients.jedis.Jedis#ping()
     */
    @Override
    public String ping() {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.ping();
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令ping异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param value
     * @return
     * @see redis.clients.jedis.Jedis#set(String, String)
     */
    @Override
    public String set(String key, String value) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.set(key, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令set异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.Jedis#get(String)
     */
    @Override
    public String get(String key) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.get(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令get异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @return
     * @see redis.clients.jedis.Jedis#quit()
     */
    @Override
    public String quit() {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.quit();
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令quit异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.Jedis#exists(String)
     */
    @Override
    public Boolean exists(String key) {
        RedisConnection RedisConnection = factory.getConnection();
        Boolean result = false;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.exists(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令exists异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param keys
     * @return
     * @see redis.clients.jedis.Jedis#del(String[])
     */
    @Override
    public Long del(String... keys) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.del(keys);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令del异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.Jedis#type(String)
     */
    @Override
    public String type(String key) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.type(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令type异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @return
     * @see redis.clients.jedis.Jedis#flushDB()
     */
    @Override
    public String flushDB() {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.flushDB();
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令flushDB异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param pattern
     * @return
     * @see redis.clients.jedis.Jedis#keys(String)
     */
    @Override
    public Set<String> keys(String pattern) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<String> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.keys(pattern);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令keys异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @return
     * @see redis.clients.jedis.Jedis#randomKey()
     */
    @Override
    public String randomKey() {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.randomKey();
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令randomKey异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param oldkey
     * @param newkey
     * @return
     * @see redis.clients.jedis.Jedis#rename(String, String)
     */
    @Override
    public String rename(String oldkey, String newkey) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.rename(oldkey, newkey);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令rename异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param oldkey
     * @param newkey
     * @return
     * @see redis.clients.jedis.Jedis#renamenx(String, String)
     */
    @Override
    public Long renamenx(String oldkey, String newkey) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.renamenx(oldkey, newkey);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令renamenx异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param seconds
     * @return
     * @see redis.clients.jedis.Jedis#expire(String, int)
     */
    @Override
    public Long expire(String key, int seconds) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.expire(key, seconds);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令expire异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param unixTime
     * @return
     * @see redis.clients.jedis.Jedis#expireAt(String, long)
     */
    @Override
    public Long expireAt(String key, long unixTime) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.expireAt(key, unixTime);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令expireAt异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.Jedis#ttl(String)
     */
    @Override
    public Long ttl(String key) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.ttl(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令ttl异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param index
     * @return
     * @see redis.clients.jedis.Jedis#select(int)
     */
    @Override
    public String select(int index) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.select(index);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令select异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param dbIndex
     * @return
     * @see redis.clients.jedis.Jedis#move(String, int)
     */
    @Override
    public Long move(String key, int dbIndex) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.move(key, dbIndex);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令move异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @return
     * @see redis.clients.jedis.Jedis#flushAll()
     */
    @Override
    public String flushAll() {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.flushAll();
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令flushAll异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param value
     * @return
     * @see redis.clients.jedis.Jedis#getSet(String, String)
     */
    @Override
    public String getSet(String key, String value) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.getSet(key, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令getSet异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param keys
     * @return
     * @see redis.clients.jedis.Jedis#mget(String[])
     */
    @Override
    public List<String> mget(String... keys) {
        RedisConnection RedisConnection = factory.getConnection();
        List<String> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.mget(keys);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令mget异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param value
     * @return
     * @see redis.clients.jedis.Jedis#setnx(String, String)
     */
    @Override
    public Long setnx(String key, String value) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.setnx(key, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令setnx异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param keysvalues
     * @return
     * @see redis.clients.jedis.Jedis#mset(String[])
     */
    @Override
    public String mset(String... keysvalues) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.mset(keysvalues);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令mset异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param keysvalues
     * @return
     * @see redis.clients.jedis.Jedis#msetnx(String[])
     */
    @Override
    public Long msetnx(String... keysvalues) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.msetnx(keysvalues);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令msetnx异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param integer
     * @return
     * @see redis.clients.jedis.Jedis#decrBy(String, long)
     */
    @Override
    public Long decrBy(String key, long integer) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.decrBy(key, integer);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令decrBy异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.Jedis#decr(String)
     */
    @Override
    public Long decr(String key) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.decr(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令decr异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param integer
     * @return
     * @see redis.clients.jedis.Jedis#incrBy(String, long)
     */
    @Override
    public Long incrBy(String key, long integer) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.incrBy(key, integer);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令incrBy异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.Jedis#incr(String)
     */
    @Override
    public Long incr(String key) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.incr(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令incr异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param value
     * @return
     * @see redis.clients.jedis.Jedis#append(String, String)
     */
    @Override
    public Long append(String key, String value) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.append(key, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令append异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param start
     * @param end
     * @return
     * @see redis.clients.jedis.Jedis#substr(String, int, int)
     */
    @Override
    public String substr(String key, int start, int end) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.substr(key, start, end);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令substr异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param field
     * @param value
     * @return
     * @see redis.clients.jedis.Jedis#hset(String, String, String)
     */
    @Override
    public Long hset(String key, String field, String value) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.hset(key, field, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令hset异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param field
     * @return
     * @see redis.clients.jedis.Jedis#hget(String, String)
     */
    @Override
    public String hget(String key, String field) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.hget(key, field);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令hget异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param field
     * @param value
     * @return
     * @see redis.clients.jedis.Jedis#hsetnx(String, String, String)
     */
    @Override
    public Long hsetnx(String key, String field, String value) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.hsetnx(key, field, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令hsetnx异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param hash
     * @return
     * @see redis.clients.jedis.Jedis#hmset(String, Map)
     */
    @Override
    public String hmset(String key, Map<String, String> hash) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.hmset(key, hash);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令hmset异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param fields
     * @return
     * @see redis.clients.jedis.Jedis#hmget(String, String[])
     */
    @Override
    public List<String> hmget(String key, String... fields) {
        RedisConnection RedisConnection = factory.getConnection();
        List<String> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.hmget(key, fields);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令hmget异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param field
     * @param value
     * @return
     * @see redis.clients.jedis.Jedis#hincrBy(String, String, long)
     */
    @Override
    public Long hincrBy(String key, String field, long value) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.hincrBy(key, field, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令hincrBy异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param field
     * @return
     * @see redis.clients.jedis.Jedis#hexists(String, String)
     */
    @Override
    public Boolean hexists(String key, String field) {
        RedisConnection RedisConnection = factory.getConnection();
        Boolean result = false;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.hexists(key, field);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令hexists异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param fields
     * @return
     * @see redis.clients.jedis.Jedis#hdel(String, String[])
     */
    @Override
    public Long hdel(String key, String... fields) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.hdel(key, fields);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令hdel异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.Jedis#hlen(String)
     */
    @Override
    public Long hlen(String key) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.hlen(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令hlen异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.Jedis#hkeys(String)
     */
    @Override
    public Set<String> hkeys(String key) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<String> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.hkeys(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令hkeys异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.Jedis#hvals(String)
     */
    @Override
    public List<String> hvals(String key) {
        RedisConnection RedisConnection = factory.getConnection();
        List<String> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.hvals(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令hvals异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.Jedis#hgetAll(String)
     */
    @Override
    public Map<String, String> hgetAll(String key) {
        RedisConnection RedisConnection = factory.getConnection();
        Map<String, String> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.hgetAll(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令hgetAll异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param strings
     * @return
     * @see redis.clients.jedis.Jedis#rpush(String, String[])
     */
    @Override
    public Long rpush(String key, String... strings) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.rpush(key, strings);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令rpush异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param strings
     * @return
     * @see redis.clients.jedis.Jedis#lpush(String, String[])
     */
    @Override
    public Long lpush(String key, String... strings) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.lpush(key, strings);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令lpush异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.Jedis#llen(String)
     */
    @Override
    public Long llen(String key) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.llen(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令llen异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param start
     * @param end
     * @return
     * @see redis.clients.jedis.Jedis#lrange(String, long, long)
     */
    @Override
    public List<String> lrange(String key, long start, long end) {
        RedisConnection RedisConnection = factory.getConnection();
        List<String> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.lrange(key, start, end);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令lrange异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param start
     * @param end
     * @return
     * @see redis.clients.jedis.Jedis#ltrim(String, long, long)
     */
    @Override
    public String ltrim(String key, long start, long end) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.ltrim(key, start, end);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令ltrim异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param index
     * @return
     * @see redis.clients.jedis.Jedis#lindex(String, long)
     */
    @Override
    public String lindex(String key, long index) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.lindex(key, index);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令lindex异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param index
     * @param value
     * @return
     * @see redis.clients.jedis.Jedis#lset(String, long, String)
     */
    @Override
    public String lset(String key, long index, String value) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.lset(key, index, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令lset异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param count
     * @param value
     * @return
     * @see redis.clients.jedis.Jedis#lrem(String, long, String)
     */
    @Override
    public Long lrem(String key, long count, String value) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.lrem(key, count, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令lrem异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.Jedis#lpop(String)
     */
    @Override
    public String lpop(String key) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.lpop(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令lpop异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.Jedis#rpop(String)
     */
    @Override
    public String rpop(String key) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.rpop(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令rpop异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param srckey
     * @param dstkey
     * @return
     * @see redis.clients.jedis.Jedis#rpoplpush(String, String)
     */
    @Override
    public String rpoplpush(String srckey, String dstkey) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.rpoplpush(srckey, dstkey);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令rpoplpush异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param members
     * @return
     * @see redis.clients.jedis.Jedis#sadd(String, String[])
     */
    @Override
    public Long sadd(String key, String... members) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.sadd(key, members);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令sadd异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.Jedis#smembers(String)
     */
    @Override
    public Set<String> smembers(String key) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<String> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.smembers(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令smembers异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param members
     * @return
     * @see redis.clients.jedis.Jedis#srem(String, String[])
     */
    @Override
    public Long srem(String key, String... members) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.srem(key, members);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令srem异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.Jedis#spop(String)
     */
    @Override
    public String spop(String key) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.spop(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令spop异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param srckey
     * @param dstkey
     * @param member
     * @return
     * @see redis.clients.jedis.Jedis#smove(String, String, String)
     */
    @Override
    public Long smove(String srckey, String dstkey, String member) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.smove(srckey, dstkey, member);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令smove异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.Jedis#scard(String)
     */
    @Override
    public Long scard(String key) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.scard(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令scard异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param member
     * @return
     * @see redis.clients.jedis.Jedis#sismember(String, String)
     */
    @Override
    public Boolean sismember(String key, String member) {
        RedisConnection RedisConnection = factory.getConnection();
        Boolean result = false;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.sismember(key, member);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令sismember异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param keys
     * @return
     * @see redis.clients.jedis.Jedis#sinter(String[])
     */
    @Override
    public Set<String> sinter(String... keys) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<String> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.sinter(keys);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令sinter异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param dstkey
     * @param keys
     * @return
     * @see redis.clients.jedis.Jedis#sinterstore(String, String[])
     */
    @Override
    public Long sinterstore(String dstkey, String... keys) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.sinterstore(dstkey, keys);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令sinterstore异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param keys
     * @return
     * @see redis.clients.jedis.Jedis#sunion(String[])
     */
    @Override
    public Set<String> sunion(String... keys) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<String> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.sunion(keys);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令sunion异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param dstkey
     * @param keys
     * @return
     * @see redis.clients.jedis.Jedis#sunionstore(String, String[])
     */
    @Override
    public Long sunionstore(String dstkey, String... keys) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.sunionstore(dstkey, keys);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令sunionstore异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param keys
     * @return
     * @see redis.clients.jedis.Jedis#sdiff(String[])
     */
    @Override
    public Set<String> sdiff(String... keys) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<String> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.sdiff(keys);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令sdiff异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param dstkey
     * @param keys
     * @return
     * @see redis.clients.jedis.Jedis#sdiffstore(String, String[])
     */
    @Override
    public Long sdiffstore(String dstkey, String... keys) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.sdiffstore(dstkey, keys);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令sdiffstore异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.Jedis#srandmember(String)
     */
    @Override
    public String srandmember(String key) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.srandmember(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令srandmember异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param score
     * @param member
     * @return
     * @see redis.clients.jedis.Jedis#zadd(String, double, String)
     */
    @Override
    public Long zadd(String key, double score, String member) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zadd(key, score, member);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zadd异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param start
     * @param end
     * @return
     * @see redis.clients.jedis.Jedis#zrange(String, long, long)
     */
    @Override
    public Set<String> zrange(String key, long start, long end) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<String> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrange(key, start, end);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrange异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param members
     * @return
     * @see redis.clients.jedis.Jedis#zrem(String, String[])
     */
    @Override
    public Long zrem(String key, String... members) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrem(key, members);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrem异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param score
     * @param member
     * @return
     * @see redis.clients.jedis.Jedis#zincrby(String, double, String)
     */
    @Override
    public Double zincrby(String key, double score, String member) {
        RedisConnection RedisConnection = factory.getConnection();
        Double result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zincrby(key, score, member);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zincrby异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param member
     * @return
     * @see redis.clients.jedis.Jedis#zrank(String, String)
     */
    @Override
    public Long zrank(String key, String member) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrank(key, member);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrank异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param member
     * @return
     * @see redis.clients.jedis.Jedis#zrevrank(String, String)
     */
    @Override
    public Long zrevrank(String key, String member) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrevrank(key, member);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrevrank异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param start
     * @param end
     * @return
     * @see redis.clients.jedis.Jedis#zrevrange(String, long, long)
     */
    @Override
    public Set<String> zrevrange(String key, long start, long end) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<String> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrevrange(key, start, end);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrevrange异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param start
     * @param end
     * @return
     * @see redis.clients.jedis.Jedis#zrangeWithScores(String, long, long)
     */
    @Override
    public Set<Tuple> zrangeWithScores(String key, long start, long end) {

        RedisConnection RedisConnection = factory.getConnection();
        Set<Tuple> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrangeWithScores(key, start, end);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrangeWithScores异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param start
     * @param end
     * @return
     * @see redis.clients.jedis.Jedis#zrevrangeWithScores(String, long, long)
     */
    @Override
    public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<Tuple> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrevrangeWithScores(key, start, end);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrevrangeWithScores异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.Jedis#zcard(String)
     */
    @Override
    public Long zcard(String key) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zcard(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zcard异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param member
     * @return
     * @see redis.clients.jedis.Jedis#zscore(String, String)
     */
    @Override
    public Double zscore(String key, String member) {
        RedisConnection RedisConnection = factory.getConnection();
        Double result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zscore(key, member);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zscore异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param keys
     * @return
     * @see redis.clients.jedis.Jedis#watch(String[])
     */
    @Override
    public String watch(String... keys) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.watch(keys);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令watch异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.Jedis#sort(String)
     */
    @Override
    public List<String> sort(String key) {
        RedisConnection RedisConnection = factory.getConnection();
        List<String> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.sort(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令sort异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param sortingParameters
     * @return
     * @see redis.clients.jedis.Jedis#sort(String, redis.clients.jedis.SortingParams)
     */
    @Override
    public List<String> sort(String key, SortingParams sortingParameters) {
        RedisConnection RedisConnection = factory.getConnection();
        List<String> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.sort(key, sortingParameters);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令sort异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param timeout
     * @param keys
     * @return
     * @see redis.clients.jedis.Jedis#blpop(int, String[])
     */
    @Override
    public List<String> blpop(int timeout, String... keys) {
        RedisConnection RedisConnection = factory.getConnection();
        List<String> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.blpop(timeout, keys);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令blpop异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param sortingParameters
     * @param dstkey
     * @return
     * @see redis.clients.jedis.Jedis#sort(String, redis.clients.jedis.SortingParams, String)
     */
    @Override
    public Long sort(String key, SortingParams sortingParameters, String dstkey) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.sort(key, sortingParameters, dstkey);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令sort异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param dstkey
     * @return
     * @see redis.clients.jedis.Jedis#sort(String, String)
     */
    @Override
    public Long sort(String key, String dstkey) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.sort(key, dstkey);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令sort异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param timeout
     * @param keys
     * @return
     * @see redis.clients.jedis.Jedis#brpop(int, String[])
     */
    @Override
    public List<String> brpop(int timeout, String... keys) {
        RedisConnection RedisConnection = factory.getConnection();
        List<String> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.brpop(timeout, keys);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令brpop异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param password
     * @return
     * @see redis.clients.jedis.Jedis#auth(String)
     */
    @Override
    public String auth(String password) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.auth(password);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令auth异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param jedisPubSub
     * @param channels
     * @see redis.clients.jedis.Jedis#subscribe(redis.clients.jedis.JedisPubSub, String[])
     */
    @Override
    public void subscribe(JedisPubSub jedisPubSub, String... channels) {
        RedisConnection RedisConnection = factory.getConnection();
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            proxy.subscribe(jedisPubSub, channels);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令subscribe异常");
        }

    }


    /**
     * (non-Javadoc)
     *
     * @param channel
     * @param message
     * @return
     * @see redis.clients.jedis.Jedis#publish(String, String)
     */
    @Override
    public Long publish(String channel, String message) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.publish(channel, message);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令publish异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param jedisPubSub
     * @param patterns
     * @see redis.clients.jedis.Jedis#psubscribe(redis.clients.jedis.JedisPubSub, String[])
     */
    @Override
    public void psubscribe(JedisPubSub jedisPubSub, String... patterns) {
        RedisConnection RedisConnection = factory.getConnection();
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            proxy.psubscribe(jedisPubSub, patterns);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令psubscribe异常");
        }

    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param min
     * @param max
     * @return
     * @see redis.clients.jedis.Jedis#zcount(String, double, double)
     */
    @Override
    public Long zcount(String key, double min, double max) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zcount(key, min, max);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zcount异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param min
     * @param max
     * @return
     * @see redis.clients.jedis.Jedis#zcount(String, String, String)
     */
    @Override
    public Long zcount(String key, String min, String max) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zcount(key, min, max);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zcount异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param min
     * @param max
     * @return
     * @see redis.clients.jedis.Jedis#zrangeByScore(String, double, double)
     */
    @Override
    public Set<String> zrangeByScore(String key, double min, double max) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<String> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrangeByScore(key, min, max);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrangeByScore异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param min
     * @param max
     * @return
     * @see redis.clients.jedis.Jedis#zrangeByScore(String, String, String)
     */
    @Override
    public Set<String> zrangeByScore(String key, String min, String max) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<String> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrangeByScore(key, min, max);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrangeByScore异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param min
     * @param max
     * @param offset
     * @param count
     * @return
     * @see redis.clients.jedis.Jedis#zrangeByScore(String, double, double, int, int)
     */
    @Override
    public Set<String> zrangeByScore(String key, double min, double max,
                                     int offset, int count) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<String> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrangeByScore(key, min, max, offset, count);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrangeByScore异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param min
     * @param max
     * @param offset
     * @param count
     * @return
     * @see redis.clients.jedis.Jedis#zrangeByScore(String, String, String, int, int)
     */
    @Override
    public Set<String> zrangeByScore(String key, String min, String max,
                                     int offset, int count) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<String> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrangeByScore(key, min, max, offset, count);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrangeByScore异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param min
     * @param max
     * @return
     * @see redis.clients.jedis.Jedis#zrangeByScoreWithScores(String, double, double)
     */
    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<Tuple> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrangeByScoreWithScores(key, min, max);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrangeByScoreWithScores异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param min
     * @param max
     * @return
     * @see redis.clients.jedis.Jedis#zrangeByScoreWithScores(String, String, String)
     */
    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<Tuple> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrangeByScoreWithScores(key, min, max);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrangeByScoreWithScores异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param min
     * @param max
     * @param offset
     * @param count
     * @return
     * @see redis.clients.jedis.Jedis#zrangeByScoreWithScores(String, double, double, int, int)
     */
    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min,
                                              double max, int offset, int count) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<Tuple> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy
                    .zrangeByScoreWithScores(key, min, max, offset, count);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrangeByScoreWithScores异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param min
     * @param max
     * @param offset
     * @param count
     * @return
     * @see redis.clients.jedis.Jedis#zrangeByScoreWithScores(String, String, String, int, int)
     */
    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min,
                                              String max, int offset, int count) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<Tuple> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy
                    .zrangeByScoreWithScores(key, min, max, offset, count);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrangeByScoreWithScores异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param max
     * @param min
     * @return
     * @see redis.clients.jedis.Jedis#zrevrangeByScore(String, double, double)
     */
    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<String> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrevrangeByScore(key, max, min);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrevrangeByScore异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param max
     * @param min
     * @return
     * @see redis.clients.jedis.Jedis#zrevrangeByScore(String, String, String)
     */
    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<String> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrevrangeByScore(key, max, min);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrevrangeByScore异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param max
     * @param min
     * @param offset
     * @param count
     * @return
     * @see redis.clients.jedis.Jedis#zrevrangeByScore(String, double, double, int, int)
     */
    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min,
                                        int offset, int count) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<String> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrevrangeByScore(key, max, min, offset, count);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrevrangeByScore异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param max
     * @param min
     * @return
     * @see redis.clients.jedis.Jedis#zrevrangeByScoreWithScores(String, double, double)
     */
    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max,
                                                 double min) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<Tuple> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrevrangeByScoreWithScores(key, max, min);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil
                    .log(Level.ERROR, "执行Redis命令zrevrangeByScoreWithScores异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param max
     * @param min
     * @param offset
     * @param count
     * @return
     * @see redis.clients.jedis.Jedis#zrevrangeByScoreWithScores(String, double, double, int, int)
     */
    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max,
                                                 double min, int offset, int count) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<Tuple> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrevrangeByScoreWithScores(key, max, min, offset,
                    count);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil
                    .log(Level.ERROR, "执行Redis命令zrevrangeByScoreWithScores异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param max
     * @param min
     * @param offset
     * @param count
     * @return
     * @see redis.clients.jedis.Jedis#zrevrangeByScoreWithScores(String, String, String, int, int)
     */
    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max,
                                                 String min, int offset, int count) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<Tuple> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrevrangeByScoreWithScores(key, max, min, offset,
                    count);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil
                    .log(Level.ERROR, "执行Redis命令zrevrangeByScoreWithScores异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param max
     * @param min
     * @param offset
     * @param count
     * @return
     * @see redis.clients.jedis.Jedis#zrevrangeByScore(String, String, String, int, int)
     */
    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min,
                                        int offset, int count) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<String> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrevrangeByScore(key, max, min, offset, count);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrevrangeByScore异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param max
     * @param min
     * @return
     * @see redis.clients.jedis.Jedis#zrevrangeByScoreWithScores(String, String, String)
     */
    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max,
                                                 String min) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<Tuple> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrevrangeByScoreWithScores(key, max, min);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil
                    .log(Level.ERROR, "执行Redis命令zrevrangeByScoreWithScores异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param start
     * @param end
     * @return
     * @see redis.clients.jedis.Jedis#zremrangeByRank(String, long, long)
     */
    @Override
    public Long zremrangeByRank(String key, long start, long end) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zremrangeByRank(key, start, end);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zremrangeByRank异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param start
     * @param end
     * @return
     * @see redis.clients.jedis.Jedis#zremrangeByScore(String, double, double)
     */
    @Override
    public Long zremrangeByScore(String key, double start, double end) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zremrangeByScore(key, start, end);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zremrangeByScore异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param start
     * @param end
     * @return
     * @see redis.clients.jedis.Jedis#zremrangeByScore(String, String, String)
     */
    @Override
    public Long zremrangeByScore(String key, String start, String end) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zremrangeByScore(key, start, end);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zremrangeByScore异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param dstkey
     * @param sets
     * @return
     * @see redis.clients.jedis.Jedis#zunionstore(String, String[])
     */
    @Override
    public Long zunionstore(String dstkey, String... sets) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zunionstore(dstkey, sets);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zunionstore异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param dstkey
     * @param params
     * @param sets
     * @return
     * @see redis.clients.jedis.Jedis#zunionstore(String, redis.clients.jedis.ZParams, String[])
     */
    @Override
    public Long zunionstore(String dstkey, ZParams params, String... sets) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zunionstore(dstkey, params, sets);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zunionstore异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param dstkey
     * @param sets
     * @return
     * @see redis.clients.jedis.Jedis#zinterstore(String, String[])
     */
    @Override
    public Long zinterstore(String dstkey, String... sets) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zinterstore(dstkey, sets);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zinterstore异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param dstkey
     * @param params
     * @param sets
     * @return
     * @see redis.clients.jedis.Jedis#zinterstore(String, redis.clients.jedis.ZParams, String[])
     */
    @Override
    public Long zinterstore(String dstkey, ZParams params, String... sets) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zinterstore(dstkey, params, sets);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zinterstore异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.Jedis#strlen(String)
     */
    @Override
    public Long strlen(String key) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.strlen(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令strlen异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param string
     * @return
     * @see redis.clients.jedis.Jedis(String, String)
     */
    public Long lpushx(String key, String string) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.lpushx(key, string);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令lpushx异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.Jedis#persist(String)
     */
    @Override
    public Long persist(String key) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.persist(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令persist异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param string
     * @return
     * @see redis.clients.jedis.Jedis(String, String)
     */
    public Long rpushx(String key, String string) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.rpushx(key, string);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令rpushx异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param string
     * @return
     * @see redis.clients.jedis.Jedis#echo(String)
     */
    @Override
    public String echo(String string) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.echo(string);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令echo异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param where
     * @param pivot
     * @param value
     * @return
     * @see redis.clients.jedis.Jedis#linsert(String, redis.clients.jedis.BinaryClient.LIST_POSITION, String, String)
     */
    @Override
    public Long linsert(String key, LIST_POSITION where, String pivot,
                        String value) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.linsert(key, where, pivot, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令linsert异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param source
     * @param destination
     * @param timeout
     * @return
     * @see redis.clients.jedis.Jedis#brpoplpush(String, String, int)
     */
    @Override
    public String brpoplpush(String source, String destination, int timeout) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.brpoplpush(source, destination, timeout);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令brpoplpush异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param offset
     * @param value
     * @return
     * @see redis.clients.jedis.Jedis#setbit(String, long, boolean)
     */
    @Override
    public Boolean setbit(String key, long offset, boolean value) {
        RedisConnection RedisConnection = factory.getConnection();
        Boolean result = false;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.setbit(key, offset, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令setbit异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param offset
     * @return
     * @see redis.clients.jedis.Jedis#getbit(String, long)
     */
    @Override
    public Boolean getbit(String key, long offset) {
        RedisConnection RedisConnection = factory.getConnection();
        Boolean result = false;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.getbit(key, offset);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令getbit异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param offset
     * @param value
     * @return
     * @see redis.clients.jedis.Jedis#setrange(String, long, String)
     */
    @Override
    public Long setrange(String key, long offset, String value) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.setrange(key, offset, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令setrange异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param startOffset
     * @param endOffset
     * @return
     * @see redis.clients.jedis.Jedis#getrange(String, long, long)
     */
    @Override
    public String getrange(String key, long startOffset, long endOffset) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.getrange(key, startOffset, endOffset);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令getrange异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param pattern
     * @return
     * @see redis.clients.jedis.Jedis#configGet(String)
     */
    @Override
    public List<String> configGet(String pattern) {
        RedisConnection RedisConnection = factory.getConnection();
        List<String> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.configGet(pattern);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令configGet异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param parameter
     * @param value
     * @return
     * @see redis.clients.jedis.Jedis#configSet(String, String)
     */
    @Override
    public String configSet(String parameter, String value) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.configSet(parameter, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令configSet异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param script
     * @param keyCount
     * @param params
     * @return
     * @see redis.clients.jedis.Jedis#eval(String, int, String[])
     */
    @Override
    public Object eval(String script, int keyCount, String... params) {
        RedisConnection RedisConnection = factory.getConnection();
        Object result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.eval(script, keyCount, params);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令eval异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param script
     * @param keys
     * @param args
     * @return
     * @see redis.clients.jedis.Jedis#eval(String, List, List)
     */
    @Override
    public Object eval(String script, List<String> keys, List<String> args) {
        RedisConnection RedisConnection = factory.getConnection();
        Object result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.eval(script, keys, args);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令eval异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param script
     * @return
     * @see redis.clients.jedis.Jedis#eval(String)
     */
    @Override
    public Object eval(String script) {
        RedisConnection RedisConnection = factory.getConnection();
        Object result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.eval(script);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令eval异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param script
     * @return
     * @see redis.clients.jedis.Jedis#evalsha(String)
     */
    @Override
    public Object evalsha(String script) {
        RedisConnection RedisConnection = factory.getConnection();
        Object result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.evalsha(script);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令evalsha异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param sha1
     * @param keys
     * @param args
     * @return
     * @see redis.clients.jedis.Jedis#evalsha(String, List, List)
     */
    @Override
    public Object evalsha(String sha1, List<String> keys, List<String> args) {
        RedisConnection RedisConnection = factory.getConnection();
        Object result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.evalsha(sha1, keys, args);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令evalsha异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param sha1
     * @param keyCount
     * @param params
     * @return
     * @see redis.clients.jedis.Jedis#evalsha(String, int, String[])
     */
    @Override
    public Object evalsha(String sha1, int keyCount, String... params) {
        RedisConnection RedisConnection = factory.getConnection();
        Object result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.evalsha(sha1, keyCount, params);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令evalsha异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param sha1
     * @return
     * @see redis.clients.jedis.Jedis#scriptExists(String)
     */
    @Override
    public Boolean scriptExists(String sha1) {
        RedisConnection RedisConnection = factory.getConnection();
        Boolean result = false;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.scriptExists(sha1);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令scriptExists异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param sha1
     * @return
     * @see redis.clients.jedis.Jedis#scriptExists(String[])
     */
    @Override
    public List<Boolean> scriptExists(String... sha1) {
        RedisConnection RedisConnection = factory.getConnection();
        List<Boolean> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.scriptExists(sha1);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令scriptExists异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param script
     * @return
     * @see redis.clients.jedis.Jedis#scriptLoad(String)
     */
    @Override
    public String scriptLoad(String script) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.scriptLoad(script);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令scriptLoad异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @return
     * @see redis.clients.jedis.Jedis#slowlogGet()
     */
    @Override
    public List<Slowlog> slowlogGet() {
        RedisConnection RedisConnection = factory.getConnection();
        List<Slowlog> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.slowlogGet();
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令slowlogGet异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param entries
     * @return
     * @see redis.clients.jedis.Jedis#slowlogGet(long)
     */
    @Override
    public List<Slowlog> slowlogGet(long entries) {
        RedisConnection RedisConnection = factory.getConnection();
        List<Slowlog> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.slowlogGet(entries);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令slowlogGet异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param string
     * @return
     * @see redis.clients.jedis.Jedis#objectRefcount(String)
     */
    @Override
    public Long objectRefcount(String string) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.objectRefcount(string);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令objectRefcount异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param string
     * @return
     * @see redis.clients.jedis.Jedis#objectEncoding(String)
     */
    @Override
    public String objectEncoding(String string) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.objectEncoding(string);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令objectEncoding异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param string
     * @return
     * @see redis.clients.jedis.Jedis#objectIdletime(String)
     */
    @Override
    public Long objectIdletime(String string) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.objectIdletime(string);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令objectIdletime异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param value
     * @return
     * @see redis.clients.jedis.BinaryJedis#set(byte[], byte[])
     */
    @Override
    public String set(byte[] key, byte[] value) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.set(key, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令set异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.BinaryJedis#get(byte[])
     */
    @Override
    public byte[] get(byte[] key) {
        RedisConnection RedisConnection = factory.getConnection();
        byte[] result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.get(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令get异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.BinaryJedis#exists(byte[])
     */
    @Override
    public Boolean exists(byte[] key) {
        RedisConnection RedisConnection = factory.getConnection();
        Boolean result = false;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.exists(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令exists异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param keys
     * @return
     * @see redis.clients.jedis.BinaryJedis#del(byte[][])
     */
    @Override
    public Long del(byte[]... keys) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.del(keys);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令del异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.BinaryJedis#type(byte[])
     */
    @Override
    public String type(byte[] key) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.type(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令type异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param pattern
     * @return
     * @see redis.clients.jedis.BinaryJedis#keys(byte[])
     */
    @Override
    public Set<byte[]> keys(byte[] pattern) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.keys(pattern);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令keys异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @return
     * @see redis.clients.jedis.BinaryJedis#randomBinaryKey()
     */
    @Override
    public byte[] randomBinaryKey() {
        RedisConnection RedisConnection = factory.getConnection();
        byte[] result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.randomBinaryKey();
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令randomBinaryKey异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param oldkey
     * @param newkey
     * @return
     * @see redis.clients.jedis.BinaryJedis#rename(byte[], byte[])
     */
    @Override
    public String rename(byte[] oldkey, byte[] newkey) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.rename(oldkey, newkey);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令rename异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param oldkey
     * @param newkey
     * @return
     * @see redis.clients.jedis.BinaryJedis#renamenx(byte[], byte[])
     */
    @Override
    public Long renamenx(byte[] oldkey, byte[] newkey) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.renamenx(oldkey, newkey);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令renamenx异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @return
     * @see redis.clients.jedis.BinaryJedis#dbSize()
     */
    @Override
    public Long dbSize() {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.dbSize();
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令dbSize异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param seconds
     * @return
     * @see redis.clients.jedis.BinaryJedis#expire(byte[], int)
     */
    @Override
    public Long expire(byte[] key, int seconds) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.expire(key, seconds);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令expire异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param unixTime
     * @return
     * @see redis.clients.jedis.BinaryJedis#expireAt(byte[], long)
     */
    @Override
    public Long expireAt(byte[] key, long unixTime) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.expireAt(key, unixTime);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令expireAt异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.BinaryJedis#ttl(byte[])
     */
    @Override
    public Long ttl(byte[] key) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.ttl(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令ttl异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param dbIndex
     * @return
     * @see redis.clients.jedis.BinaryJedis#move(byte[], int)
     */
    @Override
    public Long move(byte[] key, int dbIndex) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.move(key, dbIndex);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令move异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param value
     * @return
     * @see redis.clients.jedis.BinaryJedis#getSet(byte[], byte[])
     */
    @Override
    public byte[] getSet(byte[] key, byte[] value) {
        RedisConnection RedisConnection = factory.getConnection();
        byte[] result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.getSet(key, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令getSet异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param keys
     * @return
     * @see redis.clients.jedis.BinaryJedis#mget(byte[][])
     */
    @Override
    public List<byte[]> mget(byte[]... keys) {
        RedisConnection RedisConnection = factory.getConnection();
        List<byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.mget(keys);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令mget异常");
        }
        return result;
    }


    public Long setnx(byte[] key, byte[] value) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.setnx(key, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令setnx异常");
        }
        return result;
    }


    public String setex(byte[] key, int seconds, byte[] value) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.setex(key, seconds, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令setex异常");
        }
        return result;
    }


    public String mset(byte[]... keysvalues) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.mset(keysvalues);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令mset异常");
        }
        return result;
    }


    public Long msetnx(byte[]... keysvalues) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.msetnx(keysvalues);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令msetnx异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param integer
     * @return
     * @see redis.clients.jedis.BinaryJedis#decrBy(byte[], long)
     */
    @Override
    public Long decrBy(byte[] key, long integer) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.decrBy(key, integer);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令decrBy异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.BinaryJedis#decr(byte[])
     */
    @Override
    public Long decr(byte[] key) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.decr(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令decr异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param integer
     * @return
     * @see redis.clients.jedis.BinaryJedis#incrBy(byte[], long)
     */
    @Override
    public Long incrBy(byte[] key, long integer) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.incrBy(key, integer);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令incrBy异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.BinaryJedis#incr(byte[])
     */
    @Override
    public Long incr(byte[] key) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.incr(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令incr异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param value
     * @return
     * @see redis.clients.jedis.BinaryJedis#append(byte[], byte[])
     */
    @Override
    public Long append(byte[] key, byte[] value) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.append(key, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令append异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param start
     * @param end
     * @return
     * @see redis.clients.jedis.BinaryJedis#substr(byte[], int, int)
     */
    @Override
    public byte[] substr(byte[] key, int start, int end) {
        RedisConnection RedisConnection = factory.getConnection();
        byte[] result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.substr(key, start, end);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令substr异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param field
     * @param value
     * @return
     * @see redis.clients.jedis.BinaryJedis#hset(byte[], byte[], byte[])
     */
    @Override
    public Long hset(byte[] key, byte[] field, byte[] value) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.hset(key, field, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令hset异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param field
     * @return
     * @see redis.clients.jedis.BinaryJedis#hget(byte[], byte[])
     */
    @Override
    public byte[] hget(byte[] key, byte[] field) {
        RedisConnection RedisConnection = factory.getConnection();
        byte[] result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.hget(key, field);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令hget异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param field
     * @param value
     * @return
     * @see redis.clients.jedis.BinaryJedis#hsetnx(byte[], byte[], byte[])
     */
    @Override
    public Long hsetnx(byte[] key, byte[] field, byte[] value) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.hsetnx(key, field, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令hsetnx异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param hash
     * @return
     * @see redis.clients.jedis.BinaryJedis#hmset(byte[], Map)
     */
    @Override
    public String hmset(byte[] key, Map<byte[], byte[]> hash) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.hmset(key, hash);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令hmset异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param fields
     * @return
     * @see redis.clients.jedis.BinaryJedis#hmget(byte[], byte[][])
     */
    @Override
    public List<byte[]> hmget(byte[] key, byte[]... fields) {
        RedisConnection RedisConnection = factory.getConnection();
        List<byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.hmget(key, fields);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令hmget异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param field
     * @param value
     * @return
     * @see redis.clients.jedis.BinaryJedis#hincrBy(byte[], byte[], long)
     */
    @Override
    public Long hincrBy(byte[] key, byte[] field, long value) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.hincrBy(key, field, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令hincrBy异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param field
     * @return
     * @see redis.clients.jedis.BinaryJedis#hexists(byte[], byte[])
     */
    @Override
    public Boolean hexists(byte[] key, byte[] field) {
        RedisConnection RedisConnection = factory.getConnection();
        Boolean result = false;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.hexists(key, field);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令hexists异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param fields
     * @return
     * @see redis.clients.jedis.BinaryJedis#hdel(byte[], byte[][])
     */
    @Override
    public Long hdel(byte[] key, byte[]... fields) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.hdel(key, fields);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令hdel异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.BinaryJedis#hlen(byte[])
     */
    @Override
    public Long hlen(byte[] key) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.hlen(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令hlen异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.BinaryJedis#hkeys(byte[])
     */
    @Override
    public Set<byte[]> hkeys(byte[] key) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.hkeys(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令hkeys异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.BinaryJedis#hvals(byte[])
     */
    @Override
    public List<byte[]> hvals(byte[] key) {
        RedisConnection RedisConnection = factory.getConnection();
        List<byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.hvals(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令hvals异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.BinaryJedis#hgetAll(byte[])
     */
    @Override
    public Map<byte[], byte[]> hgetAll(byte[] key) {
        RedisConnection RedisConnection = factory.getConnection();
        Map<byte[], byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.hgetAll(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令hgetAll异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param strings
     * @return
     * @see redis.clients.jedis.BinaryJedis#rpush(byte[], byte[][])
     */
    @Override
    public Long rpush(byte[] key, byte[]... strings) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.rpush(key, strings);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令rpush异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param strings
     * @return
     * @see redis.clients.jedis.BinaryJedis#lpush(byte[], byte[][])
     */
    @Override
    public Long lpush(byte[] key, byte[]... strings) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.lpush(key, strings);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令lpush异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.BinaryJedis#llen(byte[])
     */
    @Override
    public Long llen(byte[] key) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.llen(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令llen异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param start
     * @param end
     * @return
     * @see redis.clients.jedis.BinaryJedis(byte[], int, int)
     */
    public List<byte[]> lrange(byte[] key, int start, int end) {
        RedisConnection RedisConnection = factory.getConnection();
        List<byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.lrange(key, start, end);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令lrange异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param start
     * @param end
     * @return
     * @see redis.clients.jedis.BinaryJedis(byte[], int, int)
     */

    public String ltrim(byte[] key, int start, int end) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.ltrim(key, start, end);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令ltrim异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param index
     * @return
     * @see redis.clients.jedis.BinaryJedis(byte[], int)
     */

    public byte[] lindex(byte[] key, int index) {
        RedisConnection RedisConnection = factory.getConnection();
        byte[] result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.lindex(key, index);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令lindex异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param index
     * @param value
     * @return
     * @see redis.clients.jedis.BinaryJedis(byte[], int, byte[])
     */
    public String lset(byte[] key, int index, byte[] value) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.lset(key, index, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令lset异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param count
     * @param value
     * @return
     * @see redis.clients.jedis.BinaryJedis(byte[], int, byte[])
     */
    public Long lrem(byte[] key, int count, byte[] value) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.lrem(key, count, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令lrem异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.BinaryJedis#lpop(byte[])
     */
    @Override
    public byte[] lpop(byte[] key) {
        RedisConnection RedisConnection = factory.getConnection();
        byte[] result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.lpop(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令lpop异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.BinaryJedis#rpop(byte[])
     */
    @Override
    public byte[] rpop(byte[] key) {
        RedisConnection RedisConnection = factory.getConnection();
        byte[] result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.rpop(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令rpop异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param srckey
     * @param dstkey
     * @return
     * @see redis.clients.jedis.BinaryJedis#rpoplpush(byte[], byte[])
     */

    public byte[] rpoplpush(byte[] srckey, byte[] dstkey) {
        RedisConnection RedisConnection = factory.getConnection();
        byte[] result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.rpoplpush(srckey, dstkey);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令rpoplpush异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param members
     * @return
     * @see redis.clients.jedis.BinaryJedis#sadd(byte[], byte[][])
     */
    @Override
    public Long sadd(byte[] key, byte[]... members) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.sadd(key, members);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令sadd异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.BinaryJedis#smembers(byte[])
     */
    @Override
    public Set<byte[]> smembers(byte[] key) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.smembers(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令smembers异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param member
     * @return
     * @see redis.clients.jedis.BinaryJedis#srem(byte[], byte[][])
     */
    @Override
    public Long srem(byte[] key, byte[]... member) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.srem(key, member);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令srem异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.BinaryJedis#spop(byte[])
     */
    @Override
    public byte[] spop(byte[] key) {
        RedisConnection RedisConnection = factory.getConnection();
        byte[] result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.spop(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令spop异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param srckey
     * @param dstkey
     * @param member
     * @return
     * @see redis.clients.jedis.BinaryJedis#smove(byte[], byte[], byte[])
     */
    @Override
    public Long smove(byte[] srckey, byte[] dstkey, byte[] member) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.smove(srckey, dstkey, member);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令smove异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.BinaryJedis#scard(byte[])
     */
    @Override
    public Long scard(byte[] key) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.scard(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令scard异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param member
     * @return
     * @see redis.clients.jedis.BinaryJedis#sismember(byte[], byte[])
     */
    @Override
    public Boolean sismember(byte[] key, byte[] member) {
        RedisConnection RedisConnection = factory.getConnection();
        Boolean result = false;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.sismember(key, member);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令sismember异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param keys
     * @return
     * @see redis.clients.jedis.BinaryJedis#sinter(byte[][])
     */
    @Override
    public Set<byte[]> sinter(byte[]... keys) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.sinter(keys);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令sinter异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param dstkey
     * @param keys
     * @return
     * @see redis.clients.jedis.BinaryJedis#sinterstore(byte[], byte[][])
     */
    @Override
    public Long sinterstore(byte[] dstkey, byte[]... keys) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.sinterstore(dstkey, keys);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令sinterstore异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param keys
     * @return
     * @see redis.clients.jedis.BinaryJedis#sunion(byte[][])
     */
    @Override
    public Set<byte[]> sunion(byte[]... keys) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.sunion(keys);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令sunion异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param dstkey
     * @param keys
     * @return
     * @see redis.clients.jedis.BinaryJedis#sunionstore(byte[], byte[][])
     */
    @Override
    public Long sunionstore(byte[] dstkey, byte[]... keys) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.sunionstore(dstkey, keys);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令sunionstore异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param keys
     * @return
     * @see redis.clients.jedis.BinaryJedis#sdiff(byte[][])
     */
    @Override
    public Set<byte[]> sdiff(byte[]... keys) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.sdiff(keys);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令sdiff异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param dstkey
     * @param keys
     * @return
     * @see redis.clients.jedis.BinaryJedis#sdiffstore(byte[], byte[][])
     */
    @Override
    public Long sdiffstore(byte[] dstkey, byte[]... keys) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.sdiffstore(dstkey, keys);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令sdiffstore异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.BinaryJedis#srandmember(byte[])
     */
    @Override
    public byte[] srandmember(byte[] key) {
        RedisConnection RedisConnection = factory.getConnection();
        byte[] result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.srandmember(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令srandmember异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param score
     * @param member
     * @return
     * @see redis.clients.jedis.BinaryJedis#zadd(byte[], double, byte[])
     */
    @Override
    public Long zadd(byte[] key, double score, byte[] member) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zadd(key, score, member);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zadd异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param start
     * @param end
     * @return
     * @see redis.clients.jedis.BinaryJedis(byte[], int, int)
     */
    public Set<byte[]> zrange(byte[] key, int start, int end) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrange(key, start, end);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrange异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param members
     * @return
     * @see redis.clients.jedis.BinaryJedis#zrem(byte[], byte[][])
     */
    @Override
    public Long zrem(byte[] key, byte[]... members) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrem(key, members);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrem异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param score
     * @param member
     * @return
     * @see redis.clients.jedis.BinaryJedis#zincrby(byte[], double, byte[])
     */
    @Override
    public Double zincrby(byte[] key, double score, byte[] member) {
        RedisConnection RedisConnection = factory.getConnection();
        Double result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zincrby(key, score, member);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zincrby异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param member
     * @return
     * @see redis.clients.jedis.BinaryJedis#zrank(byte[], byte[])
     */
    @Override
    public Long zrank(byte[] key, byte[] member) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrank(key, member);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrank异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param member
     * @return
     * @see redis.clients.jedis.BinaryJedis#zrevrank(byte[], byte[])
     */
    @Override
    public Long zrevrank(byte[] key, byte[] member) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrevrank(key, member);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrevrank异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param start
     * @param end
     * @return
     * @see redis.clients.jedis.BinaryJedis(byte[], int, int)
     */
    public Set<byte[]> zrevrange(byte[] key, int start, int end) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrevrange(key, start, end);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrevrange异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param start
     * @param end
     * @return
     * @see redis.clients.jedis.BinaryJedis(byte[], int, int)
     */
    public Set<Tuple> zrangeWithScores(byte[] key, int start, int end) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<Tuple> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrangeWithScores(key, start, end);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrangeWithScores异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param start
     * @param end
     * @return
     * @see redis.clients.jedis.BinaryJedis(byte[], int, int)
     */
    public Set<Tuple> zrevrangeWithScores(byte[] key, int start, int end) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<Tuple> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrevrangeWithScores(key, start, end);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrevrangeWithScores异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.BinaryJedis#zcard(byte[])
     */
    @Override
    public Long zcard(byte[] key) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zcard(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zcard异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param member
     * @return
     * @see redis.clients.jedis.BinaryJedis#zscore(byte[], byte[])
     */
    @Override
    public Double zscore(byte[] key, byte[] member) {
        RedisConnection RedisConnection = factory.getConnection();
        Double result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zscore(key, member);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zscore异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @return
     * @see redis.clients.jedis.BinaryJedis#multi()
     */
    @Override
    public Transaction multi() {
        RedisConnection RedisConnection = factory.getConnection();
        Transaction result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.multi();
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令multi异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param keys
     * @return
     * @see redis.clients.jedis.BinaryJedis#watch(byte[][])
     */
    @Override
    public String watch(byte[]... keys) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.watch(keys);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令watch异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @return
     * @see redis.clients.jedis.BinaryJedis#unwatch()
     */
    @Override
    public String unwatch() {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.unwatch();
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令unwatch异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.BinaryJedis#sort(byte[])
     */
    @Override
    public List<byte[]> sort(byte[] key) {
        RedisConnection RedisConnection = factory.getConnection();
        List<byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.sort(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令sort异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param sortingParameters
     * @return
     * @see redis.clients.jedis.BinaryJedis#sort(byte[], redis.clients.jedis.SortingParams)
     */
    @Override
    public List<byte[]> sort(byte[] key, SortingParams sortingParameters) {
        RedisConnection RedisConnection = factory.getConnection();
        List<byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.sort(key, sortingParameters);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令sort异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param timeout
     * @param keys
     * @return
     * @see redis.clients.jedis.BinaryJedis#blpop(int, byte[][])
     */
    @Override
    public List<byte[]> blpop(int timeout, byte[]... keys) {
        RedisConnection RedisConnection = factory.getConnection();
        List<byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.blpop(timeout, keys);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令blpop异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param sortingParameters
     * @param dstkey
     * @return
     * @see redis.clients.jedis.BinaryJedis#sort(byte[], redis.clients.jedis.SortingParams, byte[])
     */
    @Override
    public Long sort(byte[] key, SortingParams sortingParameters, byte[] dstkey) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.sort(key, sortingParameters, dstkey);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令sort异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param dstkey
     * @return
     * @see redis.clients.jedis.BinaryJedis#sort(byte[], byte[])
     */
    @Override
    public Long sort(byte[] key, byte[] dstkey) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.sort(key, dstkey);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令sort异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param timeout
     * @param keys
     * @return
     * @see redis.clients.jedis.BinaryJedis#brpop(int, byte[][])
     */
    @Override
    public List<byte[]> brpop(int timeout, byte[]... keys) {
        RedisConnection RedisConnection = factory.getConnection();
        List<byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.brpop(timeout, keys);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令brpop异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @return
     * @see redis.clients.jedis.BinaryJedis#pipelined()
     */
    @Override
    public Pipeline pipelined() {
        RedisConnection RedisConnection = factory.getConnection();
        Pipeline result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.pipelined();
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令pipelined异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param min
     * @param max
     * @return
     * @see redis.clients.jedis.BinaryJedis#zcount(byte[], double, double)
     */
    @Override
    public Long zcount(byte[] key, double min, double max) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zcount(key, min, max);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zcount异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param min
     * @param max
     * @return
     * @see redis.clients.jedis.BinaryJedis#zcount(byte[], byte[], byte[])
     */
    @Override
    public Long zcount(byte[] key, byte[] min, byte[] max) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zcount(key, min, max);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zcount异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param min
     * @param max
     * @return
     * @see redis.clients.jedis.BinaryJedis#zrangeByScore(byte[], double, double)
     */
    @Override
    public Set<byte[]> zrangeByScore(byte[] key, double min, double max) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrangeByScore(key, min, max);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrangeByScore异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param min
     * @param max
     * @return
     * @see redis.clients.jedis.BinaryJedis#zrangeByScore(byte[], byte[], byte[])
     */
    @Override
    public Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrangeByScore(key, min, max);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrangeByScore异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param min
     * @param max
     * @param offset
     * @param count
     * @return
     * @see redis.clients.jedis.BinaryJedis#zrangeByScore(byte[], double, double, int, int)
     */
    @Override
    public Set<byte[]> zrangeByScore(byte[] key, double min, double max,
                                     int offset, int count) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrangeByScore(key, min, max, offset, count);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrangeByScore异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param min
     * @param max
     * @param offset
     * @param count
     * @return
     * @see redis.clients.jedis.BinaryJedis#zrangeByScore(byte[], byte[], byte[], int, int)
     */
    @Override
    public Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max,
                                     int offset, int count) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrangeByScore(key, min, max, offset, count);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrangeByScore异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param min
     * @param max
     * @return
     * @see redis.clients.jedis.BinaryJedis#zrangeByScoreWithScores(byte[], double, double)
     */
    @Override
    public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<Tuple> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrangeByScoreWithScores(key, min, max);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrangeByScoreWithScores异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param min
     * @param max
     * @return
     * @see redis.clients.jedis.BinaryJedis#zrangeByScoreWithScores(byte[], byte[], byte[])
     */
    @Override
    public Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<Tuple> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrangeByScoreWithScores(key, min, max);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrangeByScoreWithScores异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param min
     * @param max
     * @param offset
     * @param count
     * @return
     * @see redis.clients.jedis.BinaryJedis#zrangeByScoreWithScores(byte[], double, double, int, int)
     */
    @Override
    public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min,
                                              double max, int offset, int count) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<Tuple> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy
                    .zrangeByScoreWithScores(key, min, max, offset, count);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrangeByScoreWithScores异常");
        }
        return result;
    }


    public Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min,
                                              byte[] max, int offset, int count) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<Tuple> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy
                    .zrangeByScoreWithScores(key, min, max, offset, count);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrangeByScoreWithScores异常");
        }
        return result;
    }


    public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrevrangeByScore(key, max, min);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrevrangeByScore异常");
        }
        return result;
    }


    public Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrevrangeByScore(key, max, min);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrevrangeByScore异常");
        }
        return result;
    }


    public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min,
                                        int offset, int count) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrevrangeByScore(key, max, min, offset, count);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrevrangeByScore异常");
        }
        return result;
    }


    public Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min,
                                        int offset, int count) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrevrangeByScore(key, max, min, offset, count);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zrevrangeByScore异常");
        }
        return result;
    }


    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max,
                                                 double min) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<Tuple> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrevrangeByScoreWithScores(key, max, min);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil
                    .log(Level.ERROR, "执行Redis命令zrevrangeByScoreWithScores异常");
        }
        return result;
    }


    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max,
                                                 double min, int offset, int count) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<Tuple> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrevrangeByScoreWithScores(key, max, min, offset,
                    count);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil
                    .log(Level.ERROR, "执行Redis命令zrevrangeByScoreWithScores异常");
        }
        return result;
    }


    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max,
                                                 byte[] min) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<Tuple> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrevrangeByScoreWithScores(key, max, min);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil
                    .log(Level.ERROR, "执行Redis命令zrevrangeByScoreWithScores异常");
        }
        return result;
    }


    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max,
                                                 byte[] min, int offset, int count) {
        RedisConnection RedisConnection = factory.getConnection();
        Set<Tuple> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zrevrangeByScoreWithScores(key, max, min, offset,
                    count);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil
                    .log(Level.ERROR, "执行Redis命令zrevrangeByScoreWithScores异常");
        }
        return result;
    }


    public Long zremrangeByRank(byte[] key, int start, int end) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zremrangeByRank(key, start, end);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zremrangeByRank异常");
        }
        return result;
    }


    public Long zremrangeByScore(byte[] key, double start, double end) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zremrangeByScore(key, start, end);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zremrangeByScore异常");
        }
        return result;
    }


    public Long zremrangeByScore(byte[] key, byte[] start, byte[] end) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zremrangeByScore(key, start, end);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zremrangeByScore异常");
        }
        return result;
    }


    public Long zunionstore(byte[] dstkey, byte[]... sets) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zunionstore(dstkey, sets);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zunionstore异常");
        }
        return result;
    }


    public Long zunionstore(byte[] dstkey, ZParams params, byte[]... sets) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zunionstore(dstkey, params, sets);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zunionstore异常");
        }
        return result;
    }


    public Long zinterstore(byte[] dstkey, byte[]... sets) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zinterstore(dstkey, sets);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zinterstore异常");
        }
        return result;
    }


    public Long zinterstore(byte[] dstkey, ZParams params, byte[]... sets) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.zinterstore(dstkey, params, sets);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令zinterstore异常");
        }
        return result;
    }


    public String save() {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.save();
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令save异常");
        }
        return result;
    }


    public String bgsave() {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.bgsave();
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令bgsave异常");
        }
        return result;
    }


    public String bgrewriteaof() {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.bgrewriteaof();
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令bgrewriteaof异常");
        }
        return result;
    }


    public Long lastsave() {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.lastsave();
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令lastsave异常");
        }
        return result;
    }


    public String shutdown() {
        RedisConnection RedisConnection = factory.getConnection();
        String result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.shutdown();
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令shutdown异常");
        }
        return result;
    }


    public String info() {
        RedisConnection RedisConnection = factory.getConnection();
        String result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.info();
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令info异常");
        }
        return result;
    }


    public void monitor(JedisMonitor jedisMonitor) {
        RedisConnection RedisConnection = factory.getConnection();
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            proxy.monitor(jedisMonitor);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令monitor异常");
        }
    }


    public String slaveof(String host, int port) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.slaveof(host, port);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令slaveof异常");
        }
        return result;

    }


    public String slaveofNoOne() {
        RedisConnection RedisConnection = factory.getConnection();
        String result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.slaveofNoOne();
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令slaveofNoOne异常");
        }
        return result;
    }


    public List<byte[]> configGet(byte[] pattern) {
        RedisConnection RedisConnection = factory.getConnection();
        List<byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.configGet(pattern);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令configGet异常");
        }
        return result;
    }


    public String configResetStat() {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.configResetStat();
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令configResetStat异常");
        }
        return result;
    }


    public byte[] configSet(byte[] parameter, byte[] value) {
        RedisConnection RedisConnection = factory.getConnection();
        byte[] result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.configSet(parameter, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令configSet异常");
        }
        return result;
    }


    public boolean isConnected() {
        RedisConnection RedisConnection = factory.getConnection();
        boolean result = false;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.isConnected();
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令isConnected异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.BinaryJedis#strlen(byte[])
     */
    public Long strlen(byte[] key) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.strlen(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令strlen异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @see redis.clients.jedis.BinaryJedis#sync()
     */
    @Override
    public void sync() {
        RedisConnection RedisConnection = factory.getConnection();
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            proxy.sync();
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令sync异常");
        }
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param string
     * @return
     * @see redis.clients.jedis.BinaryJedis(byte[], byte[])
     */
    public Long lpushx(byte[] key, byte[] string) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.lpushx(key, string);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令lpushx异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.BinaryJedis#persist(byte[])
     */
    @Override
    public Long persist(byte[] key) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.persist(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令persist异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param string
     * @return
     * @see redis.clients.jedis.BinaryJedis(byte[], byte[])
     */
    public Long rpushx(byte[] key, byte[] string) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.rpushx(key, string);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令rpushx异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param string
     * @return
     * @see redis.clients.jedis.BinaryJedis#echo(byte[])
     */
    @Override
    public byte[] echo(byte[] string) {
        RedisConnection RedisConnection = factory.getConnection();
        byte[] result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.echo(string);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令echo异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param where
     * @param pivot
     * @param value
     * @return
     * @see redis.clients.jedis.BinaryJedis#linsert(byte[], redis.clients.jedis.BinaryClient.LIST_POSITION, byte[], byte[])
     */
    @Override
    public Long linsert(byte[] key, LIST_POSITION where, byte[] pivot,
                        byte[] value) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.linsert(key, where, pivot, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令linsert异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param params
     * @return
     * @see redis.clients.jedis.BinaryJedis#debug(redis.clients.jedis.DebugParams)
     */
    @Override
    public String debug(DebugParams params) {
        RedisConnection RedisConnection = factory.getConnection();
        String result = "";
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.debug(params);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令debug异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @return
     * @see redis.clients.jedis.BinaryJedis#getClient()
     */
    @Override
    public Client getClient() {
        RedisConnection RedisConnection = factory.getConnection();
        Client result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.getClient();
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令getClient异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param source
     * @param destination
     * @param timeout
     * @return
     * @see redis.clients.jedis.BinaryJedis#brpoplpush(byte[], byte[], int)
     */
    @Override
    public byte[] brpoplpush(byte[] source, byte[] destination, int timeout) {
        RedisConnection RedisConnection = factory.getConnection();
        byte[] result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.brpoplpush(source, destination, timeout);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令brpoplpush异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param offset
     * @param value
     * @return
     * @see redis.clients.jedis.BinaryJedis#setbit(byte[], long, byte[])
     */
    @Override
    public Boolean setbit(byte[] key, long offset, byte[] value) {
        RedisConnection RedisConnection = factory.getConnection();
        Boolean result = false;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.setbit(key, offset, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令setbit异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param offset
     * @return
     * @see redis.clients.jedis.BinaryJedis#getbit(byte[], long)
     */
    @Override
    public Boolean getbit(byte[] key, long offset) {
        RedisConnection RedisConnection = factory.getConnection();
        Boolean result = false;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.getbit(key, offset);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令getbit异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @param offset
     * @param value
     * @return
     * @see redis.clients.jedis.BinaryJedis#setrange(byte[], long, byte[])
     */
    @Override
    public Long setrange(byte[] key, long offset, byte[] value) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.setrange(key, offset, value);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令setrange异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param channel
     * @param message
     * @return
     * @see redis.clients.jedis.BinaryJedis#publish(byte[], byte[])
     */
    public Long publish(byte[] channel, byte[] message) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.publish(channel, message);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令publish异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param jedisPubSub
     * @param channels
     * @see redis.clients.jedis.BinaryJedis#subscribe(redis.clients.jedis.BinaryJedisPubSub, byte[][])
     */
    @Override
    public void subscribe(BinaryJedisPubSub jedisPubSub, byte[]... channels) {
        RedisConnection RedisConnection = factory.getConnection();
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            proxy.subscribe(jedisPubSub, channels);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令subscribe异常");
        }
    }


    /**
     * (non-Javadoc)
     *
     * @param jedisPubSub
     * @param patterns
     * @see redis.clients.jedis.BinaryJedis#psubscribe(redis.clients.jedis.BinaryJedisPubSub, byte[][])
     */
    @Override
    public void psubscribe(BinaryJedisPubSub jedisPubSub, byte[]... patterns) {
        RedisConnection RedisConnection = factory.getConnection();
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            proxy.psubscribe(jedisPubSub, patterns);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令psubscribe异常");
        }
    }


    /**
     * (non-Javadoc)
     *
     * @return
     * @see redis.clients.jedis.BinaryJedis#getDB()
     */
    @Override
    public Long getDB() {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.getDB();
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令getDB异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param script
     * @param keys
     * @param args
     * @return
     * @see redis.clients.jedis.BinaryJedis#eval(byte[], List, List)
     */
    @Override
    public Object eval(byte[] script, List<byte[]> keys, List<byte[]> args) {
        RedisConnection RedisConnection = factory.getConnection();
        Object result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.eval(script, keys, args);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令eval异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param script
     * @param keyCount
     * @param params
     * @return
     * @see redis.clients.jedis.BinaryJedis#eval(byte[], byte[], byte[][])
     */
    public Object eval(byte[] script, byte[] keyCount, byte[] params) {
        RedisConnection RedisConnection = factory.getConnection();
        Object result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.eval(script, keyCount, params);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令eval异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param sha1
     * @return
     * @see redis.clients.jedis.BinaryJedis#scriptExists(byte[][])
     */
    @Override
    public List<Long> scriptExists(byte[]... sha1) {
        RedisConnection RedisConnection = factory.getConnection();
        List<Long> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.scriptExists(sha1);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令scriptExists异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param script
     * @return
     * @see redis.clients.jedis.BinaryJedis#scriptLoad(byte[])
     */
    @Override
    public byte[] scriptLoad(byte[] script) {
        RedisConnection RedisConnection = factory.getConnection();
        byte[] result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.scriptLoad(script);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令scriptLoad异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @return
     * @see redis.clients.jedis.BinaryJedis#slowlogGetBinary()
     */
    public List<byte[]> slowlogGetBinary() {
        RedisConnection RedisConnection = factory.getConnection();
        List<byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.slowlogGetBinary();
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令slowlogGetBinary异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param entries
     * @return
     * @see redis.clients.jedis.BinaryJedis#slowlogGetBinary(long)
     */
    @Override
    public List<byte[]> slowlogGetBinary(long entries) {
        RedisConnection RedisConnection = factory.getConnection();
        List<byte[]> result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.slowlogGetBinary(entries);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令slowlogGetBinary异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.BinaryJedis#objectRefcount(byte[])
     */
    @Override
    public Long objectRefcount(byte[] key) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.objectRefcount(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令objectRefcount异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.BinaryJedis#objectEncoding(byte[])
     */
    @Override
    public byte[] objectEncoding(byte[] key) {
        RedisConnection RedisConnection = factory.getConnection();
        byte[] result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.objectEncoding(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令objectEncoding异常");
        }
        return result;
    }


    /**
     * (non-Javadoc)
     *
     * @param key
     * @return
     * @see redis.clients.jedis.BinaryJedis#objectIdletime(byte[])
     */
    @Override
    public Long objectIdletime(byte[] key) {
        RedisConnection RedisConnection = factory.getConnection();
        Long result = null;
        try {
            Jedis proxy = (Jedis) RedisConnection.getNativeConnection();
            result = proxy.objectIdletime(key);
            RedisConnection.close();
        } catch (Exception e) {
            RedisConnection.close();
            LoggerUtil.log(Level.ERROR, "执行Redis命令objectIdletime异常");
        }
        return result;
    }

}
