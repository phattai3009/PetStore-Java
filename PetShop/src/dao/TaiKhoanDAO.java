/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.TaiKhoan;
import java.sql.ResultSet;
import jframemain.jFQuanLy;

/**
 *
 * @author lengu
 */
public class TaiKhoanDAO {
    private static String sql;
    private static boolean kq = false;
    
    //Kiểm tra xem tài khoản có tồn tại hay chưa
    public static boolean kiemTraTaiKhoan(String ma){
        try{
            sql = "SELECT TENTK FROM TAIKHOAN Where TENTK = '" + ma + "'";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            if(rs.next())
                return true; //Đã tồn tại
            provider.close();
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return false; //Chưa tồn tại
    }
    
    //Xóa một tài khoản nhân viên
    public static boolean xoaTaiKhoan(String ma){
        try{
            sql = String.format("DELETE FROM TAIKHOAN Where TENTK='%s'", ma);
            SQLServerDataProvider provider=new SQLServerDataProvider();
            provider.open();
            int n = provider.executeUpdate(sql);
            if(n==1){
                kq=true;
            }
            provider.close();
            }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return kq;
    }
    
    //Cập nhật thông tin tài khoản
    //Mật khẩu là thông tin bảo mật nên ko cập nhật qua hàm này
    public static boolean suaTaiKhoan(TaiKhoan tk){
        try{
            sql = String.format("UPDATE TAIKHOAN SET TENTK = '%s' WHERE MANV = '%s'",
                    tk.getTenTK(), tk.getMaNV());
            SQLServerDataProvider provider=new SQLServerDataProvider();
            provider.open();
            int n = provider.executeUpdate(sql);
            if(n==1){
                kq=true;
            }
            provider.close();
            }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return kq;
    }
    
    //Thêm mới 1 tài khoản
    //Khi thêm mới, mật khẩu sẽ được đặt theo mật khẩu mặc định
    public static boolean themTaiKhoan(TaiKhoan tk){
        try{
            sql = String.format("INSERT INTO TAIKHOAN(MANV, TENTK, MATKHAU) VALUES('%s', '%s', '%s')", 
                    tk.getMaNV(), tk.getTenTK(), jFQuanLy.pswDefault);
            SQLServerDataProvider provider=new SQLServerDataProvider();
            provider.open();
            int n = provider.executeUpdate(sql);
            if(n==1){
                kq=true;
            }
            provider.close();
            }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return kq;
    }
    
    //Đổi mật khẩu của tài khoản
    public static boolean doiMatKhau(String user, String pass){
        try{
            sql = "UPDATE TAIKHOAN SET MATKHAU = '"+pass+"' WHERE TENTK = '"+user+"'";
            SQLServerDataProvider provider=new SQLServerDataProvider();
            provider.open();
            int n = provider.executeUpdate(sql);
            if(n==1){
                kq=true;
            }
            provider.close();
            }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return kq;
    }
    
    //Kiểm tra đăng nhập dành cho user quản lý
    public static boolean checkLoginQuanLy(String user, String pass){
        try{
            sql = "SELECT TENTK, MATKHAU, MACV FROM TAIKHOAN, NHANVIEN Where "
                    + "TAIKHOAN.MANV = NHANVIEN.MANV AND (TENTK = '" + user + "' AND MATKHAU = '" + pass + "' AND MACV = 'QUANLY')";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            if(rs.next())
                return true;
                
            provider.close();
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    //Kiểm tra đăng nhập dành cho user của nhân viên
    public static boolean checkLoginNhanVien(String user, String pass){
        try{
            sql = "SELECT TENTK, MATKHAU, MACV FROM TAIKHOAN, NHANVIEN Where "
                    + "TAIKHOAN.MANV = NHANVIEN.MANV AND (TENTK = '" + user + "' AND MATKHAU = '" + pass + "')";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            if(rs.next())
                return true;
                
            provider.close();
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    //Kiểm tra mật khẩu
    //Dùng khi đổi mật khẩu
    public static String layMatKhau(String ma){
        String pass = "";
        try{
            sql = "SELECT MATKHAU FROM TAIKHOAN Where TENTK = '" + ma + "'";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            if(rs.next())
                pass = rs.getString(1);
            provider.close();
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return pass;
    }
    
    //Lấy thông tin tài khoản
    //Dùng để hiện thị tên TK trong tab thông ti
    public static TaiKhoan layThongTinTaiKhoan(String user){
        TaiKhoan tk = new TaiKhoan();
        try{
            sql = "SELECT TENTK FROM TAIKHOAN WHERE TENTK = '"+user+"'";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while(rs.next()){
                tk.setTenTK(rs.getString("TENTK"));
            }
            provider.close();
        }
        
        catch(Exception ex){
            ex.printStackTrace();
        }
        return tk;
    }
}
