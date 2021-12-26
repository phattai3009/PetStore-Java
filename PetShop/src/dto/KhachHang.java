/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;


/**
 *
 * @author lengu
 */
public class KhachHang {
    private String makH;
    private String tenKH;
    private String gioiTinh;
    private String sdt;
    private String diaChi;
    private String ngayDK;
    private int doanhSo;
    

    public KhachHang() {
    }

    public KhachHang(String makH, String hoTen, String gioiTinh, String sdt, String diaChi, String ngayDK, int doanhSo) {
        this.makH = makH;
        this.tenKH = hoTen;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.ngayDK = ngayDK;
        this.doanhSo = doanhSo;
    }
    
    public KhachHang(String makH, String hoTen, String gioiTinh, String sdt, String diaChi, String ngayDK) {
        this.makH = makH;
        this.tenKH = hoTen;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.ngayDK = ngayDK;
    }

    
    /**
     * @return the makH
     */
    public String getMakH() {
        return makH;
    }

    /**
     * @param makH the makH to set
     */
    public void setMakH(String makH) {
        this.makH = makH;
    }

    /**
     * @return the hoTen
     */
    public String getHoTen() {
        return tenKH;
    }

    /**
     * @param hoTen the hoTen to set
     */
    public void setHoTen(String hoTen) {
        this.tenKH = hoTen;
    }

    /**
     * @return the gioiTinh
     */
    public String getGioiTinh() {
        return gioiTinh;
    }

    /**
     * @param gioiTinh the gioiTinh to set
     */
    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    /**
     * @return the sdt
     */
    public String getSdt() {
        return sdt;
    }

    /**
     * @param sdt the sdt to set
     */
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    /**
     * @return the diaChi
     */
    public String getDiaChi() {
        return diaChi;
    }

    /**
     * @param diaChi the diaChi to set
     */
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    /**
     * @return the ngayDK
     */
    public String getNgayDK() {
        return ngayDK;
    }

    /**
     * @param ngayDK the ngayDK to set
     */
    public void setNgayDK(String ngayDK) {
        this.ngayDK = ngayDK;
    }

    /**
     * @return the doanhSo
     */
    public int getDoanhSo() {
        return doanhSo;
    }

    /**
     * @param doanhSo the doanhSo to set
     */
    public void setDoanhSo(int doanhSo) {
        this.doanhSo = doanhSo;
    }
    
    
}
