/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7;

/**
 *
 * @author 20114
 */
public class Student extends User{

    public Student(String id, String name, String email, String passwordHash) {
        super(id, name, email, passwordHash, "Student");
    }
}
