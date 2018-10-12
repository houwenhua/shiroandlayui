package com.hwh.session;

import com.hwh.util.JedisUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 使用Redis实现session管理共享
 * @author hwh
 * @create 2018/9/26 11:47
 */
public class RedisSessionDao extends AbstractSessionDAO {

    private static Log log = LogFactory.getLog(RedisSessionDao.class);

    @Autowired
    private JedisUtil jedisUtil;
    //redis前缀
    private final String SHIRO_SESSION_PREFIX = "hwh-session:";

    private byte[] getKey(String key) {
        return (SHIRO_SESSION_PREFIX + key).getBytes();
    }

    private void saveSession (Session session) {
        if(session != null && session.getId() != null) {
            //获得key和value
            byte[] key = getKey(session.getId().toString());
            byte[] value = SerializationUtils.serialize(session);
            //放入Redis的set中
            jedisUtil.set(key,value);
            //设置过期时间
            jedisUtil.expire(key,600);
        }
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        //分派session，解决报sessionID为null问题
        assignSessionId(session,sessionId);
        saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        log.info("读取session");
        System.out.println("读取session");
        if(sessionId == null) {
            return null;
        }
        byte[] key = getKey(sessionId.toString());
        byte[] value = jedisUtil.get(key);
        return (Session) SerializationUtils.deserialize(value);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        saveSession(session);
    }

    @Override
    public void delete(Session session) {
        if(session == null && session.getId() ==null) {
            return;
        }
        byte[] key = getKey(session.getId().toString());
        jedisUtil.del(key);
    }

    @Override
    public Collection<Session> getActiveSessions() {
        //获得所有的key
        Set<byte[]> keys = jedisUtil.keys(SHIRO_SESSION_PREFIX);
        Set<Session> sessions = new HashSet<>();
        if(CollectionUtils.isEmpty(sessions)) {
            return sessions;
        }
        for(byte[] key : keys) {
            Session session = (Session) SerializationUtils.deserialize(jedisUtil.get(key));
            sessions.add(session);
        }
        return sessions;
    }
}
