package com.demo.adminUser;

import com.demo.common.model.AdminUser;
import com.demo.common.model.User;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.json.Json;

import java.util.List;

public class AdminUserController extends Controller {
    @Inject
    AdminUserService adminUserService;

    public void index(){
        User user = getSessionAttr("user");
        setAttr("user",user);
        //setAttr("adminUserPage",adminService.paginate(getParaToInt(0, 1),10));
        render("adminUser.html");
    }

    public void personCenter(){
        User user = getSessionAttr("user");
        setAttr("user",user);
        render("adminPerson.html");}

    public void list(){
        int page = getParaToInt("page");
        int limit = getParaToInt("limit");

        List<AdminUser> lists = adminUserService.paginate(page,limit).getList();
        String jsonList = Json.getJson().toJson(lists);

        int recordCount = adminUserService.allRecordCount();

        String jsons= "{\"code\":\"0\",\"msg\":\"\",\"count\":\""+ recordCount +"\",\"data\":"+jsonList+"}";

        renderJson(jsons);
    }

    public void delete(){
        int id = Integer.parseInt(getPara("id"));
        adminUserService.delete(id);
        render("adminUser.html");
        //return 1;
    }

    public void update(){
        int id = Integer.parseInt(getPara("id"));
        String value = getPara("value");
        String field = getPara("field");
        adminUserService.update(id, field, value);
    }

    public void add(){
        AdminUser adminUser = getModel(AdminUser.class);
        //System.out.println(student.get)
        adminUserService.add(adminUser);
        redirect("/adminUser");
    }
}
