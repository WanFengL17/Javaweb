//Redis实现分布式会话
import redis.clients.jedis.Jedis;

public void setSessionValue(String sessionId, String key, String value) {
        Jedis jedis = new Jedis("localhost");
        jedis.hset(sessionId, key, value);
        jedis.close();
        }

public String getSessionValue(String sessionId, String key) {
        Jedis jedis = new Jedis("localhost");
        String value = jedis.hget(sessionId, key);
        jedis.close();
        return value;
        }
