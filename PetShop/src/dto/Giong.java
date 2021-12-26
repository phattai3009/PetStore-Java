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
public class Giong {
    private String maLoai;
    private String maGiong;
    private String tenGiong;
    private String moTa;

    public Giong() {
    }

    public Giong(String maLoai, String maGiong, String tenGiong, String moTa) {
        this.maLoai = maLoai;
        this.maGiong = maGiong;
        this.tenGiong = tenGiong;
        this.moTa = moTa;
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
    
}
