package com.BScamp.MovieTheater.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import lombok.Builder;

@Entity

@Builder


// Default Constructor, All Args Constructor, Getters Setters, ToString
public class Course implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Cid;

	@Column(length = 50, nullable = false, unique = true)
	@NotBlank(message = "Required")
	private String Cname;
	
	@Column(length = 50, nullable = false)
	@NotBlank(message = "Required")
	private int Price;
	
	@OneToOne
	@JoinColumn(name = "instructor_id", referencedColumnName = "Iid")
	private Instructor instructor;

	@Column(length = 200, nullable = false)
	@NotBlank(message = "Required")
	private String Cvideopath;

	@Column(length = 500, nullable = false)
	private String Cdesc;
	
	public Course(int Cid, @NotBlank(message = "Required") String Cname,
			@NotBlank(message = "Required") int Price, 
			@NotBlank(message = "Required") String Cvideopath, 
			Instructor instructor, String Cdesc) {
		super();
		this.Cid = Cid;
		this.Cname = Cname;
		this.Price = Price;
		this.Cvideopath = Cvideopath;		
		this.Cdesc = Cdesc;
		this.instructor = instructor;	
	}

	public int getCid() {
		return Cid;
	}

	public void setCid(int cid) {
		Cid = cid;
	}

	public String getCname() {
		return Cname;
	}

	public void setCname(String cname) {
		Cname = cname;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public String getCvideopath() {
		return Cvideopath;
	}

	public void setCvideopath(String cvideopath) {
		Cvideopath = cvideopath;
	}

	public String getCdesc() {
		return Cdesc;
	}

	public void setCdesc(String cdesc) {
		Cdesc = cdesc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
