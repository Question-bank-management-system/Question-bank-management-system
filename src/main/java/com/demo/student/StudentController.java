package com.demo.student;

import com.demo.common.model.Student;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.json.Json;

import java.util.List;

public class StudentController extends Controller {
    @Inject
    private StudentService studentService;

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
        render("personal_center.html");
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
        setAttr("studentPage",studentService.paginate(getParaToInt(0, 1),10));
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
        studentService.add(student);
        redirect("/student");
    }
}
