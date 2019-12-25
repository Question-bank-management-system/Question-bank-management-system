package com.demo.register;

import com.demo.common.model.Student;
import com.demo.common.model.Teacher;
import com.demo.common.model.User;
import com.demo.user.UserService;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;

import java.util.Date;

@Clear
public class RegisterController extends Controller {
    @Inject
    private UserService userService;

    public void index(){
        render("register.html");
    }

    public void register(){
        User user = getModel(User.class);
        Object id = userService.queryByPara(user);
        if(id == null){
            if(!user.getType().equals("admin")){
                if(user.getType().equals("teacher")){
                    user.setUserlv(2);
                }else if(user.getType().equals("student")){
                    user.setUserlv(3);
                }
                user.save();
                int userid = (int)userService.queryByPara(user);
                user.setId(userid);
                User user1 = userService.findById(userid);
                //写到session中
                setSessionAttr("user",user1);

                if(user.getUserlv() == 2){
                    Teacher teacher = new Teacher();
                    teacher.setUserId(userid);
                    teacher.save();
                }else if(user.getUserlv() == 3){
                    Student student = new Student();
                    student.setUserId(userid);
                    student.save();
                }
            }
            redirect("/");
        }
        else{
            redirect("/register");
        }
    }
}
