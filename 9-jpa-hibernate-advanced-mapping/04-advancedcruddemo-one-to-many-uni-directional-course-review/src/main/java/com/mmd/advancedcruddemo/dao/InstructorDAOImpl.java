package com.mmd.advancedcruddemo.dao;

import com.mmd.advancedcruddemo.entity.Course;
import com.mmd.advancedcruddemo.entity.Instructor;
import com.mmd.advancedcruddemo.entity.InstructorDetail;
import com.mmd.advancedcruddemo.entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    @Transactional
    public void save(Course course) {entityManager.persist(course);}

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

        //Because you don't have cascade delete on the relationship that will delete associated courses too when u delete an instructor You need to set all courses'
        // instructorId to null that are dependent on the instructor, or you will see ORA-02292: integrity constraint (HR.INSTR_FK) violated - child record found
        instructor.getCourses().forEach(course -> course.setInstructor(null));

        //The cascadeType.ALL on the relationship between instructor and instructorDetail will remove associated instructorDetails too.
        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void deleteCourse(int courseId) {

        //Find the course by id
        Course course = findCourseById(courseId);

        //The relationship between Course and Reviews has CascadeType.ALL on it, so by deleting a course you will delete all associated reviews too.
        entityManager.remove(course);
    }

    @Override
    public List<Instructor> findAllInstructors() {
        TypedQuery<Instructor> instructors =  entityManager.createQuery("FROM Instructor", Instructor.class);
        return instructors.getResultList();
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

    @Override //We want to load courses by instructor on demand. Instead of fetching every course for every instructor when an instructor is loaded(Eager fetching).
    public List<Course> findCourseByInstructorId(int instructorId){

        //Create the query to fetch courses based on the instructor id that is passed, making use of query parameters
        TypedQuery<Course> courseByInstructorIdQuery = entityManager.createQuery("FROM Course where instructor.id = :instructorId", Course.class);

        //Set the query parameter to use the instructor id.
        courseByInstructorIdQuery.setParameter("instructorId", instructorId);

        //Execute the query and return its ResultList()
        return courseByInstructorIdQuery.getResultList();
    }

    @Override
    public Course findCourseById(int courseId) {
        return entityManager.find(Course.class, courseId);
    }

    @Override
    public Course findCourseByTitle(String title){
        return entityManager.createQuery("FROM Course where title = :title", Course.class).setParameter("title", title).getSingleResult();
    }


    //The previous query is ok, but you execute two queries, let's cut the query to only one In case you needed a query to fetch instructor and courses both with one query.
    @Override
    public Instructor findInstructorByIdWithAssociatedCourses(int instructorId) {

        //JOIN FETCH loads the associated courses along with the main entity in an eager fashion.
        TypedQuery<Instructor> loadInstructorWithCoursesEagerlyQuery = entityManager.createQuery("SELECT i FROM Instructor i JOIN FETCH i.courses" +
                                                                                                                " JOIN FETCH i.instructorDetail "
                                                                                                        +" WHERE i.id = :instructorId", Instructor.class);
        loadInstructorWithCoursesEagerlyQuery.setParameter("instructorId", instructorId);

        return loadInstructorWithCoursesEagerlyQuery.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Instructor instructor){
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course){
        entityManager.merge(course);
    }


    @Override
    public Course findCourseByIdWithReviews(int courseId) {

        //Since the fetch type is lazy, when a course is loaded reviews are not, so let's use JOIN FETCH to demand it
        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c JOIN FETCH c.reviews WHERE c.id = :courseId", Course.class);
        query.setParameter("courseId", courseId);

        //You retrieved the Course with its reviews in an eager passion. Now return it.
        return query.getSingleResult();
    }
}
