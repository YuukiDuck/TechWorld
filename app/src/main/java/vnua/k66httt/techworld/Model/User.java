package vnua.k66httt.techworld.Model;

public class User {
    private int maTaiKhoan;
    private String tenDangNhap;
    private String gioiTinh;
    private String matKhau;
    private String hoTen;
    private String email;
    private String diaChi;
    private int soTien;
    private String loaiTaiKhoan;

    public User(String tenDangNhap,String gioiTinh, String matKhau, String hoTen, String email, String diaChi, int soTien, String loaiTaiKhoan, String anhnguoidung) {
        this.tenDangNhap = tenDangNhap;
        this.gioiTinh = gioiTinh;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.email = email;
        this.diaChi = diaChi;
        this.soTien = soTien;
        this.loaiTaiKhoan = loaiTaiKhoan;
    }

    public User() {
    }

    public int getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(int maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public String getLoaiTaiKhoan() {
        return loaiTaiKhoan;
    }

    public void setLoaiTaiKhoan(String loaiTaiKhoan) {
        this.loaiTaiKhoan = loaiTaiKhoan;
    }
}
