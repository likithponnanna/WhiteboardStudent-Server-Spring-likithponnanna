package com.example.WhiteboardStudentServerSpringlikithponnanna.repositories;

import com.example.WhiteboardStudentServerSpringlikithponnanna.model.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
    //List<Course> findCoursesForAuthorId(Long id);
}
