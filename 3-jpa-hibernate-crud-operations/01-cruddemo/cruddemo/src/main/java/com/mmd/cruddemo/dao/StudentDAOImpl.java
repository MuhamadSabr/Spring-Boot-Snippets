package com.mmd.cruddemo.dao;

import com.mmd.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository  //Enables componentScanning, and JDBC exception translation.
public class StudentDAOImpl implements StudentDAO{
    //Define the entityManager field
    private final EntityManager entityManager;

    //Inject the systematically created entityManager into your application.
    @Autowired
    public StudentDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    //Implement the save method.
    @Override
    @Transactional  //U perform a CRUD transaction n this manages the transaction behind the scenes in Spring for u.
    public void save(Student student) {
        entityManager.persist(student); //Saves the Java obj to the DB table
    }

    //Implement the findById method.
    @Override
    public Student findById(int studentId){
        return entityManager.find(Student.class, studentId); //1st arg is the Entity class, 2nd is the primary key.
    }

    //Implement the findByLastName
    @Override
    public List<Student> findByLastName(String lastName) {
        //Create the query
        TypedQuery<Student> theStudent = entityManager.createQuery("FROM Student WHERE lastName=:lastName", Student.class);

        //Set the parameter
        theStudent.setParameter("lastName", lastName);

        //Execute the query n return the result.
        return theStudent.getResultList();
    }

    //Implement allStudents
    @Override
    public List<Student> findAll(){
        TypedQuery<Student> allStudents = entityManager.createQuery("FRoM Student oRDEr BY lastName dEsc", Student.class);//The entity name is case-sensitive,
        return allStudents.getResultList();                                         //along with all of its fields be careful.
    }

    @Override
    public List<Student> findByFullName(String firstName, String lastName) {
        TypedQuery<Student> students = entityManager.createQuery("FROM Student WHERE firstName=:firstName oR lastName=:lastName", Student.class);
        students.setParameter("firstName", firstName);
        students.setParameter("lastName", lastName);
        return students.getResultList();
    }

    @Override
    @Transactional  //Manage the lifecycle of the data manipulation for us.
    public Student update(Student student){
        return entityManager.merge(student);//JPA checks whether the student is in a detached state. If true, then synchronize the DB record with it.
    }

    @Override
    @Transactional
    public int updateAll(){
        return entityManager.createQuery("UPDATE Student Set firstName='Tapo' WHERE firstName <>'Tapo'").executeUpdate();
    }

    @Override
    @Transactional
    public void truncate() {
//        entityManager.createQuery("Truncate TABLE Student").executeUpdate(); //Remember JPQL operates on Entities and not tables.
        System.out.println("U cannot truncate a table anywhere except on the DB itself");
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public int deleteAll() {
        return entityManager.createQuery("DELETE Student").executeUpdate();
    }

    @Override
    @Transactional
    public void deleteStudent(int studentId) {
        Student student = findById(studentId);
        entityManager.remove(student);
        System.out.println("Successfully removed " + student);
    }
}
