package com.demo.login;

import com.demo.common.model.User;
import com.demo.user.UserService;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;

@Clear
public class LoginController extends Controller {

    @Inject
    private UserService userService;
    public void index(){
        render("login.html");
    }

    public void verify(){
        User user = getModel(User.class);

    }

}
