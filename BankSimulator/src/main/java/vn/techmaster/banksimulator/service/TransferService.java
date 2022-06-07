package vn.techmaster.banksimulator.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.banksimulator.exception.NotFoundException;
import vn.techmaster.banksimulator.exception.TransferException;
import vn.techmaster.banksimulator.model.Account;
import vn.techmaster.banksimulator.repository.AccountRepository;

import java.util.Optional;

@Service
public class TransferService {
    @Autowired
    private AccountRepository accountRepository;


    public void transfer(String accountSendId,String accountReceiveid,long amount){
        Optional<Account> accountSendOptional = accountRepository.findById(accountSendId);
        Account accountSend = new Account();
        if(accountSendOptional.isPresent()){
            accountSend  = accountSendOptional.get();
        }
        else {
            throw new NotFoundException("Account with id = " + accountSendId + " not found");
        }
        Account accountReceive = new Account();
        Optional<Account> accountRecvOptional = accountRepository.findById(accountReceiveid);
        if(accountRecvOptional.isPresent()){
            accountReceive = accountRecvOptional.get();
        }
        else {
            throw new NotFoundException("Account with id = " + accountReceiveid + " not found");
        }

        long monneyAccountSend = accountSend.getBalance();
        long monneyAccountRecv = accountReceive.getBalance();

        if(monneyAccountSend < 0){
            throw new TransferException("Số tiền phải lớn hơn 0");
        }
        if(monneyAccountSend < amount){
            throw new TransferException("Số dư của bạn không đủ thực hiện được giao dịch");
        }
        if(amount < 0){
            throw new TransferException("Số tiền gửi phải lớn 0");
        }
        accountSend.setBalance(monneyAccountSend - amount);
        accountReceive.setBalance(monneyAccountRecv + amount);
        accountRepository.save(accountSend);
        accountRepository.save(accountReceive);
    }
}
