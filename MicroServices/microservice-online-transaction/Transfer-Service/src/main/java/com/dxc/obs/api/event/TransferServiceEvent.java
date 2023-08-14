package com.dxc.obs.api.event;

import com.dxc.obs.api.service.impl.TransferServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.math.BigDecimal;


@Getter
@Setter
public class TransferServiceEvent extends ApplicationEvent {
    private Long userId;
    private Long fromAccountNo;
    private String mediumOfTrans;
    private String transType;
    private BigDecimal transAmount;
    private String toAccountNo;
    private String comments;

    /*public TransferServiceEvent(Object source, String skuCode) {
        super(source);
        this.skuCode = skuCode;
    }

    public TransferServiceEvent(String skuCode) {
        super(skuCode);
        this.skuCode = skuCode;
    }

    public TransferServiceEvent(TransferService source, String fromAccountNo, String toAccountNo,BigDecimal transAmount) {
        super(source);
        this.toAccountNo = toAccountNo;
        this.transAmount = transAmount;
    }*/

    public TransferServiceEvent(Long fromAccountNo, String toAccountNo,BigDecimal transAmount) {
        super(fromAccountNo);
        this.fromAccountNo = fromAccountNo;
        this.toAccountNo = toAccountNo;
        this.transAmount = transAmount;
    }

    public TransferServiceEvent(TransferServiceImpl source, Long fromAccountNo, String toAccountNo, BigDecimal transAmount) {
        super(source);
        this.fromAccountNo = fromAccountNo;
        this.toAccountNo = toAccountNo;
        this.transAmount = transAmount;

    }
}
