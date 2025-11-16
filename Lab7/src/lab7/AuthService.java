    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7;

import java.util.ArrayList;

/**
 *
 * @author 20114
 */

public class AuthService {
    private ArrayList<User> users;
    
    public AuthService()
    {
        users = JsonDatabaseManager.loadAllUsers();
    }
    
    public User login(String email, String password)
    {
        String hashed = HashUtil.hashPassword(password);
        
        for(User u : users)
        {
            if(u.getEmail().equalsIgnoreCase(email) && u.getPasswordHash().equals(hashed))
                return u;
        }
        return null;
    }
    
    public boolean signup(String id, String name, String email, String password, String role)
    {
        if (idExists(id)) return false;

        if (emailExists(email)) return false;
        
        String hashed = HashUtil.hashPassword(password);
        
        User newUser;
        if(role.equalsIgnoreCase("student"))
        {
            newUser = new Student(id, name, email, hashed);
        }
        else
        {
            newUser = new Instructor(id, name, email, hashed);
        }
        
        users.add(newUser);
        
        JsonDatabase.saveUsers(users);
        
        return true;
    }
    
    private boolean emailExists(String email)
    {
        for(User u : users)
        {
            if(u.getEmail().equalsIgnoreCase(email))
            {
                return true;
            }
        }
        return false;
    }

    private boolean idExists(String id)
    {
        for(User u : users)
        {
            if(u.getId().equals(id))
            {
                return true;
            }
        }
        return false;
    }



}
