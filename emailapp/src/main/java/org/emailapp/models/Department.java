package org.emailapp.models;

import jakarta.persistence.*;
import jakarta.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String abbreviation;

    // This is the "one" side of the One-to-Many relationship.
    //
    // mappedBy = "department" means:
    //   "the Account class owns this relationship — look at its field named 'department'"
    //
    // cascade = CascadeType.ALL means:
    //   if you delete a Department, all its Accounts are deleted too
    //
    // fetch = FetchType.LAZY means:
    //   don't load the accounts list from the database unless we actually ask for it
    //   (better for performance — you don't always need all accounts)
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Account> accounts = new ArrayList<>();

    // JPA
    public Department() {}

    // Constructor to receive the department name and abbreviation
    public Department(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    // --- GETTERS ---
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    // This method DERIVES the employee count from the actual list size.
    // No separate column — always accurate, never out of sync.
    public int getNumberOfEmployees() {
        return accounts.size();
    }

    // --- SETTERS ---
    public void setName(String name) {
        this.name = name;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
