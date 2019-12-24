package com.demo.common;

import com.demo.common.model.User;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class GlobalActionInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation invocation) {
        User user = invocation.getController().getSessionAttr("user");
        if(user != null){
            invocation.invoke();
        } else{
            invocation.getController().redirect("/login");
        }
    }
}
