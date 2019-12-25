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

    int allRecordCount(){
        return Db.queryInt("select count(*) from student");
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
                .set("test_paperid",student1.getTestPaperid())
                .set("classid",student1.getClassid()).set("score",student1.getScore());
        Db.save("student",student);
    }
}
