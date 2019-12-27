package com.demo.teacher;

import com.demo.common.model.Teacher;
import com.demo.common.model.User;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.json.Json;

import java.util.List;

public class TeacherController extends Controller{

    @Inject
    TeacherService teacherService;

    //    教师系统前端首页
    public void teaIndex(){
        render("teaIndex.html");}

    public void examManagement(){render("examManagement.html");}
    public void testpaperManagement(){render("testpaperManagement.html");}
    public void managementClass(){render("managementClass.html");}
    public void personalCenter(){render("personalfile/personalCenter.html");}
    public void personalUpdate(){render("personalfile/personalUpdate.html");}



    public void index() {
        //setAttr("teacherPage",teacherService.paginate(getParaToInt(0, 1), 10));
        render("teaIndex.html");
    }

    public void index1(){
        User user = getSessionAttr("user");
        setAttr("user",user);
        render("teacher.html");
    }

    public void list(){
        int page = getParaToInt("page");
        int limit = getParaToInt("limit");

        List<Teacher> lists = teacherService.paginate(page,limit).getList();
        String jsonList = Json.getJson().toJson(lists);

        int recordCount = teacherService.allRecordCount();

        String jsons = "{\"code\":\"0\",\"msg\":\"\",\"count\":\""+
                recordCount+"\",\"data\":"+jsonList+"}";
        renderJson(jsons);
    }

    public void delete(){
        int id = Integer.parseInt(getPara("id"));
        teacherService.delete(id);
        render("teaIndex.html");
        //return 1;
    }

    public void update(){
        int id = Integer.parseInt(getPara("id"));
        String value = getPara("value");
        String field = getPara("field");
        teacherService.update(id, field, value);
    }

    public void add(){
        Teacher teacher = getModel(Teacher.class);
        //System.out.println(user.getUsername());
        teacherService.add(teacher);
        redirect("/teacher");
    }

}
