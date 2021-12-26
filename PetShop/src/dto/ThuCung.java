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
public class ThuCung {
    private String maThuCung;
    private String maLoai;
    private String maGiong;
    private int donGia;
    private int soLuong;
    private String moTa;
    private String tenGiong;
    private String tenLoai;

    public ThuCung() {
    }

    public ThuCung(String maThuCung, String maLoai, String maGiong, int donGia, int soLuong) {
        this.maThuCung = maThuCung;
        this.maLoai = maLoai;
        this.maGiong = maGiong;
        this.donGia = donGia;
        this.soLuong = soLuong;
        
    }
    
    public ThuCung(String maThuCung, String tenGiong, int donGia, int soLuong, String moTa) {
        this.maThuCung = maThuCung;
        this.tenGiong = tenGiong;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.moTa = moTa;
    }
    
    public ThuCung(String tenLoai, String tenGiong, String moTa) {
        this.tenLoai = tenLoai;
        this.tenGiong = tenGiong;
        this.moTa = moTa;
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
     * @return the maLoai
     */
    public String getMaLoai() {
        return maLoai;
    }

    /**
     * @param maLoai the maLoai to set
     */
    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    /**
     * @return the maGiong
     */
    public String getMaGiong() {
        return maGiong;
    }

    /**
     * @param maGiong the maGiong to set
     */
    public void setMaGiong(String maGiong) {
        this.maGiong = maGiong;
    }

    /**
     * @return the donGia
     */
    public int getDonGia() {
        return donGia;
    }

    /**
     * @param donGia the donGia to set
     */
    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    /**
     * @return the soLuong
     */
    public int getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    /**
     * @return the moTa
     */
    public String getMoTa() {
        return moTa;
    }

    /**
     * @param moTa the moTa to set
     */
    public void setMoTa(String moTa) {
        this.moTa = moTa;
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

    /**
     * @return the tenLoai
     */
    public String getTenLoai() {
        return tenLoai;
    }

    /**
     * @param tenLoai the tenLoai to set
     */
    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
    
}
