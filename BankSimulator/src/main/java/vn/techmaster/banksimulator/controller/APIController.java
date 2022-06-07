package vn.techmaster.banksimulator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.banksimulator.exception.NotFoundException;
import vn.techmaster.banksimulator.model.Account;
import vn.techmaster.banksimulator.model.User;
import vn.techmaster.banksimulator.repository.AccountRepository;
import vn.techmaster.banksimulator.repository.UserRepository;
import vn.techmaster.banksimulator.service.TransferService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v3")
public class APIController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransferService transferService;

    @GetMapping("user/{id}")
    @Operation(summary = "Find User By ID")
    public ResponseEntity<?> userById(@Parameter(description = "id") @PathVariable(name = "id") String id){
        Optional<User> userOptional = userRepository.findById(id);
        User user = new User();
        if(userOptional.isPresent()){
            user = userOptional.get();
        }
        else {
            throw new NotFoundException("User not found");
        }
        return  ResponseEntity.ok(user);
    }

    @GetMapping("account/{id}")
    @Operation(summary = "Find Account By ID")
    public ResponseEntity<?> accountById(@Parameter(description = "id") @PathVariable(name = "id") String id){
        Optional<Account> accountOptional = accountRepository.findById(id);
        Account account = new Account();
        if(accountOptional.isPresent()){
            account = accountOptional.get();
        }
        else {
            throw new NotFoundException("Account not found");
        }
        String user_id = account.getUser().getId();

        return  ResponseEntity.ok(account);
    }

    @Operation(summary = "Find Account By UserId")
    @GetMapping("accountByUserId/{id}")
    public ResponseEntity<?> accountByUserId(@Parameter(description = "id_user") @PathVariable(name = "id") String id){
        Optional<User> userOptional = userRepository.findById(id);
        User user = new User();
        if(userOptional.isPresent()){
            user = userOptional.get();
        }
        else {
            throw new NotFoundException("Account not found");
        }
        List<Account> accountList = accountRepository.findAllByUser(user);
        return  ResponseEntity.ok(accountList);
    }

    @Operation(summary = "Transfer Money")
    @GetMapping("tranfer")
    public ResponseEntity<?> tranfer(@Parameter(description = "id_A") @RequestParam String accountA , @Parameter(description = "id_B") @RequestParam String accountB, @Parameter(description = "Tiền gửi")  @RequestParam long amount){
        transferService.transfer(accountA,accountB,amount);
        return  ResponseEntity.ok("Chuyển thành công số tiền: " + amount);
    }

}
