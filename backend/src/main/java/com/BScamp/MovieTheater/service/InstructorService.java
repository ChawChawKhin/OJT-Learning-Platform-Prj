package com.BScamp.MovieTheater.service;

import java.util.List;

import com.BScamp.MovieTheater.entity.Instructor;

public interface InstructorService {

	public List<Instructor> getAll();

	public Instructor get(int id);

	public Instructor create(Instructor instructor);

	public Instructor update(int id, Instructor instructor);

	public boolean delete(int id);

	public Instructor getByIname(String iname);	
}

