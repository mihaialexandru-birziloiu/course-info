package org.example.cli.service;

import org.example.domain.Course;
import org.example.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

public class CourseStorageService {
    private final CourseRepository courseRepository;
    private static final String PS_BASE_URL = "https://app.pluralsight.com";

    public CourseStorageService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void storePluralsightCourses(List<PluralsightCourse> psCourses) {
        for (PluralsightCourse psCourse : psCourses) {
            Course course = new Course(
                    psCourse.id(),
                    psCourse.title(),
                    psCourse.durationInMinutes(),
                    PS_BASE_URL + psCourse.contentUrl(), Optional.empty()
            );
            courseRepository.saveCourse(course);
        }
    }
}
