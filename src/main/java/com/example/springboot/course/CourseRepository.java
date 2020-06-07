package com.example.springboot.course;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Utilises the built-in CrudRepository to perform standard JPA activities
 */
public interface CourseRepository extends CrudRepository<Course, String> {

    // Spring Data JPA dynamically implements a method to filter courses based on name property
    // follows the pattern "findBy<PropertyName>"
    public List<Course> findByName(String name);

    public List<Course> findByDescription(String description);

    // filter method based on an Object, follows pattern "findBy<PropertyName><PropertyAttribute>"
    // methods should be written in CamelCase
    public List<Course> findByTopicId(String id);

}
