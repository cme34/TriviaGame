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
    private boolean quizTaken;

    public User() {
        username = "";
        password = "";
        points = 0;
        quizTaken = false;
    }

    public User(String name, String pass, int pts, boolean qTaken) {
        username = name;
        password = pass;
        points = pts;
        quizTaken = qTaken;
    }

    public User(User u) {
        username = u.getUsername();
        password = u.getPassword();
        points = u.getPoints();
        quizTaken = u.isQuizTaken();
    }

    public void setUser(String name, String pass, int pts, boolean qTaken) {
        username = name;
        password = pass;
        points = pts;
        quizTaken = qTaken;
    }

    public void setUsername(String name) {
        username = name;
    }

    public void setPassword(String pass) {
        password = pass;
    }

    public void setPoints(int pts) {
        points = pts;
    }

    public void setQuizTaken(boolean qTaken) { quizTaken = qTaken; }

    public String getUsername() { return username; }

    public String getPassword() {
        return password;
    }

    public int getPoints() {
        return points;
    }

    public boolean isQuizTaken() { return quizTaken; }

    public String toString() { return username + ", " + password + ", " + points + ", " + quizTaken; }

    public boolean equals(Object obj) {
        if (this.getClass() == obj.getClass()) {
            User u = (User) obj;
            return this.getUsername().equals(u.getUsername()) &&
                    this.getPassword().equals(u.getPassword()) &&
                    this.getPoints() == u.getPoints() && this.isQuizTaken() == u.isQuizTaken();
        }
        else
            return false;
    }
}
