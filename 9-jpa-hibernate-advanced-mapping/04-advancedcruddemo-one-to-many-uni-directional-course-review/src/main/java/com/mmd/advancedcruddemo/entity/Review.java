package com.mmd.advancedcruddemo.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

@Entity
@Table(name = "review")
public class Review {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "feedback")
    private String comment;

//    @Column(name = "course_id")
//    private int courseId; You don't need this the foreign and primary key relationship in the Course Entity will automatically set course_id to that specific Course_id

    public Review(){}
    public Review(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }
}
