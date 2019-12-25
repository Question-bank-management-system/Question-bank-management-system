package com.demo.user;

import com.demo.common.model.User;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class UserService {
    private User dao = new User().dao();

    public Page<User> paginate(int pageNumber, int pageSize){
        return dao.paginate(pageNumber,pageSize,"select *","from user order by id asc");
    }

    int allRecordCount(){
        return Db.queryInt("select count(*) from user");
    }

    public User findById(int id){
        return dao.findById(id);
    }

    public void deleteById(int id){
        dao.deleteById(id);
    }

    public void update(int id,String field, String value){
        Record user = new Record();
        user = Db.findById("user",id).set(field, value);
        Db.update("user",user);
    }

    public void add(User user1){
        Record user = new Record();
        if (user1.getType().equals("admin")){
            user.set("username",user1.getUsername()).
                    set("password",user1.getPassword()).set("userlv",1)
                    .set("type",user1.getType()).set("realname",user1.getRealname())
                    .set("phonenumber",user1.getPhonenumber()).set("address",user1.getAddress())
                    .set("email",user1.getEmail()).set("birthdate",user1.getBirthdate());
        }
        else if(user1.getType().equals("teacher")){
            user.set("username",user1.getUsername()).
                    set("password",user1.getPassword()).set("userlv",2)
                    .set("type",user1.getType()).set("realname",user1.getRealname())
                    .set("phonenumber",user1.getPhonenumber()).set("address",user1.getAddress())
                    .set("email",user1.getEmail()).set("birthdate",user1.getBirthdate());
        }
        else{
            user.set("username",user1.getUsername()).
                    set("password",user1.getPassword()).set("userlv",3)
                    .set("type",user1.getType()).set("realname",user1.getRealname())
                    .set("phonenumber",user1.getPhonenumber()).set("address",user1.getAddress())
                    .set("email",user1.getEmail()).set("birthdate",user1.getBirthdate());
        }
        Db.save("user",user);
    }
    public Object queryByPara(User user){
        return Db.queryInt("select id from user where username = ? and password = ? and type = ?", user.getUsername(),user.getPassword(),user.getType());
    }

    public int queryByUserlv(int id){
        return Db.queryInt("select userlv from user where id = ? ",id);
    }
}
