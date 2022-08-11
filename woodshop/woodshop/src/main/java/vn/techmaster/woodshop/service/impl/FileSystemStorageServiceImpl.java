package vn.techmaster.woodshop.service.impl;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.woodshop.config.StorageProperties;
import vn.techmaster.woodshop.exception.NotFoundException;
import vn.techmaster.woodshop.exception.StorageException;
import vn.techmaster.woodshop.service.StorageService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemStorageServiceImpl implements StorageService {

    private final Path rootLocation; //Xác định đường dẫn gốc lưu hình ảnh

    //Cho phép lưu thông tin và lấy thông tin về tên file được lưu trữ
    //Tạo ra file lưu trữ dựa vào id truyền vào
    @Override
    public String getStoredFilename(MultipartFile file, String id) {
        //Trả về phần mở rộng của tên file được update lên server
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());

        return "p" + id + "." + ext;
    }

    //Đưa cấu hình cho phép lưu trữ vào
    public FileSystemStorageServiceImpl(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    //Lưu file từ MultipartFile
    @Override
    public void store(MultipartFile file, String storedFilename) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file");
            }

            //Lấy đường dẫn tuyệt đối của file
            Path destinationFile = this.rootLocation.resolve(Paths.get(storedFilename))
                    .normalize().toAbsolutePath();

            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new StorageException("Cannot store file outside current directory");
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            throw new StorageException("Failed to store file" + e);
        }
    }

    //Phương thước cho phép nạp nội dung của file dưới dạng resource
    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            }

            throw new NotFoundException("Could not read file: " + filename);
        } catch (Exception e) {
            throw new NotFoundException("Could not read file: " + filename);
        }
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    //Xóa file
    @Override
    public void delete(String storedFilename) throws IOException {
        Path destinationFile = rootLocation.resolve(Paths.get(storedFilename)).normalize().toAbsolutePath();

        Files.delete(destinationFile);
    }

    //Phương thức khởi tạo
    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
            System.out.println(rootLocation.toString());
        } catch (Exception e) {
            throw new StorageException("Could not initialize storage " + e);
        }
    }
}
