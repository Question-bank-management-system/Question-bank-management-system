package com.demo.user;

import com.demo.common.model.User;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.json.Json;

import java.util.List;

public class UserController extends Controller{

    @Inject
    UserService userService;

    public void index() {
//        setAttr("userPage",userService.paginate(getParaToInt(0, 1), 10));
        render("user.html");
    }

    public void list(){
        int page = getParaToInt("page");
        int limit = getParaToInt("limit");

        List<User> lists = userService.paginate(page,limit).getList();
        String jsonList = Json.getJson().toJson(lists);

        int recordCount = userService.allRecordCount();

        String jsons = "{\"code\":\"0\",\"msg\":\"\",\"count\":\""+
                recordCount+"\",\"data\":"+jsonList+"}";
        renderJson(jsons);
    }

    public void delete(){
        int id = Integer.parseInt(getPara("id"));
        userService.deleteById(id);
        render("user.html");
        //return 1;
    }

    public void update(){
        int id = Integer.parseInt(getPara("id"));
        String value = getPara("value");
        String field = getPara("field");
        userService.update(id, field, value);
    }

    public void add(){
        User user = getModel(User.class);
        System.out.println(user.getUsername());
        userService.add(user);
        redirect("/user");
    }

}
