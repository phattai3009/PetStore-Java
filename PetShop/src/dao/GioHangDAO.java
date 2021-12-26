/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.GioHang;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author lengu
 */
public class GioHangDAO {
    private static String sql;
    private static boolean kq = false;
    
    //Lấy danh danh sách thú cưng có trong giỏ hàng
    public static ArrayList<GioHang> layDSItemGioHang(){
        ArrayList<GioHang> dsItem = new ArrayList<GioHang>();
        try{
            
            String sql = "SELECT GH.MATC, TENGIONG, DONGIA, GH.SOLUONG, THANHTIEN"
                    + " from GIOHANG GH, THUCUNG TC, GIONG G WHERE GH.MATC = TC.MATC AND G.MAGIONG = TC.MAGIONG";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while(rs.next()){
                GioHang gh = new GioHang();
                gh.setMaTC(rs.getString(1));
                gh.setTenGiong(rs.getString(2));
                gh.setDonGia(rs.getLong(3));
                gh.setSoLuong(rs.getLong(4));
                gh.setThanhTien(rs.getLong(5));
                
                dsItem.add(gh);
            }
            provider.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return dsItem;
    }
    
    //Thêm thú cưng vào giỏ hàng
    public static boolean themItemVaoGioHang(GioHang gh){
        try{
            sql = String.format("INSERT INTO GIOHANG(MATC, SOLUONG) VALUES('%s', %d)",
                    gh.getMaTC(), gh.getSoLuong());
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
    
    //Cập nhật số lượng thú cưng trong giỏ hàng
    public static boolean suaItemGioHang(GioHang gh){
        try{
            sql = String.format("UPDATE GIOHANG SET SOLUONG = %d WHERE MATC = '%s'",
                    gh.getSoLuong(), gh.getMaTC());
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
    
    //Kiểm tra xem thú cưng đã có trong giỏ hàng hay chưa
    public static boolean kiemTraGioHang(String ma){
        try{
            sql = "SELECT MATC FROM GIOHANG Where MATC = '" + ma + "'";
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
    
    //Xóa 1 thú cưng ra khỏi giỏ hàng
    public static boolean xoa1ItemGioHang(String ma){
        try{
            sql = String.format("DELETE FROM GIOHANG Where MATC='%s'", ma);
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
    
    //Xóa tất cả thú cưng ra khỏi giỏ hàng
    public static boolean xoaGioHang(){
        try{
            sql = "DELETE FROM GIOHANG";
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
    
    //Lấy tổng tiền của giỏ hàng
    public static long getTongTienGioHang(){
        long tongTien = 0;
        try{
            
            String sql = "exec TongTienGioHang";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while(rs.next()){
                tongTien = rs.getLong(1);
            }
            provider.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return tongTien;
    }
}
