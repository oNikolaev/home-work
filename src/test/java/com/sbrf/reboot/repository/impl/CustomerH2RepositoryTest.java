package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerH2RepositoryTest {

    private static CustomerRepository customerRepository;

    @BeforeAll
    public static void before() {
        customerRepository = new CustomerH2Repository();
        customerRepository.clearAll();
    }

    @Test
    void getAll() {
        boolean created = customerRepository.createCustomer("Tom", "tom@ya.ru");
        List<Customer> all = customerRepository.getAll();
        assertTrue(all.size() != 0);
    }

    @Test
    void createCustomerSuccess() {
        boolean created = customerRepository.createCustomer("Maria", "maria98@ya.ru");
        assertTrue(created);
    }

    @Test
    void createCustomerNotCreated() {
        boolean created = customerRepository.createCustomer("Maria", "maria98@ya.ru");
        assertFalse(created);
    }

    @Test
    void deleteCustomerSuccess() {
        boolean deleted = customerRepository.deleteCustomer(customerRepository.getCustomer("Maria", "maria98@ya.ru").getId());
        assertTrue(deleted);
    }

    @Test
    void deleteCustomerNotDeleted() {
        boolean deleted = customerRepository.deleteCustomer(1000L);
        assertFalse(deleted);
    }

    @Test
    void updateCustomerSuccess() {
        boolean updated = customerRepository.updateCustomer(customerRepository.getCustomer("Maria", "maria98@ya.ru").getId(), "Oleg", "oleg@bk.ru");
        assertTrue(updated);
    }

    @Test
    void updateCustomerNotUpdated() {
        boolean updated = customerRepository.updateCustomer(300L, "Oleg", "oleg1@bk.ru");
        assertFalse(updated);
    }

    @Test
    void getCustomerSuccess() {
        String name = "Maria";
        String eMail = "maria98@ya.ru";
        long id = customerRepository.getCustomer(name, eMail).getId();

        Customer customer = new Customer(id, name, eMail);
        Customer customerRepo = customerRepository.getCustomer(id);

        assertEquals(customer, customerRepo);
    }

    @Test
    void getCustomerNullValue() {
        assertNull(customerRepository.getCustomer(1000L));
    }

}