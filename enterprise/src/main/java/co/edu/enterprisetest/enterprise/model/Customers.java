package co.edu.enterprisetest.enterprise.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Entity
@Table(name = "customers")
public class Customers {
    @Id
    @Column(name = "customerNumber", nullable = false)
    @NotNull
    private int customerNumber;

    @Column(name = "customerName", nullable = false)
    @NotNull
    private String customerName;

    @Column(name = "contactLastName", nullable = false)
    @NotNull
    private String contactLastName;

    @Column(name = "contactFirstName", nullable = false)
    @NotNull
    private String contactFirstName;

    @Column(name = "phone", nullable = false)
    @NotNull
    private String phone;

    @Column(name = "addressLine1", nullable = false)
    @NotNull
    private String addressLine1;

    @Column(name = "addressLine2")
    private String addressLine2;

    @Column(name = "city", nullable = false)
    @NotNull
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "postalCode")
    private String postalCode;

    @Column(name = "country", nullable = false)
    @NotNull
    private String country;

    @Column(name = "salesRepEmployeeNumber")
    private Integer salesRepEmployeeNumber;

    @Column(name = "creditLimit")
    private Double creditLimit;
}
