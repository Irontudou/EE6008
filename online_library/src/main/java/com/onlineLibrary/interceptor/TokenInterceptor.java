package com.onlineLibrary.interceptor;

import com.onlineLibrary.context.BaseContext;
import com.onlineLibrary.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 令牌校验的拦截器
 */
@Component
public class TokenInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
        //获取请求头 token
        String token = request.getHeader("token");
        System.out.println("token:"+token);
        //判断token是否存在 不存在说明用户未登录 响应401(或者响应超时，请重新登录等)
        if (token == null || token.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //如果纯在，校验token，校验失败，响应401
        try {
            Jws<Claims> claims = JwtUtils.parseJwt(token);  // 获取 claims

            if (claims == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }

            // 从 claims 中提取用户 id
            Integer userId = (Integer) claims.getBody().get("id");
            System.out.println("userId:"+userId);
            if (userId == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }

            // 将 userId 存入 BaseContext（ThreadLocal）
            BaseContext.setCurrentId(userId);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 校验通过，允许请求继续处理
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContext.removeCurrentId();
    }
}
