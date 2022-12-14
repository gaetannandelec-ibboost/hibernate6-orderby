package com.test.hibernate6orderby;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.hibernate6orderby.model.Group;
import com.test.hibernate6orderby.model.Organisation;
import com.test.hibernate6orderby.model.Task;
import com.test.hibernate6orderby.model.TaskVersion;
import com.test.hibernate6orderby.model.User;
import com.test.hibernate6orderby.repo.ITaskRepo;
import com.test.hibernate6orderby.repo.IUserRepo;

@SpringBootTest
class Hibernate6OrderbyApplicationTests {

	@Autowired
	private ITaskRepo taskRepo;

	@Autowired
	private IUserRepo userRepo;

	@Test
	void contextLoads() {

		User u = new User();
		Group g = new Group();
		g.setName("A");
		u.setGroups(new HashSet<>());
		u.getGroups().add(g);
		Organisation o = new Organisation();
		o.setName("A");
		u.setOrganisations(new HashSet<>());
		u.getOrganisations().add(o);
		u = userRepo.save(u);

		User u2 = new User();
		Group g2 = new Group();
		g2.setName("B");
		u2.setGroups(new HashSet<>());
		u2.getGroups().add(g2);
		Organisation o2 = new Organisation();
		o2.setName("B");
		u2.setOrganisations(new HashSet<>());
		u2.getOrganisations().add(o2);
		u2 = userRepo.save(u2);

		Task task = new Task();
		TaskVersion v = new TaskVersion();
		v.setName("V1");
		v.setAssignee(u);
		task.setTaskVersions(new ArrayList<>());
		task.getTaskVersions().add(v);
		v.setTask(task);
		task = taskRepo.save(task);

		TaskVersion v2 = new TaskVersion();
		v2.setName("V2");
		v2.setAssignee(u2);
		task.getTaskVersions().add(v2);
		v2.setTask(task);
		task = taskRepo.save(task);

		Task taskCheck = taskRepo.findById(task.getId()).orElse(task);
		assertNotNull(taskCheck);
		assertNotNull(taskCheck.getLatestVersion());
		assertEquals("V2", taskCheck.getLatestVersion().getName());
	}

}
