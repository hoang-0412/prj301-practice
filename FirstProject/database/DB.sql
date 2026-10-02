CREATE DATABASE PRJ301SU25
Go
USE PRJ301SU25

-- Tạo bảng tblRoles
CREATE TABLE tblRoles (
    roleID INT IDENTITY(1,1) PRIMARY KEY,
    roleName NVARCHAR(255) NOT NULL
)
Go
-- Tạo bảng tblUsers
CREATE TABLE tblUsers (
    userID VARCHAR(50) PRIMARY KEY,
    fullName NVARCHAR(255) NOT NULL,
    password NVARCHAR(255) NOT NULL,
    roleID INT NOT NULL,
    address NVARCHAR(255),
    phone NVARCHAR(50),
    email NVARCHAR(255),
	activate BIT
    CONSTRAINT FK_Users_Roles FOREIGN KEY (roleID) REFERENCES tblRoles(roleID) ON DELETE CASCADE
)

-- Tạo bảng tblCategory
CREATE TABLE tblCategories (
    categoryID VARCHAR(10) PRIMARY KEY,
    categoryName NVARCHAR(255) NOT NULL,
	describe NVARCHAR(255)
)
Go
-- Tạo bảng tblProduct
CREATE TABLE tblProducts (
    productID INT IDENTITY(1,1) PRIMARY KEY,
    productName NVARCHAR(255) NOT NULL,
    image NVARCHAR(255),
    price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL,
    categoryID VARCHAR(10) NOT NULL,
    importDate DATE,
    usingDate DATE,
	status int
    CONSTRAINT FK_Product_Category 
	FOREIGN KEY (categoryID) REFERENCES tblCategories(categoryID) 
	--ON DELETE CASCADE
)
Go
-- Tạo bảng tblOrder
CREATE TABLE tblOrders (
    orderID INT IDENTITY(1,1) PRIMARY KEY,
    orderDate DATE NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    userID VARCHAR(50) NOT NULL,
    CONSTRAINT FK_Order_Users FOREIGN KEY (userID) REFERENCES tblUsers(userID) ON DELETE CASCADE
)
Go
-- Tạo bảng tblOrderDetail
CREATE TABLE tblOrderDetails (
    detailID INT IDENTITY(1,1) PRIMARY KEY,
    price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL,
    orderID INT NOT NULL,
    productID INT NOT NULL,
    CONSTRAINT FK_OrderDetail_Order FOREIGN KEY (orderID) REFERENCES tblOrders(orderID) ON DELETE CASCADE,
    CONSTRAINT FK_OrderDetail_Product FOREIGN KEY (productID) REFERENCES tblProducts(productID) ON DELETE CASCADE
)
Go
INSERT INTO tblRoles (roleName) VALUES
('Admin'),
('Customer'),
('Manager'),
('Staff'),
('Guest')
Go
INSERT INTO tblUsers (userID, fullName, password, roleID, address, phone, email, activate) VALUES
('U001', N'Nguyễn Văn A', 'password1', 1, N'Hà Nội', '0123456789', 'a@example.com', 1),
('U002', N'Nguyễn Thị B', 'password2', 2, N'Hải Phòng', '0987654321', 'b@example.com', 1),
('U003', N'Lê Văn C', 'password3', 3, N'Đà Nẵng', '0934567890', 'c@example.com', 1),
('U004', N'Trần Thị D', 'password4', 4, N'TP HCM', '0945678901', 'd@example.com', 0),
('U005', N'Phạm Văn E', 'password5', 5, N'Cần Thơ', '0956789012', 'e@example.com', 1);
Go
-- Insert vào bảng tblCategories
INSERT INTO tblCategories (categoryID, categoryName, describe) VALUES
('C001', N'Điện thoại', N'Các loại điện thoại thông minh'),
('C002', N'Máy tính', N'Máy tính xách tay và để bàn'),
('C003', N'Phụ kiện', N'Tai nghe, sạc, cáp kết nối'),
('C004', N'Đồ gia dụng', N'Thiết bị điện gia đình'),
('C005', N'Thời trang', N'Quần áo, giày dép, phụ kiện thời trang');
Go
-- Insert vào bảng tblProducts
INSERT INTO tblProducts (productName, image, price, quantity, categoryID, importDate, usingDate,status) VALUES
(N'iPhone 14', 'iphone14.jpg', 29990.00, 50, 'C001', '2023-01-01', '2025-01-01',1),
(N'MacBook Pro', 'macbookpro.jpg', 49990.00, 30, 'C002', '2023-02-01', '2026-02-01',1),
(N'Tai nghe AirPods', 'airpods.jpg', 4990.00, 100, 'C003', '2023-03-01', '2024-03-01',1),
(N'Nồi chiên không dầu', 'noichien.jpg', 2990.00, 20, 'C004', '2023-04-01', '2025-04-01',1),
(N'Áo khoác', 'aokhoac.jpg', 990.00, 200, 'C005', '2023-05-01', '2024-05-01',1);
Go
-- Insert vào bảng tblOrders
INSERT INTO tblOrders (orderDate, total, userID) VALUES
('2023-06-01', 59980.00, 'U001'),
('2023-06-02', 34990.00, 'U002'),
('2023-06-03', 2990.00, 'U003'),
('2023-06-04', 990.00, 'U004'),
('2023-06-05', 4990.00, 'U005');
Go
-- Insert vào bảng tblOrderDetails
INSERT INTO tblOrderDetails (price, quantity, orderID, productID) VALUES
(29990.00, 1, 1, 1),
(49990.00, 1, 2, 2),
(2990.00, 1, 3, 4),
(990.00, 2, 4, 5),
(4990.00, 1, 5, 3);