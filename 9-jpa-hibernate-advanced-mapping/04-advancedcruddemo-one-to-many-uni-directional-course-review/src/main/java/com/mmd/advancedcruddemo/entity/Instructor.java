package com.mmd.advancedcruddemo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructor")
public class Instructor {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    //This contains the foreign key, so this is the Owning side, responsible for managing the relationship, insert, delete, and update.
    //You don't specify the owning side, in the owning side itself.
    //So in InstructorDetail we will specify the owning side which is this entity. mappedBy="
    @OneToOne(targetEntity = InstructorDetail.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id", referencedColumnName = "id") // name specifies the fk column, referencedColumnName is the pk in the inverse side's table
    private InstructorDetail instructorDetail;


    //Bidirectional relationship between Instructor and Course
    @OneToMany(mappedBy = "instructor", //Remember this is the field that creates the relationship in che child(Course) class. instructor represents this in Course class
//  fetch = FetchType.EAGER, // OneToMany by default is lazy Will load the courses later(on-demand) and not with the instructor's initial loading from the DB.
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Course> courses;

    //Define the default constructor
    public Instructor() {courses = new ArrayList<>();}

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", instructorDetail=" + instructorDetail +
                '}';
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        courses.forEach(course-> course.setInstructor(this));
        this.courses = courses;
    }

    public void add(Course course){
        courses.add(course);
        course.setInstructor(this); // To keep the bidirectional link
    }

}
