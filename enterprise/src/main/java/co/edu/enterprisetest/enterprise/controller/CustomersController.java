package co.edu.enterprisetest.enterprise.controller;

import co.edu.enterprisetest.enterprise.model.Customers;
import co.edu.enterprisetest.enterprise.repository.CustomersRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;;

@RestController
@RequestMapping("/customers")
public class CustomersController {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomersRepository customersRepository;

    @GetMapping
    public List<Customers> getCustomers() {
        List<Customers> customers = new ArrayList<>();

        try(Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");

            while (resultSet.next()) {
                Customers customer = new Customers();
                customer.setCustomerNumber(resultSet.getInt("customerNumber"));
                customer.setCustomerName(resultSet.getString("customerName"));
                customer.setContactLastName(resultSet.getString("contactLastName"));
                customer.setContactFirstName(resultSet.getString("contactFirstName"));
                customer.setPhone(resultSet.getString("phone"));
                customer.setAddressLine1(resultSet.getString("addressLine1"));
                customer.setAddressLine2(resultSet.getString("addressLine2"));
                customer.setCity(resultSet.getString("city"));
                customer.setState(resultSet.getString("state"));
                customer.setPostalCode(resultSet.getString("postalCode"));
                customer.setCountry(resultSet.getString("country"));
                customer.setSalesRepEmployeeNumber(resultSet.getInt("salesRepEmployeeNumber"));
                customer.setCreditLimit(resultSet.getDouble("creditLimit"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    @GetMapping("/{id}")
    public Customers getCustomer(@PathVariable int id) {
        Customers customer = new Customers();

        try(Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE customerNumber = ?")) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()) {
                        customer.setCustomerNumber(resultSet.getInt("customerNumber"));
                        customer.setCustomerName(resultSet.getString("customerName"));
                        customer.setContactLastName(resultSet.getString("contactLastName"));
                        customer.setContactFirstName(resultSet.getString("contactFirstName"));
                        customer.setPhone(resultSet.getString("phone"));
                        customer.setAddressLine1(resultSet.getString("addressLine1"));
                        customer.setAddressLine2(resultSet.getString("addressLine2"));
                        customer.setCity(resultSet.getString("city"));
                        customer.setState(resultSet.getString("state"));
                        customer.setPostalCode(resultSet.getString("postalCode"));
                        customer.setCountry(resultSet.getString("country"));
                        customer.setSalesRepEmployeeNumber(resultSet.getInt("salesRepEmployeeNumber"));
                        customer.setCreditLimit(resultSet.getDouble("creditLimit"));
                    } else {
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @PostMapping
    public String createCustomer(@RequestBody @NotNull Customers customer) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, customer.getCustomerNumber());
                preparedStatement.setString(2, customer.getCustomerName());
                preparedStatement.setString(3, customer.getContactLastName());
                preparedStatement.setString(4, customer.getContactFirstName());
                preparedStatement.setString(5, customer.getPhone());
                preparedStatement.setString(6, customer.getAddressLine1());
                preparedStatement.setString(7, customer.getAddressLine2());
                preparedStatement.setString(8, customer.getCity());
                preparedStatement.setString(9, customer.getState());
                preparedStatement.setString(10, customer.getPostalCode());
                preparedStatement.setString(11, customer.getCountry());
                preparedStatement.setInt(12, customer.getSalesRepEmployeeNumber());
                preparedStatement.setDouble(13, customer.getCreditLimit());

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    return "Customer created successfully.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Failed to create customer.";
    }

    @PutMapping("/{id}")
    public String updateCustomer(@PathVariable int id, @RequestBody @NotNull Customers customer) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "UPDATE customers " +
                    "SET customerNumber = ?, customerName = ?, contactLastName = ?, contactFirstName = ?, phone = ?, addressLine1 = ?, " +
                    "addressLine2 = ?, city = ?, state = ?, postalCode = ?, country = ?, salesRepEmployeeNumber = ?, creditLimit = ? " +
                    "WHERE customerNumber = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, customer.getCustomerNumber());
                preparedStatement.setString(2, customer.getCustomerName());
                preparedStatement.setString(3, customer.getContactLastName());
                preparedStatement.setString(4, customer.getContactFirstName());
                preparedStatement.setString(5, customer.getPhone());
                preparedStatement.setString(6, customer.getAddressLine1());
                preparedStatement.setString(7, customer.getAddressLine2());
                preparedStatement.setString(8, customer.getCity());
                preparedStatement.setString(9, customer.getState());
                preparedStatement.setString(10, customer.getPostalCode());
                preparedStatement.setString(11, customer.getCountry());
                preparedStatement.setInt(12, customer.getSalesRepEmployeeNumber());
                preparedStatement.setDouble(13, customer.getCreditLimit());
                preparedStatement.setInt(14, id);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    return "Customer updated successfully.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Failed to update customer.";
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable int id) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "DELETE FROM customers WHERE customerNumber = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    return "Customer deleted successfully.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Failed to delete customer.";
    }
}
