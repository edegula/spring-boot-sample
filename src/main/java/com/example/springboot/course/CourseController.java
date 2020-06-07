package com.example.springboot.course;

import com.example.springboot.topic.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class CourseController {

    // @Autowired   - field injection is not recommended
    private CourseService courseService;

    // uses constructor-based dependency injection
    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping("/topics/{topicId}/courses")
    public ResponseEntity<List> getAllCourses(@PathVariable String topicId) {
        List<Course> courses = courseService.getAllCourses(topicId);
        return ResponseEntity.ok(courses);
    }

    @RequestMapping("/topics/{topicId}/courses/{courseId}")
    public ResponseEntity<Course> getCourse(@PathVariable String topicId, @PathVariable String courseId) {
        Course course = courseService.getCourse(courseId);
        if (course == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(course);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/topics/{topicId}/courses")
    public ResponseEntity<Course> addCourse(@RequestBody Course course, @PathVariable String topicId) {
        course.setTopic(new Topic(topicId, "", ""));
        Course createdCourse = courseService.addCourse(course);

        // return a 404 not found error
        if (createdCourse == null) return ResponseEntity.notFound().build();

        // populate the HTTP Header Location and return 201 Created
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{courseId}")
                .buildAndExpand(createdCourse.getId())
                .toUri();
        return ResponseEntity.created(uri)
                .body(createdCourse);

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/topics/{topicId}/courses/{courseId}")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course, @PathVariable String topicId, @PathVariable String courseId) {
        course.setTopic(new Topic(topicId, "", ""));
        Course updatedCourse = courseService.updateCourse(course);
        if (updatedCourse == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updatedCourse);
    }

    @RequestMapping(method = RequestMethod.DELETE ,value = "/topics/{topicId}/courses/{courseId}")
    public ResponseEntity<Course> deleteCourse(@PathVariable String id) {
        if (!courseService.deleteCourse(id)) { return ResponseEntity.notFound().build(); }
        return ResponseEntity.noContent().build();
    }


}
