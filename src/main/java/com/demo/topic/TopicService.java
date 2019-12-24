package com.demo.topic;

import com.demo.common.model.Topic;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class TopicService {
    private Topic dao = new Topic().dao();

    public Page<Topic> paginate(int pageNumber, int pageSize){
        return dao.paginate(pageNumber,pageSize,"select *","from topic order by id asc");
    }

    int allRecordCount(){
        return Db.queryInt("select count(*) from topic");
    }

    public Topic findById(int id){
        return dao.findById(id);
    }

    public void deleteById(int id){
        dao.deleteById(id);
    }

    public void update(int id,String field, String value){
        Record topic= new Record();
        topic = Db.findById("topic",id).set(field, value);
        Db.update("topic",topic);
    }

    public void add(Topic topic1){
        Record topic = new Record().set("score",topic1.getScore()).
                set("content",topic1.getContent()).set("answer",topic1.getAnswer())
                .set("subject_id",topic1.getSubjectId());
        Db.save("topic",topic);
    }
}
