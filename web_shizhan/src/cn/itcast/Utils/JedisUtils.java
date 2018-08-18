package cn.itcast.Utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {
    
    private static JedisPool pool;

    private static ThreadLocal<Jedis> local = new ThreadLocal<>();
    
    static{
      //获得连接池配置项对象
        JedisPoolConfig config = new JedisPoolConfig();
        //进行连接的一些配置信息的设置(这些配置不是必须的！)
        config.setMaxTotal(100);
        //通过这个连接池配置项对象获得连接池对象
        pool = new JedisPool(config,"192.168.157.129",6379);
    }

    /**
     * 获得Jedis
     */
    public static Jedis getJedis(){
        Jedis jedis = local.get();
        //判断
        if(jedis==null){
            local.set(pool.getResource());
            return local.get();
        }else{
            return jedis;
        }
    }
}
