package com.BScamp.MovieTheater.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.BScamp.MovieTheater.entity.Instructor;

public interface InstructorRepo extends JpaRepository<Instructor, Integer> {

	//Instructor getByIname(String iname);
	//public Instructor findByIname(String iname);
}
