package com.demo.subject;

import com.demo.common.model.Subject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;


public class SubjectService {
    private Subject dao = new Subject().dao();

    public Page<Subject> paginate(int pageNumber, int pageSize){
        return dao.paginate(pageNumber,pageSize,"select *","from subject order by id asc");
    }

    int allRecordCount(){
        return Db.queryInt("select count(*) from subject");
    }

    void delete(int id){
        dao.deleteById(id);
    }

    public void update(int id, String field,String value){
        Record subject = new Record();
        subject = Db.findById("subject",id).set(field,value);
        Db.update("subject",subject);
    }

    public void add(Subject  subject1){
        Record subject = new Record().set("subject_name",subject1.getSubjectName());
        Db.save("subject",subject);
    }

    public String queryName(int id){
        return Db.queryStr("select subject_name from subject where id = ?", id);
    }
}
