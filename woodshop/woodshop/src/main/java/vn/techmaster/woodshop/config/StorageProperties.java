package vn.techmaster.woodshop.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
@Data
public class StorageProperties {
    //Chứa các khai báo cấu hình sử dụng để thao tác với file
    private String location; //Xác định vị trí dùng để lưu các file được upload lên server

}
