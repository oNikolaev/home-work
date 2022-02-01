package com.sbrf.reboot.dto;

import lombok.Data;

@Data
public class Request {
    private String atmNumber;

    public Request() {
    }

    public Request(String atm) {
        this.atmNumber = atm;
    }

    public String getAtmNumber() {
        return atmNumber;
    }
}
