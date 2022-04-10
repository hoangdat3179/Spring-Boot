package vn.techmaster.miniproject.service;

import org.springframework.stereotype.Service;
import vn.techmaster.miniproject.exception.BadRequestException;

import java.util.Random;

@Service
public class ColorService {
    public String randomColor(int type) {
        return switch (type) {
            case 1 -> randomColor();
            case 2 -> randomHexColor();
            case 3 -> randomRgbColor();
            default -> throw new BadRequestException("Không hợp lệ");
        };
    }

    public String randomColor() {
        // Tạo 1 mảng color dạng chữ
        Random rd = new Random();
        String[] colorsName = {"red", "blue", "green", "black", "white", "pink"};
        return colorsName[rd.nextInt(colorsName.length)];
    }

    public String randomHexColor() {
        // Tạo đối tượng random
        Random rd = new Random();
        // Tạo mảng ký tự
        String[] characters = {"a","b","c","d","e","f","0","1","2","3","4","5","6","7","8","9"};
        // Tạo chuỗi
        StringBuilder sb = new StringBuilder("#");
        // In ra các ký tự thành chuỗi
        for (int i = 0; i < 6; i++) {
            // Random ký tự
            String character = characters[rd.nextInt(characters.length)];
            // Add ký tự tạo thành chuỗi
            sb.append(character);
        }
        // Trả về chuỗi
        return sb.toString();
    }

    public String randomRgbColor() {
        Random rd = new Random();
        int r = rd.nextInt(256);
        int g = rd.nextInt(256);
        int b = rd.nextInt(256);
        return "rgb(" + r + "," + g + "," + b + ")";
    }
}
