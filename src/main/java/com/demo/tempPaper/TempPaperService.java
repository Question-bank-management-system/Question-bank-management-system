package com.demo.tempPaper;

import com.demo.common.model.TempPaper;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;


public class TempPaperService {
    private TempPaper dao = new TempPaper().dao();

    public Page<TempPaper> paginate(int pageNumber, int pageSize){
        return dao.paginate(pageNumber,pageSize,"select *","from temp_paper order by id asc");
    }

    int allRecordCount(){
        return Db.queryInt("select count(*) from temp_paper");
    }

    void delete(int id){
        dao.deleteById(id);
    }

    public void update(int id, String field,String value){
        Record tempPaper = new Record();
        tempPaper  = Db.findById("temp_paper",id).set(field,value);
        Db.update("temp_paper",tempPaper);
    }

    public void add(TempPaper tempPaper1){
        Record tempPaper = new Record().set("test_paperid",tempPaper1.getTestPaperid())
                .set("topicid",tempPaper1.getTopicid());
        Db.save("temp_paper",tempPaper);
    }
}
