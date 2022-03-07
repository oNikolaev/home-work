package com.sbrf.reboot.repository;

import com.sbrf.reboot.dto.Customer;
import lombok.NonNull;

import java.util.List;

public interface CustomerRepository {

    List<Customer> getAll();

    boolean createCustomer(@NonNull String userName, String eMail);

    Customer getCustomer(@NonNull Long id);

    Customer getCustomer(@NonNull String name, @NonNull String email);

    boolean updateCustomer(@NonNull Long id, @NonNull String name, @NonNull String eMail);

    boolean deleteCustomer(@NonNull Long id);

    boolean existCustomer(@NonNull String name, @NonNull String eMail);

    boolean clearAll();
}
