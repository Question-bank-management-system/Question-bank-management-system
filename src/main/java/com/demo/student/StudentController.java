package com.demo.student;

import com.demo.common.model.Student;
import com.demo.common.model.User;
import com.demo.user.UserService;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.json.Json;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

public class StudentController extends Controller {
    @Inject
    private StudentService studentService;

    @Inject
    private UserService userService;

//    导航栏
    public void testPaper() {
        render("testPaper.html");
    }
    public void practiceExam() {
        render("practiceExam.html");
    }
    public void redo() {
        render("redo.html");
    }
    public void viewResults() {
        render("viewResults.html");
    }
    public void personal_center() {
        User user = getSessionAttr("user");
        User user1 = userService.findById(user.getId());
        setAttr("user", user1);
        render("personalfile/personal_center.html");
    }

    public void personal_update(){
        User user = getSessionAttr("user");
        setAttr("user", userService.findById(user.getId()));
        render("personalfile/personal_update.html");
    }

    public void personal_information_update(){
        User user1 = getSessionAttr("user");
        User user = getModel(User.class);
        user.setId(user1.getId());
        user.update();
        setAttr("user", userService.findById(user.getId()));
        render("personalfile/personal_update.html");
    }
    public void class_index(){
        render("personalfile/addclass.html");
    }

    public void addclass(){
        int classid = Integer.parseInt(getPara("classid"));
        Student student = new Student();
        User user = getSessionAttr("user");

        // 在student表中查询该生是否加入该班级
        List<Record> students = Db.find("select * from student where classid = ? and user_id = ?",classid, user.getId());
        if(students.size() == 0){
            student.setUserId(user.getId());
            student.setClassid(classid);
            student.save();
        }
        redirect("/student/class_index");
    }

    public void list_myclass(){
        render("personalfile/mycalss.html");
    }

    public void myclass(){
        int page = getParaToInt("page");
        int limit = getParaToInt("limit");

        User user = getSessionAttr("user");
        List<Student> lists = studentService.paginateStu(page,limit,user.getId()).getList();
        String jsonList = Json.getJson().toJson(lists);

        int recordCount = studentService.allRecordCountStu(user.getId());

        String jsons = "{\"code\":\"0\",\"msg\":\"\",\"count\":\""+
                recordCount+"\",\"data\":"+jsonList+"}";
        renderJson(jsons);
    }

    public void queryCount(){
        int classid = getParaToInt("class_id");
        int count = studentService.queryCount(classid);
        renderJson("count",count);
    }

    //试卷考试
    public void examChinese(){render("examfile/examChinese.html");}
    public void examMath(){render("examfile/examMath.html");}
    public void examEnglish(){render("examfile/examEnglish.html");}

    //练习考试
    public void practiceChinese(){
        render("practicefile/practiceChinese.html");
    }
    public void practiceEnglish(){
        render("practicefile/practiceEnglish.html");
    }
    public void practiceMath(){
        render("practicefile/practiceMath.html");
    }

    //    错题重做
    public void redoChinese(){render("redofile/redoChinese.html");}
    public void redoMath(){render("redofile/redoMath.html");}
    public void redoEnglish(){render("redofile/redoEnglish.html");}
    public void redoNull(){render("redofile/redoNull.html");}

    public void index(){
        //setAttr("studentPage",studentService.paginate(getParaToInt(0, 1),10));
        render("student.html");
    }

    public void list(){
        int page = getParaToInt("page");
        int limit = getParaToInt("limit");

        List<Student> lists = studentService.paginate(page,limit).getList();
        String jsonList = Json.getJson().toJson(lists);

        int recordCount = studentService.allRecordCount();

        String jsons= "{\"code\":\"0\",\"msg\":\"\",\"count\":\""+ recordCount +"\",\"data\":"+jsonList+"}";

        renderJson(jsons);
    }

    public void delete(){
        int id = Integer.parseInt(getPara("id"));
        studentService.delete(id);
        render("student.html");
        //return 1;
    }

    public void update(){
        int id = Integer.parseInt(getPara("id"));
        String value = getPara("value");
        String field = getPara("field");
        studentService.update(id, field, value);
    }

    public void add(){
        Student student = getModel(Student.class);
        //System.out.println(student.get)
//        studentService.add(student);
        redirect("/student");
    }
}
