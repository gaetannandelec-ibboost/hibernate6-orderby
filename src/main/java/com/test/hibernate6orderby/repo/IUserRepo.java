package com.test.hibernate6orderby.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.test.hibernate6orderby.model.User;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface IUserRepo extends CrudRepository<User, Long> {

}
