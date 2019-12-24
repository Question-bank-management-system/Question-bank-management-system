package com.demo.admin;

import com.demo.common.model.AdminUser;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;


public class AdminService {
    private AdminUser dao = new AdminUser().dao();

    public Page<AdminUser> paginate(int pageNumber, int pageSize){
        return dao.paginate(pageNumber,pageSize,"select *","from admin_user order by id asc");
    }

    int allRecordCount(){
        return Db.queryInt("select count(*) from admin_user");
    }

    void delete(int id){
        dao.deleteById(id);
    }

    public void update(int id, String field,String value){
        Record adminUser = new Record();
        adminUser  = Db.findById("admin_user",id).set(field,value);
        Db.update("admin_user",adminUser);
    }

    public void add(AdminUser adminUser1){
        Record adminUser = new Record().set("user_id",adminUser1.getUserId());
        Db.save("admin_user",adminUser);
    }
}
