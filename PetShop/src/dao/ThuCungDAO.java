/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ThuCung;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author lengu
 */
public class ThuCungDAO {
    private static String sql;
    private static boolean kq = false;
    
    //Lấy danh sách thú cưng
    public static ArrayList<ThuCung> layDanhSachThuCung(){
        ArrayList<ThuCung> dsTC = new ArrayList<ThuCung>();
        try{
            sql = "SELECT * FROM ThuCung";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while(rs.next()){
                ThuCung tc = new ThuCung();
                tc.setMaThuCung(rs.getString("MaTC"));
                tc.setMaLoai(rs.getString("MaLoai"));
                tc.setMaGiong(rs.getString("MaGiong"));
                tc.setDonGia(rs.getInt("DonGia"));
                tc.setSoLuong(rs.getInt("TonKho"));
                dsTC.add(tc);
            }
            provider.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return dsTC;
    }
    
    //Kiểm tra thú cưng có tồn tại hay không
    public static boolean kiemTraThuCung(String maTC){
        try{
            sql = "SELECT * FROM THUCUNG Where MaTC = '" + maTC + "'";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            if(rs.next())
                return true; //Thú cưng này đã tồn tại
            provider.close();
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return false; //Chưa tồn tại mã thú cưng này
    }
    
    //Xóa thú cưng dựa trên mã TC
    public static boolean xoaThuCung(String maTC){
        try{
            sql = String.format("DELETE FROM THUCUNG Where MaTC='%s'", maTC);
            SQLServerDataProvider provider=new SQLServerDataProvider();
            provider.open();
            int n = provider.executeUpdate(sql);
            if(n==1){
                kq=true; //Xóa thành công
            }
            provider.close();
            }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return kq;
    }
    
    //Sửa thông tin thú cưng dựa trên mã TC
    public static boolean suaThuCung(ThuCung tc){
        try{
            sql = String.format("UPDATE THUCUNG SET MALOAI = '%s',"
                    + " MAGIONG = '%s', DONGIA = %d, TONKHO = %d WHERE MATC = '%s'",
                    tc.getMaLoai(),tc.getMaGiong(),tc.getDonGia(), tc.getSoLuong(), tc.getMaThuCung());
            SQLServerDataProvider provider=new SQLServerDataProvider();
            provider.open();
            int n = provider.executeUpdate(sql);
            if(n==1){
                kq=true; //Sửa thành công
            }
            provider.close();
            }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return kq;
    }
    
    //Thêm mới 1 thú cưng
    public static boolean themThuCung(ThuCung tc){
        try{
            sql = String.format("INSERT INTO THUCUNG VALUES('%s', '%s', '%s', %d, %d)",
                    tc.getMaThuCung(),tc.getMaLoai(),tc.getMaGiong(),tc.getDonGia(), tc.getSoLuong());
            SQLServerDataProvider provider=new SQLServerDataProvider();
            provider.open();
            int n = provider.executeUpdate(sql);
            if(n==1){
                kq=true; //Thêm thành công
            }
            provider.close();
            }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return kq;
    }
    
    //Tìm kiếm thú cưng thông qua mã tc, mã loài hoặc mã giống
    public static ArrayList<ThuCung> timKiemThuCung(String duLieuTim){
        ArrayList<ThuCung> dsTC = new ArrayList<ThuCung>();
        try{
            sql = "SELECT * FROM THUCUNG WHERE MATC LIKE '%" + duLieuTim +
                    "%' OR MALOAI LIKE '%" + duLieuTim + "%' OR MAGIONG LIKE '%" + duLieuTim + "%'";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while(rs.next()){
                ThuCung tc = new ThuCung();
                tc.setMaThuCung(rs.getString("MaTC"));
                tc.setMaLoai(rs.getString("MaLoai"));
                tc.setMaGiong(rs.getString("MaGiong"));
                tc.setDonGia(rs.getInt("DonGia"));
                tc.setSoLuong(rs.getInt("TonKho"));
                dsTC.add(tc);
            }
            provider.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return dsTC;
    }
    
    //Tìm kiếm thú cưng dựa vào mô tả và khoảng giá
    public static ArrayList<ThuCung> timKiemThuCungTheoGiaVaMoTa(String duLieuTim, int giaA, int giaB){
        ArrayList<ThuCung> dsTC = new ArrayList<ThuCung>();
        try{
            sql = "SELECT * FROM ThuCung WHERE (MATC LIKE '%" + duLieuTim +
                    "%' OR THUCUNG.MALOAI LIKE '%" + duLieuTim + "%' OR THUCUNG.MAGIONG LIKE "
                    + "'%" + duLieuTim + "%') and (DONGIA BETWEEN "+giaA+" AND "+giaB+")";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while(rs.next()){
                ThuCung tc = new ThuCung();
                tc.setMaThuCung(rs.getString("MaTC"));
                tc.setMaLoai(rs.getString("MaLoai"));
                tc.setMaGiong(rs.getString("MaGiong"));
                tc.setDonGia(rs.getInt("DonGia"));
                tc.setSoLuong(rs.getInt("TonKho"));
                dsTC.add(tc);
            }
            provider.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return dsTC;
    }
    
    //Tìm kiếm thú cưng dựa vào khoảng giá
    public static ArrayList<ThuCung> timKiemThuCungTheoGia(int giaA, int giaB){
        ArrayList<ThuCung> dsTC = new ArrayList<ThuCung>();
        try{
            sql = "SELECT * FROM THUCUNG WHERE (DONGIA BETWEEN "+giaA+" AND "+giaB+")";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while(rs.next()){
                ThuCung tc = new ThuCung();
                tc.setMaThuCung(rs.getString("MaTC"));
                tc.setMaLoai(rs.getString("MaLoai"));
                tc.setMaGiong(rs.getString("MaGiong"));
                tc.setDonGia(rs.getInt("DonGia"));
                tc.setSoLuong(rs.getInt("TonKho"));
                dsTC.add(tc);
            }
            provider.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return dsTC;
    }
    
    //Lấy tổng số lượng thú cưng đang có trong cửa hàng
    public static int laySoLuongThuCung(String maLoai){
        int soLuong = 0;
        try{
            sql = "SELECT SUM(TONKHO) FROM THUCUNG WHERE MALOAI = '"+maLoai+"'";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            if(rs.next()){
                soLuong = rs.getInt(1);
            }
            provider.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return soLuong;
    }
    
    //Lấy thông tin của thú cưng dựa vào mã tc 
    //Dùng cho tab thú cưng của nhân viên
    public static ThuCung layThongTinThuCung(String maTC){
        ThuCung tc = new ThuCung();
        try{
            sql = "SELECT TENLOAI, TENGIONG, MOTA FROM ThuCung T, GIONG G, LOAI L "
                    + "WHERE T.MALOAI=L.MALOAI AND T.MAGIONG = G.MAGIONG AND MATC = '"+maTC+"'";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            if(rs.next()){
                tc.setTenLoai(rs.getString(1));
                tc.setTenGiong(rs.getString(2));
                tc.setMoTa(rs.getString(3));    
            }
            provider.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return tc;
    }
    
    //Kiểm tra xem mã loài có đang được dùng trong bảng thú cưng hay không
    public static boolean kiemTraMaLoai_ThuCung(String ma){
        try{
            sql = "SELECT MATC FROM THUCUNG WHERE MATC = '" + ma + "'";
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
    
    ////Kiểm tra xem mã giống có đang được dùng trong bảng thú cưng hay không
    public static boolean kiemTraMaGiong_ThuCung(String ma){
        try{
            sql = "SELECT MAGIONG FROM GIONG WHERE MAGIONG = '" + ma + "'";
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
