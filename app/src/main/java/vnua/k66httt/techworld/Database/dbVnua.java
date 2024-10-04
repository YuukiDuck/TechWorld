package vnua.k66httt.techworld.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbVnua extends SQLiteOpenHelper {
    static String DB_NAME = "TechWorldShop";
    static int DB_VERSION = 23;

    public dbVnua(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // 1. Bảng tài khoản
        String taiKhoan = "CREATE TABLE TAIKHOAN(" +
                "mataikhoan integer primary key autoincrement," +
                " hoten text not null," +
                " matkhau text not null," +
                " email text not null," +
                " sodienthoai interger not null," +
                " diachi text not null," +
                " sotien integer not null," +
                " loaitaikhoan text not null)";
        sqLiteDatabase.execSQL(taiKhoan);

        sqLiteDatabase.execSQL("INSERT INTO TAIKHOAN VALUES(1,'111','Nguyễn Quý Đức','ducnq@gmail.com','0787613866','ha noi',10000,'admin')");
        sqLiteDatabase.execSQL("INSERT INTO TAIKHOAN VALUES(2,'123','Phạm Đức Hoàng','hoangpd@gmail.com','0787613866','ha noi',10000,'admin')");

        // 2. Bảng loại sản phẩm
        String loaiSanPham = "CREATE TABLE LOAISANPHAM(" +
                "maloaisanpham integer primary key autoincrement," +
                " tenloaisanpham text not null)";
        sqLiteDatabase.execSQL(loaiSanPham);

        sqLiteDatabase.execSQL("INSERT INTO LOAISANPHAM VALUES(1,'Tai nghe')");
        sqLiteDatabase.execSQL("INSERT INTO LOAISANPHAM VALUES(2,'Điện thoại')");
        sqLiteDatabase.execSQL("INSERT INTO LOAISANPHAM VALUES(3,'Sạc dự phòng')");
        sqLiteDatabase.execSQL("INSERT INTO LOAISANPHAM VALUES(4,'Phụ kiện điện thoại')");

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

        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(11,'Ốp lưng iPhone 15 Pro Silicon',23000,3,'Ốp lưng bảo vệ dành cho iPhone 15 Pro, chất liệu silicon mềm mại và dễ cầm nắm.','https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/o/p/op-lung-iphone-15-pro-silicon.png',25,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(12,'Sạc nhanh Anker PowerPort',45000,3,'Sạc nhanh Anker PowerPort với công suất 18W, giúp sạc thiết bị nhanh chóng và an toàn.','https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/s/a/sac-nhanh-anker-powerport.png',30,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(13,'Giá đỡ điện thoại Xịn',60000,3,'Giá đỡ điện thoại tiện dụng, điều chỉnh nhiều góc độ, phù hợp cho việc xem video, đọc sách.','https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/g/i/gia-do-dien-thoai-xin.png',22,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(14,'Dây đeo Apple Watch',75000,3,'Dây đeo cao cấp dành cho Apple Watch, chất liệu silicone mềm mại và thoải mái khi đeo.','https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/d/a/day-deo-apple-watch.png',18,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(15,'Đế sạc không dây',95000,3,'Đế sạc không dây tiện lợi, tương thích với nhiều loại điện thoại, sạc nhanh chóng và an toàn.','https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/d/ế/sạc-không-dây.png',10,0)");

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

        // 6. Bảng chi tiết đơn hàng
        String chiTietDonHang = "CREATE TABLE CHITIETDONHANG(" +
                "machiTietDonHang integer primary key autoincrement," +
                " madonhang integer REFERENCES DONHANG(madonhang)," +
                " masanpham integer REFERENCES SANPHAM(masanpham)," +
                " soluong integer not null)";
        sqLiteDatabase.execSQL(chiTietDonHang);
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

}
