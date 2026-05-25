package emailapp;

import java.util.Scanner;

public class Email {
    private String firstName;
    private String lastName;
    private String password;
    private String department;
    private String email;
    private int mailboxCapacity = 500;
    private int defaultPasswordLength = 10;
    private String alternativeEmail;
    private String companySuffix = "chrv.com";

    // Constructor to receive the first name and last name
    public Email(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        System.out.println("Email created: " + this.firstName + " " + this.lastName);

        // Call a method asking for the department - return the department
        department = setDepartment();
        System.out.println("Department: " + department);

        // Call method that return a random password
        password = setRandomPassword(defaultPasswordLength);
        System.out.println("Password: " + password);

        // Combine elements to generate email
        email = setGenerateEmail();
    }

    // Ask for the department
    private String setDepartment() {
        System.out.println("\nDEPARTMENT CODE\n1 for sales\n2 for development\n3 for accounting");
        System.out.println("Enter your department code: ");
        Scanner input = new Scanner(System.in);
        int departChoice = input.nextInt();
        if (departChoice == 1) return "sale";
        else if (departChoice == 2) return "devs";
        else if (departChoice == 3) return "acct";
        else return "";
    }

    // Generate a random password
    private String setRandomPassword(int length) {
        String passwordSet = "QWERTYUIOPASDFGHJKLZXCVBNM!@#$%";
        char[] password = new char[length];
        for(int i = 0; i < length; i++) {
            int random = (int) (Math.random() * passwordSet.length());
            password[i] = passwordSet.charAt(random);
        }
        return new String(password);
    }

    // Set generated email
    private String setGenerateEmail() {
        return firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + department + "." + companySuffix;
    }

    // Set the mailbox capacity
    public void setMailboxCapacity(int capacity) {
        this.mailboxCapacity = capacity;
    }

    // Set the alternate email
    public void setAlternativeEmail(String altEmail) {
        this.alternativeEmail = altEmail;
    }

    // Change the password
    public void changePassword(String password) {
        this.password = password;
    }

    // GETTERS
    // Get mailbox capacity
    public int getMailboxCapacity() {
        return mailboxCapacity;
    }

    // get alternative email
    public String getAlternativeEmail() {
        return alternativeEmail;
    }

    // get password
    public String getPassword() {
        return password;
    }

    // show info
    public String showInfo() {
        return "DISPLAY NAME: " + firstName + " " + lastName +
                "\nCOMPANY EMAIL: " + email +
                "\nDEPARMENT: " + department +
                "\nMAILBOX CAPACITY: " + mailboxCapacity;
    }
}