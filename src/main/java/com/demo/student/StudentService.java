package com.demo.student;

import com.demo.common.model.Student;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;


public class StudentService {
    private Student dao = new Student().dao();

    public Page<Student> paginate(int pageNumber, int pageSize){
        return dao.paginate(pageNumber,pageSize,"select *","from student order by id asc");
    }

    Page<Student> paginateStu(int pageNumber, int pageSize, int id){
        return dao.paginate(pageNumber,pageSize,"select *","from student where user_id = ?",id);
    }

    int allRecordCount(){
        return Db.queryInt("select count(*) from student");
    }

    int allRecordCountStu(int id){
        return Db.queryInt("select count(*) from student where user_id = ?",id);
    }


    void delete(int id){
        dao.deleteById(id);
    }

    public void update(int id, String field,String value){
        Record student = new Record();
        student = Db.findById("student",id).set(field,value);
        Db.update("student",student);
    }

    public void add(Student student1){
        Record student = new Record().set("user_id",student1.getUserId())
                .set("classid",student1.getClassid());
        Db.save("student",student);
    }

    int queryCount(int classid) {
        return Db.queryInt("select count(*) from student where classid = ?",classid);
    }


}
