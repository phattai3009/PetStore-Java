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
public class CTHoaDon {
    private int maHD;
    private String maThuCung;
    private long soLuong;
    private long donGia;
    private long thanhTien;

    public CTHoaDon() {
    }

    public CTHoaDon(int maHD, String maThuCung, long soLuong, long donGia) {
        this.maHD = maHD;
        this.maThuCung = maThuCung;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }
    
    public CTHoaDon(int maHD, String maThuCung, long soLuong) {
        this.maHD = maHD;
        this.maThuCung = maThuCung;
        this.soLuong = soLuong;
    }

    /**
     * @return the maHD
     */
    public int getMaHD() {
        return maHD;
    }

    /**
     * @param maHD the maHD to set
     */
    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    /**
     * @return the maThuCung
     */
    public String getMaThuCung() {
        return maThuCung;
    }

    /**
     * @param maThuCung the maThuCung to set
     */
    public void setMaThuCung(String maThuCung) {
        this.maThuCung = maThuCung;
    }

    /**
     * @return the soLong
     */
    public long getSoLong() {
        return soLuong;
    }

    /**
     * @param soLong the soLong to set
     */
    public void setSoLong(long soLong) {
        this.soLuong = soLong;
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
}
