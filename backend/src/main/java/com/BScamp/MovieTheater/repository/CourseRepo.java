package com.BScamp.MovieTheater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.BScamp.MovieTheater.entity.Course;

@EnableJpaRepositories
public interface CourseRepo extends JpaRepository<Course, Integer> {
	//public Course findByCname(String Cname);
}

