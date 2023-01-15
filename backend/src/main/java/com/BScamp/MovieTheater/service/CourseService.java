package com.BScamp.MovieTheater.service;

import java.util.List;
import com.BScamp.MovieTheater.entity.Course;

public interface CourseService {

	public List<Course> getAll();

	public Course get(int id);

	public Course create(Course course);

	public Course update(int id, Course course);

	public boolean delete(int id);
	
	public Course getByCname(String Cname);

}
