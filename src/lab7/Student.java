/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author 20114
 */
public class Student extends User {

    private ArrayList<String> enrolledCourses;
    private HashMap<String, ArrayList<String>> progress;

    public Student(String id, String name, String email, String passwordHash) {
        super(id, name, email, passwordHash, "Student");
        enrolledCourses = new ArrayList<>();
        progress = new HashMap<>();
    }

    // --- ENROLLMENT ---
    public void enrollCourse(String courseId) {
        if (!enrolledCourses.contains(courseId)) {
            enrolledCourses.add(courseId);
        }
    }

    public ArrayList<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    // --- PROGRESS ---
    public void markLessonCompleted(String courseId, String lessonId) {
        progress.putIfAbsent(courseId, new ArrayList<>());
        ArrayList<String> completed = progress.get(courseId);

        if (!completed.contains(lessonId)) {
            completed.add(lessonId);
        }
    }

    public boolean hasCompletedLesson(String courseId, String lessonId) {
        return progress.containsKey(courseId) 
                && progress.get(courseId).contains(lessonId);
    }

    public HashMap<String, ArrayList<String>> getProgress() {
        return progress;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
    
    

}
