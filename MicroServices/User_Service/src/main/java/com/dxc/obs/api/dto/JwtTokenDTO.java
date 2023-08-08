package com.dxc.obs.api.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtTokenDTO {

    private String subject;

    private Date expirationDate;

}
