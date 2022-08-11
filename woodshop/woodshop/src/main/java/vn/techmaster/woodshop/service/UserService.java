package vn.techmaster.woodshop.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import vn.techmaster.woodshop.dto.UserRegistrationDto;
import vn.techmaster.woodshop.entity.User;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
