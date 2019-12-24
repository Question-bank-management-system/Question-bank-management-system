package com.demo.login;

import com.demo.common.model.AdminUser;
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
        Object userid = userService.queryByPara(user);
        if(userid == null){
            redirect("/login");
        }
        else {
            user.setId((int) userid);
            user.setUserlv(userService.queryByUserlv((int) userid));
            setSessionAttr("user", user);
            redirect("/");
        }
    }
}
