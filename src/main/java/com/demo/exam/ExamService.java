package com.demo.exam;

import com.demo.common.model.Exam;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;


public class ExamService {
    private Exam dao = new Exam().dao();

    public Page<Exam> paginate(int pageNumber, int pageSize){
        return dao.paginate(pageNumber,pageSize,"select *","from exam order by id asc");
    }

    //Page<Exam> paginateStu(int pageNumber, int pageSize, int id){
        //return dao.paginate(pageNumber,pageSize,"select *","from exam where user_id = ?",id);
   // }

    int allRecordCount(){
        return Db.queryInt("select count(*) from exam");
    }

    //int allRecordCountStu(int id){
       // return Db.queryInt("select count(*) from exam where user_id = ?",id);
   //}


    void delete(int id){
        dao.deleteById(id);
    }

    public void update(int id, String field,String value){
        Record exam = new Record();
        exam  = Db.findById("exam",id).set(field,value);
        Db.update("exam",exam);
    }

    public void add(Exam exam1){
        Record exam = new Record().set("testpaper_id",exam1.getTestpaperId())
                .set("score",exam1.getScore())
                .set("student_id",exam1.getStudentId());
        Db.save("exam",exam);
    }

    //int queryCount(int classid) {
        //return Db.queryInt("select count(*) from student where classid = ?",classid);
    //}
}
