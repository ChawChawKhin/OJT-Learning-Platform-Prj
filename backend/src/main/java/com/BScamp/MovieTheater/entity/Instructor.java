package com.BScamp.MovieTheater.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity

@Builder


public class Instructor implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Iid;

	@Column(length = 30, nullable = false)
	@NotBlank(message = "Required")
	private String Iname;
	
	@Column(length = 200, nullable = false)
	@NotBlank(message = "Required")
	private String Iphotopath;

	@Column(length = 500, nullable = false)
	private String Idesc;
	
	public Instructor()
	{
		
	}
	public Instructor(int Iid,  String Iname,  String Iphotopath, String Idesc) {
		super();
		this.Iid = Iid;
		this.Iname = Iname;
		this.Iphotopath = Iphotopath;
		this.Idesc = Idesc;		
	}

	public int getIid() {
		return Iid;
	}

	public void setIid(int iid) {
		Iid = iid;
	}

	public String getIname() {
		return Iname;
	}

	public void setIname(String iname) {
		Iname = iname;
	}

	public String getIphotopath() {
		return Iphotopath;
	}

	public void setIphotopath(String iphotopath) {
		Iphotopath = iphotopath;
	}

	public String getIdesc() {
		return Idesc;
	}

	public void setIdesc(String idesc) {
		Idesc = idesc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	}

