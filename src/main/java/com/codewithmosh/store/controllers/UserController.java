package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.mappers.UserMapper;
import com.codewithmosh.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository; // đảm bảo các biến này không bị thay đổi sau khi khởi tạo
    private final UserMapper userMapper;
    @GetMapping("")
    public Iterable<UserDto> getAllUsers(){
        return userRepository.findAll().stream().map(userMapper::toDto).toList(); // lấy toàn bộ User (Entity)
        // từ DB dùng userMapper để chuyển từng ông thành UserDto và trả về danh sách.
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser (@PathVariable Long id){ //trả về "Cả một gói tin HTTP".
        // Gói tin này bao gồm cả dữ liệu và Status Code.
        var user = userRepository.findById(id).orElse( null) ;
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userMapper.toDto(user));
    }
}
