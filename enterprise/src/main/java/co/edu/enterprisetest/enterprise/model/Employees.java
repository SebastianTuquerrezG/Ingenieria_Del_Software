package co.edu.enterprisetest.enterprise.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "employees")
public class Employees {
    @Id
    @Column(name = "employeeNumber", nullable = false)
    private int employeeNumber;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "extension", nullable = false)
    private String extension;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "officeCode", nullable = false)
    private String officeCode;

    @Column(name = "reportsTo")
    private int reportsTo;

    @Column(name = "jobTitle", nullable = false)
    private String jobTitle;
}
