/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Giong;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author lengu
 */
public class GiongDAO {
    private static String sql;
    private static boolean kq = false;
    
    //Lấy danh sách giống
    public static ArrayList<Giong> layDanhSachGiong(){
        ArrayList<Giong> ds = new ArrayList<Giong>();
        try{
            sql = String.format("SELECT * FROM GIONG");
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while(rs.next()){
                Giong ob = new Giong();
                ob.setMaLoai(rs.getString("MaLoai"));
                ob.setMaGiong(rs.getString("MaGiong"));
                ob.setTenGiong(rs.getString("TenGiong"));
                ob.setMoTa(rs.getString("MoTa"));
                ds.add(ob);
            }
            provider.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return ds;
    }
    
    //Kiểm tra giống có tồn tại ko
    public static boolean kiemTraGiong(String ma){
        try{
            sql = "SELECT MAGIONG FROM GIONG Where MAGIONG = '" + ma + "'";
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
    
    //Xóa giống
    public static boolean xoaGiong(String ma){
        try{
            sql = String.format("DELETE FROM GIONG Where MAGIONG='%s'", ma);
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
    
    //Cập nhật thông tin giống
    public static boolean suaGiong(Giong giong){
        try{
            sql = String.format("UPDATE GIONG SET MALOAI = '%s',"
                    + "TENGIONG = N'%s', MOTA = N'%s' WHERE MAGIONG = '%s'",
                    giong.getMaLoai(), giong.getTenGiong(), giong.getMoTa(), giong.getMaGiong());
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
    
    //Thêm giống mới
    public static boolean themGiong(Giong giong){
        try{
            sql = String.format("INSERT INTO GIONG VALUES('%s', '%s', N'%s', N'%s')",
                    giong.getMaLoai(),giong.getMaGiong(),giong.getTenGiong(),giong.getMoTa());
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
    
    //Kiểm tra xem loài có đang nằm trong bảng giống ko
    //Dùng khi xóa loài
    public static boolean kiemTraMaLoai_Giong(String ma){
        try{
            sql = "SELECT MALOAI FROM GIONG WHERE MALOAI = '" + ma + "'";
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
}
