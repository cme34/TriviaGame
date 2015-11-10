package pitt.triviagame;

/**
 * Created by Cory on 11/5/2015.
 * A data structure that holds a user's username, password, and points
 */
public class User {
    /** This is the User data for the person logged into their client */
    public static User loggedInUser;

    private String username, password;
    private int points;

    //Leonard Aronson code review: You may want to beware that code passed from appengine could come in as a json 
    //file so you may need to access this information in a different way when reading in a user from the database.
    public User() {
        username = "";
        password = "";
        points = 0;
    }

    public User(String name, String pass, int pts) {
        username = name.intern();
        password = pass.intern();
        points = pts;
    }

    public User(User u) {
        username = u.getUsername().intern();
        password = u.getPassword().intern();
        points = u.getPoints();
    }

    public void setUser(String name, String pass, int pts) {
        username = name.intern();
        password = pass.intern();
        points = pts;
    }

    public void setUsername(String name) {
        username = name.intern();
    }

    public void setPassword(String pass) {
        password = pass.intern();
    }

    public void setPoints(int pts) {
        points = pts;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPoints() {
        return points;
    }

    public String toString() {
        return username + ", " + password + ", " + points;
    }

    public boolean equals(Object obj) {
        if (this.getClass() == obj.getClass()) {
            User u = (User) obj;
            return this.getUsername().intern().equals(u.getUsername().intern()) &&
                    this.getPassword().intern().equals(u.getPassword().intern()) && this.getPoints() == u.getPoints();
        }
        else
            return false;
    }
}
