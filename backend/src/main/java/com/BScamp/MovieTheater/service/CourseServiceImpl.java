package com.BScamp.MovieTheater.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BScamp.MovieTheater.repository.CourseRepo;
import com.BScamp.MovieTheater.entity.Course;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseRepo courseRepo;

	@Override
	public Course create(Course course) {
		return courseRepo.save(course);
	}

	@Override
	public Course get(int id) {
		return courseRepo.findById(id).orElse(null);
	}

	@Override
	public List<Course> getAll() {
		return courseRepo.findAll();
	}

	@Override
	public Course update(int id, Course course) {
		Course toUpdateCourse = this.get(id);
		if (toUpdateCourse == null) {
			return null;
		}			
		toUpdateCourse.setCname(course.getCname());
		toUpdateCourse.setPrice(course.getPrice());
		toUpdateCourse.setCvideopath(course.getCvideopath());
		toUpdateCourse.setCdesc(course.getCdesc());
		toUpdateCourse.setInstructor(course.getInstructor());		
		courseRepo.save(toUpdateCourse);
		return toUpdateCourse;
	}

	@Override
	public boolean delete(int id) {
		Course course = this.get(id);
		if (course == null) {
			return false;
		}
		courseRepo.deleteById(id);
		return true;
	}
   @Override
	public Course getByCname(String Cname) {
		return ((CourseServiceImpl) courseRepo).getByCname(Cname);		
	}

}

