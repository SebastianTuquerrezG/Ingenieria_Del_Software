package co.edu.enterprisetest.enterprise.repository;

import co.edu.enterprisetest.enterprise.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<Customers, String>{

}
