package com.dxc.obs.api.dto;

import lombok.Data;

@Data
public class Principal {

    private String emailAddress;

    private String token;

    private String role;

}