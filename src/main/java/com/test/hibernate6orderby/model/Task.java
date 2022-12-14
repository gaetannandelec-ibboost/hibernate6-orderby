package com.test.hibernate6orderby.model;

import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@OrderBy("id DESC")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<TaskVersion> taskVersions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<TaskVersion> getTaskVersions() {
		return taskVersions;
	}

	public void setTaskVersions(List<TaskVersion> taskVersions) {
		this.taskVersions = taskVersions;
	}

	public TaskVersion getLatestVersion() {
		if (taskVersions == null || taskVersions.isEmpty())
			return null;

		return taskVersions.get(0);
	}

}
