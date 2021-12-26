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
public class GioHang {
    private String maTC;
    private String tenGiong;
    private long donGia;
    private long soLuong;
    private long thanhTien;

    public GioHang() {
    }

    public GioHang(String maTC, String tenGiong, long donGia, long soLuong, long thanhTien) {
        this.maTC = maTC;
        this.tenGiong = tenGiong;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }
    
    public GioHang(String maTC, String tenGiong, long donGia, long soLuong) {
        this.maTC = maTC;
        this.tenGiong = tenGiong;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    /**
     * @return the maTC
     */
    public String getMaTC() {
        return maTC;
    }

    /**
     * @param maTC the maTC to set
     */
    public void setMaTC(String maTC) {
        this.maTC = maTC;
    }

    /**
     * @return the donGia
     */
    public long getDonGia() {
        return donGia;
    }

    /**
     * @param donGia the donGia to set
     */
    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }

    /**
     * @return the soLuong
     */
    public long getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(long soLuong) {
        this.soLuong = soLuong;
    }

    /**
     * @return the thanhTien
     */
    public long getThanhTien() {
        return thanhTien;
    }

    /**
     * @param thanhTien the thanhTien to set
     */
    public void setThanhTien(long thanhTien) {
        this.thanhTien = thanhTien;
    }

    /**
     * @return the tenGiong
     */
    public String getTenGiong() {
        return tenGiong;
    }

    /**
     * @param tenGiong the tenGiong to set
     */
    public void setTenGiong(String tenGiong) {
        this.tenGiong = tenGiong;
    }
    
}
