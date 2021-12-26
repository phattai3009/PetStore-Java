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
public class TaiKhoan {
    private String maNV;
    private String tenTK;
    private String matKhau;

    public TaiKhoan() {
    }

    public TaiKhoan(String maNV, String tenTK, String matKhau) {
        this.maNV = maNV;
        this.tenTK = tenTK;
        this.matKhau = matKhau;
    }
    
    public TaiKhoan(String maNV, String tenTK) {
        this.maNV = maNV;
        this.tenTK = tenTK;
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
     * @return the tenTK
     */
    public String getTenTK() {
        return tenTK;
    }

    /**
     * @param tenTK the tenTK to set
     */
    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }

    /**
     * @return the matKhau
     */
    public String getMatKhau() {
        return matKhau;
    }

    /**
     * @param matKhau the matKhau to set
     */
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    
}
