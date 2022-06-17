package vn.techmaster.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.bank.exception.CommandException;
import vn.techmaster.bank.exception.NotFoundException;
import vn.techmaster.bank.exception.TradeException;
import vn.techmaster.bank.model.*;
import vn.techmaster.bank.repository.AccountRepo;
import vn.techmaster.bank.repository.CommandRepo;
import vn.techmaster.bank.repository.UserRepo;
import vn.techmaster.bank.request.DepositRequest;
import vn.techmaster.bank.request.TransferRequest;
import vn.techmaster.bank.request.WithDrawRequest;

@Service
public class BankService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private CommandRepo commandRepo;

    public AccountInfo deposit(DepositRequest depositRequest) {
        User user = userRepo.findById(depositRequest.getUser_id()).orElseThrow(() -> new NotFoundException("No data!"));
        Account account = accountRepo.findById(depositRequest.getAccount_id()).orElseThrow(() -> new NotFoundException("No data!"));
        if (!account.getUser().getId().equals(depositRequest.getUser_id())) {
            throw new CommandException("Requester is not own of account");
        }
        account.setBalance(account.getBalance() + depositRequest.getAmount());
        Deposit deposit = new Deposit(user, depositRequest.getAmount(), account);
        AccountInfo accountInfo;
        try {
            accountRepo.save(account);
            deposit.setCommandStatus(CommandStatus.SUCESSED);
            commandRepo.save(deposit);
            accountInfo = new AccountInfo(depositRequest.getAccount_id(), account.getBank().getName(), account.getBalance());
        } catch (Exception exception) {
            deposit.setCommandStatus(CommandStatus.FAILED);
            commandRepo.save(deposit);
            var commanEx = new CommandException("Deposit Error");
            commanEx.initCause(exception);
            throw  commanEx;
        }
        return accountInfo;
    }

    public AccountInfo withDraw(WithDrawRequest withDrawRequest) {
        User user = userRepo.findById(withDrawRequest.getUser_id()).orElseThrow(() -> new NotFoundException("No data!"));
        Account account = accountRepo.findById(withDrawRequest.getAccount_id()).orElseThrow(() -> new NotFoundException("No data!"));
        if (!account.getUser().getId().equals(withDrawRequest.getUser_id())) {
            throw new CommandException("Requester is not own of account");
        }
        account.setBalance(account.getBalance() - withDrawRequest.getAmount());
        WithDraw withDraw = new WithDraw(user, withDrawRequest.getAmount(), account);
        AccountInfo accountInfo;
        try {
            accountRepo.save(account);
            withDraw.setCommandStatus(CommandStatus.SUCESSED);
            commandRepo.save(withDraw);
            accountInfo = new AccountInfo(withDrawRequest.getAccount_id(), account.getBank().getName(), account.getBalance());
        } catch (Exception exception) {
            withDraw.setCommandStatus(CommandStatus.FAILED);
            commandRepo.save(withDraw);
            var commanEx = new CommandException("Deposit Error");
            commanEx.initCause(exception);
            throw  commanEx;
        }
        return accountInfo;
    }

    public TransferInfo tranfer(TransferRequest transferRequest) {
        User user = userRepo.findById(transferRequest.getUser_id()).orElseThrow(() -> new NotFoundException("No data!"));
        Account accountSend = accountRepo.findById(transferRequest.getAccount_send()).orElseThrow(() -> new NotFoundException("No data!"));
        Account accountRecv = accountRepo.findById(transferRequest.getAccount_recv()).orElseThrow(() -> new NotFoundException("No data!"));
        if (!accountSend.getUser().getId().equals(transferRequest.getUser_id())) {
            throw new CommandException("Requester is not own of account");
        }
        if(transferRequest.getAmount() > accountSend.getBalance()){
            throw new TradeException("Số dư của bạn không đủ");
        }
        if(transferRequest.getAmount() <= 0){
            throw new TradeException("Số tiền chuyển phải lớn hơn 0");
        }
        accountSend.setBalance(accountSend.getBalance() - transferRequest.getAmount());
        accountRecv.setBalance(accountRecv.getBalance() + transferRequest.getAmount());
        Transact transact = new Transact(user,accountSend,accountRecv,transferRequest.getAmount());
        TransferInfo accountInfo;
        try {
            accountRepo.save(accountSend);
            accountRepo.save(accountRecv);
            transact.setCommandStatus(CommandStatus.SUCESSED);
            commandRepo.save(transact);
            accountInfo = new TransferInfo("Chuyển tiền thành công" , accountSend.getBalance());
        } catch (Exception exception) {
            transact.setCommandStatus(CommandStatus.FAILED);
            commandRepo.save(transact);
            var commanEx = new CommandException("Transfer Error");
            commanEx.initCause(exception);
            throw  commanEx;
        }
        return accountInfo;
    }
}
