/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.KhachHang;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author lengu
 */
public class KhachHangDAO {
    private static String sql;
    private static boolean kq = false;
    
    //Lấy danh sách khách hàng
    public static ArrayList<KhachHang> layDanhSachKhachHang(){
        ArrayList<KhachHang> dsKH = new ArrayList<KhachHang>();
        try{
            sql = "SELECT * FROM KhachHang";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            String oldNgayDK = "";
            String newNgayDK = "";
            
            while(rs.next()){
                KhachHang kh = new KhachHang();
                kh.setMakH(rs.getString("MaKH"));
                kh.setHoTen(rs.getString("HoTen"));
                kh.setGioiTinh(rs.getString("GioiTinh"));
                kh.setSdt(rs.getString("SDT"));
                kh.setDiaChi(rs.getString("DiaChi"));
                oldNgayDK = rs.getString("NgayDK");
                try{
                    java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(oldNgayDK);
                    newNgayDK = new SimpleDateFormat("dd-MM-yyyy").format(date);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                kh.setNgayDK(newNgayDK);
                kh.setDoanhSo(rs.getInt("DoanhSo"));
                
                dsKH.add(kh);
            }
            provider.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return dsKH;
    }
    
    //Tìm kiếm khách hàng dựa vào mã KH, họ tên, giới tính, sđt hoặc địa chỉ
    public static ArrayList<KhachHang> timKiemKhachHang(String duLieuTim){
        ArrayList<KhachHang> dsKH = new ArrayList<KhachHang>();
        try{
            sql = "SELECT * FROM KHACHHANG WHERE MAKH LIKE '%" + duLieuTim +"%' "
                    + "OR HOTEN LIKE N'%" + duLieuTim + "%' OR GIOITINH LIKE N'%" + duLieuTim + "%'"
                    + "OR SDT LIKE '%" + duLieuTim + "%' OR DIACHI LIKE N'%" + duLieuTim + "%'";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while(rs.next()){
                KhachHang kh = new KhachHang();
                kh.setMakH(rs.getString("MaKH"));
                kh.setHoTen(rs.getString("HoTen"));
                kh.setGioiTinh(rs.getString("GioiTinh"));
                kh.setSdt(rs.getString("SDT"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setNgayDK((rs.getString("NgayDK")));
                kh.setDoanhSo(rs.getInt("DoanhSo"));
                
                dsKH.add(kh);
            }
            provider.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return dsKH;
    }
    
    //Lấy thông tin khách hàng
    //Dùng trong tab Khách Hàng của nhân viên
    public static KhachHang layThongTinKhachHangTheoMaKH(String maKH){
        KhachHang kh = new KhachHang();
        try{
            sql = "SELECT GIOITINH, DIACHI, NGAYDK, SDT FROM KhachHang WHERE MAKH = '"+maKH+"'";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while(rs.next()){
                kh.setGioiTinh(rs.getString("GioiTinh"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setNgayDK((rs.getString("NgayDK")));
                kh.setSdt(rs.getString("SDT"));
                
            }
            provider.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return kh;
    }
    
    //Kiểm tra KH đã tồn tại hay chưa
    public static boolean kiemTraMaKhachHang(String ma){
        try{
            sql = "SELECT MAKH FROM KHACHHANG Where MAKH = '" + ma + "'";
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
    
    //Kiểm tra SĐT KH đã tồn tại hay chưa
    //Dùng khi cập nhật thông tin hoặc thêm mới KH
    public static boolean kiemTraSDTKhachHang(String ma){
        try{
            sql = "SELECT SDT FROM KHACHHANG Where SDT = '" + ma + "'";
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
    
    //Xóa khách hàng
    public static boolean xoaKhachHang(String ma){
        try{
            sql = String.format("DELETE FROM KHACHHANG Where MAKH='%s'", ma);
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
    
    //Cập nhật thông tin KH
    public static boolean suaKhachHang(KhachHang kh){
        try{
            sql = String.format("SET DATEFORMAT DMY UPDATE KHACHHANG SET HOTEN = N'%s', GIOITINH = N'%s',"
                    + " SDT = '%s', DIACHI = N'%s', NGAYDK = '%s' WHERE MAKH = '%s'",
                    kh.getHoTen(), kh.getGioiTinh(), kh.getSdt(), kh.getDiaChi(), kh.getNgayDK(), kh.getMakH());
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
    
    //Thêm mới KH
    public static boolean themKhachHang(KhachHang kh){
        try{
            sql = String.format("SET DATEFORMAT DMY INSERT INTO KHACHHANG(MAKH, HOTEN, GIOITINH, SDT, "
                    + "DIACHI, NGAYDK) VALUES('%s', N'%s', '%s', N'%s', N'%s', '%s')",
                    kh.getMakH(), kh.getHoTen(), kh.getGioiTinh(), kh.getSdt(), kh.getDiaChi(), kh.getNgayDK());
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
    
    //Lấy danh sách Top 10 KH đã chi nhiều tiền nhất
    public static ArrayList<KhachHang> layTop10KhachHang(){
        ArrayList<KhachHang> dsKH = new ArrayList<KhachHang>();
        try{
            sql = "SELECT DISTINCT TOP 10 K.MAKH, HOTEN, SDT, DOANHSO FROM KHACHHANG K, "
                    + "HOADON H WHERE K.MAKH = H.MAKH ORDER BY DOANHSO DESC";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while(rs.next()){
                KhachHang kh = new KhachHang();
                kh.setMakH(rs.getString(1));
                kh.setHoTen(rs.getString(2));
                kh.setSdt(rs.getString(3));
                kh.setDoanhSo(rs.getInt(4));
                
                dsKH.add(kh);
            }
            provider.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return dsKH;
    }
}
