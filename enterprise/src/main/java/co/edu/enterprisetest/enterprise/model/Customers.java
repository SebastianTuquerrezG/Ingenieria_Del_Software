package co.edu.enterprisetest.enterprise.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "customers")
public class Customers {
    @Id
    @Column(name = "customerNumber", nullable = false)
    private int customerNumber;

    @Column(name = "customerName", nullable = false)
    private String customerName;

    @Column(name = "contactLastName", nullable = false)
    private String contactLastName;

    @Column(name = "contactFirstName", nullable = false)
    private String contactFirstName;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "addressLine1", nullable = false)
    private String addressLine1;

    @Column(name = "addressLine2")
    private String addressLine2;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "postalCode")
    private String postalCode;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "salesRepEmployeeNumber")
    private Integer salesRepEmployeeNumber;

    @Column(name = "creditLimit")
    private Double creditLimit;
}
