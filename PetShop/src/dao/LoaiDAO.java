/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Loai;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author lengu
 */
public class LoaiDAO {
    private static String sql;
    private static boolean kq = false;
    
    //Lấy danh sách loài
    public static ArrayList<Loai> layDanhSachLoai(JComboBox cbb){
        ArrayList<Loai> ds = new ArrayList<Loai>();
        try{
            sql = "SELECT * FROM LOAI";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            cbb.removeAllItems();
            cbb.addItem("Chọn Loài");
            while(rs.next()){
                Loai ob = new Loai();
                ob.setMaLoai(rs.getString("MaLoai"));
                ob.setTenLoai(rs.getString("TenLoai"));
                ds.add(ob);
                cbb.addItem(rs.getString("MaLoai"));
            }
            provider.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return ds;
    }
    
    //Kiểm tra loài đã tồn tại hay chưa
    public static boolean kiemTraLoai(String ma){
        try{
            sql = "SELECT MALOAI FROM LOAI Where MALOAI = '" + ma + "'";
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
    
    //Xóa loài
    public static boolean xoaLoai(String ma){
        try{
            sql = String.format("DELETE FROM LOAI Where MALOAI='%s'", ma);
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
    
    //Sửa loài
    public static boolean suaLoai(Loai loai){
        try{
            sql = String.format("UPDATE LOAI SET TENLOAI = N'%s' WHERE MALOAI = '%s'",
                    loai.getTenLoai(), loai.getMaLoai());
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
    
    //Thêm loài mới
    public static boolean themLoai(Loai loai){
        try{
            sql = String.format("INSERT INTO LOAI VALUES('%s', N'%s')",
                    loai.getMaLoai(), loai.getTenLoai());
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
}
