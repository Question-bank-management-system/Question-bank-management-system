package com.demo.user;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class UserInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation inv) {
        System.out.println("Before invoking " + inv.getActionKey());
        inv.invoke();//调用以后，继续往下执行
        System.out.println("After invoking " + inv.getActionKey());
    }
}
