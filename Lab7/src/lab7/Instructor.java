/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author 20114
 */
public class Instructor extends User {
    private List<Course> createdCourses;

    public Instructor(String id, String name, String email, String passwordHash) {
        super(id, name, email, passwordHash, "Instructor");
        this.createdCourses = new ArrayList<Course>();
    }
    
    public Course createCourse(String title, String description, String instructorId) {
        String courseId = UUID.randomUUID().toString();
        Course c = new Course(courseId, title, description, instructorId);
        createdCourses.add(c);
        saveCourses();
        return c;
    }
    
    public void editCourse(String courseId, String newTitle, String newDescription, String newInstructorId) {
        Course c = getCourseById(courseId);
        if (c != null) {
            c.editCourse(newTitle, newDescription, newInstructorId);
            saveCourses();
        }
    }
    public Course getCourseById(String courseId) {
    for (Course c : createdCourses) {
        if (c.getCourseId().equals(courseId)) return c;
    }
    return null;
}
    public void deleteCourse(String courseId) {
        createdCourses.removeIf(c -> c.getCourseId().equals(courseId));
        saveCourses();
    }
    
     public void addLessonToCourse(String courseId, Lesson lesson) {
        Course c = getCourseById(courseId);
        if (c != null) {
            c.getLessons().add(lesson);
            saveCourses();
        }
    }
     
     public void editLessonInCourse(String courseId, String lessonId, String newTitle, String newContent) {
        Course c = getCourseById(courseId);
        if (c != null) {
            for (Lesson l : c.getLessons()) {
                if (l.getLessonId().equals(lessonId)) {
                    l.setTitle(newTitle);
                    l.setContent(newContent);
                }
            }
            saveCourses();
        }
    }
     
     public void deleteLessonFromCourse(String courseId, String lessonId) {
        Course c = getCourseById(courseId);
        if (c != null) {
            c.getLessons().removeIf(l -> l.getLessonId().equals(lessonId));
            saveCourses();
        }
    }
     
     public void viewEnrolledStudents(String courseId) {
        Course c = getCourseById(courseId);
        if (c != null) {
            System.out.println("Students enrolled in " + c.getTitle() + ":");
            if (c.getStudents().isEmpty()) {
                System.out.println("No students enrolled yet.");
            } else {
                for (Student s : c.getStudents()) {
                    System.out.println("- " + s.getName() + " (" + s.getId() + ")");
                }
            }
        }
    }

     
}
