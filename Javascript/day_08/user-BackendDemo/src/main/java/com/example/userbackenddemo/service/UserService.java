package com.example.userbackenddemo.service;

import com.example.userbackenddemo.Utils.Utils;
import com.example.userbackenddemo.dto.UserDto;
import com.example.userbackenddemo.exception.BadRequestException;
import com.example.userbackenddemo.exception.NotFoundException;
import com.example.userbackenddemo.mapper.UserMapper;
import com.example.userbackenddemo.model.User;
import com.example.userbackenddemo.request.CreateUserRequest;
import com.example.userbackenddemo.request.UpdatePassWordRequest;
import com.example.userbackenddemo.request.UpdateUserRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service

public class UserService {
    private EmailService emailService;
    private List<User> users;

    public UserService() {
        init();
    }

    public void init() {
        users = new ArrayList<>();
        users.add(new User(1, "Nguyễn Văn A", "NVA@gmail.com", "0123456789", "Tình Thái Bình", null, "111"));
        users.add(new User(2, "Nguyễn Văn B", "NVB@gmail.com", "0123456789", "Tình Nam Định", null, "222"));
        users.add(new User(3, "Nguyễn Văn C", "NVC@gmail.com", "0123456789", "Tình Hưng Yên", null, "333"));
    }

    public List<UserDto> getUsers() {
        return users.stream()
                .map(user -> UserMapper.toUserDto(user))
                .collect(Collectors.toList());
    }

    public UserDto getUserById(int id) {
        Optional<User> userOptional = findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            return UserMapper.toUserDto(user);
        }
        throw new NotFoundException("User with id = " + id + " Not Found");
    }

    public void deleteUser(int id) {
        Optional<User> userOptional = findById(id);
        if(userOptional.isEmpty()){
            throw new NotFoundException("User with id " + id + " Not found");
        }
        users.removeIf(user -> user.getId() == id);
    }
    public List<UserDto> searchUser(String name) {
        return users.stream()
                .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                .map(user -> UserMapper.toUserDto(user))
                .collect(Collectors.toList());
    }


    public Optional<User> findById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    public Optional<User> findByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }

    public UserDto createUser(CreateUserRequest request) {
        if(findByEmail(request.getEmail()).isPresent()){
            throw new BadRequestException("email = " + request.getEmail() + " is existed");
        }

        Random rd = new Random();
        User user = new User();
        user.setId(rd.nextInt(100));
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setPassword(request.getPassword());

        users.add(user);

        return UserMapper.toUserDto(user);
    }

    public UserDto updateUser(int id, UpdateUserRequest request) {
        Optional<User> userOptional = findById(id);
        if(userOptional.isEmpty()){
            throw new NotFoundException("User with id " + id + " Not found");
        }

        User user = userOptional.get();
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());

        return UserMapper.toUserDto(user);
    }

    public UserDto updatePassword(int id, UpdatePassWordRequest request) {
        // kiểm tra có tồn tại hay không

        Optional<User> userOptional = findById(id);
        if(userOptional.isEmpty()){
            throw new NotFoundException("User with id " + id + " Not found");
        }
        // Kiểm tra oldPassWord có đúng không

        User user = userOptional.get();
        if(!user.getPassword().equals(request.getOldPassWord())) {
            throw new BadRequestException(" Mật khẩu cũ Không chính xác");

        }
        // Kiểm tra oldPassWord == newPassWord không
        if(request.getOldPassWord().equals(request.getNewPassWord())) {
            throw new BadRequestException("Mật khẩu cũ không được giống mật khẩu mới") ;
        }
        // Cập nhật newPassWord cho user tương ứng
        user.setPassword(request.getNewPassWord());

        return UserMapper.toUserDto(user);
    }

    public String fogotPassWord(int id) {
        // kiểm tra user id có hay ko
        Optional<User> userOptional = findById(id);
        if(userOptional.isEmpty()){
            throw new NotFoundException("User with id " + id + " Not found");
        }
        // Random chuỗi password mới cho user
        String newPass = Utils.generatePassWord(3);

        //lấy thông tin của user và đặt lại password mới cho user
        User user = userOptional.get();
        user.setPassword(newPass);

        // Send mail
        emailService.sendMail(user.getEmail(),"Đổi mật khẩu", "Mật khẩu mới của bạn là : " + newPass);

        // Trả về thông tin password mới
        return newPass;
    }

}
