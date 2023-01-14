package com.BScamp.MovieTheater.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
// Default Constructor, All Args Constructor, Getters Setters, ToString
public class Movie implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50, nullable = false, unique = true)
	@NotBlank(message = "Required")
	private String title;

	@Column(length = 200)
	@NotBlank(message = "Required")
	private String posterPath;

	@Column(nullable = false)
	private int budget;

	@Column(length = 200)
	private String homePage;

	@Column(length = 200)
	@NotBlank(message = "Required")
	private String trailerPath;

	@Column(length = 1000)
	@NotBlank(message = "Required")
	private String overview;

	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
	private Category category;

	@Column(columnDefinition = "boolean", nullable = false)
	@ColumnDefault("false")
	private Boolean adult;

	@Column(nullable = false)
	@ColumnDefault("CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
	
	

	public Movie(int id, @NotBlank(message = "Required") String title,
			@NotBlank(message = "Required") String posterPath, int budget, String homePage,
			@NotBlank(message = "Required") String trailerPath, @NotBlank(message = "Required") String overview,
			Category category, Boolean adult, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.title = title;
		this.posterPath = posterPath;
		this.budget = budget;
		this.homePage = homePage;
		this.trailerPath = trailerPath;
		this.overview = overview;
		this.category = category;
		this.adult = adult;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getTrailerPath() {
		return trailerPath;
	}

	public void setTrailerPath(String trailerPath) {
		this.trailerPath = trailerPath;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Boolean getAdult() {
		return adult;
	}

	public void setAdult(Boolean adult) {
		this.adult = adult;
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
