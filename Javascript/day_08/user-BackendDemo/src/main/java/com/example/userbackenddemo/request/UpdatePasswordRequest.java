package com.example.userbackenddemo.request;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UpdatePassWordRequest {
    private String oldPassWord ;
    private String newPassWord ;
}
