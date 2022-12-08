package APPLICATION;

import DB.UserDAO;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;

import static DB.UserDAO.getUsers;

public class User {

    // instantievariabelen
    private static int helpUserNumber = 0;
    private int userNumber;                    // unique key
    private String firstName;
    private String lastName;
    private String name;
    private java.sql.Date dateOfBirth;
    private int age;
    private String email;
    private String phoneNumber;
    private String password;

    // constructor for GUI
    public User(String firstName, String lastName, Date dateOfBirth, String email, String phoneNumber, String password) {
        helpUserNumber = getUsers().size() + 1;
        this.userNumber = helpUserNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.name = firstName + " " + lastName;
        this.dateOfBirth = dateOfBirth;
        LocalDate lDateOfBirth = dateOfBirth.toLocalDate();
        this.age = Period.between(lDateOfBirth, LocalDate.now()).getYears();
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    // constructor for UserDAO
    public User(int userNumber, String firstName, String lastName, java.sql.Date dateOfBirth, int age, String email, String phoneNumber, String password) {
        this.userNumber = userNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
    //empty constructor used in the main

    public User() {
    }

    public static boolean logUserInFromEmail(String email, String password){
        for(User user : getUsers())
            if (email.equals(user.getEmail()) && password.equals(user.getPassword()))
                return true;
        return false;
    }


    // methodes
    /*
    public Event createEmptyEvent (String eventName, LocalDateTime startDate, LocalDateTime endDate, String description, URL linkToPage) {
        Event newEvent = new Event(getUserNumber(), eventName, startDate, endDate, description, linkToPage);
        actualEvents.add(newEvent);
        return newEvent;
    }

     */

    // getters en setters
    public static int getHelpUserNumber() {
        return helpUserNumber;
    }

    public static void setHelpUserNumber(int helpUserNumber) {
        User.helpUserNumber = helpUserNumber;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.sql.Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(java.sql.Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmails(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}