package org.emailapp.services;

import java.util.List;

import org.emailapp.dto.AccountResponse;
import org.emailapp.dto.CreateAccountRequest;
import org.emailapp.dto.DepartmentResponse;
import org.emailapp.models.Account;
import org.emailapp.models.Department;
import org.emailapp.repositories.AccountRepository;
import org.emailapp.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// @Service tells Spring Boot: "this class contains business logic"
// Spring will create one instance of this class and reuse it everywhere.
// You never write: new AccountService() — Spring does that for you.
@Service
public class AccountService {
    // "final" mean this variable must be assigned once and cannot be changed.
    // it set once in the constructor and never changes after that.
    // This is constructor injection -- the modern recommended way to get dependencies in Spring.
    // Spring will automatically call this constructor and pass in the AccountRepository instance.
    private final AccountRepository accountRepository;
    private final DepartmentRepository departmentRepository;

    // @Autowired tell framework to autoamtically inject (provide) the required dependency (AccountRepository) when creating an instance of AccountService.
    // @Autowired is optional on constructors since Spring 4.3, but it can be included for clarity.
    // Spring sees this constructor and knows to inject the AccountRepository and DepartmentService beans when creating an instance of AccountService.
    @Autowired
    public AccountService(AccountRepository accountRepository, DepartmentRepository departmentRepository) {
        // Two dependencies injected via constructor
        this.accountRepository = accountRepository;
        this.departmentRepository = departmentRepository;
    }

    // -- CREATE ACCOUNT --
    public AccountResponse createAccount(CreateAccountRequest request) {
        // Look up the Department from the database using the ID sent by the frontend
        // orElseThrow(): if not found, throw an error immediately with a clear message
        Department department = departmentRepository.findByAbbreviation(request.departmentAbbreviation())
                .orElseThrow(() -> new IllegalArgumentException("Department not found with abbreviation: " + request.departmentAbbreviation()));

        // Generate email address using the department abbreviation and a unique number (e.g., "sales001", "sales002")
        // getAbbreviation() is a method in the Department entity that returns the department's abbreviation (e.g., "sales", "hr")
        String email = generateUniqueEmail(request.firstName(), request.lastName(), department.getAbbreviation());

        // Create a new Account entity using the data from the request and the found Department
        Account account = new Account(
            request.firstName(),
            request.lastName(),
            department
        );
        account.setEmail(email); // Set the generated email address

        // Save to database. .save() will INSERT a new row and return the saved entity (with generated ID).
        Account saved = accountRepository.save(account);

        // Convert the saved Account entity to an AccountResponse DTO to send back to the frontend
        return toResponse(saved);
    }

    // -- GET ALL ACCOUNTS --
    public List<AccountResponse> getAllAccounts() {
        // .findAll() returns a list of all Account entities from the database
        return accountRepository.findAll().stream() // Convert the list to a stream for functional operations
                .map(this::toResponse) // Convert each Account entity to an AccountResponse DTO
                .toList(); // Collect the results back into a List and return it
    }

    // -- HELPER METHODS --

    // Generate a unique email address based on the first name, last name, and department abbreviation
    // First tries: no number
    // If that exists, then add number suffix
    private String generateUniqueEmail(String firstName, String lastName, String departmentAbbreviation) {
        // build the base email address (e.g., "jon.doe")
        String baseEmail = firstName.toLowerCase() + "." + lastName.toLowerCase();

        // Build domain part using department abbreviation (e.g., "sales.company.com")
        String domain = "@" + departmentAbbreviation.toLowerCase() + ".chrv.com";

        // Start with no number suffix
        String candidate = baseEmail + domain;

        // Check if the candidate email already exists in the database
        int suffix = 1; // Start suffix at 1
        while (accountRepository.existsByEmail(candidate)) {
            // e.g.: "jon.doe1@sales.chrv.com", then "jon.doe2@sales.chrv.com", etc.
            candidate = baseEmail + suffix + domain;
            suffix++;
        }

        // Return the first email address that is not already taken in the database
        return candidate;
    }

    // Convert an Account Entity to an AccountResponse DTO
    private AccountResponse toResponse(Account account) {
        Department dept = account.getDepartment();
        DepartmentResponse departmentResponse = new DepartmentResponse(
            dept.getId(),
            dept.getName(),
            dept.getAbbreviation(),
            dept.getNumberOfEmployees()
        );
        // Response follows the structure defined in AccountResponse record: id, firstName, lastName, email, department, mailboxCapacity, alternativeEmail
        return new AccountResponse(
            account.getId(),
            account.getFirstName(),
            account.getLastName(),
            account.getEmail(),
            departmentResponse,
            account.getMailboxCapacity(),
            account.getAlternativeEmail()
        );
    }
}
