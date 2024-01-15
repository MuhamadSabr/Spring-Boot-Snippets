package com.mmd.restcontrollerdemo.student;

public record Student(int id, String firstName, String lastName, boolean active) {

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", active=" + active +
                '}';
    }
}
