----use master
--drop database QL_ShopThuCung

--use master
--alter database QL_ShopThuCung set single_user with rollback immediate
--drop database QL_ShopThuCung

--use QL_ShopThuCung
--drop table CTHOADON
--drop table HOADON
--drop table GIOHANG
--drop table THUCUNG
--drop table GIONG
--drop table LOAI
--drop table TAIKHOAN
--drop table NHANVIEN
--drop table KHACHHANG

CREATE DATABASE QL_ShopThuCung
GO
USE QL_ShopThuCung
GO

SET DATEFORMAT DMY
CREATE TABLE NHANVIEN(
	MANV VARCHAR(20) NOT NULL,
	MACV VARCHAR(20) NOT NULL,
	HOTEN NVARCHAR(50),
	GIOITINH NVARCHAR(10),
	NGAYSINH DATE,
	DIACHI NVARCHAR(200),
	SDT VARCHAR(15) NOT NULL UNIQUE,
	LUONG MONEY,
	CMND VARCHAR(15) NOT NULL UNIQUE,
	NGAYVL DATE
	CONSTRAINT PK_NHANVIEN PRIMARY KEY (MANV)
)

CREATE TABLE TAIKHOAN(	
	MANV VARCHAR(20),
	TENTK VARCHAR(50) NOT NULL,
	MATKHAU VARCHAR(50),
	CONSTRAINT PK_TAIKHOAN PRIMARY KEY (TENTK)
)

CREATE TABLE LOAI( 
	MALOAI VARCHAR(20) NOT NULL,
	TENLOAI NVARCHAR(50),
	CONSTRAINT PK_LOAI PRIMARY KEY (MALOAI)
)

CREATE TABLE GIONG(
	MALOAI VARCHAR(20),
	MAGIONG VARCHAR(20) NOT NULL,
	TENGIONG NVARCHAR(50),
	MOTA NVARCHAR(500)
	CONSTRAINT PK_GIONG PRIMARY KEY (MAGIONG)
)

CREATE TABLE THUCUNG
(
	MATC VARCHAR(20) NOT NULL,
	MALOAI VARCHAR(20),
	MAGIONG VARCHAR (20),
	DONGIA MONEY,
	TONKHO INT,
	CONSTRAINT PK_THUCUNG PRIMARY KEY (MATC)
)

CREATE TABLE HOADON(
	MAHD INT IDENTITY(20,1),
	MANV VARCHAR(20),
	NGAYLAP DATETIME,
	MAKH VARCHAR(20),
	TONGTIEN MONEY,
	CONSTRAINT PK_HOADON PRIMARY KEY (MAHD)
)

CREATE TABLE CTHOADON(
	MAHD INT,
	MATC VARCHAR(20),
	SOLUONG INT,
	THANHTIEN MONEY,
	CONSTRAINT PK_CTHOADON PRIMARY KEY (MAHD,MATC)
)

CREATE TABLE KHACHHANG(
	MAKH VARCHAR (20) NOT NULL,
	HOTEN NVARCHAR(50),
	GIOITINH NVARCHAR(10),
	SDT VARCHAR(15) NOT NULL UNIQUE,
	DIACHI NVARCHAR(200),
	NGAYDK DATE,
	DOANHSO MONEY
	CONSTRAINT PK_KHACHHANG PRIMARY KEY (MAKH)
)

CREATE TABLE GIOHANG
(
	MATC VARCHAR(20) NOT NULL PRIMARY KEY,
	SOLUONG INT,
	THANHTIEN MONEY,
	CONSTRAINT FK_GIOHANG_THUCUNG FOREIGN KEY(MATC) REFERENCES THUCUNG(MATC)
)


--------------------------------------TẠO RÀNG BUỘC KHÓA NGOẠI--------------------------------------
ALTER TABLE TAIKHOAN ADD CONSTRAINT FK_TAIKHOAN_NHANVIEN FOREIGN KEY (MANV) REFERENCES NHANVIEN (MANV)

ALTER TABLE HOADON ADD CONSTRAINT FK_HOADON_NHANVIEN FOREIGN KEY (MANV) REFERENCES NHANVIEN (MANV)
ALTER TABLE HOADON ADD CONSTRAINT FK_HOADON_KHACHHANG FOREIGN KEY (MAKH) REFERENCES KHACHHANG(MAKH)

ALTER TABLE CTHOADON ADD CONSTRAINT FK_CTHOADON_HOADON FOREIGN KEY (MAHD) REFERENCES HOADON (MAHD)
ALTER TABLE CTHOADON ADD CONSTRAINT FK_CTHOADON_THUCUNG FOREIGN KEY (MATC) REFERENCES THUCUNG(MATC)

ALTER TABLE THUCUNG ADD CONSTRAINT FK_THUCUNG_GIONG FOREIGN KEY (MAGIONG) REFERENCES GIONG (MAGIONG)
ALTER TABLE THUCUNG ADD CONSTRAINT FK_THUCUNG_LOAI FOREIGN KEY (MALOAI) REFERENCES LOAI (MALOAI)

ALTER TABLE GIONG ADD CONSTRAINT FK_GIONG_MALOAI FOREIGN KEY (MALOAI) REFERENCES LOAI(MALOAI)
go

------Ràng buộc toàn vẹn


alter table Giohang
add constraint df_thanhTienGioHang default(0) for thanhtien

alter table HOADON
add constraint df_ngaylap default(getdate()) for ngaylap

alter table KHACHHANG
add constraint df_ngaydk default(getdate()) for ngaydk

alter table KHACHHANG
add constraint df_doanhso default(0) for doanhso

alter table taikhoan
add constraint df_matkhau default '0000' for matkhau

alter table NHANVIEN
add constraint chk_manv check(MANV like 'NV%')

alter table NHANVIEN
add constraint chk_luong check(LUONG>=0)

alter table KHACHHANG
add constraint chk_makh check(MAKH like 'KH%')
alter table KHACHHANG
add constraint chk_doanhso check(DOANHSO>=0)

alter table HOADON
add constraint chk_tongtien check(TONGTIEN>=0)

alter table THUCUNG
add constraint chk_matc check(MATC like 'TC%')
alter table THUCUNG
add constraint chk_dongia check(DONGIA>=0)
alter table THUCUNG
add constraint chk_soluong check(TONKHO>=0)

alter table CTHOADON
add constraint chk_thanhtien check(THANHTIEN>=0)

-------------------------------------------------------------------------------------------------------------------
------------------------------------------------------TRIGGER------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------

--1/Viết trigger thực hiện việc cập nhật THANHTIEN trên bảng CTHOADON
GO
CREATE TRIGGER CapNhapThanhTien
on CTHOADON
for insert
as
	update CTHOADON
	set THANHTIEN =(select THUCUNG.DONGIA * CTHOADON.SOLUONG from THUCUNG where CTHOADON.MATC =THUCUNG.MATC )
	where MAHD = (select MAHD from inserted)
go
--drop trigger CapNhapThanhTien
CREATE TRIGGER CapNhapThanhTienGioHang
on GioHang
for insert,delete,update
as
	update GioHang
	set THANHTIEN= (Select thucung.dongia * giohang.soluong from THUCUNG where GIOHANG.MATC = THUCUNG.MATC)
	where MATC=(select MATC from inserted) or MATC=(select MATC from deleted)
go

--2/Viết trigger thực hiện việc cập nhật TONGTIEN trên bảng HOADON
create trigger CapNhapTongTien
on CTHOADON
for insert
as
	update HOADON
	set TONGTIEN = (select sum(THANHTIEN) from CTHOADON where MAHD = (select MAHD from inserted))
	where MAHD = (select MAHD from inserted)
go

--drop trigger CapNhapTongTien
--3.Viết trigger kiểm tra tuổi của nhân viên phải >=18
--create trigger KiemTraTuoiNV
--on NHANVIEN
--for insert,update
--as
--   if(select year(getdate())-year(NGAYSINH) from inserted)<18
--   print N'Tuổi nhân viên phải trên 18'
--   rollback tran
--go

--4.Viết trigger cập nhật số lượng thú cưng khi mua.
CREATE TRIGGER CapNhapSoLuongThuCung
ON CTHOADON 
FOR INSERT
AS
	UPDATE THUCUNG
	set TONKHO = THUCUNG.TONKHO - (SELECT SOLUONG FROM inserted)
	where MATC = (select MATC from inserted)
GO


CREATE TRIGGER CapNhatDoanhSo
ON HOADON 
FOR INSERT
AS
	UPDATE KHACHHANG
	set DOANHSO = DOANHSO + (SELECT TONGTIEN FROM inserted)
	where MAKH = (select MAKH from inserted)
GO

-------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------PROCEDUCE-----------------------------------------------------
-------------------------------------------------------------------------------------------------------------------

--1.Viết Thủ tục truyền vào tham số tháng năm. Tính tổng số lượng của từng sản phẩm bán ra trong tháng đó.
CREATE PROC TinhTongSLTCTheoDMY @THANG INT, @NAM INT
AS
SELECT MATC, SUM(SOLUONG) as Tong
FROM CTHOADON C INNER JOIN HOADON H
ON C.MAHD = H.MAHD
WHERE MONTH(NGAYLAP) = @THANG AND YEAR(NGAYLAP) = @NAM
GROUP BY MATC
GO

create proc TongTienGioHang
as
	SELECT SUM(Thanhtien)
	FROM GIOHANG
go

--2.Viết thủ tục tính doanh thu bán xuất theo ngày có hoá đơn.
create proc DoanhThuMoiNgay
as
	SELECT NGAYLAP, SUM(TONGTIEN) AS DOANHTHU
	FROM HOADON
	GROUP BY NGAYLAP
go

--3.Viết thủ tục truyền vào tham số mã hóa đơn sẽ trả về mã khách hàng, ngày lập và tổng tiền của hóa đơn đó.
create proc	TraMaHoaDon @maHD varchar(20), @maKH varchar(20) output, @ngayLap date output,@tongTien money output
as
   select @maKH = MAKH, @ngayLap = NGAYLAP, @tongTien = TONGTIEN
   from HOADON 
   where @maHD = MAHD
go

create proc DSTCXAndY @maLoai varchar(20), @ngayban date
as
	SELECT*
	FROM THUCUNG T INNER JOIN CTHOADON C
	ON T.MATC = C.MATC
	WHERE MALOAI = @maLoai AND c.MAHD IN(SELECT DISTINCT CT.MAHD
										 FROM CTHOADON CT INNER JOIN HOADON H ON CT.MAHD = H.MAHD
										 WHERE NGAYLAP = @ngayban)
GO


--5.Viết thủ tục in ra danh sách thú cưng không bán được.
create proc DSTCKhongBanDuoc
as
	SELECT *
	FROM THUCUNG T
	WHERE NOT EXISTS(SELECT * 
	FROM THUCUNG TC INNER JOIN CTHOADON C
	ON TC.MATC = C.MATC AND TC.MATC = T.MATC)
go


--6.Viết thủ tục tính doanh thu bán hàng của từng tháng trong năm X.
CREATE PROC DoanhThuTungThangTrongNam @namX varchar(10)
AS
	SELECT MONTH(NGAYLAP) AS THANG, SUM(TONGTIEN) AS DOANHTHU
	FROM HOADON
	WHERE YEAR(NGAYLAP) = @namX
	GROUP BY MONTH(NGAYLAP)
GO

CREATE PROC ThongKeHD @from date, @to date
as
	select count(mahd)
	from HOADON
	where (NGAYLAP BETWEEN @from and @to)

go 
CREATE PROC ThongKeTC @from date, @to date
as
	select sum(cthoadon.soluong)
	from HOADON, CTHOADON
	where HOADON.MAHD = CTHOADON.MAHD
	and (NGAYLAP BETWEEN @from and @to)


go
CREATE PROC ThongKeTongTien @from date, @to date
as
	select sum(tongtien)
	from HOADON
	where (NGAYLAP BETWEEN @from and @to)

GO

-------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------FUNCTION-----------------------------------------------------
-------------------------------------------------------------------------------------------------------------------

--1.viết hàm truyền vào tham số mã khách hàng sẽ in ra danh sách các hóa đơn(MAHD,TONGTIEN) của khách hàng đó
create function	InDSHDTheoMaKH(@maKH varchar(20))
returns table
as
   return (select MAHD,sum(TONGTIEN) as N'Tổng Thành Tiền' from HOADON where MAKH=@maKH group by MAHD)
go




--2.Viết hàm truyền vào nhân viên có tên x và ngày lập hoá đơn y , hàm trả về mã hoá đơn, tổng tiền các hóa đơn lập trong ngày y.
CREATE FUNCTION InRaHDCuaNVXTrongNgayY(@tennv nvarchar(50),@ngay date)
RETURNS TABLE 
AS
RETURN (SELECT MAHD, TONGTIEN FROM HOADON H INNER JOIN NHANVIEN N ON H.MANV = N.MANV 
        WHERE HOTEN = @tennv AND NGAYLAP = @ngay)
go


--3.Viết hàm truyền vào mã giống thú cưng X hàm sẽ trả về danh sách hóa đơn của thú cưng đó.
create function	InDSHDCuaThuCungX(@ThuCungX varchar(20)) 
returns table
as
   return (select h.MAHD, sum(ct.THANHTIEN) as N'Tổng Thành Tiền' 
			from THUCUNG TC, CTHOADON CT, HOADON H
			where TC.MATC = CT.MATC and H.MAHD = CT.MAHD and TC.MAGIONG = @ThuCungX
			Group by H.MAHD)
go


--4.Viết hàm truyền vào năm X. Đếm số thú cưng của từng loại được bán ra trong năm đó và tổng tiền của từng loại.
CREATE FUNCTION DemThuCungKhacLoai(@NAM INT)
RETURNS TABLE
AS
RETURN (SELECT C.MATC, SUM(SOLUONG) AS N'Số sản phẩm', Sum(C.THANHTIEN) as N'Thành Tiền'
         FROM CTHOADON C INNER JOIN HOADON H ON C.MAHD = H.MAHD WHERE YEAR(NGAYLAP) = @NAM
		 Group by C.MATC)
GO
--TEST
--SELECT * FROM DBO.DemThuCungKhacLoai(2021)

--5.Viết hàm trả về họ tên khách hàng đã mua hóa đơn có tổng tiền cao nhất trong năm X.
CREATE FUNCTION TraVeKHCoGiaTriHDCaoNhatNamX(@nam int)
RETURNS NVARCHAR(50)
AS
BEGIN
DECLARE @KH NVARCHAR(50)
SET @KH=(
SELECT HOTEN
FROM KHACHHANG K INNER JOIN HOADON H
ON K.MAKH = H.MAKH 
AND MAHD = (SELECT MAHD
			FROM HOADON
			WHERE TONGTIEN = (SELECT MAX(TONGTIEN)
							FROM HOADON WHERE YEAR(NGAYLAP)=@nam)))
RETURN @KH
END
GO



-------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------CURSOR--------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------
---1.Định nghĩa một biến tên cursor_sv có kiểu cursor gồm mã nv, mã cv, họ tên, sđt, ngày vào làm  của các nhân viên là NV001. Đọc và xuất từng dòng dữ liệu.
DECLARE cursor_nv CURSOR
DYNAMIC
FOR Select MANV,MACV, HOTEN, SDT, NGAYVL
From NHANVIEN
Where MANV='NV001'
OPEN cursor_nv
DECLARE @maNV varchar(20),@maCV varchar(20),@ten nvarchar(50),@sdt varchar(15),@ngayvl date
FETCH NEXT FROM cursor_nv into @maNV,@maCV,@ten,@sdt,@ngayvl
While(@@FETCH_STATUS=0)
Begin 
  Print	@maNV +','+ @maCV +','+ @ten+ ','+@sdt+','+convert(varchar,@ngayvl,103)
  Fetch next From cursor_nv into @maNV,@maCV,@ten,@sdt,@ngayvl
End
CLOSE cursor_nv
DEALLOCATE cursor_nv
GO

--2.Hãy viết lệnh định nghĩa cursor có tên là THÀNH TIỀN gồm các thuộc tính:Mã TC, số lượng tòn kho, đơn giá . Thực hiện xử lý trên cursor như sau: 
--Mỗi khi cursor di chuyển đến mẫu tin kế tiếp thì tính THÀNH TIỀN (THANHTIEN) của chi tiết hóa đơn tương ứng theo công thức: 
--THANHTIEN = SOLUUONG*DONGIA.
DECLARE cursor_tt CURSOR 
DYNAMIC 
FOR SELECT MAHD, TC.MATC,TC.TONKHO,TC.DONGIA FROM THUCUNG TC, CTHOADON CT
WHERE TC.MATC=CT.MATC
OPEN  cursor_tt
DECLARE @maHD INT, @maTC VARCHAR(20), @tonKho INT, @donGia MONEY
FETCH NEXT FROM  cursor_tt INTO  @maHD, @maTC, @tonKho, @donGia
WHILE(@@FETCH_STATUS = 0)
BEGIN
	UPDATE CTHOADON
	SET THANHTIEN=(select @tonKho*@donGia )
	WHERE @maTC=MATC and @maHD=MAHD
	FETCH NEXT FROM  cursor_tt INTO @maHD, @maTC, @tonKho, @donGia
END
CLOSE  cursor_tt
DEALLOCATE  cursor_tt
GO

-------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------BACKUP--------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------
--BACKUP DATABASE QL_ShopThuCung
--TO DISK = 'C:\backupsql\Test_FULL.bak'
--WITH INIT

---- Thêm một bản ghi mới 
--insert into CTHOADON values(1, 'TC004', 2,NULL) 

--BACKUP LOG QL_ShopThuCung 
--TO DISK = 'C:\backupsql\Test_TRAN.trn'
--WITH INIT

---- Thêm một bản ghi mới 
--insert into CTHOADON values(1, 'TC005', 2,NULL) 

---- Thời điểm t3: Log Backup
--BACKUP LOG QL_ShopThuCung 
--TO DISK = 'C:\backupsql\Test_TRAN.trn'

---- Thêm một bản ghi mới 
--insert into CTHOADON values(2, 'TC005', 2,NULL) 

---- Thời điểm t4: Differential backup
--BACKUP DATABASE QL_ShopThuCung 
--TO DISK = 'C:\backupsql\Test_DIFF.bak' 
--WITH INIT, DIFFERENTIAL

---- Thời điểm t5: Log Backup
--BACKUP LOG QL_ShopThuCung 
--TO DISK = 'C:\backupsql\Test_TRAN.trn' 
--WITH INIT

---- Thêm một bản ghi mới 
--insert into CTHOADON values(4, 'TC005', 2,NULL) 

---- Giả sử sau đó xảy ra sự cố, ta mô phỏng sự việc này bằng cách xóa CSDL:

----khôi phục đuôi
--BACKUP LOG QL_ShopThuCung
--TO DISK= 'C:\backupsql\Test_TRAN.trn'
--WITH NO_TRUNCATE

----Khôi phục
---- Bước 1: Khôi phục từ bản Full Backup 
--RESTORE DATABASE QL_ShopThuCung 
--FROM DISK = 'C:\backupsql\Test_FULL.bak' 
--WITH NORECOVERY   
---- Bước 2: Khôi phục từ bản Differential Backup 
--RESTORE DATABASE QL_ShopThuCung 
--FROM DISK = 'C:\backupsql\Test_DIFF.bak' 
--WITH NORECOVERY  
---- Bước 3: Khôi phục từ các bản Log Backup kể từ sau lần Diferential Backup gần nhất 

--RESTORE DATABASE QL_ShopThuCung 
--FROM DISK = 'C:\backupsql\Test_TRAN.trn' 
--WITH FILE = 1, NORECOVERY

--RESTORE DATABASE QL_ShopThuCung 
--FROM DISK = 'C:\backupsql\Test_TRAN.trn'
--WITH FILE=2, RECOVERY

------------------------------------------ DỮ LIỆU MẪU------------------------------------

INSERT INTO LOAI VALUES
('DOG', N'Chó'),
('CAT', N'Mèo')

INSERT INTO GIONG VALUES
('DOG','HUSKY', N'Chó Husky', N'Chó Husky là một giống chó tuyết có nguồn gốc từ Sibir, Nga.
Husky có vẻ đẹp quyến rũ, thân hình dũng mãnh, sức khỏe dẻo dai phi thường.
Là giống chó hiền lành, rất tình cảm, hay tò mò, ưa vận động, rất thích người và đặc biệt thân thiện với trẻ em. 
Ở Việt Nam, chó Husky rất được yêu thích và được săn đón bởi đông đảo những người yêu chó.'),

('DOG', 'CORGI', N'Chó Corgi', N'Chó Corgi là một giống chó nhỏ, chân ngắn nhưng thân dài, đuôi cụt và một đôi tai lớn.
Corgi có vẻ ngoài đáng yêu, cặp mông hình trái tim tạo nên nét quyến rũ và đã tạo nên cơn sốt ngắm mông Corgi.  
Là giống chó rất thông minh, biết vâng lời, có bản năng bảo vệ, rất tận tâm với chủ và thân thiện với trẻ em. 
Chúng rất điềm tĩnh, trung thành và đáng yêu, song rất cảnh giác trước người lạ.'),

('DOG', 'GOLDEN', N'Chó Golden Retriever', N'Chó Golden Retriever là một giống chó săn thượng hạng đến từ Scotland.
Golden có bộ lông vàng mượt khá sang trọng, khuôn mặt thường xuyên cười vui vẻ, tuy nhiên, lúc buồn lại tỏ vẻ đáng thương rõ ràng.
Là giống chó rất thông minh, dễ huấn luyện, luôn biết cách làm hài lòng chủ nhân và thích vui chơi cùng mọi người.
Golden rất điềm tĩnh, hiền lành và tình cảm, lại rất nhanh nhẹn và năng động.'),

('CAT', 'BRITISHSH', N'Mèo Anh lông ngắn', N'Mèo Anh lông ngắn là một giống mèo cảnh có nguồn gốc từ Anh.
Chúng sở hữu một thân hình vô cùng mập mạp đáng yêu, nổi bật với khuôn mặt tròn và bộ lông màu xám xanh cổ điển và một cái đuôi to.
Tính cách của chúng tuy khá lười biếng tuy nhiên lại phù hợp với những người bận rộn không có quá nhiều thời gian và không đòi hỏi chủ nhân của chúng phải chải chuốt vệ sinh thường xuyên.'),

('CAT', 'BRITISHLH', N'Mèo Nga lông dài', N'Mèo Nga lông dài thực chất có nguồn gốc là giống mèo Angora Turkish, có xuất xứ từ Thổ Nhĩ Kỳ.
Mèo Nga sở hữu bộ lông dài trắng muốt như tuyết tuyệt đẹp, tuy nhiên không xù, thân hình nhỏ gọn, thanh thoát và quý phái.
Tính tình thông minh, linh hoạt, quấn chủ và hiền lành, mèo Nga được xem như loại mèo toàn diện nhất.')

INSERT INTO THUCUNG VALUES
('TC001', 'DOG', 'HUSKY', 5000000, 100),
('TC002', 'DOG', 'CORGI', 7000000, 100),
('TC003', 'DOG', 'GOLDEN', 9000000, 100),
('TC004', 'CAT', 'BRITISHSH', 10000000, 100),
('TC005', 'CAT', 'BRITISHLH', 4000000, 100)

SET DATEFORMAT DMY
Insert into NHANVIEN Values
('NV001','QUANLY',N'Đinh Phát Tài',N'Nam','30/09/2001',N'Long An','0359975249',30000000,'301770325','01/01/2021'),
('NV002','QUANLY',N'Lê Nguyễn Đại Đức Tâm',N'Nam','1/1/2001',N'Long An','0987678901',5000000,'305840781','01/01/2021'),
('NV003','NVBH',N'Võ Thi Nở',N'Nữ','28/2/2001',N'Pháp','0987689012',4500000,'291150900','01/01/2021')
INSERT INTO NHANVIEN VALUES('NV004', 'QL', N'Nguyễn Như Quỳnh', N'Nữ', '30-04-1997', 'Tân Phú, Tp. HCM', '0354403455', 5500000, '301754689', '3-2-2020')

Insert into TAIKHOAN(MANV, TENTK) Values
('NV001','taidp'),
('NV002','tamlndd'),
('NV003','novt'),
('NV004','quynhnn')

SET DATEFORMAT DMY;
INSERT INTO KHACHHANG(MAKH, HOTEN, GIOITINH, SDT, DIACHI) VALUES
('KH001',N'Nguyễn Văn A',N'Nam','0987612345',N'Củ Chi'),
('KH002',N'Nguyễn Văn B',N'Nữ','0987623456',N'Mỹ Tho'),
('KH003',N'Nguyễn Thị C',N'Nữ','0987634567',N'Pháp'),
('KH004',N'Nguyễn Văn D',N'Nam','0916395633',N'TP HCM'),
('KH005',N'Nguyễn Văn E',N'Nam','0355440999',N'Long An'),
('KH006',N'Nguyễn Thị F',N'Nữ','0344112881',N'Hà Nội'),
('KH007',N'Trần Hoàng Long',N'Nam','0913564799',N'TP HCM'),
('KH008',N'Nguyễn Tiến Sĩ',N'Nam','0983856719',N'Long An'),
('KH009',N'Đinh Thanh Minh',N'Nữ','0952362856',N'TP HCM'),
('KH0010',N'Nguyễn Hoàng Yến',N'Nữ','0975955429',N'TP HCM'),
('KH0011',N'Nguyễn Thuý Kiều',N'Nữ','0975932429',N'Long An'),
('KH0012',N'Trần Hoàng Bảo Trâm',N'Nữ','0988323841',N'Long An'),
('KH0013',N'Trần Phương Anh',N'Nữ','0959652004',N'TP HCM'),
('KH0014',N'Nguyễn Thị Như Quỳnh',N'Nữ','0955001220',N'Long An'),
('KH0015',N'Lê Hoàng Minh Tâm',N'Nam','0958833512',N'TP HCM')

