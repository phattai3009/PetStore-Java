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
public class NhanVien {
    private String maNV;
    private String maChucVu;
    private String tenNV;
    private String gioiTinh;
    private String ngaySinh;
    private String diaChi;
    private String sdt;
    private int luong;
    private String cmnd;
    private String ngayVL;

    public NhanVien() {
    }

    public NhanVien(String maNV, String maChucVu, String tenNV, String gioiTinh, String ngaySinh, String diaChi, String sdt, int luong, String cmnd, String ngayVL) {
        this.maNV = maNV;
        this.maChucVu = maChucVu;
        this.tenNV = tenNV;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.luong = luong;
        this.cmnd = cmnd;
        this.ngayVL = ngayVL;
    }
    

    /**
     * @return the maNV
     */
    public String getMaNV() {
        return maNV;
    }

    /**
     * @param maNV the maNV to set
     */
    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    /**
     * @return the maChucVu
     */
    public String getMaChucVu() {
        return maChucVu;
    }

    /**
     * @param maChucVu the maChucVu to set
     */
    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }

    /**
     * @return the tenNV
     */
    public String getTenNV() {
        return tenNV;
    }

    /**
     * @param tenNV the tenNV to set
     */
    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
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
     * @return the ngaySinh
     */
    public String getNgaySinh() {
        return ngaySinh;
    }

    /**
     * @param ngaySinh the ngaySinh to set
     */
    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
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
     * @return the luong
     */
    public int getLuong() {
        return luong;
    }

    /**
     * @param luong the luong to set
     */
    public void setLuong(int luong) {
        this.luong = luong;
    }

    /**
     * @return the cmnd
     */
    public String getCmnd() {
        return cmnd;
    }

    /**
     * @param cmnd the cmnd to set
     */
    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    /**
     * @return the ngayVL
     */
    public String getNgayVL() {
        return ngayVL;
    }

    /**
     * @param ngayVL the ngayVL to set
     */
    public void setNgayVL(String ngayVL) {
        this.ngayVL = ngayVL;
    }
    
}
