package APPLICATION;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class User {

    // instantievariabelen
    private static int helpUserNumber = 0;
    private int userNumber;                    // unique key
    private String firstName;
    private String lastName;
    private String name;
    private Date dateOfBirth;
    private int age;                            // derived from dateOfBirth
    private String email;           // multiple emails
    private String phoneNumber;     // multiple phoneNumbers

    // constructor for GUI
    public User(String firstName, String lastName, Date dateOfBirth, String email, String phoneNumber) {
        this.userNumber = helpUserNumber++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.name = firstName + " " + lastName;
        this.dateOfBirth = dateOfBirth;
        Instant instant = dateOfBirth.toInstant();
        LocalDate localDateOfBirth = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        this.age = Period.between(localDateOfBirth, LocalDate.now()).getYears();
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // constructor for UserDAO
    public User(int userNumber, String firstName, String lastName, Date dateOfBirth, int age, String email, String phoneNumber) {
        this.userNumber = userNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
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

}