package com.demo.topic;

import com.demo.common.model.Topic;
import com.demo.topic.TopicService;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.json.Json;

import java.util.List;

public class TopicController extends Controller{

    @Inject
    TopicService topicService;

    public void index() {
        setAttr("topicPage",topicService.paginate(getParaToInt(0, 1), 10));
        render("topic.html");
    }

    public void list(){
        int page = getParaToInt("page");
        int limit = getParaToInt("limit");

        List<Topic> lists = topicService.paginate(page,limit).getList();
        String jsonList = Json.getJson().toJson(lists);

        int recordCount = topicService.allRecordCount();

        String jsons = "{\"code\":\"0\",\"msg\":\"\",\"count\":\""+
                recordCount+"\",\"data\":"+jsonList+"}";
        renderJson(jsons);
    }

    public void delete(){
        int id = Integer.parseInt(getPara("id"));
        topicService.deleteById(id);
        render("topic.html");
        //return 1;
    }

    public void update(){
        int id = Integer.parseInt(getPara("id"));
        String value = getPara("value");
        String field = getPara("field");
        topicService.update(id, field, value);
    }

    public void add(){
        Topic topic = getModel(Topic.class);
        //System.out.println(user.getUsername());
        topicService.add(topic);
        redirect("/topic");
    }

}
