package com.mmd.advancedcruddemo.dao;

import com.mmd.advancedcruddemo.entity.Instructor;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class InstructorDAOImpl implements InstructorDAO{

    private EntityManager entityManager; //The entity manager is created by Spring Boot when setting up JDBC connection

    //We inject the already defined entity manager. We don't need the @Autowired since there is only one constructor here.
    public InstructorDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional //Don't U forget to tell Spring Boot that this DB operation is a transactional DB transaction.
    public void save(Instructor instructor){
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findById(int instructorId) {

        //By default fetch type is eager, meaning the associated instructor detail will also be retrieved.
        return entityManager.find(Instructor.class, instructorId);
    }

    @Override
    @Transactional
    public void delete(int instructorId) {

        //Fetch The corresponding instructor
        Instructor instructor = findById(instructorId);

        //Removing, because of the cascadeType.ALL will also remove the instructor_detail entity in the corresponding table.
        entityManager.remove(instructor);
    }
}
