    package vnua.k66httt.techworld.Database;

    import android.content.Context;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;

    public class dbVnua extends SQLiteOpenHelper {
        static String DB_NAME = "TechShop";
        static int DB_VERSION = 23;

        public dbVnua(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            // 1. Bảng tài khoản
            String taiKhoan = "CREATE TABLE TAIKHOAN(" +
                    "mataikhoan INTEGER primary key autoincrement," +
                    " tendangnhap text not null," +
                    " matkhau text not null," +
                    " hoten text not null," +
                    " email text not null," +
                    " sodienthoai INTEGER not null," +
                    " diachi text not null," +
                    " sotien integer not null," +
                    "loaitaikhoan text not null)";
            sqLiteDatabase.execSQL(taiKhoan);
    //        sqLiteDatabase.execSQL("INSERT INTO TAIKHOAN VALUES(1,'admin','admin','Nguyễn Quý Đức','nqduc@gmail.com','0787613866','18 An Duong Vuong',10000,'admin')");
            sqLiteDatabase.execSQL("INSERT INTO TAIKHOAN(tendangnhap, matkhau, hoten, email, sodienthoai, diachi, sotien, loaitaikhoan) VALUES('admin','admin','Nguyễn Quý Đức','nqduc@gmail.com','0787613866','18 An Duong Vuong',10000,'admin')");
            sqLiteDatabase.execSQL("INSERT INTO TAIKHOAN VALUES(2,'hoang','111','Phạm Đức Hoàng','pdhoang@gmail.com','0787613866','14 Duong Y',100,'user')");
            sqLiteDatabase.execSQL("INSERT INTO TAIKHOAN VALUES(3,'linh','111','Hoàng Thùy Linh','htling@gmail.com','0987654321','144 Vo Chi Cong',0,'user')");
            sqLiteDatabase.execSQL("INSERT INTO TAIKHOAN VALUES(4,'giang','111','Lê Duy Giang','ldgiang@gmail.com','0987612345','20 Hoang Quoc Viet',0,'user')");
            sqLiteDatabase.execSQL("INSERT INTO TAIKHOAN VALUES(5,'minh','111','Phạm Đức Minh','pdminh@gmail.com','0989796959','25 Duong Noi',0,'user')");

            // 2. Bảng loại sản phẩm
            String loaiSanPham = "CREATE TABLE LOAISANPHAM(" +
                    "maloaisanpham integer primary key autoincrement," +
                    " tenloaisanpham text not null)";
            sqLiteDatabase.execSQL(loaiSanPham);

            sqLiteDatabase.execSQL("INSERT INTO LOAISANPHAM VALUES(1,'Tai nghe')");
            sqLiteDatabase.execSQL("INSERT INTO LOAISANPHAM VALUES(2,'Điện thoại')");
            sqLiteDatabase.execSQL("INSERT INTO LOAISANPHAM VALUES(3,'Laptop')");
            sqLiteDatabase.execSQL("INSERT INTO LOAISANPHAM VALUES(4,'Phụ kiện điện thoại')");
            sqLiteDatabase.execSQL("INSERT INTO LOAISANPHAM VALUES(5,'Bàn phím')");

            // 3. Bảng sản phẩm
            String sanPham = "CREATE TABLE SANPHAM(" +
                    "masanpham integer primary key autoincrement," +
                    " tensanpham text not null," +
                    " gia integer not null," +
                    " maloaisanpham integer REFERENCES LOAISANPHAM(maloaisanpham)," +
                    " mota text not null," +
                    " anhsanpham text not null," +
                    " soluong integer not null," +
                    " soluongbanra integer not null)";
            sqLiteDatabase.execSQL(sanPham);

            // Thêm dữ liệu sản phẩm điện thoại vào bảng
            sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(1,'iPhone 15 Pro trắng ',3500,2,'iPhone 15 Pro với màn hình OLED Super Retina XDR 6.1 inch, chip A17 Bionic, camera 48MP, và hỗ trợ 5G.','https://store.storeimages.cdn-apple.com/1/as-images.apple.com/is/iphone-15-pro-finish-select-202309-6-1inch-whitetitanium?wid=5120&hei=2880&fmt=p-jpg&qlt=80&.v=VW44dkRidm5wazhwcGxtL0cyaEJ2VTkrNXBUdUJSK1k4NE5seUtJaW80ZE9GbVRLdFoyOVBmczRNaU91Q1BaNWlCQmV2WTA2cncybDF2YzFnKzI0S3J4M3lYcDIyK01lckZBaW5GTC9DMEQxcjBVRyswWG14bEI4WVZBcUIybEZZRW9vZFNTZzkrM0RIZnlSSUJ5cEdRPT0=&traceId=1',10,0)");
            sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(2,'iPhone 16 Pro Max vàng titanium',4200,2,'iPhone 16 Pro Max với màn hình OLED Super Retina XDR 6.7 inch, chip A18 Bionic, camera 48MP, LiDAR Scanner và pin lâu hơn.','https://cdn2.cellphones.com.vn/insecure/rs:fill:358:0/q:90/plain/https://cellphones.com.vn/media/catalog/product/i/p/iphone-16-pro_1.png',5,0)");
            sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(3,'Samsung Galaxy S24 Ultra đen',3400,2,'Samsung Galaxy S24 Ultra với màn hình AMOLED 6.8 inch, bộ xử lý Exynos 2400, camera chính 200MP, pin 5000mAh và hỗ trợ S-Pen.','https://cdn2.cellphones.com.vn/358x/media/catalog/product/g/a/galaxy-s24-ultra-den-1_1_3.png',8,0)");
            sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(4,'Samsung Galaxy S23 đen',2800,2,'Samsung Galaxy S24 với màn hình AMOLED 6.1 inch, chip Exynos 2400 hoặc Snapdragon 8 Gen 3, camera chính 50MP và pin 4000mAh.','https://cdn2.cellphones.com.vn/358x/media/catalog/product/s/a/samsung-galaxy-s23-128gb_1.png',12,0)");
            sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(5,'Xiaomi Mi 11 đen',1500,2,'Xiaomi Mi 11 với màn hình AMOLED 6.81 inch, bộ xử lý Snapdragon 888, camera chính 108MP và hỗ trợ sạc nhanh 55W.','https://cdn.hoanghamobile.com/i/previewV2/Uploads/2023/12/16/pms-1634300956-87533336.png',15,0)");

            sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(6,'Tai nghe Apple AirPods Pro2',549,1,'Tai nghe Apple AirPods Pro với khả năng chống ồn chủ động, âm thanh chất lượng cao, và hỗ trợ sạc không dây.','https://cdn.tgdd.vn/Products/Images/54/315014/tai-nghe-bluetooth-airpods-pro-2nd-gen-usb-c-charge-apple-1-750x500.jpg',20,0)");
            sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(7,'Tai nghe Sony WH-1000XM4',900,1,'Tai nghe Sony WH-1000XM4 với công nghệ chống ồn tiên tiến, thời lượng pin lên tới 30 giờ, và âm thanh Hi-Res.','https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/t/a/tai-nghe-chup-tai-sony-wh-1000xm4-ksp-3.png',12,0)");
            sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(8,'Tai nghe Samsung Galaxy Buds 3 Pro',500,1,'Tai nghe Samsung Galaxy Buds 3 Pro với âm thanh vòm 360 độ, chống ồn chủ động, và kết nối liền mạch với thiết bị Galaxy.','https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/t/a/tai-nghe-samsung-galaxy-buds-3-pro-spa_1.png',18,0)");
            sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(9,'Tai nghe Samsung Galaxy Buds2 Pro',240,1,'Tai nghe không dây Samsung Galaxy Buds2 Pro với âm thanh chất lượng cao, khả năng chống ồn chủ động, thiết kế nhỏ gọn, tiện lợi cho việc di chuyển và đàm thoại rõ ràng. Màu sắc: Đen, Trắng, Tím.','https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/g/r/group_182_2_1.png',20,5)");
            sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(10,'Tai nghe Samsung Galaxy Buds Live',175,1,'Tai nghe không dây Samsung Galaxy Buds Live với thiết kế độc đáo dạng hạt đậu, âm thanh rõ ràng và sâu, hỗ trợ chống ồn chủ động. Màu sắc: Đồng, Trắng, Đen.','https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/s/a/samsung-galaxy-buds-live.png',15,7)");

            sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(11,'Lenovo Legion 5 Pro',31990000,3,'Laptop gaming với card đồ họa NVIDIA GeForce RTX 3060, chip Intel Core i7, màn hình 16 inch.','https://laptopaz.vn/media/product/120_2816_2816_e2e2e23.png',40,20)");
            sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(12,'Acer Predator Helios 300',28990000,3,'Laptop gaming với card đồ họa NVIDIA GeForce RTX 3060, chip Intel Core i7, màn hình 15.6 inch.','https://laptopaz.vn/media/product/3313_',25,5)");
            sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(13,'Dell Alienware m15 R7',42990000,3,'Laptop gaming cao cấp với card đồ họa NVIDIA GeForce RTX 3070, chip Intel Core i7, màn hình 15.6 inch.','https://laptopaz.vn/media/product/120_2538_laptopaz_dell_alienware_m15_r7_2022_1s.jpg',20,2)");
            sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(14,'Asus ROG Strix G15',28990000,3,'Laptop gaming với card đồ họa NVIDIA GeForce GTX 1660 Ti, chip AMD Ryzen 7, màn hình 15.6 inch.','https://laptopaz.vn/media/product/120_2541_',50,15)");
            sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(15,'Asus ROG Zephyrus G14',34990000,3,'Laptop gaming với card đồ họa NVIDIA GeForce RTX 3060, chip AMD Ryzen 9, màn hình 14 inch.','https://laptopaz.vn/media/product/120_3162_',30,8)");

            sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(16,'Sạc nhanh Apple 20W',550000,4,'Sạc nhanh Apple 20W với thiết kế nhỏ gọn, tương thích với nhiều thiết bị Apple, hỗ trợ sạc nhanh.','https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/g/r/group_117_1.png',50,10)");
            sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(17,'Kính cường lực Samsung Galaxy S23',300000,4,'Kính cường lực bảo vệ màn hình Samsung Galaxy S23, chịu lực tốt, dễ dàng lắp đặt.','https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/0/3/03_2_6.jpg',30,5)");
            sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(18,'Ốp lưng iPhone 14',350000,4,'Ốp lưng iPhone 14 bảo vệ thiết bị khỏi va đập, thiết kế mỏng nhẹ và thời trang.','https://cdn2.cellphones.com.vn/x/media/catalog/product/o/p/op-lung-iphone-14-plus-zagg.png',25,4)");
            sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(19,'Dây cáp USB-C Anker',200000,4,'Dây cáp USB-C Anker với tốc độ truyền dữ liệu nhanh, bền bỉ và an toàn khi sử dụng.','https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/c/a/cap-type-c-to-lightning-542-1-8m-tpe-bio-based-a80b2_1_.png',35,6)");
            sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(20,'Giá đỡ điện thoại đa năng',150000,4,'Giá đỡ điện thoại đa năng giúp giữ điện thoại ở nhiều góc độ khác nhau, thuận tiện khi xem video hay gọi video.','https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/g/i/gia-do-dien-thoai-hyperwork-t3_1_.png',40,7)");

            sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(21,'Lucky65',1700000,5,'Bàn phím cơ Lucky65 với layout 65%, switch hot-swappable, đèn RGB đa sắc, và vỏ nhôm chắc chắn.','https://bizweb.dktcdn.net/thumb/1024x1024/100/436/596/products/3-min-1713935782738.png?v=1713935789853',50,10)");
            sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(22,'Keydous NJ81',2000000,5,'Bàn phím cơ Keydous NJ81 với layout 75%, switch hot-swappable, hỗ trợ Bluetooth 5.0, và đèn RGB.','https://bizweb.dktcdn.net/thumb/1024x1024/100/436/596/products/kit-smoke-thep-1666453151415.jpg?v=1666453159877',30,5)");
            sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(23,'Bridge75',1900000,5,'Bàn phím cơ Bridge75 với layout 75%, switch hot-swappable, thiết kế đẹp mắt và đèn nền RGB.','https://bizweb.dktcdn.net/thumb/1024x1024/100/436/596/products/6-1714839203677.png?v=1714839391273',40,8)");
            sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(24,'Yunzii AL75',2200000,5,'Bàn phím cơ Yunzii AL75 với layout 75%, switch hot-swappable, hỗ trợ kết nối không dây và đèn RGB.','https://bizweb.dktcdn.net/thumb/1024x1024/100/436/596/products/10-1712134994791.png?v=1713008884050',25,3)");
            sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(25,'RAINY75',2500000,5,'Bàn phím cơ RAINY75 với layout 75%, switch hot-swappable, thiết kế chống nước và đèn RGB.','https://bizweb.dktcdn.net/thumb/1024x1024/100/436/596/products/22-min-1702460125985-1581efc5-d174-4c59-87b5-e582d9684396.png?v=1729429780613',20,1)");

            // 4. Bảng giỏ hàng
            String gioHang = "CREATE TABLE GIOHANG(" +
                    "magiohang integer primary key autoincrement," +
                    " mataikhoan integer REFERENCES TAIKHOAN(mataikhoan)," +
                    " masanpham integer REFERENCES SANPHAM(masanpham)," +
                    " soluong integer not null)";
            sqLiteDatabase.execSQL(gioHang);

            // 5. Bảng đơn hàng
            String donHang = "CREATE TABLE DONHANG(" +
                    "madonhang integer primary key autoincrement," +
                    " mataikhoan integer REFERENCES TAIKHOAN(mataikhoan)," +
                    " ngaydat text not null," +
                    " trangthai text not null," +
                    " diachigiaohang text not null," +
                    " tongtien integer not null)";
            sqLiteDatabase.execSQL(donHang);
            sqLiteDatabase.execSQL("INSERT INTO DONHANG VALUES(1,2,'16/11/2023','Đã nhận hàng','18 An Duong Vuong',300)");
            sqLiteDatabase.execSQL("INSERT INTO DONHANG VALUES(2,5,'16/12/2023','Đã nhận hàng','25 Duong Noi',1000)");
            sqLiteDatabase.execSQL("INSERT INTO DONHANG VALUES(3,2,'17/09/2023','Đã nhận hàng','14 Duong Y',200)");
            sqLiteDatabase.execSQL("INSERT INTO DONHANG VALUES(4,4,'18/01/2023','Đã nhận hàng','20 Hoang Quoc Viet',10)");
            sqLiteDatabase.execSQL("INSERT INTO DONHANG VALUES(5,3,'19/11/2023','Đã nhận hàng','144 Vo Chi Cong',20000)");

            // 6. Bảng chi tiết đơn hàng
            String chiTietDonHang = "CREATE TABLE CHITIETDONHANG(" +
                    "machiTietDonHang integer primary key autoincrement," +
                    " madonhang integer REFERENCES DONHANG(madonhang)," +
                    " masanpham integer REFERENCES SANPHAM(masanpham)," +
                    " soluong integer not null)";
            sqLiteDatabase.execSQL(chiTietDonHang);
            sqLiteDatabase.execSQL("INSERT INTO CHITIETDONHANG VALUES(1,2,5,5)");
            sqLiteDatabase.execSQL("INSERT INTO CHITIETDONHANG VALUES(2,2,1,4)");
            sqLiteDatabase.execSQL("INSERT INTO CHITIETDONHANG VALUES(3,3,2,3)");
            sqLiteDatabase.execSQL("INSERT INTO CHITIETDONHANG VALUES(4,2,3,2)");
            sqLiteDatabase.execSQL("INSERT INTO CHITIETDONHANG VALUES(5,3,5,5)");

            //7. Bảng đánh giá
            String danhGia = "CREATE TABLE DANHGIA(" +
                    "madanhgia integer primary key autoincrement," +
                    " mataikhoan integer REFERENCES TAIKHOAN(mataikhoan)," +
                    " masanpham integer REFERENCES SANPHAM(masanpham)," +
                    " danhgia text not null," +
                    " nhanxet text not null," +
                    " ngaydanhgia text not null)";
            sqLiteDatabase.execSQL(danhGia);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            // Xóa các bảng cũ nếu có
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CHITIETDONHANG");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DONHANG");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS GIOHANG");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SANPHAM");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LOAISANPHAM");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS TAIKHOAN");

            // Tạo lại các bảng
            onCreate(sqLiteDatabase);
        }

        public void resetDatabase(Context context) {
            context.deleteDatabase("database_name"); // Xóa cơ sở dữ liệu
        }

    }