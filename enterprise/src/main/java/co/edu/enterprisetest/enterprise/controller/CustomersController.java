package co.edu.enterprisetest.enterprise.controller;

import co.edu.enterprisetest.enterprise.model.Customers;
import co.edu.enterprisetest.enterprise.repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Customers createCustomer(@RequestBody Customers customer) {
        return customersRepository.save(customer);
    }

    @PutMapping("/{id}")
    public Customers updateCustomer(@PathVariable int id, @RequestBody Customers customer) {
        if (customersRepository.existsById(String.valueOf(id))) {
            customer.setCustomerNumber(id);
            return customersRepository.save(customer);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable int id) {
        customersRepository.deleteById(String.valueOf(id));
    }
}
