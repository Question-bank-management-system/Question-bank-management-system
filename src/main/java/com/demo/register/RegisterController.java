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
        if(!user.getType().equals("admin")){
            Date date = new Date();
            if(user.getType().equals("teacher")){
                user.setUserlv(2);
            }else if(user.getType().equals("student")){
                user.setUserlv(3);
            }
            user.setAddress("缺省值");
            user.setBirthdate(date);
            user.setEmail("2014123456@ctgu.edu.cn");
            user.setPhonenumber("0717-6392121");
            user.setRealname("default");
            user.setSex("女");
            user.save();
            int userid = (int)userService.queryByPara(user);
            user.setId(userid);
            setSessionAttr("user",user);
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
}
