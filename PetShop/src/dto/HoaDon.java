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
public class HoaDon {
    private String maHD;
    private String maNV;
    private String ngayLap;
    private String maKH;
    private long tongTien;
    private String sdt;
    private String tenKH;
    private String tenNV;

    public HoaDon() {
    }

    public HoaDon(String maNV, String maKH, long tongTien, long tienNhan, long tienThua) {
        this.maNV = maNV;
        this.maKH = maKH;
        this.tongTien = tongTien;
    }
    
    public HoaDon(String maHD, String maNV, String ngayLap, String maKH, String sdt, String tenKH, String tenNV) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.ngayLap = ngayLap;
        this.maKH = maKH;
        this.tongTien = tongTien;
        this.sdt = sdt;
        this.tenKH = tenKH;
        this.tenNV = tenNV;
    }

    /**
     * @return the maHD
     */
    public String getMaHD() {
        return maHD;
    }

    /**
     * @param maHD the maHD to set
     */
    public void setMaHD(String maHD) {
        this.maHD = maHD;
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
     * @return the ngayLap
     */
    public String getNgayLap() {
        return ngayLap;
    }

    /**
     * @param ngayLap the ngayLap to set
     */
    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    /**
     * @return the maKH
     */
    public String getMaKH() {
        return maKH;
    }

    /**
     * @param maKH the maKH to set
     */
    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    /**
     * @return the tongTien
     */
    public long getTongTien() {
        return tongTien;
    }

    /**
     * @param tongTien the tongTien to set
     */
    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
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
     * @return the tenKH
     */
    public String getTenKH() {
        return tenKH;
    }

    /**
     * @param tenKH the tenKH to set
     */
    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
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
    
}
