package com.BScamp.MovieTheater.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ColumnDefault;

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
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 30, nullable = false)
	@NotBlank(message = "Required")
	private String name;

	@Column(length = 100, nullable = false)
	@NotBlank(message = "Required")
	private String password;

	@Column(length = 100, nullable = false, unique = true)
	@NotBlank(message = "Required")
	private String gmail;

	@Column(columnDefinition = "ENUM('active', 'deactivated', 'banned', 'violated') NOT NULL")
	@Enumerated(EnumType.STRING)
	private UserStatus status;

	@Column(columnDefinition = "ENUM('admin', 'user') NOT NULL")
	@Enumerated(EnumType.STRING)
	private UserRole role;

	@Column(nullable = false)
	private LocalDate startJoinDate;

	private LocalDate lastJoinDate;

	private int accessCount;

	@Column(nullable = false)
	@ColumnDefault("CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
	
	public User() {
		
	}
	public User(int id, @NotBlank(message = "Required") String name, @NotBlank(message = "Required") String password,
			@NotBlank(message = "Required") String gmail, UserStatus status, UserRole role, LocalDate startJoinDate,
			LocalDate lastJoinDate, int accessCount, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.gmail = gmail;
		this.status = status;
		this.role = role;
		this.startJoinDate = startJoinDate;
		this.lastJoinDate = lastJoinDate;
		this.accessCount = accessCount;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public LocalDate getStartJoinDate() {
		return startJoinDate;
	}

	public void setStartJoinDate(LocalDate startJoinDate) {
		this.startJoinDate = startJoinDate;
	}

	public LocalDate getLastJoinDate() {
		return lastJoinDate;
	}

	public void setLastJoinDate(LocalDate lastJoinDate) {
		this.lastJoinDate = lastJoinDate;
	}

	public int getAccessCount() {
		return accessCount;
	}

	public void setAccessCount(int accessCount) {
		this.accessCount = accessCount;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
