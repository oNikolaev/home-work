package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.repository.CustomerRepository;

import java.nio.channels.ConnectionPendingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerH2Repository implements CustomerRepository {

    private final String JDBC_DRIVER = "org.h2.Driver";
    private final String DB_URL = "jdbc:h2:~/test";
    private final String USER = "sa";
    private final String PASS = "";

    public CustomerH2Repository() {
        createTable();
    }

    private Optional<Connection> getConnection() throws SQLException {
        return Optional.ofNullable(DriverManager.getConnection(DB_URL, USER, PASS));
    }

    private void createTable() {
        try (Connection conn = getConnection().orElseThrow(ConnectionPendingException::new)){
            try (PreparedStatement pStmt = conn.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS CUSTOMER" +
                    "(ID INT AUTO_INCREMENT, NAME VARCHAR(255), EMAIL VARCHAR(255), PRIMARY KEY(ID));")) {
                pStmt.executeUpdate();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();

        try (Connection conn = getConnection().orElseThrow(ConnectionPendingException::new)) {
            try (PreparedStatement pStmt = conn.prepareStatement("SELECT ID, NAME, EMAIL FROM CUSTOMER;")) {
                ResultSet rst = pStmt.executeQuery();

                while (rst.next()) {
                    customers.add(new Customer(
                            rst.getLong(1),
                            rst.getString(2),
                            rst.getString(3)
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    @Override
    public boolean clearAll() {
        try (Connection conn = getConnection().orElseThrow(ConnectionPendingException::new)){
            try (PreparedStatement pStmt = conn.prepareStatement(
                    "DELETE FROM CUSTOMERS;")) {
                pStmt.executeUpdate();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean createCustomer(String name, String eMail) {
        if (existCustomer(name, eMail)) {
            return false;
        }

        int rows = 0;

        try (Connection conn = getConnection().orElseThrow(SQLException::new)) {
            try (PreparedStatement pStmt = conn.prepareStatement("INSERT INTO CUSTOMER (NAME, EMAIL) VALUES (?, ?);")) {
                pStmt.setString(1, name);
                pStmt.setString(2, eMail);
                rows = pStmt.executeUpdate();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return (rows > 0);
    }

    @Override
    public Customer getCustomer(Long id) {
        Customer customer = null;

        try (Connection conn= getConnection().orElseThrow(ConnectionPendingException::new)) {
            try (PreparedStatement pStmt = conn.prepareStatement("SELECT ID, NAME, EMAIL FROM CUSTOMER WHERE ID = ?;")) {
                pStmt.setLong(1, id);
                ResultSet rst = pStmt.executeQuery();

                if (rst.next()) {
                    customer = (new Customer(rst.getLong(1),
                            rst.getString(2),
                            rst.getString(3))
                    );
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return customer;
    }

    @Override
    public Customer getCustomer(String name, String eMail) {
        Customer customer = null;

        try (Connection conn= getConnection().orElseThrow(ConnectionPendingException::new)) {
            try (PreparedStatement pStmt = conn.prepareStatement("SELECT ID, NAME, EMAIL FROM CUSTOMER WHERE (NAME = ? AND EMAIL = ?);")) {
                pStmt.setString(1, name);
                pStmt.setString(2, eMail);
                ResultSet rst = pStmt.executeQuery();

                if (rst.next()) {
                    customer = (new Customer(rst.getLong(1),
                            rst.getString(2),
                            rst.getString(3))
                    );
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return customer;
    }

    @Override
    public boolean updateCustomer(Long id, String name, String eMail) {
        int rows = 0;

        try (Connection conn = getConnection().orElseThrow(ConnectionPendingException::new)) {
            try (PreparedStatement pStmt = conn.prepareStatement("UPDATE CUSTOMER SET NAME = ?, EMAIL = ? WHERE ID = ?;")) {
                pStmt.setString(1, name);
                pStmt.setString(2, eMail);
                pStmt.setLong(3, id);

                rows = pStmt.executeUpdate();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return (rows > 0);
    }

    @Override
    public boolean deleteCustomer(Long id) {
        int rows = 0;

        try (Connection conn = getConnection().orElseThrow(ConnectionPendingException::new)) {
            try (PreparedStatement pStmt = conn.prepareStatement("DELETE FROM CUSTOMER WHERE (ID = ?);")) {
                pStmt.setLong(1, id);
                rows = pStmt.executeUpdate();
            }
        }
        catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return (rows > 0);
    }

    @Override
    public boolean existCustomer(String name, String eMail) {
        try (Connection conn = getConnection().orElseThrow(ConnectionPendingException::new)) {
            try (PreparedStatement pStmt = conn.prepareStatement("SELECT ID FROM CUSTOMER WHERE (NAME = ? AND EMAIL = ?);")) {
                pStmt.setString(1, name);
                pStmt.setString(2, eMail);

                ResultSet rst = pStmt.executeQuery();

                return (rst.next());
            }
        }
        catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return false;
    }
}


