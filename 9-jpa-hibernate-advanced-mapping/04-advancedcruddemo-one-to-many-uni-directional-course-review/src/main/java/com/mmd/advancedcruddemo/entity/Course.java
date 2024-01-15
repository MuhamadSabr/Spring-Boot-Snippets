package com.mmd.advancedcruddemo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "title")
	private String title;

	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "instructor_id")
	private Instructor instructor;

	//Create One-To-Many relationship between Course and Review
	//U don't want to load reviews every time a course is loaded, and default FetchType.LAZY of @OneToOne achieves that.
	//U also want to cascade REMOVE, DETACH, MERGE, REFRESH, PERSIST from the Course entity to the related entity Review.
	//We don't even set course_id anywhere, the relationship will take care of it, and for each record you add will insert this specific course's id
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "course_id", referencedColumnName = "id")// name is the FK in Review Entity that references referencedColumnName 'id' in Course(this)
			List<Review> reviews;

	public Course(){reviews = new ArrayList<>();}

	public Course(String title) {
		this();
		this.title = title;
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

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}


	//Helper method for adding reviews to the course
	public void addReview(Review review){
		reviews.add(review);
	}

	@Override
	public String toString() {
		return "Course{" +
				"id=" + id +
				", title='" + title + '\'' +
				'}';
	}

}
