package com.sbrf.reboot.dto;

import lombok.Data;

@Data
public class Response extends Request{
    private String statusCode;

    public Response(){

    }

    public Response(String statusCode){
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
