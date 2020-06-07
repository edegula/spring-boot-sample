package com.example.springboot.course;

import com.example.springboot.HelloController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service // creates a Singleton - only one instance for the whole app
public class CourseService {

    private CourseRepository courseRepository;

    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    // uses constructor-based dependency injection
    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses(String topicId) {
        List<Course> courses = new ArrayList<>();
        courseRepository.findByTopicId(topicId)
                .forEach(courses::add);  // uses method reference
        return courses;
    }

    public Course getCourse(String id) {
        if (courseRepository.findById(id).isPresent()) {
            return courseRepository.findById(id).get();
        } else {
            return null;
        }
    }

    public Course addCourse(Course course) {
        courseRepository.save(course);
        return course;
    }

    public Course updateCourse(Course course) {
        courseRepository.save(course);
        return course;
    }

    public boolean deleteCourse(String id) {
        if (courseRepository.findById(id).isPresent()) {
            courseRepository.deleteById(id);
            return true;
        } else {
            logger.warn("Course not found : " + id);
            return false;
        }
    }
}
