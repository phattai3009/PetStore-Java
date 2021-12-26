/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author lengu
 */
public class Check {
    //Kiểm tra mã khách hàng
    //Mã KH có độ dài tối 20 ký tự, bắt đầu với KH và theo sau là 1 dãy số
    public static boolean checkMaKH(String ma){
        Pattern pattern = Pattern.compile("^(KH|kh|kH|Kh)[0-9]{1,18}$");
        Matcher matcher = pattern.matcher(ma);
        return matcher.matches();
    }   
    
    //Kiểm tra mã nhân viên
    //Mã NV có độ dài tối 20 ký tự, bắt đầu với NV và theo sau là 1 dãy số
    public static boolean checkMaNV(String ma){
        Pattern pattern = Pattern.compile("^(NV|nv|Nv|nV)[0-9]{1,18}$");
        Matcher matcher = pattern.matcher(ma);
        return matcher.matches();
    }
    
    //Kiểm tra mã thú cưng
    //Mã TC có độ dài tối 20 ký tự, bắt đầu với TC và theo sau là 1 dãy số
    public static boolean checkMaTC(String ma){
        Pattern pattern = Pattern.compile("^(TC|tc|tC|Tc)[0-9]{1,18}$");
        Matcher matcher = pattern.matcher(ma);
        return matcher.matches();
    }   
    
    //Kiểm tra mã chức vụ
    //Tối đa 20 ký tự tiếng anh, không bao gồm ký tự đặc biệt và khoảng trắng
    public static boolean checkMaChucVu(String ma){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{1,20}$");
        Matcher matcher = pattern.matcher(ma);
        return matcher.matches();
    }   
    
    //Check địa chỉ, tối đa 200 ký tự
    public static boolean checkDiaChi(String moTa){
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầ"
                + "ẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆfFgGhHiIìÌỉỈĩĨíÍịỊjJ"
                + "kKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTuUùÙủỦũ"
                + "ŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ$&+,:;=?@#|'<>.-^*()%!\\s]){1,200}$");
        Matcher matcher = pattern.matcher(moTa);
        return matcher.matches();
    }
    
    //Kiểm tra tên tài khoản
    //Tên TK phải gồm tối đa 50 ký tự tiếng anh, ko bao gồm ký tự đặc biệt và khoảng trắng
    public static boolean checkTenTK(String ma){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{1,50}$");
        Matcher matcher = pattern.matcher(ma);
        return matcher.matches();
    }   
    
    //Kiểm tra số điện thoại
    //Chỉ chấp nhận các số từ 0-9, độ dài là 10 hoặc 11 ký tự
    public static boolean checkSDT(String num){
        Pattern pattern = Pattern.compile("^[0-9]{10,11}$");
        Matcher matcher = pattern.matcher(num);
        return matcher.matches();
    }
    
    //Kiểm tra họ tên nhân viên hoặc họ tên khách hàng
    //Họ tên có độ dài tối đa 50 ký tự, không bao gồm ký tự đặc biệt và số
    public static boolean checkHoTen(String hoten){
        Pattern pattern = Pattern.compile("^([a-zA-ZaAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầ"
                + "ẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆfFgGhHiIìÌỉỈĩĨíÍịỊjJ"
                + "kKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTuUùÙủỦũ"
                + "ŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ\\s]){1,50}$");
        Matcher matcher = pattern.matcher(hoten);
        return matcher.matches();
    }
    
    //Kiểm tra chứng minh nhân dân
    //CMND chỉ bao gồm các ký tự số có chiều dài là 9 ký tự đối với cmnd và 12 ký tự đối với cccd
    public static boolean checkCMND(String cmnd){
        Pattern pattern = Pattern.compile("^[0-9]{9,12}$");
        Matcher matcher = pattern.matcher(cmnd);
        return matcher.matches();
    }
    
    //Kiểm tra ngày tháng
    //Ngày tháng có thể được nhập theo 2 định dạng là dd/MM/yyyy hoặc dd-MM-yyyy
    public static boolean checkNgayThang(String date){
        Pattern pattern = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])(/|-)(0?[1-9]|1[012])(/|-)((?:19|20)[0-9][0-9])");
        Matcher matcher = pattern.matcher(date);
        if(matcher.matches()){
            int year = Integer.parseInt(matcher.group(5));
            //Khai báo biến month và day có kiểu dữ liệu là string thay vì int là do: ví dụ mình nhập tháng 2 hoặc tháng 02 đều được
            String month = matcher.group(3);
            String day = matcher.group(1);
            
            if(matcher.group(2).equals(matcher.group(4)) == false) //Nếu dấu phân cách ngày tháng năm khác nhau
                return false;           
            
            //Kiểm tra 30 hoặc 31 ngày
            //tháng 1,3,5,7,8,10,12 có 31 ngày
            if ((month.equals("4") || month.equals("6") || month.equals("9") ||
                    month.equals("04") || month.equals("06") || month.equals("09") ||
                    month.equals("11")) && day.equals("31")) {
                return false;
            } else if (month.equals("2") || month.equals("02")) {
                if (day.equals("30") || day.equals("31")) {
                    return false;
                } else if (day.equals("29")) {  // Năm nhuận tháng 2 có 29 ngày
                    if (!ktraNamNhuan(year)) {
                        return false;
                    }
                }
            }
        }
        return matcher.matches();
    }
    
    //Kiểm tra năm nhuận hay không
    private static boolean ktraNamNhuan(int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }
    
    //Kiểm tra số nguyên dương áp dụng cho số lượng, tổng tiền,...
    public static boolean checkSoNguyenDuong(String num){
        Pattern pattern = Pattern.compile("^\\d+$");
        Matcher matcher = pattern.matcher(num);
        return matcher.matches();
    }
    
    //check mã giống, mã loài
    //Tối đa 20 ký tự tiếng anh và số
    public static boolean checkMa(String ma){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{1,20}$");
        Matcher matcher = pattern.matcher(ma);
        return matcher.matches();
    }
    
    //Kiểm tra tên giống, tên loài
    //Tối đa 50 ký tự
    public static boolean checkTen(String ten){
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầ"
                + "ẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆfFgGhHiIìÌỉỈĩĨíÍịỊjJ"
                + "kKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTuUùÙủỦũ"
                + "ŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ$&+,:;=?@#|'<>.-^*()%!\\s]){1,50}$");
        Matcher matcher = pattern.matcher(ten);
        return matcher.matches();
    }
    
    //Kiểm tra mô tả giống thú cưng
    public static boolean checkMoTa(String moTa){
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầ"
                + "ẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆfFgGhHiIìÌỉỈĩĨíÍịỊjJ"
                + "kKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTuUùÙủỦũ"
                + "ŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ$&+,:;=?@#|'<>.-^*()%!\\s]){1,500}$");
        Matcher matcher = pattern.matcher(moTa);
        return matcher.matches();
    }
    
    //Kiểm tra ngày tháng dùng Jcalender
    public static boolean checkNgayThangJDC(String date){
        Pattern pattern = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((?:19|20)[0-9][0-9])");
        Matcher matcher = pattern.matcher(date);
        if(matcher.matches()){
            int year = Integer.parseInt(matcher.group(3));
            //Khai báo biến month và day có kiểu dữ liệu là string thay vì int là do: ví dụ mình nhập tháng 2 hoặc tháng 02 đều được
            String month = matcher.group(2);
            String day = matcher.group(1);
                      
            
            //Kiểm tra 30 hoặc 31 ngày
            //tháng 1,3,5,7,8,10,12 có 31 ngày
            if ((month.equals("4") || month.equals("6") || month.equals("9") ||
                    month.equals("04") || month.equals("06") || month.equals("09") ||
                    month.equals("11")) && day.equals("31")) {
                return false;
            } else if (month.equals("2") || month.equals("02")) {
                if (day.equals("30") || day.equals("31")) {
                    return false;
                } else if (day.equals("29")) {  // Năm nhuận tháng 2 có 29 ngày
                    if (!ktraNamNhuan(year)) {
                        return false;
                    }
                }
            }
        }
        return matcher.matches();
    }
}
