package ru.geekbrains.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    @Query("select c " +
            " from Customer c " +
            "where (c.cardNumber like concat('%', :cardNumber, '%') or :cardNumber is null)")
    List<Customer> findCustomerByFilter(@Param("cardNumber") String cardNumber);
}
