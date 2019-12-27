package com.demo.subject;

import com.demo.common.model.User;
import com.demo.subject.SubjectService;
import com.demo.common.model.Subject;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.json.Json;

import java.util.List;

public class SubjectController extends Controller {
    @Inject
    SubjectService subjectService;

    public void index(){
        User user = getSessionAttr("user");
        setAttr("user",user);
        //setAttr("adminUserPage",adminService.paginate(getParaToInt(0, 1),10));
        render("subject.html");
    }

    public void list(){
        int page = getParaToInt("page");
        int limit = getParaToInt("limit");

        List<Subject> lists = subjectService.paginate(page,limit).getList();
        String jsonList = Json.getJson().toJson(lists);

        int recordCount = subjectService.allRecordCount();

        String jsons= "{\"code\":\"0\",\"msg\":\"\",\"count\":\""+ recordCount +"\",\"data\":"+jsonList+"}";

        renderJson(jsons);
    }

    public void delete(){
        int id = Integer.parseInt(getPara("id"));
        subjectService.delete(id);
        render("subject.html");
        //return 1;
    }

    public void update(){
        int id = Integer.parseInt(getPara("id"));
        String value = getPara("value");
        String field = getPara("field");
        subjectService.update(id, field, value);
    }

    public void add(){
        Subject subject = getModel(Subject.class);
        //System.out.println(student.get)
        subjectService.add(subject);
        redirect("/subject");
    }

    public void querySubjectname(){
        int subject_id = getParaToInt("subjectid");
        String name = subjectService.queryName(subject_id);
        renderJson("subject_name", name);
    }
}
