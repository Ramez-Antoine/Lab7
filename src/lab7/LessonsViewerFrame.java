/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LessonsViewerFrame extends JFrame {

    private Student student;
    private String courseId;
    private StudentService service;

    private JList<String> lessonsList;
    private DefaultListModel<String> lessonsModel;
    private JTextArea contentArea;

    public LessonsViewerFrame(Student s, String courseId, StudentService service) {
        this.student = s;
        this.courseId = courseId;
        this.service = service;

        setTitle("Lessons for " + courseId);
        setSize(600, 500);
        setLayout(new BorderLayout());

        lessonsModel = new DefaultListModel<>();
        lessonsList = new JList<>(lessonsModel);
        contentArea = new JTextArea();
        contentArea.setEditable(false);

        add(new JScrollPane(lessonsList), BorderLayout.WEST);
        add(new JScrollPane(contentArea), BorderLayout.CENTER);

        JButton completeBtn = new JButton("Mark as Completed");
        add(completeBtn, BorderLayout.SOUTH);

        loadLessons();

        lessonsList.addListSelectionListener(e -> showLessonContent());
        completeBtn.addActionListener(e -> markCompleted());

        setVisible(true);
    }

    private void loadLessons() {
        lessonsModel.clear();
        ArrayList<Lesson> lessons = service.getLessons(courseId);

        for (Lesson l : lessons) {
            lessonsModel.addElement(l.getLessonId() + " - " + l.getTitle());
        }
    }

    private void showLessonContent() {
        String selected = lessonsList.getSelectedValue();
        if (selected == null) return;

        String lessonId = selected.split(" - ")[0];

        for (Lesson l : service.getLessons(courseId)) {
            if (l.getLessonId().equals(lessonId)) {
                contentArea.setText(l.getContent());
                break;
            }
        }
    }

    private void markCompleted() {
        String selected = lessonsList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Select a lesson.");
            return;
        }

        String lessonId = selected.split(" - ")[0];

        boolean done = service.completeLesson(student, courseId, lessonId);

        if (done) {
            JOptionPane.showMessageDialog(this, "Marked Completed!");
        } else {
            JOptionPane.showMessageDialog(this, "Already Completed!");
        }
    }
}
