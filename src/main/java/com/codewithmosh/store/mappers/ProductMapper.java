package com.codewithmosh.store.mappers;

import com.codewithmosh.store.dtos.ProductDto;
import com.codewithmosh.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring") // khai báo phương thức

public interface ProductMapper {
    @Mapping(target = "categoryId", source = "category.id") // lấy id của category gắn vào categoryId trong product
    ProductDto toDto(Product product);
}
//ProductMapper