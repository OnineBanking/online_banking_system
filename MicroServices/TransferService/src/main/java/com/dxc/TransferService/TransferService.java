package com.dxc.TransferService;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dxc.Entity.BankAccount;
import com.dxc.Entity.Transaction;
import com.dxc.Repository.BankAccountRepository;
import com.dxc.Repository.TransactionRepository;

@Service
public class TransferService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public void transfer(String senderAccountNumber, String receiverAccountNumber, double amount) {
        BankAccount senderAccount = bankAccountRepository.findByAccountNumber(senderAccountNumber);
//        BankAccount receiverAccount = bankAccountRepository.findByAccountNumber(receiverAccountNumber);

//        if (senderAccount != null && receiverAccount != null) {
        if (senderAccount != null) {
            if (senderAccount.getBalance() >= amount) {
                senderAccount.setBalance(senderAccount.getBalance() - amount);
//                receiverAccount.setBalance(receiverAccount.getBalance() + amount);

                
//                bankAccountRepository.save(receiverAccount);

                Transaction transaction = new Transaction();
                transaction.setSenderAccount(senderAccount.getAccountNumber());
//                transaction.setReceiverAccount(@receiverAccount.getAccountNumber());
                transaction.setReceiverAccount(receiverAccountNumber);
                transaction.setAmount(amount);
                transaction.setTimestamp(LocalDateTime.now());

                transactionRepository.save(transaction);
                bankAccountRepository.save(senderAccount);
            } else {
                throw new RuntimeException("Insufficient balance in the sender's account.");
            }
        } else {
            throw new RuntimeException("Invalid account numbers.");
        }
    }
}
