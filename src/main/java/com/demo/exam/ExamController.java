package com.demo.exam;

import com.demo.exam.ExamService;
import com.demo.common.model.Exam;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.json.Json;

import java.util.List;

public class ExamController extends Controller {
    @Inject
    ExamService examService;

    public void index(){
        //setAttr("adminUserPage",adminService.paginate(getParaToInt(0, 1),10));
        render("exam.html");
    }

    public void list(){
        int page = getParaToInt("page");
        int limit = getParaToInt("limit");

        List<Exam> lists = examService.paginate(page,limit).getList();
        String jsonList = Json.getJson().toJson(lists);

        int recordCount = examService.allRecordCount();

        String jsons= "{\"code\":\"0\",\"msg\":\"\",\"count\":\""+ recordCount +"\",\"data\":"+jsonList+"}";

        renderJson(jsons);
    }

    public void delete(){
        int id = Integer.parseInt(getPara("id"));
        examService.delete(id);
        render("exam.html");
        //return 1;
    }

    public void update(){
        int id = Integer.parseInt(getPara("id"));
        String value = getPara("value");
        String field = getPara("field");
        examService.update(id, field, value);
    }

    public void add(){
        Exam exam = getModel(Exam.class);
        //System.out.println(student.get)
        examService.add(exam);
        redirect("/exam");
    }
}
