package com.example.enjoytrip.common;

import com.example.enjoytrip.dto.UserDto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        System.out.println("LoginInterceptor >>>> " + request.getRequestURI());
        System.out.println(handler);

        String async = request.getHeader("async");

        if (isSwaggerRequest(request)) {
            return true;
        }

        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("userDto");
                
        if (userDto == null) {
            if ("true".equals(async)) {
                Gson gson = new Gson();
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("result", "login");

                String jsonStr = gson.toJson(jsonObject);
                response.getWriter().write(jsonStr);
            } else {
                response.sendRedirect("/login");
            }
            return false;
        }

        return true;
    }

    private boolean isSwaggerRequest(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return uri.contains("swagger") || uri.contains("api-docs") || uri.contains("webjars");
    }
}