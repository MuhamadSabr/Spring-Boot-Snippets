package com.mmd.advancedcruddemo.dao;

import com.mmd.advancedcruddemo.entity.Instructor;
import com.mmd.advancedcruddemo.entity.InstructorDetail;
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
    public Instructor findInstructorById(int instructorId) {

        //By default fetch type is eager, meaning the associated instructor detail will also be retrieved.
        return entityManager.find(Instructor.class, instructorId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int instructorId) {

        //Fetch The corresponding instructor
        Instructor instructor = findInstructorById(instructorId);

        //Removing, because of the cascadeType.ALL will also remove the instructor_detail entity in the corresponding table.
        entityManager.remove(instructor);
    }


    @Override
    public InstructorDetail findInstructorDetailById(int instructorDetailId) {
        return entityManager.find(InstructorDetail.class, instructorDetailId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int instructorDetailId) {

        //Fetch the instructorDetail by Id.
        InstructorDetail instructorDetail = findInstructorDetailById(instructorDetailId);

        //You need to break the bidirectional link by updating the associated entity's InstructorDetail to null. Otherwise, the operation won't be successful.
        //Hibernate will automatically set the corresponding field in the Instructor that references the ID in the InstructorDetail table to null in the DB.
        instructorDetail.getInstructor().setInstructorDetail(null);

        //Remove it. The instructor has CascadeType.All on, just like instructorDetail, so this will also result in remove of the associated entity.
        entityManager.remove(instructorDetail);
    }
}
