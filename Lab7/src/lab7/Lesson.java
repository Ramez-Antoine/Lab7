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
public class Lesson {

    private String lessonId;
    private String title;
    private String content;
    private ArrayList<String> resources;
    
    public Lesson(String lessonId, String title, String content) {
        this.lessonId = lessonId;
        this.title = title;
        this.content = content;
        this.resources = new ArrayList<>();
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
    
    public Lesson(String lessonId, String title, String content, ArrayList<String> resources) {
        this.lessonId = lessonId;
        this.title = title;
        this.content = content;
        this.resources = resources;
    }
    public void addResource(String resource) {
        if (!resources.contains(resource)) {
            resources.add(resource);
        }
    }
    
    public void removeResource(String resource) {
        resources.remove(resource);
    }

    

}
