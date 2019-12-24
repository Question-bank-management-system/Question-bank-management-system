package com.demo.classs;

import com.demo.classs.ClassService;
import com.demo.common.model.Class;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.json.Json;

import java.util.List;

public class ClassController extends Controller {
    @Inject
    ClassService classService;

    public void index(){
        setAttr("classPage",classService.paginate(getParaToInt(0, 1),10));
        render("class.html");
    }

    public void list(){
        int page = getParaToInt("page");
        int limit = getParaToInt("limit");

        List<Class> lists = classService.paginate(page,limit).getList();
        String jsonList = Json.getJson().toJson(lists);

        int recordCount = classService.allRecordCount();

        String jsons= "{\"code\":\"0\",\"msg\":\"\",\"count\":\""+ recordCount +"\",\"data\":"+jsonList+"}";

        renderJson(jsons);
    }

    public void delete(){
        int id = Integer.parseInt(getPara("id"));
        classService.delete(id);
        render("class.html");
        //return 1;
    }

    public void update(){
        int id = Integer.parseInt(getPara("id"));
        String value = getPara("value");
        String field = getPara("field");
        classService.update(id, field, value);
    }

    public void add(){
        Class class1 = getModel(Class.class);
        //System.out.println(student.get)
        classService.add(class1);
        redirect("/class");
    }
}
