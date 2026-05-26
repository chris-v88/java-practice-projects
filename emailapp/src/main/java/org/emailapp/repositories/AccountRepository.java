package org.emailapp.repositories;

import org.emailapp.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// JpaRepository<Account, Long> means:
//   - This repository manages Account objects
//   - The primary key (id) is of type Long
//
// By extending JpaRepository, you AUTOMATICALLY get these methods for FREE:
//   save(entity)         → INSERT or UPDATE a row
//   findById(id)         → SELECT WHERE id = ?
//   findAll()            → SELECT * FROM accounts
//   deleteById(id)       → DELETE WHERE id = ?
//   count()              → SELECT COUNT(*)
//   existsById(id)       → returns true/false

// @Repository annotation is optional when extending JpaRepository, but it can be used for clarity and to enable component scanning.
// @Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // You can also define custom query methods here if needed, for example:
    // List<Account> findByDepartment(String department);

    // You can declare extra queries just by naming the method correctly.
    // Spring reads the method name and generates the SQL automatically.
    // This one generates: SELECT * FROM email_accounts WHERE email_address = ?
    boolean existsByEmail(String email);
    // The method existsByEmail belongs in the repository because it directly queries the database to check if an email address exists
    // The repository’s job is to handle all data access logic, including queries like this.
    // The service layer is where you decide when and why to call existsByEmail.
    // The service uses the repository to enforce business rules (like “don’t allow duplicate emails”)

    // JpaRepository (and Spring Data JPA) uses a feature called "query method naming." 
    // It reads the method name you declare in your repository interface and automatically generates the correct SQL query based on your entity’s fields.
    boolean existsByAlternativeEmail(String alternativeEmail);
    // Spring Data JPA will automatically generate the query: 
    // SELECT COUNT(*) > 0 FROM accounts WHERE alternative_email = ?
    // You can use this pattern for any field in your entity—just follow the naming convention:
    // existsBy<FieldName>(Type fieldValue)
    // Spring matches the method name to the entity’s field and builds the query for you.
}