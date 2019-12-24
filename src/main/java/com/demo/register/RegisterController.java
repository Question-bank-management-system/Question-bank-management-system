package com.demo.register;

import com.demo.common.model.Student;
import com.demo.common.model.Teacher;
import com.demo.common.model.User;
import com.demo.user.UserService;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;

@Clear
public class RegisterController extends Controller {
    @Inject
    private UserService userService;

    public void index(){
        render("register.html");
    }

    public void register(){
        User user = getModel(User.class);
        if(!user.getType().equals("admin")){
            userService.add(user);
            int userid = (int)userService.queryByPara(user);
            int userlv = userService.queryByUserlv((int)userid);
            user.setId(userid);
            user.setUserlv(userlv);
            setSessionAttr("user",user);
            if(userlv == 2){
                Teacher teacher = new Teacher();
                teacher.setUserId(userid);
                teacher.save();
            }else if(userlv == 3){
                Student student = new Student();
                student.setUserId(userid);
                student.save();
            }
        }
        redirect("/");
    }
}
