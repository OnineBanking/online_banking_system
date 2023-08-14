package com.obs.notification.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferServiceEvent {
    private Long userId;

    private Long fromAccountNo;

    private String mediumOfTrans;

    private String transType;

    private BigDecimal transAmount;

    private String toAccountNo;

    private String comments;
}
