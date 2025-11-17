/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonDatabaseManager {

    private final String USERS_FILE = "users.json";
    private final String COURSES_FILE = "courses.json";

    // ------------------------- LOAD USERS -------------------------
    public ArrayList<User> loadAllUsers() {
        ArrayList<User> users = new ArrayList<>();

        try {
            JSONArray arr = new JSONArray(new FileReader(USERS_FILE).readAllBytes());

            for (int i = 0; i < arr.length(); i++) {
                JSONObject json = arr.getJSONObject(i);

                String id = json.getString("id");
                String name = json.getString("name");
                String email = json.getString("email");
                String password = json.getString("passwordHash");
                String role = json.getString("role");

                if (role.equals("Student")) {
                    Student s = new Student(id, name, email, password);

                    // enrolled courses
                    JSONArray enrolled = json.getJSONArray("enrolledCourses");
                    for (int j = 0; j < enrolled.length(); j++) {
                        s.enrollCourse(enrolled.getString(j));
                    }

                    // progress
                    JSONObject prog = json.getJSONObject("progress");
                    Iterator<String> keys = prog.keys();

                    while (keys.hasNext()) {
                        String courseId = keys.next();
                        JSONArray lessons = prog.getJSONArray(courseId);

                        for (int j = 0; j < lessons.length(); j++) {
                            s.markLessonCompleted(courseId, lessons.getString(j));
                        }
                    }

                    users.add(s);
                }

                else if (role.equals("Instructor")) {
                    users.add(new Instructor(id, name, email, password));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    // ------------------------- LOAD COURSES -------------------------
    public ArrayList<Course> loadAllCourses() {
        ArrayList<Course> list = new ArrayList<>();

        try {
            JSONArray arr = new JSONArray(new FileReader(COURSES_FILE).readAllBytes());

            for (int i = 0; i < arr.length(); i++) {
                JSONObject json = arr.getJSONObject(i);

                String id = json.getString("courseId");
                String title = json.getString("title");
                String desc = json.getString("description");
                String instructorId = json.getString("instructorId");

                Course c = new Course(id, title, desc, instructorId);

                // lessons
                JSONArray lessonsArr = json.getJSONArray("lessons");
                for (int j = 0; j < lessonsArr.length(); j++) {
                    JSONObject l = lessonsArr.getJSONObject(j);
                    c.addLessonToCourse(new Lesson(
                            l.getString("lessonId"),
                            l.getString("title"),
                            l.getString("content")
                    ));
                }

                // students
                JSONArray st = json.getJSONArray("students");
                for (int j = 0; j < st.length(); j++) {
                    c.addStudent(st.getString(j));
                }

                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public Course getCourseById(String id) {
        for (Course c : loadAllCourses()) {
            if (c.getCourseId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    // ------------------------- UPDATE USER -------------------------
    public void updateUser(Student s) {
        try {
            JSONArray arr = new JSONArray(new FileReader(USERS_FILE).readAllBytes());

            for (int i = 0; i < arr.length(); i++) {
                JSONObject json = arr.getJSONObject(i);

                if (json.getString("id").equals(s.getId())) {

                    // enrolledCourses
                    JSONArray e = new JSONArray();
                    for (String course : s.getEnrolledCourses()) {
                        e.put(course);
                    }
                    json.put("enrolledCourses", e);

                    // progress
                    JSONObject pr = new JSONObject();
                    for (String courseId : s.getProgress().keySet()) {
                        JSONArray lessons = new JSONArray();
                        for (String lid : s.getProgress().get(courseId)) {
                            lessons.put(lid);
                        }
                        pr.put(courseId, lessons);
                    }
                    json.put("progress", pr);
                }
            }

            FileWriter fw = new FileWriter(USERS_FILE);
            fw.write(arr.toString(4));
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ------------------------- UPDATE COURSE -------------------------
    public void updateCourse(Course c) {
        try {
            JSONArray arr = new JSONArray(new FileReader(COURSES_FILE).readAllBytes());

            for (int i = 0; i < arr.length(); i++) {
                JSONObject json = arr.getJSONObject(i);

                if (json.getString("courseId").equals(c.getCourseId())) {

                    JSONArray s = new JSONArray();
                    for (String sid : c.getStudents()) {
                        s.put(sid);
                    }

                    json.put("students", s);
                }
            }

            FileWriter fw = new FileWriter(COURSES_FILE);
            fw.write(arr.toString(4));
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

