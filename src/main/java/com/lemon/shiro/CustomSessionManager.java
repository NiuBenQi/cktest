package com.lemon.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * @program: cktest
 * @description 会话管理
 * @author: NiuBenQi
 * @create: 2020-02-21 21:20
 **/
public class CustomSessionManager extends DefaultWebSessionManager {

    private static final String AUTHORIZATION ="Authorization";

    private static final String REFERENCED_SESSION_ID_SOURCE = "cookie";

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {

        String sessionId = WebUtils.toHttp(request).getHeader(AUTHORIZATION);

        if (StringUtils.isNotEmpty(sessionId)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return sessionId;
        }
        return super.getSessionId(request, response);
    }

}
