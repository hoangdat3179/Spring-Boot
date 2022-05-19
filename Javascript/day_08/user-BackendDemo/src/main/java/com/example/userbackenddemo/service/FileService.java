package com.example.userbackenddemo.service;

import com.example.userbackenddemo.Utils.Utils;
import com.example.userbackenddemo.exception.BadRequestException;
import com.example.userbackenddemo.exception.NotFoundException;
import com.example.userbackenddemo.model.User;
import com.example.userbackenddemo.response.FileReturn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FileService {
    private final Path rootDir = Paths.get("uploads");

    @Autowired
    private UserService userService ;

    public FileService() {
        createFolder(rootDir.toString());
    }

    public void createFolder (String path ) {
        File folder = new File(path) ;
        if(!folder.exists()) {
            folder.mkdirs();
        }
    }
    public String uploadFile(int id, MultipartFile file) {
        Optional<User> userOptional = userService.findById(id) ;
        if(userOptional.isEmpty()) {
            throw new NotFoundException(" users with is " + id + "not found") ;
        }
        // Kiểm tra file
        String fileName = file.getOriginalFilename();
        if(fileName == null || fileName.equals("")) {
            throw new BadRequestException("Tên file không hợp lệ ");
        }
        // Kiểm tra type file
        String fileExtension = Utils.getFileExtension(fileName) ;

        // Kiểm tra extension file có hợp lệ hay ko

        if(!Utils.checkFileExtension(fileExtension)) {
            throw new BadRequestException("File không hợp lệ") ;
        }

        // kiểm tra size file
        if(file.getSize() / 1_000_000L > 2) {
            throw new BadRequestException("File ko đc vượt quá 2MB") ;
        }
        // Tạo folder tương ứng với userid
        Path userDir = Paths.get("uploads").resolve(String.valueOf(id));
        createFolder(userDir.toString());

        // Tạo path tương ứng với file upload lên
        String generateFileName = UUID.randomUUID() +"."+ fileExtension ;
        File serverFile = new File(userDir.toString() + "/" + generateFileName) ;

        try {
            // sử dụng buffer để lưu dữ liệu
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile)) ;

            stream.write(file.getBytes());
            stream.close();
            String filePath = "/api/v1/users/files/" + id + "/" + generateFileName ;

            //Chèn lại thông tin filePath cho user
            userOptional.get().setAvatar(filePath) ;
            return filePath ;
        }
        catch (Exception e) {
            throw new RuntimeException("Lỗi khi upload file ") ;
        }
    }

    public byte[] readFile(int id, String fileName) {
        // Lấy đường dẫn file tương ứng với user
        Path userPath = rootDir.resolve(String.valueOf(id));

        // Kiểm tra đường dẫn có tồn tại hay ko
        if(!Files.exists(userPath)) {
            throw  new RuntimeException("Không thể đọc file : " + fileName) ;
        }
        try {
            // Lấy đường dẫn file tương ứng với userid và filename
            Path file = userPath.resolve(fileName) ;
            Resource resource = new UrlResource(file.toUri());

            // Kiểm tra có tồn tại hay ko và cho phép đọc hay ko
            if(resource.exists() || resource.isReadable()) {
                return StreamUtils.copyToByteArray(resource.getInputStream());

            }
            else {
                throw new RuntimeException("Không thể đọc file : "+ fileName) ;
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Không thể đọc file : "+ fileName ) ;
        }
    }

    public FileReturn getFiles(int id , int page){
        final int IMAGE_OF_PAGE = 10;
        // Lấy đường dẫn file tương ứng với user
        Path userPath = rootDir.resolve(String.valueOf(id));

        // Kiểm tra đường dẫn có tồn tại hay ko
        if(!Files.exists(userPath)) {
            throw  new RuntimeException("lỗi khi lấy danh sách file") ;
        }
        //Lấy danh sách tất cả file theo userId
        List<File> files = Arrays.asList(userPath.toFile().listFiles());

        // Công thức tính page
        List<File> filesReturn = files
                .stream()
                .skip((page -1)  * IMAGE_OF_PAGE)
                .limit(IMAGE_OF_PAGE)
                .collect(Collectors.toList());

        int totalPage = (int) Math.ceil((double) files.size() / IMAGE_OF_PAGE);
        List<String> filesPath = filesReturn
                .stream()
                .map(file -> "/api/v1/users/files/" + id + "/files/" + file.getName())
                .toList();
        return new FileReturn(filesPath,totalPage);
    }
}



