package org.code4everything.springbee.dao;

import org.code4everything.springbee.domain.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * @author pantao
 * @since 2018/9/24
 */
@Repository
public interface TodoDAO extends MongoRepository<Todo, String> {

    Todo getById(String todoId);

    List<Todo> getByDoingDate(Date doingDate);
}
