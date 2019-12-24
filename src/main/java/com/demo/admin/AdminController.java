package com.demo.admin;

import com.demo.common.model.AdminUser;
import com.demo.admin.AdminService;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.json.Json;

import java.util.List;

public class AdminController extends Controller {
    @Inject
    AdminService adminService;

    public void index(){
        //setAttr("adminUserPage",adminService.paginate(getParaToInt(0, 1),10));
        render("adminUser.html");
    }

    public void list(){
        int page = getParaToInt("page");
        int limit = getParaToInt("limit");

        List<AdminUser> lists = adminService.paginate(page,limit).getList();
        String jsonList = Json.getJson().toJson(lists);

        int recordCount = adminService.allRecordCount();

        String jsons= "{\"code\":\"0\",\"msg\":\"\",\"count\":\""+ recordCount +"\",\"data\":"+jsonList+"}";

        renderJson(jsons);
    }

    public void delete(){
        int id = Integer.parseInt(getPara("id"));
        adminService.delete(id);
        render("admin.html");
        //return 1;
    }

    public void update(){
        int id = Integer.parseInt(getPara("id"));
        String value = getPara("value");
        String field = getPara("field");
        adminService.update(id, field, value);
    }

    public void add(){
        AdminUser adminUser = getModel(AdminUser.class);
        //System.out.println(student.get)
        adminService.add(adminUser);
        redirect("/admin");
    }
}
