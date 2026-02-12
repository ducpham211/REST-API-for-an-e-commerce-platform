package com.codewithmosh.store.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class UserDto {
    private Long id;
    private String email;
    private String name;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
}
//chỉ chứa những thông tin mà bạn muốn người dùng nhìn thấy