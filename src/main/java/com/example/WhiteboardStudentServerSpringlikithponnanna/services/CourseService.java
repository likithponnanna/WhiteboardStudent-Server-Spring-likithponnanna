package com.example.WhiteboardStudentServerSpringlikithponnanna.services;

import com.example.WhiteboardStudentServerSpringlikithponnanna.model.Course;
import com.example.WhiteboardStudentServerSpringlikithponnanna.model.User;
import com.example.WhiteboardStudentServerSpringlikithponnanna.repositories.CourseRepository;
import com.example.WhiteboardStudentServerSpringlikithponnanna.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders ="*")
@RestController
public class CourseService {
  @Autowired
  UserRepository userRepository;

  @Autowired
  CourseRepository courseRepository;

  private static List<Course> courses;

  public CourseService(){
    courses = new ArrayList <>();
  }



  @GetMapping("/api/courses")
  public List<Course> findAllCourses(HttpSession session) {
    User loggedUser = (User) session.getAttribute("currentUser");
    User retUser = userRepository.findById(loggedUser.getUserId()).get();

    return retUser.getAuthoredCourses();
  }

  @GetMapping("/api/courses/{cid}")
  public Course findCourseById(@PathVariable("cid") Long id, HttpSession session) {
    User loggedUser = (User) session.getAttribute("currentUser");
    if(loggedUser!=null) {
     return courseRepository.findById(id).get();
    }
    return new Course();
  }

  @PostMapping("/api/courses")
  public List<Course> createCourse(@RequestBody Course course, HttpSession session) {
    User loggedUser = (User) session.getAttribute("currentUser");
    if(session.getAttribute("currentUser")!=null){
      course.setUser((User) session.getAttribute("currentUser"));
      course.setId(new Date().getTime());
      if(course.getTitle().equals("")){
        course.setTitle("New Course");
      }
      course.setModules(new ArrayList <>());


      courseRepository.save(course);

    }
    return userRepository.findById(loggedUser.getUserId()).get().getAuthoredCourses();

  }

  @DeleteMapping("/api/courses/{cid}")
  public List<Course> deleteCourse(@PathVariable("cid") Long id, HttpSession session) {
    User loggedUser = (User) session.getAttribute("currentUser");
    if(loggedUser!=null) {

      courseRepository.deleteById(id);
      return userRepository.findById(loggedUser.getUserId()).get().getAuthoredCourses();
    }
    return new ArrayList <>(Arrays.asList(new Course()));
  }

  @PutMapping("/api/courses/{cid}")
  public Course updateCourse(@PathVariable("cid") Long id, @RequestBody Course course, HttpSession session) {
    User loggedUser = (User) session.getAttribute("currentUser");

    if(loggedUser!=null) {
     Course courseRet = courseRepository.findById(id).get();
     course.setId(courseRet.getId());
     course.setUser(courseRet.getUser());
     return courseRepository.save(course);
    }
    return new Course();
  }




}
