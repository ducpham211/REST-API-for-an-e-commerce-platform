package com.codewithmosh.store.mappers;
import com.codewithmosh.store.dtos.RegisterUserRequest;
import com.codewithmosh.store.dtos.UpdateUserRequest;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.dtos.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);//Lấy dữ liệu khách gửi lên, đóng gói lại để lưu vào Database.
    void update (UpdateUserRequest request, @MappingTarget User use);
    //Mapping target để yêu cầu chú ý vào đối tượng sẽ bị "đè" dữ liệu lên mà không tạo mới dữ liệu.
}
//thay vì phải nhập 20 dòng code để lấy thông tin của 20 người thì bây giờ chỉ cần 1