package com.demo.classs;

import com.demo.common.model.Class;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;


public class ClassService {
    private Class dao = new Class().dao();

    public Page<Class> paginate(int pageNumber, int pageSize){
        return dao.paginate(pageNumber,pageSize,"select *","from class order by id asc");
    }

    int allRecordCount(){
        return Db.queryInt("select count(*) from class");
    }

    void delete(int id){
        dao.deleteById(id);
    }

    public void update(int id, String field,String value){
        Record class1 = new Record();
        class1  = Db.findById("class",id).set(field,value);
        Db.update("class",class1);
    }

    public void add(Class class1){
        Record class2 = new Record().set("teacher_id",class1.getTeacherId());
        Db.save("class",class2);
    }
}
