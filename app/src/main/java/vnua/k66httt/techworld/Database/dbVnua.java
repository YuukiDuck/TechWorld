package vnua.k66httt.techworld.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class dbVnua extends SQLiteOpenHelper {
    static String DB_NAME = "TechWorldShop";
    static int DB_VERSION = 22;

    public dbVnua(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        ///1. Bảng tài khoản
        String taiKhoan = "CREATE TABLE TAIKHOAN(" +
                "mataikhoan integer primary key autoincrement," +
                " tendangnhap text not null," +
                " matkhau text not null," +
                " hoten text not null," +
                " email text not null," +
                " sodienthoai text not null," +
                " diachi text not null," +
                " sotien integer not null," +
                "loaitaikhoan text not null," +
                " anhtaikhoan text not null)";
        sqLiteDatabase.execSQL(taiKhoan);
        sqLiteDatabase.execSQL("INSERT INTO TAIKHOAN VALUES(1,'nqduc','111','Nguyễn Quý Đức','ducnq@gmail.com','0787613866','ha noi',10000,'admin','https://chiemtaimobile.vn/images/companies/1/%E1%BA%A2nh%20Blog/avatar-facebook-dep/Anh-avatar-hoat-hinh-de-thuong-xinh-xan.jpg?1704788263223')");
        sqLiteDatabase.execSQL("INSERT INTO TAIKHOAN VALUES(2,'pdhoang','123','Phạm Đức Hoàng','hoangpd@gmail.com','0787613866','ha noi',10000,'admin','https://cellphones.com.vn/sforum/wp-content/uploads/2023/10/avatar-trang-4.jpg')");


        //2. Bảng loại sản phẩm
        String loaiSanPham = "CREATE TABLE LOAISANPHAM(" +
                "maloaisanpham integer primary key autoincrement," +
                " tenloaisanpham text not null)";
        sqLiteDatabase.execSQL(loaiSanPham);
        sqLiteDatabase.execSQL("INSERT INTO LOAISANPHAM VALUES(1,'Tai Nghe')");
        sqLiteDatabase.execSQL("INSERT INTO LOAISANPHAM VALUES(2,'Điện thoại')");
        sqLiteDatabase.execSQL("INSERT INTO LOAISANPHAM VALUES(3,'Phụ kiên')");
        sqLiteDatabase.execSQL("INSERT INTO LOAISANPHAM VALUES(4,'Dây sạc')");


        String sanPham = "CREATE TABLE SANPHAM(" +
                " masanpham integer primary key autoincrement," +
                " tensanpham text not null," +
                " gia integer not null," +
                " maloaisanpham integer REFERENCES LOAISANPHAM(maloaisanpham)," +
                " mota text not null," +
                " anhsanpham text not null," +
                " soluong integer not null," +
                " soluongbanra integer not null)";
        sqLiteDatabase.execSQL(sanPham);

// Thêm dữ liệu sản phẩm điện thoại vào bảng
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(1,'iPhone 15 Pro trắng ',35000000,1,'iPhone 15 Pro với màn hình OLED Super Retina XDR 6.1 inch, chip A17 Bionic, camera 48MP, và hỗ trợ 5G.','https://store.storeimages.cdn-apple.com/1/as-images.apple.com/is/iphone-15-pro-finish-select-202309-6-1inch-whitetitanium?wid=5120&hei=2880&fmt=p-jpg&qlt=80&.v=VW44dkRidm5wazhwcGxtL0cyaEJ2VTkrNXBUdUJSK1k4NE5seUtJaW80ZE9GbVRLdFoyOVBmczRNaU91Q1BaNWlCQmV2WTA2cncybDF2YzFnKzI0S3J4M3lYcDIyK01lckZBaW5GTC9DMEQxcjBVRyswWG14bEI4WVZBcUIybEZZRW9vZFNTZzkrM0RIZnlSSUJ5cEdRPT0=&traceId=1',10,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(2,'iPhone 16 Pro Max vàng titanium',42000000,1,'iPhone 16 Pro Max với màn hình OLED Super Retina XDR 6.7 inch, chip A18 Bionic, camera 48MP, LiDAR Scanner và pin lâu hơn.','https://cdn2.cellphones.com.vn/insecure/rs:fill:358:0/q:90/plain/https://cellphones.com.vn/media/catalog/product/i/p/iphone-16-pro_1.png',5,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(3,'Samsung Galaxy S24 Ultra đen',34000000,1,'Samsung Galaxy S24 Ultra với màn hình AMOLED 6.8 inch, bộ xử lý Exynos 2400, camera chính 200MP, pin 5000mAh và hỗ trợ S-Pen.','https://cdn2.cellphones.com.vn/358x/media/catalog/product/g/a/galaxy-s24-ultra-den-1_1_3.png',8,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(4,'Samsung Galaxy S23 đen',28000000,1,'Samsung Galaxy S24 với màn hình AMOLED 6.1 inch, chip Exynos 2400 hoặc Snapdragon 8 Gen 3, camera chính 50MP và pin 4000mAh.','https://cdn2.cellphones.com.vn/358x/media/catalog/product/s/a/samsung-galaxy-s23-128gb_1.png',12,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(5,'Xiaomi Mi 11 đen',15000000,1,'Xiaomi Mi 11 với màn hình AMOLED 6.81 inch, bộ xử lý Snapdragon 888, camera chính 108MP và hỗ trợ sạc nhanh 55W.','https://cdn.hoanghamobile.com/i/previewV2/Uploads/2023/12/16/pms-1634300956-87533336.png',15,0)");

        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(6,'Tai nghe Apple AirPods Pro2',5490000,2,'Tai nghe Apple AirPods Pro với khả năng chống ồn chủ động, âm thanh chất lượng cao, và hỗ trợ sạc không dây.','https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/a/p/apple-airpods-pro-2-usb-c-ksp-1_3_.png',20,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(7,'Tai nghe Sony WH-1000XM4',9000000,2,'Tai nghe Sony WH-1000XM4 với công nghệ chống ồn tiên tiến, thời lượng pin lên tới 30 giờ, và âm thanh Hi-Res.','https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/t/a/tai-nghe-chup-tai-sony-wh-1000xm4-ksp-3.png',12,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(8,'Tai nghe Samsung Galaxy Buds 3 Pro',5000000,2,'Tai nghe Samsung Galaxy Buds 3 Pro với âm thanh vòm 360 độ, chống ồn chủ động, và kết nối liền mạch với thiết bị Galaxy.','https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/t/a/tai-nghe-samsung-galaxy-buds-3-pro-spa_1.png',18,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(9,'Tai nghe Samsung Galaxy Buds2 Pro',2400000,2,'Tai nghe không dây Samsung Galaxy Buds2 Pro với âm thanh chất lượng cao, khả năng chống ồn chủ động, thiết kế nhỏ gọn, tiện lợi cho việc di chuyển và đàm thoại rõ ràng. Màu sắc: Đen, Trắng, Tím.','https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/s/a/samsung-galaxy-buds-2-pro-ksp-1_1.png',20,5)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(10,'Tai nghe Samsung Galaxy Buds Live',1750000,2,'Tai nghe không dây Samsung Galaxy Buds Live với thiết kế độc đáo dạng hạt đậu, âm thanh rõ ràng và sâu, hỗ trợ chống ồn chủ động. Màu sắc: Đồng, Trắng, Đen.','https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/s/a/samsung-galaxy-buds-live.png',15,7)");

        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(11,'Ốp lưng iPhone 15 Pro Silicon',23000,3,'Ốp lưng bảo vệ dành cho iPhone 15 Pro Max với chất liệu silicon cao cấp, bảo vệ tối đa, và thiết kế mỏng nhẹ.','https://cdn2.cellphones.com.vn/358x/media/catalog/product/o/p/op-iphone-15-pro-chinh-hang-04_3.png',25,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(12,'Miếng dán cường lực Zeelot Samsung Galaxy S21',80000,3,'Miếng dán cường lực Samsung Galaxy S21, bảo vệ chống xước, chống va đập, và giữ độ nhạy cảm ứng cao.','https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/k/i/kinh-cuong-luc-samsung-galaxy-s21-plus-zeelot-uv-loca-1.jpg',30,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(13,'Dây đeo điện thoại chống rơi ZAGG',10000,3,'Dây đeo điện thoại chống rơi đa năng, tương thích với nhiều loại điện thoại, và thiết kế thời trang.','https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/d/a/day-deo-zagg-iphone-01.png',40,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(14,'Pin sạc dự phòng Xiaomi Mi 10000mAh',230000,3,'Pin sạc dự phòng Xiaomi Mi dung lượng 10000mAh, hỗ trợ sạc nhanh 18W, thiết kế nhỏ gọn, có thể sạc cho nhiều thiết bị cùng lúc. Màu sắc: Đen, Bạc.','https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/p/i/pin-du-phong-xiaomi-10000-gen-3-2019-cate.jpg',30,15)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(15,'Kính cường lực zagg cho iPhone 16 Pro Max',89000,3,'Kính cường lực chống trầy xước, chống vỡ, độ trong suốt cao, thiết kế riêng cho iPhone 16 Pro Max.','https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/k/i/kinh-cuong-luc-iphone-16-pro-max-zagg-plus-edge-full_2__1.png',100,5)");

        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(16,'Cáp sạc nhanh iPhone 20W',45000,4,'Cáp sạc nhanh iPhone 20W USB-C to Lightning, hỗ trợ sạc nhanh và truyền dữ liệu tốc độ cao.','https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/c/a/cap-sac-nhanh-tu-ngat-type-c-to-iphone-baseus-explorer-series-20w-1m-1.png',50,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(17,'Cáp sạc nhanh Samsung 25W',40000,4,'Cáp sạc nhanh Samsung 25W USB-C to USB-C, tương thích với nhiều thiết bị, hỗ trợ sạc nhanh và bền bỉ.','https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/c/u/cu-sac-nhanh-samsung-25w-ep-t2510nbegww-1.png',60,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(18,'Cáp sạc Xiaomi ',35000,4,'Cáp sạc nhanh Xiaomi USB-A to USB-C, tích hợp chip thông minh, đảm bảo an toàn khi sạc.','https://product.hstatic.net/200000547447/product/3020698db02a1065a4817b59b42237e9_ceaeaac4c45c4dd9bacb52e2b7f7a250_69ede30f734a497aa0bc49ea6c96a316_master.png',45,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(19,'Cáp sạc nhanh Samsung USB-C to USB-C 3A',75000,4,'Cáp sạc nhanh Samsung chuẩn USB-C to USB-C hỗ trợ sạc nhanh 3A, dài 1.2m, tương thích với các dòng máy Samsung và Android khác.','https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/f/r/frame_8_1_.png',70,8)");


        //4. Bảng giỏ hàng
        String gioHang = "CREATE TABLE GIOHANG(" +
                "magiohang integer primary key autoincrement, " +
                "mataikhoan integer REFERENCES TAIKHOAN(mataikhoan)," +
                " masanpham integer REFERENCES SANPHAM(masanpham)," +
                " soluong integer not null)";
        sqLiteDatabase.execSQL(gioHang);


        //5. Bảng đơn hàng
        String donHang = "CREATE TABLE DONHANG(" +
                " madonhang integer primary key autoincrement," +
                " mataikhoan integer REFERENCES TAIKHOAN(mataikhoan)," +
                " ngaydathang text not null," +
                " tongtien integer not null," +
                " trangthai text not null)";
        sqLiteDatabase.execSQL(donHang);
        sqLiteDatabase.execSQL("INSERT INTO DONHANG VALUES(1,2,'16/06/2024',100,'Đã nhận hàng')");
        sqLiteDatabase.execSQL("INSERT INTO DONHANG VALUES(2,5,'16/08/2024',200,'Đã nhận hàng')");
        sqLiteDatabase.execSQL("INSERT INTO DONHANG VALUES(3,2,'17/09/2024',300,'Đã nhận hàng')");
        sqLiteDatabase.execSQL("INSERT INTO DONHANG VALUES(4,4,'18/01/2024',400,'Đã nhận hàng')");
        sqLiteDatabase.execSQL("INSERT INTO DONHANG VALUES(5,3,'19/09/2024',50,'Đã nhận hàng')");


        //6. Bảng chi tiết đơn hàng
        String chiTietDonHang = "CREATE TABLE CHITIETDONHANG(" +
                "machitietdonhang integer primary key autoincrement," +
                " madonhang integer REFERENCES DONHANG(madonhang)," +
                " masanpham integer REFERENCES SANPHAM(masanpham)," +
                "soluong integer not null," +
                " dongia integer not null," +
                " thanhtien integer not null)";
        sqLiteDatabase.execSQL(chiTietDonHang);
        sqLiteDatabase.execSQL("INSERT INTO CHITIETDONHANG VALUES(1,2,5,5,20,20)");
        sqLiteDatabase.execSQL("INSERT INTO CHITIETDONHANG VALUES(2,2,1,4,30,30)");
        sqLiteDatabase.execSQL("INSERT INTO CHITIETDONHANG VALUES(3,3,2,3,30,30)");
        sqLiteDatabase.execSQL("INSERT INTO CHITIETDONHANG VALUES(4,2,3,2,30,30)");
        sqLiteDatabase.execSQL("INSERT INTO CHITIETDONHANG VALUES(5,3,5,5,10,10)");


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
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS TAIKHOAN");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SANPHAM");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LOAISANPHAM");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS GIOHANG");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DONHANG");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CHITIETDONHANG");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DANHGIA");
            onCreate(sqLiteDatabase);

        }
    }

}
