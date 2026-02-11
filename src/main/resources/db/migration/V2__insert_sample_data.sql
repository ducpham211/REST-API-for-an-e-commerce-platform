-- 1. Chèn dữ liệu cho bảng categories (Danh mục)
INSERT INTO categories (name) VALUES
                                  ('Điện tử & Công nghệ'),
                                  ('Thời trang Thể thao'),
                                  ('Phụ kiện cao cấp');

-- 2. Chèn dữ liệu cho bảng users (Người dùng)
-- Lưu ý: Trong thực tế mật khẩu phải được mã hóa BCrypt, ở đây tạm để text thô
INSERT INTO users (name, email, password) VALUES
                                              ('Duc Pham', 'ducpham211@gmail.com', 'password123'),
                                              ('Minh Anh', 'minhanh@example.com', 'securepass'),
                                              ('Hoang Nam', 'namhoang@example.com', 'mypassword');

-- 3. Chèn dữ liệu cho bảng profiles (Hồ sơ chi tiết)
-- ID ở đây phải khớp với ID của bảng users (1, 2, 3)
INSERT INTO profiles (id, bio, phone_number, date_of_birth, loyalty_points) VALUES
                                                                                (1, 'Lập trình viên Fullstack, đam mê công nghệ và bóng đá.', '0912345678', '2000-01-01', 500),
                                                                                (2, 'Khách hàng thân thiết của SportGear.', '0987654321', '1995-05-15', 150),
                                                                                (3, 'Chuyên gia review các sản phẩm Apple.', '0909090909', '1992-10-20', 0);

-- 4. Chèn dữ liệu cho bảng addresses (Địa chỉ)
INSERT INTO addresses (street, city, state, zip, user_id) VALUES
                                                              ('123 Đường Lê Lợi', 'Hà Nội', 'Hoàn Kiếm', '100000', 1),
                                                              ('456 Đường Nguyễn Huệ', 'TP. Hồ Chí Minh', 'Quận 1', '700000', 2),
                                                              ('789 Đường Đà Nẵng', 'Đà Nẵng', 'Hải Châu', '550000', 1);

-- 5. Chèn dữ liệu cho bảng products (Sản phẩm)
INSERT INTO products (name, price, description, category_id) VALUES
                                                                 ('iPhone 15 Pro Max 256GB', 29990000.00, 'Siêu phẩm mới nhất từ Apple với khung viền Titan.', 1),
                                                                 ('MacBook Pro M3 14 inch', 39990000.00, 'Hiệu năng cực đỉnh cho công việc lập trình và đồ họa.', 1),
                                                                 ('Quả bóng đá FIFA Quality Pro', 1250000.00, 'Bóng tiêu chuẩn thi đấu quốc tế, độ bền cao.', 2),
                                                                 ('Áo đấu CLB Manchester United', 850000.00, 'Phiên bản sân nhà mùa giải 2025/2026.', 2),
                                                                 ('Tai nghe AirPods Pro Gen 2', 5490000.00, 'Chống ồn chủ động và âm thanh không gian.', 3);

-- 6. Chèn dữ liệu cho bảng wishlist (Danh sách yêu thích)
INSERT INTO wishlist (product_id, user_id) VALUES
                                               (1, 1), -- Duc Pham thích iPhone 15
                                               (3, 1), -- Duc Pham thích Quả bóng đá
                                               (2, 2), -- Minh Anh thích MacBook Pro
                                               (5, 3); -- Hoang Nam thích AirPods Pro