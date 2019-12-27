package com.demo.testPaper;

import com.demo.common.model.TestPaper;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class TestPaperService {
    private TestPaper dao = new TestPaper().dao();

    public Page<TestPaper> paginate(int pageNumber, int pageSize){
        return dao.paginate(pageNumber,pageSize,"select *","from test_paper order by id asc");
    }

    int allRecordCount(){
        return Db.queryInt("select count(*) from test_paper");
    }

    public TestPaper findById(int id){
        return dao.findById(id);
    }

    public void deleteById(int id){
        dao.deleteById(id);
    }

    public void update(int id,String field, String value){
        Record testPaper = new Record();
        testPaper = Db.findById("test_paper",id).set(field, value);
        Db.update("test_paper",testPaper);
    }

    public void add(TestPaper testPaper1){
        Record testPaper = new Record().set("all_time",testPaper1.getAllTime()).
                set("starttime",testPaper1.getStarttime()).set("endtime",testPaper1.getEndtime())
                .set("full_marks",testPaper1.getFullMarks()).set("classid",testPaper1.getClassId());
        Db.save("test_paper",testPaper);
    }

}
