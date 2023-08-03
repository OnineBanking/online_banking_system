package com.dxc.Restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.TransferService.TransferService;

@RestController
@RequestMapping("/api/transfer")
public class TransferController {
    @Autowired
    private TransferService transferService;

    @PostMapping
    public ResponseEntity<String> transfer(@RequestParam String senderAccountNumber,
                                           @RequestParam String receiverAccountNumber,
                                           @RequestParam double amount) {
        try {
            transferService.transfer(senderAccountNumber, receiverAccountNumber, amount);
            return ResponseEntity.ok("Transfer successful!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

