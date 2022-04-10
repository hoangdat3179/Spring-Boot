package vn.techmaster.miniproject.service;

import org.springframework.stereotype.Service;
import vn.techmaster.miniproject.exception.BadRequestException;

@Service
public class BmiService {
    public double calculateBmi(double height,double weight) {
        if (height <= 0 || weight <= 0){
            throw new BadRequestException("Chiều cao và cân nặng không hợp lệ");
        }
        return weight / (height * height );
    }
}
