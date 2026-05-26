package org.emailapp.models;

import jakarta.persistence.*;

// This @Entity annotation tells Spring Boot: "make a database table for thsi class"
// The table will name "accounts" because of the @Table annotation. 
// If we didn't specify the table name, Spring Boot would have named it "account" by default.
@Entity
@Table(name = "accounts")
public class Account {

    // This @Id annotation tells Spring Boot: "make this field the primary key"
    // The @GeneratedValue annotation tells Spring Boot: "automatically generate a value for this field when a new record is created"
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column annotation nullable = false means this column cannot be null in the database
    // This means that when we create a new Account, we must provide values for these fields, otherwise we will get an error.
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = true, unique = true)
    private String email;

    @Column(nullable = true)
    private String password;

    // PREVIOUS
    // @Column(nullable = false)
    // private String department;

    // NEW
    // This is the "many" side of the relationship
    //
    // @ManyToOne means: "many accounts can belong to one department"
    //
    // @JoinColumn(name = "department_id") means:
    //   create a column in the accounts table named "department_id"
    //   that holds the foreign key pointing to the departments table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(nullable = false)
    private int mailboxCapacity = 500;

    @Column(nullable = true, unique = true)
    private String alternativeEmail;

    // Default constructor is required by JPA
    // This constructor is needed by JPA to create instances of the Account class when it retrieves data from the database. 
    // JPA requires a no-argument constructor to instantiate the entity class and populate its fields with data from the database.
    public Account() {}

    // Constructor to receive the first name and last name
    public Account(String firstName, String lastName, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }

    // --- GETTERS ---
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDepartment() {
        return department;
    }

    public int getMailboxCapacity() {
        return mailboxCapacity;
    }

    public String getAlternativeEmail() {
        return alternativeEmail;
    }

    // --- SETTERS ---
    // --- These are suppposed tobe in service layer ---
    // Set the department
    // private String setDepartment(int departChoice) {
    //     if (departChoice == 1) return "sale";
    //     else if (departChoice == 2) return "devs";
    //     else if (departChoice == 3) return "acct";
    //     else return "";
    // }

    // Generate a random password
    // private String setRandomPassword(int length) {
    //     String passwordSet = "QWERTYUIOPASDFGHJKLZXCVBNM!@#$%";
    //     char[] password = new char[length];
    //     for(int i = 0; i < length; i++) {
    //         int random = (int) (Math.random() * passwordSet.length());
    //         password[i] = passwordSet.charAt(random);
    //     }
    //     return new String(password);
    // }

    // Set generated email
    // private String setGenerateEmail() {
    //     return firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + department + "." + "company.com";
    // }
    // --- These are suppposed tobe in service layer ---

    // Set the mailbox capacity
    public void setMailboxCapacity(int capacity) {
        this.mailboxCapacity = capacity;
    }

    // Set the alternate email
    public void setAlternativeEmail(String altEmail) {
        this.alternativeEmail = altEmail;
    }

    // Set Deparmtnet for the account
    public void setDepartment(Department department) {
        this.department = department;
    }
}