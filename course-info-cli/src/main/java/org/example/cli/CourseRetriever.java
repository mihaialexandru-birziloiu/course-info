package org.example.cli;

import org.example.cli.service.CourseRetrievalService;
import org.example.cli.service.CourseStorageService;
import org.example.cli.service.PluralsightCourse;
import org.example.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Predicate;

public class CourseRetriever {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRetriever.class);

    static void main(String... args) {
        LOGGER.info("CourseRetriever started!");

        if (args.length == 0) {
            LOGGER.warn("Please provide an author name as first arg");
            return;
        }

        try {
            retrieveCourses(args[0]);
        } catch (Exception e) {
            LOGGER.error("Unexpected error", e);
        }
    }

    private static void retrieveCourses(String authorId) {
        LOGGER.info("Retrieving courses for author '{}'", authorId);
        CourseRetrievalService courseRetrievalService = new CourseRetrievalService();
        CourseRepository courseRepository = CourseRepository.openCourseRepository("./courses.db");
        CourseStorageService courseStorageService = new CourseStorageService(courseRepository);

        List<PluralsightCourse> coursesToStore = courseRetrievalService.getCoursesFor(authorId)
                .stream().filter(Predicate.not(PluralsightCourse::isRetired)).toList();
        LOGGER.info("Retrieved the following {} courses {}", coursesToStore.size(), coursesToStore);

        courseStorageService.storePluralsightCourses(coursesToStore);
        LOGGER.info("Courses stored");
    }
}
