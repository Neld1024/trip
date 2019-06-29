package cn.wolfcode.trip.app.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckLoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object currentUser = request.getSession().getAttribute("USER_IN_SESSION");
        if(currentUser == null){
            response.sendRedirect("/login.html");
            return false;//拦截到,不再往前执行
        }
        return true;//放行
    }
}
