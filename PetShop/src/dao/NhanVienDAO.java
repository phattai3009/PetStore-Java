/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.NhanVien;
import dto.TaiKhoan;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author lengu
 */
public class NhanVienDAO {
    private static String sql;
    private static boolean kq = false;
    private static String oldNgaySinh = "";
    private static String oldNgayVL = "";
    private static String newNgaySinh = "";
    private static String newNgayVL = "";
    
    //Lấy danh sách nhân viên
    public static ArrayList<NhanVien> layDanhSachNhanVien(){
        ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
        try{
            sql = "SELECT * FROM NHANVIEN";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while(rs.next()){
                NhanVien ob = new NhanVien();
                ob.setMaNV(rs.getString("MANV"));
                ob.setMaChucVu(rs.getString("MACV"));
                ob.setTenNV(rs.getString("HOTEN"));
                ob.setGioiTinh(rs.getString("GIOITINH"));
                oldNgaySinh = rs.getString("NgaySinh");
                try{
                    java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(oldNgaySinh); //Định dạng Date trong database
                    newNgaySinh = new SimpleDateFormat("dd/MM/yyyy").format(date); //Định dạng Date xuất hiện trên phần mềm
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                ob.setNgaySinh(newNgaySinh);
                ob.setDiaChi(rs.getString("DIACHI"));
                ob.setSdt(rs.getString("SDT"));
                ob.setLuong(rs.getInt("LUONG"));
                ob.setCmnd(rs.getString("CMND"));
                oldNgayVL = rs.getString("NgayVL");
                try{
                    java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(oldNgayVL);//Định dạng Date trong database
                    newNgayVL = new SimpleDateFormat("dd/MM/yyyy").format(date);//Định dạng Date xuất hiện trên phần mềm
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                ob.setNgayVL(newNgayVL);

                ds.add(ob);          
            }
            provider.close();
        }
        
        catch(Exception ex){
            ex.printStackTrace();
        }
        return ds;
    }
    
    //Kiểm tra nhân viên đã tồn tại hay chưa
    public static boolean kiemTraNhanVien(String ma){
        try{
            sql = "SELECT MANV FROM NHANVIEN Where MANV = '" + ma + "'";
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
    
    //Xóa nhân viên
    public static boolean xoaNhanVien(String ma){
        try{
            sql = String.format("DELETE FROM NHANVIEN Where MANV='%s'", ma);
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
    
    //Cập nhật thông tin nhân viên
    public static boolean suaNhanVien(NhanVien nv){
        try{
            sql = String.format("SET DATEFORMAT DMY UPDATE NHANVIEN SET MACV = '%s', HOTEN = N'%s',"
                    + " GIOITINH = N'%s', NGAYSINH = '%s', DIACHI = N'%s', SDT = '%s', "
                    + "LUONG = '%d', CMND = '%s', NGAYVL = '%s' WHERE MANV = '%s'",
                    nv.getMaChucVu(), nv.getTenNV(), nv.getGioiTinh(), nv.getNgaySinh(), 
                    nv.getDiaChi(), nv.getSdt(), nv.getLuong(),nv.getCmnd(), nv.getNgayVL(), nv.getMaNV());
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
    
    //Thêm mới nhân viên
    public static boolean themNhanVien(NhanVien nv){
        try{
            sql = String.format("SET DATEFORMAT DMY INSERT INTO NHANVIEN VALUES('%s', '%s', N'%s', "
                    + "N'%s', '%s', N'%s', '%s', '%d', '%s', '%s')",
                    nv.getMaNV(), nv.getMaChucVu(), nv.getTenNV(), nv.getGioiTinh(), nv.getNgaySinh(), 
                    nv.getDiaChi(), nv.getSdt(), nv.getLuong(),nv.getCmnd(), nv.getNgayVL());
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
    
    //Tiềm kiếm nhân viên theo mã nv, chức vụ, họ tên, giới tính, địa chỉ, sđt, cmnd, hoặc ngày sinh
    public static ArrayList<NhanVien> timKiemNhanVien(String duLieuTim){
        ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
        try{
            sql = "SELECT * FROM NHANVIEN WHERE MANV LIKE '%" + duLieuTim +"%'"
                    + "OR MACV LIKE '%" + duLieuTim + "%' OR HOTEN LIKE N'%" + duLieuTim + "%'"
                    + "OR GIOITINH LIKE N'%" + duLieuTim + "%' OR DIACHI LIKE N'%" + duLieuTim + "%'"
                    + "OR SDT LIKE '%"+duLieuTim+"%' OR CMND LIKE '%" +duLieuTim+"%'"
                    + "OR NGAYSINH LIKE '%"+duLieuTim+"%'";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while(rs.next()){
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MANV"));
                nv.setMaChucVu(rs.getString("MACV"));
                nv.setTenNV(rs.getString("HOTEN"));
                nv.setGioiTinh(rs.getString("GIOITINH"));
                oldNgaySinh = rs.getString("NgaySinh");
                try{
                    java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(oldNgaySinh);
                    newNgaySinh = new SimpleDateFormat("dd/MM/yyyy").format(date);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                nv.setNgaySinh(newNgaySinh);
                nv.setDiaChi((rs.getString("DIACHI")));
                nv.setSdt(rs.getString("SDT"));
                nv.setLuong(rs.getInt("LUONG"));
                nv.setCmnd(rs.getString("CMND"));
                oldNgayVL = rs.getString("NgayVL");
                try{
                    java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(oldNgayVL);
                    newNgayVL = new SimpleDateFormat("dd/MM/yyyy").format(date);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                nv.setNgayVL(newNgayVL);
       
                dsNV.add(nv);
            }
            provider.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return dsNV;
    }
    
    //Lấy thông tin nhân viên dựa vào tên tk
    //Dùng để hiện thông tin ở tab thông tin
    public static NhanVien layThongTinNhanVienTheoUser(String user){
        NhanVien nv = new NhanVien();
        try{
            sql = "SELECT * FROM NHANVIEN, TAIKHOAN WHERE NHANVIEN.MANV = TAIKHOAN.MANV AND TENTK = '"+user+"'";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while(rs.next()){
                nv.setMaNV(rs.getString("MANV"));
                nv.setMaChucVu(rs.getString("MACV"));
                nv.setTenNV(rs.getString("HOTEN"));
                nv.setGioiTinh(rs.getString("GIOITINH"));
                oldNgaySinh = rs.getString("NgaySinh");
                try{
                    java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(oldNgaySinh);
                    newNgaySinh = new SimpleDateFormat("dd/MM/yyyy").format(date);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                nv.setNgaySinh(newNgaySinh);
                nv.setDiaChi(rs.getString("DIACHI"));
                nv.setSdt(rs.getString("SDT"));
                nv.setLuong(rs.getInt("LUONG"));
                nv.setCmnd(rs.getString("CMND"));
                oldNgayVL = rs.getString("NgayVL");
                try{
                    java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(oldNgayVL);
                    newNgayVL = new SimpleDateFormat("dd/MM/yyyy").format(date);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                nv.setNgayVL(newNgayVL);             
            }
            provider.close();
        }
        
        catch(Exception ex){
            ex.printStackTrace();
        }
        return nv;
    }
    
    //Lấy thông tin tài khoản dựa vào mã nv
    //dùng để hiển thị tên TK trong tab Nhân viên, kiểm tra trước khi xóa nv và kiểm tra trước khi thêm hoặc cập nhật nv
    public static TaiKhoan layTaiKhoanNhanVienTheoMaNV(String maNV){
        TaiKhoan tk = new TaiKhoan();
        try{
            String sql = "SELECT TENTK FROM NHANVIEN, TAIKHOAN WHERE "
                    + "NHANVIEN.MANV = TAIKHOAN.MANV AND NHANVIEN.MANV = '"+maNV+"' ";
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
    
    //Kiểm tra chứng minh nhân dân của NV
    //Dùng khi cập nhật thông tin hoặc thêm NV
    public static boolean kiemTraCMNDNhanVien(String ma){
        try{
            sql = "SELECT CMND FROM NHANVIEN Where CMND = '" + ma + "'";
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
    
    //Kiểm tra số điện thoại của NV
    //Dùng khi cập nhật thông tin hoặc thêm NV
    public static boolean kiemTraSDTNhanVien(String ma){
        try{
            sql = "SELECT SDT FROM NHANVIEN Where SDT = '" + ma + "'";
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
    
    //Lấy thông tin nhân viên
    //Dùng khi cập nhật thông tin hoặc thêm NV
    public static NhanVien layThongTinNhanVien(String ma){
        NhanVien nv = new NhanVien();
        try{
            sql = "SELECT SDT, CMND FROM NHANVIEN Where MANV = '" + ma + "'";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            if(rs.next()){
                nv.setSdt(rs.getString(1));
                nv.setCmnd(rs.getString(2));
            }
            provider.close();
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return nv;
    }
}
