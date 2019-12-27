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
            User user1 = user.dao().findById((int)userid);
            setSessionAttr("user", user1);
            if(user1.getUserlv() == 1){
                redirect("/user");
            }else if(user1.getUserlv() == 2){
                redirect("/teacher");
            }
            else {
                redirect("/");
            }
        }
    }

    public void logout(){
        getSession().invalidate();
        redirect("/login");
    }
}
