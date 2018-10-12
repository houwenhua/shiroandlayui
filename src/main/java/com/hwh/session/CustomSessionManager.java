package com.hwh.session;


import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

import javax.servlet.ServletRequest;
import java.io.Serializable;

/**
 * @author hwh
 * @create 2018/9/27 10:40
 */
public class CustomSessionManager extends DefaultWebSessionManager {

    /**
     * 实现减少读取Redis，将sessionID放入request域中
     * @param sessionKey
     * @return
     * @throws UnknownSessionException
     */
    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId = getSessionId(sessionKey);
        ServletRequest request = null;
        //判断是否是web容器中的session
        if(sessionKey instanceof WebSessionKey) {
            request = ((WebSessionKey) sessionKey).getServletRequest();
        }
        //判断sessionID是否存在，存在就直接从request域中取出来
        if(request != null && sessionId != null) {
            Session session = (Session) request.getAttribute(sessionId.toString());
            if(session != null) {
                return session;
            }
        }
        //如果sessionID不在request域中，就获得并放入域中(通过父类中的获得session方法，从Redis中获取)
        Session session = super.retrieveSession(sessionKey);
        if(request != null && sessionId != null) {
            request.setAttribute(sessionId.toString(),session);
        }
        return session;
    }
}
