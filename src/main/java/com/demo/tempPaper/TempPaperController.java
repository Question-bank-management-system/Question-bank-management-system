package com.demo.tempPaper;

import com.demo.common.model.TempPaper;
import com.demo.common.model.User;
import com.demo.tempPaper.TempPaperService;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.json.Json;

import java.util.List;

public class TempPaperController extends Controller {
    @Inject
    TempPaperService tempPaperService;

    public void index(){
        User user = getSessionAttr("user");
        setAttr("user",user);
        //setAttr("adminUserPage",adminService.paginate(getParaToInt(0, 1),10));
        render("tempPaper.html");
    }

    public void list(){
        int page = getParaToInt("page");
        int limit = getParaToInt("limit");

        List<TempPaper> lists = tempPaperService.paginate(page,limit).getList();
        String jsonList = Json.getJson().toJson(lists);

        int recordCount = tempPaperService.allRecordCount();

        String jsons= "{\"code\":\"0\",\"msg\":\"\",\"count\":\""+ recordCount +"\",\"data\":"+jsonList+"}";

        renderJson(jsons);
    }

    public void delete(){
        int id = Integer.parseInt(getPara("id"));
        tempPaperService.delete(id);
        render("tempPaper.html");
        //return 1;
    }

    public void update(){
        int id = Integer.parseInt(getPara("id"));
        String value = getPara("value");
        String field = getPara("field");
        tempPaperService.update(id, field, value);
    }

    public void add(){
        TempPaper tempPaper = getModel(TempPaper.class);
        //System.out.println(student.get)
        tempPaperService.add(tempPaper);
        redirect("/tempPaper");
    }
}
