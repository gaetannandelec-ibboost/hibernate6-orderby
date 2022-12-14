package com.test.hibernate6orderby.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.test.hibernate6orderby.model.Task;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ITaskRepo extends CrudRepository<Task, Long> {

}
