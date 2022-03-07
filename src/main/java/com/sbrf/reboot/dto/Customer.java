package com.sbrf.reboot.dto;

import lombok.Data;

@Data
public class Customer {
    private Long id;
    private String name;
    private String eMail;

    public Customer(long id, String name, String eMail) {
        this.id = id;
        this.name = name;
        this.eMail = eMail;
    }
}
