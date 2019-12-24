package com.demo.teacher;

import com.demo.common.model.Teacher;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;


public class TeacherService {
    private Teacher dao = new Teacher().dao();

    public Page<Teacher> paginate(int pageNumber, int pageSize){
        return dao.paginate(pageNumber,pageSize,"select *","from teacher order by id asc");
    }

    int allRecordCount(){
        return Db.queryInt("select count(*) from teacher");
    }

    void delete(int id){
        dao.deleteById(id);
    }

    public void update(int id, String field,String value){
        Record teacher = new Record();
        teacher = Db.findById("teacher",id).set(field,value);
        Db.update("teacher",teacher);
    }

    public void add(Teacher teacher1){
        Record teacher = new Record().set("user_id",teacher1.getUserId())
                .set("subject_id",teacher1.getSubjectId());
        Db.save("teacher",teacher);
    }
}
