package com.demo.testPaper;

import com.demo.common.model.TestPaper;
import com.demo.testPaper.TestPaperService;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.json.Json;

import java.util.List;

public class TestPaperController extends Controller{

    @Inject
    TestPaperService testPaperService;

    public void index() {
        //setAttr("testPaperPage",testPaperService.paginate(getParaToInt(0, 1), 10));
        render("testPaper.html");
    }

    public void list(){
        int page = getParaToInt("page");
        int limit = getParaToInt("limit");

        List<TestPaper> lists = testPaperService.paginate(page,limit).getList();
        String jsonList = Json.getJson().toJson(lists);

        int recordCount = testPaperService.allRecordCount();

        String jsons = "{\"code\":\"0\",\"msg\":\"\",\"count\":\""+
                recordCount+"\",\"data\":"+jsonList+"}";
        renderJson(jsons);
    }

    public void delete(){
        int id = Integer.parseInt(getPara("id"));
        testPaperService.deleteById(id);
        render("testPaper.html");
        //return 1;
    }

    public void update(){
        int id = Integer.parseInt(getPara("id"));
        String value = getPara("value");
        String field = getPara("field");
        testPaperService.update(id, field, value);
    }

//    public void add(){
//        TestPaper testPaper = getModel(TestPaper.class);
//        //System.out.println(user.getUsername());
//        testPaperService.add(testPaper);
//        redirect("/testPaper");
//    }

}
