package com.BScamp.MovieTheater.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.BScamp.MovieTheater.entity.Instructor;
import com.BScamp.MovieTheater.repository.InstructorRepo;

@Service
public class InstructorServiceImpl implements InstructorService {

	@Autowired
	InstructorRepo instructorRepo;

	@Override
	public Instructor create(Instructor instructor) {
		// Create Instructor
		return instructorRepo.save(instructor);
	}

	@Override
	public Instructor get(int id) {
		return instructorRepo.findById(id).orElse(null);
	}

	@Override
	public List<Instructor> getAll() {
		return instructorRepo.findAll();
	}

	@Override
	public Instructor update (int id, Instructor instructor) {
		Instructor toUpdateInstructor = this.get(id);
		if (toUpdateInstructor == null) {
			return null;
		}
		toUpdateInstructor.setIname(instructor.getIname());
		toUpdateInstructor.setIphotopath(instructor.getIphotopath());
		toUpdateInstructor.setIdesc(instructor.getIphotopath());		
		instructorRepo.save(toUpdateInstructor);
		return toUpdateInstructor;
	}

	@Override
	public boolean delete(int id) {
		Instructor instructor = this.get(id);
		if (instructor == null) {
			return false;
		}
		instructorRepo.deleteById(id);
		return true;
	}

	@Override
	public Instructor getByIname(String iname) {
		// TODO Auto-generated method stub
		return ((InstructorServiceImpl) instructorRepo).getByIname(iname);
	}
}

