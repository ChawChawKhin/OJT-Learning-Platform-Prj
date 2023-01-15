package com.BScamp.MovieTheater.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//import com.bsCamp.SpringBootDBDemo.entity.Payment;
import com.BScamp.MovieTheater.service.CourseService;
import com.BScamp.MovieTheater.service.StorageService;
import com.BScamp.MovieTheater.entity.Course;

@RestController
public class CourseContoller {

	@Autowired
	CourseService courseService;

	@Autowired
	StorageService storageService;

	@GetMapping("/course")
	public Object getCourses() {
		List<Course> courses = courseService.getAll();
		if(courses.size()>0)
			return courses;
		else
			return "there is no course";
		
	}

	@GetMapping("/course/{Cid}")
	public ResponseEntity<Course> getCourse(
			@PathVariable("Cid") int courseID
	) {
		Course course = courseService.get(courseID);
		if (course == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(course);
	}

	@GetMapping("/media/{fileType}/{fileName}")
	public ResponseEntity<?> getCoursepath(
			@PathVariable("fileType") String fileType,
			@PathVariable("fileName") String fileName
	) throws IOException {
		MediaType contentType = MediaType.IMAGE_PNG;
		switch (fileType) {
			case "mp4" :
				contentType = MediaType.APPLICATION_OCTET_STREAM;
				break;
			case "jpg" :
				contentType = MediaType.IMAGE_JPEG;
				break;
			case "png" :
				contentType = MediaType.IMAGE_PNG;
				break;
			default :
				return ResponseEntity.badRequest()
						.body("Unsupported File Type");
		}
		byte[] fileBytes = storageService.load(fileName);
		if (fileBytes == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().contentType(contentType).body(fileBytes);
	}

}
