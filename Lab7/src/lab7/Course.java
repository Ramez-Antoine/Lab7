/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7;

import java.util.ArrayList;

/**
 *
 * @author youssef
 */
public class Course {
    private String courseId;
    private String title;
    private String description;
    private String instructorId;
    private ArrayList<Lesson> lessons;
    private ArrayList<Student> students;
    


public Course(String courseId, String title, String description, String instructorId) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.instructorId = instructorId;
        this.lessons = new ArrayList<Lesson>();
        this.students = new ArrayList<Student>();
    }

    public String getCourseId() {
        return courseId;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public String getTitle() {
        return title;
    }

    

 public void editCourse(String newTitle, String newDescription, String newInstructorId) {
        if (newTitle != null) this.title = newTitle;
        if (newDescription != null) this.description = newDescription;
        if (newInstructorId != null) this.instructorId = newInstructorId;
    }
 
 public void viewEnrolledStudents() {
        System.out.println("Enrolled Students in " + title + ":");
        if (students.isEmpty()) {
            System.out.println("No students enrolled yet.");
        } else {
            for (Student s : students) {
                System.out.println("- " + s.getUsername() + " (" + s.getUserId() + ")");
            }
        }
    }


 }
