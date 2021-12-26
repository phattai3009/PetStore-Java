/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.CTHoaDon;
import dto.HoaDon;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author lengu
 */
public class HoaDonDAO {
    private static String sql;
    private static boolean kq = false;
    
    //Lấy danh sách hóa đơn
    public static ArrayList<HoaDon> layDanhSachHoaDon(){
        ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
        try{
            String sql = "SELECT MAHD, NV.HOTEN, NGAYLAP, KH.MAKH, KH.HOTEN, TONGTIEN, KH.SDT "
                    + "FROM HoaDon HD, NHANVIEN NV, KHACHHANG KH WHERE HD.MANV = NV.MANV AND HD.MAKH = KH.MAKH";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            String oldNgayLap = "";
            String newNgayLap = "";
            while(rs.next()){
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString(1));
                hd.setTenNV(rs.getString(2));
                oldNgayLap = rs.getString("NgayLap");
                try{
                    java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(oldNgayLap);
                    newNgayLap = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(date);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                hd.setNgayLap(newNgayLap);
                hd.setMaKH(rs.getString(4));
                hd.setTenKH(rs.getString(5));
                hd.setTongTien(rs.getLong(6));
                hd.setSdt(rs.getString(7));

                dsHD.add(hd);
            }
            provider.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return dsHD;
    }
    
    //Lấy chi tiết hóa đơn
    public static ArrayList<CTHoaDon> layChiTietHoaDon(String maHD){
        ArrayList<CTHoaDon> dsHD = new ArrayList<CTHoaDon>();
        try{
            String sql = "SELECT CT.MAHD, CT.MATC, SOLUONG, DONGIA, "
                    + "THANHTIEN FROM CTHOADON CT, HOADON HD, THUCUNG TC WHERE "
                    + "CT.MAHD = HD.MAHD AND CT.MATC = TC.MATC AND CT.MAHD = '" +maHD+"'";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while(rs.next()){
                CTHoaDon ct = new CTHoaDon();
                ct.setMaHD(rs.getInt(1));
                ct.setMaThuCung(rs.getString(2));
                ct.setSoLong(rs.getLong(3));
                ct.setDonGia(rs.getLong(4));
                ct.setThanhTien(rs.getLong(5));
                dsHD.add(ct);
            }
            provider.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return dsHD;
    }
    
    //Tìm kiếm hóa đơn
    public static ArrayList<HoaDon> timKiemHoaDon(String duLieuTim){
        ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
        try{
            String sql = "SELECT MAHD, NV.HOTEN, NGAYLAP, KH.MAKH, KH.HOTEN, TONGTIEN, KH.SDT "
                    + "FROM HoaDon HD, NHANVIEN NV, KHACHHANG KH WHERE HD.MANV = NV.MANV AND HD.MAKH = KH.MAKH "
                    + "AND (MAHD LIKE '%" + duLieuTim + "%' OR NV.HOTEN LIKE '%" + duLieuTim + "%' "
                    + "OR NGAYLAP LIKE '%" + duLieuTim + "%' OR KH.MAKH LIKE '%" + duLieuTim + "%' "
                    + "OR KH.HOTEN LIKE '%" + duLieuTim + "%' OR KH.SDT LIKE '%" + duLieuTim + "%' "
                    + "OR HD.MANV LIKE '%" + duLieuTim + "%')";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            String oldNgayLap = "";
            String newNgayLap = "";
            while(rs.next()){
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString(1));
                hd.setTenNV(rs.getString(2));
                oldNgayLap = rs.getString("NgayLap");
                try{
                    java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(oldNgayLap);
                    newNgayLap = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(date);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                hd.setNgayLap(newNgayLap);
                hd.setMaKH(rs.getString(4));
                hd.setTenKH(rs.getString(5));
                hd.setTongTien(rs.getLong(6));
                hd.setSdt(rs.getString(7));
                
                dsHD.add(hd);
            }
            provider.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return dsHD;
    }
    
    //Thêm mới hóa đơn
    public static boolean themHoaDon(HoaDon hd){
        try{
            sql = String.format("INSERT INTO HOADON(MANV, MAKH, TONGTIEN) "
                    + "VALUES('%s', '%s', %d)", hd.getMaNV(),
                    hd.getMaKH(), hd.getTongTien());
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
    
    //Thêm chi tiết hóa đơn
    public static boolean themChiTietHoaDon(CTHoaDon ct){
        try{
            sql = String.format("INSERT INTO CTHOADON(MAHD, MATC, SOLUONG) "
                    + "VALUES('%s', '%s', %d)", ct.getMaHD(), ct.getMaThuCung(), ct.getSoLong());
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
    
    //Lấy mã hóa đơn
    //Dùng khi thanh toán
    public static int layMaHoaDon(){
        int maHD = 0;
        try{
            String sql = "SELECT MAHD FROM HOADON WHERE MAHD = (SELECT MAX(MAHD) FROM HOADON)";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while(rs.next()){
                maHD = rs.getInt("MAHD");
            }
            provider.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return maHD;
    }
    
    //Lấy tổng số hóa đơn của khách hàng
    public static int layHoaDonCuaKH(String maKH){
        int soHD = 0;
        try{
            
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            String sql = "SELECT COUNT(MAHD) FROM KHACHHANG K, HOADON H WHERE K.MAKH = H.MAKH AND K.MAKH = '" + maKH +"'";
            ResultSet rs = provider.executeQuery(sql);
            if(rs.next()){
                soHD = rs.getInt(1);
            } 
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return soHD;
    }
    
    //Lấy tổng số tiền KH đã chi tiêu ở cửa hàng
    public static long layTongTienCuaKH(String maKH){
        long tong = 0;
        try{
            
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            String sql = "SELECT SUM(DOANHSO) FROM KHACHHANG WHERE MAKH = '" + maKH +"'";
            ResultSet rs = provider.executeQuery(sql);
            if(rs.next()){
                tong = rs.getLong(1);
            } 
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return tong;
    }
    
    //Thống kê tổng số hóa đơn
    public static int thongKeHoaDon(String from, String to){
        int soHD = 0;
        try{
            String sql = "exec ThongKeHD @from = '"+from+"', @to = '"+to+"'";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while(rs.next()){
                soHD = rs.getInt(1);
            }
            provider.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return soHD;
    }
    
    //Thống kê tổng số thú cưng đã bán
    public static int thongKeThuCung(String from, String to){
        int soTC = 0;
        try{
            String sql = "exec ThongKeTC @from = '"+from+"', @to = '"+to+"'";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while(rs.next()){
                soTC = rs.getInt(1);
            }
            provider.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return soTC;
    }
    
    //Thống kê tổng số tiền đã bán được
    public static long thongKeTongTien(String from, String to){
        long tongTien = 0;
        try{
            String sql = "exec ThongKeTongTien @from = '"+from+"', @to = '"+to+"'";
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
    
    //Kiểm tra khách hàng đã tồn tại hay chưa
    public static boolean kiemTraMaKhachHang(String ma){
        try{
            sql = "SELECT MAKH FROM HOADON WHERE MAKH = '" + ma + "'";
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
    
    //Kiểm tra xem khách hàng có đang nằm trong hóa đơn ko
    //Dùng khi xóa KH
    public static boolean kiemTraMaThuCung_HoaDon(String ma){
        try{
            sql = "SELECT MATC FROM CTHOADON WHERE MATC = '" + ma + "'";
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
    
    //Kiểm tra xem nhân viên có đang nằm trong hóa đơn ko
    //Dùng khi xóa NV
    public static boolean kiemTraMaNhanVien_HoaDon(String ma){
        try{
            sql = "SELECT MANV FROM HOADON WHERE MANV = '" + ma + "'";
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
