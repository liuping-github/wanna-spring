package org.code4everything.springbee.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCollection;
import com.zhazhapan.modules.constant.ValueConsts;
import com.zhazhapan.util.Checker;
import com.zhazhapan.util.DateUtils;
import com.zhazhapan.util.annotation.AopLog;
import org.bson.Document;
import org.code4everything.springbee.dao.TodoDAO;
import org.code4everything.springbee.domain.Todo;
import org.code4everything.springbee.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pantao
 * @since 2018/9/24
 */
@Service
public class TodoServiceImpl implements TodoService {

    private final TodoDAO todoDAO;

    private final MongoTemplate mongoTemplate;

    @Autowired
    public TodoServiceImpl(TodoDAO todoDAO, MongoTemplate mongoTemplate) {
        this.todoDAO = todoDAO;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    @AopLog("列出指定日期的待办事项")
    public List<Todo> listTodo(Date doingDate) {
        return todoDAO.getByDoingDate(doingDate);
    }

    @Override
    @AopLog("列出所有待办事项日期")
    public List<Date> listDate(String userId) {
        BasicDBObject dbObject = new BasicDBObject("userId", userId);
        MongoCollection<Document> collection = mongoTemplate.getCollection("todo");
        DistinctIterable<Date> iterable = collection.distinct("doingDate", dbObject, Date.class);
        List<Date> dateList = new ArrayList<>(128);
        for (Date date : iterable) {
            dateList.add(date);
        }
        return dateList;
    }

    @Override
    @AopLog("更新待办事项状态")
    public Todo updateTodoStatus(String todoId, String status) {
        Todo todo = todoDAO.getById(todoId);
        if (Checker.isNull(todo)) {
            return null;
        }
        if (ValueConsts.ONE_STR.equals(status)) {
            todo.setStatus("1");
            todo.setDoneTime(DateUtils.getCurrentTimestamp());
        } else {
            todo.setStatus("0");
        }
        return todoDAO.save(todo);
    }

    @Override
    @AopLog("更新待办事项内容")
    public Todo updateTodo(String todoId, String content) {
        Todo todo = todoDAO.getById(todoId);
        if (Checker.isNull(todo)) {
            return null;
        }
        todo.setContent(content);
        return todoDAO.save(todo);
    }

    @Override
    @AopLog("删除待办事项")
    public void remove(String todoId) {
        todoDAO.deleteById(todoId);
    }

    @Override
    @AopLog("添加待办事项")
    public Todo saveTodo(String userId, Date doingDate, String content) {
        Todo todo = new Todo();
        todo.setContent(content);
        todo.setCreateTime(DateUtils.getCurrentTimestamp());
        todo.setDoingDate(doingDate);
        todo.setId(RandomUtil.simpleUUID());
        todo.setStatus("0");
        todo.setUserId(userId);
        return todoDAO.save(todo);
    }
}