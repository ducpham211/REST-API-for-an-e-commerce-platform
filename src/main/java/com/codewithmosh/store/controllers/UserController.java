package com.codewithmosh.store.controllers;
import com.codewithmosh.store.dtos.UpdateUserRequest;
import com.codewithmosh.store.dtos.RegisterUserRequest;
import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.mappers.UserMapper;
import com.codewithmosh.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@RestController // Đánh dấu lớp này là một Controller, sẵn sàng tiếp nhận các yêu cầu HTTP và trả về JSON
@AllArgsConstructor // Tự động tạo Constructor cho tất cả các biến 'final', hỗ trợ Dependency Injection mà không cần @Autowired
@RequestMapping("/users") // Định nghĩa "địa chỉ nhà" gốc cho API này là http://localhost:8080/users
public class UserController {

    // Khai báo các 'xương sống' của Controller. Dùng 'final' giúp các biến này không bị thay đổi (immutable) và an toàn hơn.
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping("") // Lắng nghe yêu cầu GET gửi tới địa chỉ gốc (/users)
    public Iterable<UserDto> getAllUsers(@RequestParam (required = false, defaultValue = "", name = "sortBy") String sortBy){
        if(!Set.of("name","email").contains(sortBy)) sortBy = "name"; // kiểm tra xem sort được gửi lên có phải là name hay email ?
        // @RequestParam String sort: Lấy tham số 'sort' từ URL (ví dụ: ?sort=name)
        // userRepository.findAll(Sort.by(sort)): Gọi Database và yêu cầu sắp xếp theo trường vừa nhận
        // .stream().map(userMapper::toDto).toList():
        // Biến danh sách 'User' (Entity) lấy từ DB thành danh sách 'UserDto' (Sạch sẽ) để trả về cho Frontend
        return userRepository.findAll(Sort.by(sortBy)).stream().map(userMapper::toDto).toList();
    }

    @GetMapping("/{id}") // Lắng nghe yêu cầu GET kèm ID động (ví dụ: /users/5)
    public ResponseEntity<UserDto> getUser (@PathVariable Long id){
        // @PathVariable Long id: "Nhặt" cái ID từ đường dẫn URL để đưa vào hàm xử lý

        // Tìm user trong Database. Nếu không thấy thì gán là null
        var user = userRepository.findById(id).orElse(null);

        // Kiểm tra logic: Nếu không có user, trả về mã 404 (Not Found) thay vì trả về dữ liệu rỗng gây lỗi cho React
        if (user == null) return ResponseEntity.notFound().build();

        // Nếu thấy user: Chuyển qua Mapper để lọc dữ liệu, sau đó đóng gói vào gói tin HTTP với mã 200 (OK)
        return ResponseEntity.ok(userMapper.toDto(user));
    }
    @PostMapping("")
    public ResponseEntity <UserDto> createUser(@RequestBody RegisterUserRequest request, UriComponentsBuilder uriBuilder ){
        var user = userMapper.toEntity(request);//DTO ( form từ front end )-> Entity để lưu vào db
        userRepository.save(user);
        var userDto = userMapper.toDto(user); // chuyển về lại DTO để gửi cho front end
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }
    @PutMapping ("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(name = "id") Long id, @RequestBody UpdateUserRequest request){
      var user = userRepository.findById(id).orElse(null);
      if(user == null) return ResponseEntity.notFound().build();

      userMapper.update(request, user);

    userRepository.save(user);
    return ResponseEntity.ok(userMapper.toDto(user));

    }
}