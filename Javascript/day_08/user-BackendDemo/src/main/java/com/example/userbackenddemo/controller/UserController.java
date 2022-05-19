package com.example.userbackenddemo.controller;

import com.example.userbackenddemo.dto.UserDto;
import com.example.userbackenddemo.model.User;
import com.example.userbackenddemo.request.CreateUserRequest;
import com.example.userbackenddemo.request.UpdatePassWordRequest;
import com.example.userbackenddemo.request.UpdateUserRequest;
import com.example.userbackenddemo.response.FileReturn;
import com.example.userbackenddemo.service.FileService;
import com.example.userbackenddemo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final FileService fileService ;
    @GetMapping("/users")
    public ResponseEntity<?> getUsers(){
        List<UserDto> userDtos = userService.getUsers();
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id){
        UserDto userDtos = userService.getUserById(id);
        return ResponseEntity.ok(userDtos);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/users/search")
    public ResponseEntity<?> searchUser(@RequestParam("name") String name) {
        List<UserDto> userDtos = userService.searchUser(name);
        return ResponseEntity.ok(userDtos);
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request){
        UserDto userDtos = userService.createUser(request);
        return ResponseEntity.ok(userDtos);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> uodateUser(@PathVariable int id, @RequestBody UpdateUserRequest request) {
        UserDto userDtos = userService.updateUser(id,request);
        return ResponseEntity.ok(userDtos);
    }

    @PutMapping("/users/{id}/change-password")
    public ResponseEntity<?> updatePassWord (@PathVariable int id  ,
                                             @RequestBody UpdatePassWordRequest request) {
        userService.updatePassword (id , request) ;
        return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
    }
    @PostMapping("/users/{id}/fogot-password")
    public ResponseEntity<?> fogotPassWord (@PathVariable int id) {
        String password = userService.fogotPassWord(id) ;
        return ResponseEntity.ok(password) ;
    }
    @PostMapping("/users/{id}/upload-file")
    public ResponseEntity<?> uploadFile (@PathVariable int id , @ModelAttribute("file") MultipartFile file) {
        String fileUrl = fileService.uploadFile(id,file) ;
        return ResponseEntity.ok(fileUrl) ;
    }
    @GetMapping("users/files/{id}/{fileName}")
    public ResponseEntity<?> readFile(@PathVariable int id, @PathVariable String fileName){
        byte [] bytes = fileService.readFile(id, fileName);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
    }
    @GetMapping("users/{id}/files")
    public ResponseEntity<?> getFiles(@PathVariable int id, @RequestParam int page){
        FileReturn files = fileService.getFiles(id,page);
        return ResponseEntity.ok(files);
    }

}
