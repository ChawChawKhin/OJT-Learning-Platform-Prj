package com.BScamp.MovieTheater.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

//import com.BScamp.LearningPlatform.entity.PaymentType;
//import com.bsCamp.SpringBootDBDemo.entity.PaymentType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Payment implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Pid;
	
	@Column(columnDefinition = "ENUM('Visa', 'Credit') NOT NULL")
	@Enumerated(EnumType.STRING)
	private PaymentType Ptype;
	
	@Column(length = 50, nullable = false)
	@NotBlank(message = "Required")
	private String Pbname;	
	
	@Column(length = 200, nullable = false)
	@NotBlank(message = "Required")
	private String Psspath;

	public Payment(int Pid, PaymentType Ptype, 
			@NotBlank(message = "Required") String Pbname, 
			@NotBlank(message = "Required") String Psspath) {
		super();
		this.Pid = Pid;
		this.Pbname = Pbname;
		this.Psspath = Psspath;	
	}
}
