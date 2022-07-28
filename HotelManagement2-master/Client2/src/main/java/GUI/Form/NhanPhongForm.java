package GUI.Form;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ClientService.KhachHangService;
import ClientService.LoaiPhongService;
import ClientService.PhieuThueService;
import ClientService.PhongService;
import Rmi.DTO.KhachHangDTO;
import Rmi.DTO.LoaiPhongDTO;
import Rmi.DTO.PhieuThueDTO;
import Rmi.DTO.PhongDTO;

public class NhanPhongForm extends JPanel implements ActionListener {
	private JTextField txtCMT;
	private JLabel lblTenKH, lblCmtKH, lblSdtKH, lblDiaChiKH, lblEmailKH;
	private JLabel lblNgayLapPT, lblNgayDatPT, lblNgayKetPT, lblNgayNhanPT, lblNgayTraPT, lblSoNgay;
	private JLabel lblTenPhong, lblLoaiPhong;
	private JButton btnXoaTrang, btnTim, btnNhanPhong;
	private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	private List<Component> lstComponent = new ArrayList<>();
	private PhieuThueDTO phieuthue = null;

	public NhanPhongForm() {
		setBackground(Color.decode("#d4d5d6"));
		setBounds(0, 0, 1180, 820);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(20, 20, 1140, 780);
		add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 390, 130);
		panel.add(panel_1);
		panel_1.setLayout(null);

		txtCMT = new JTextField();
		txtCMT.setBounds(10, 70, 240, 30);
		panel_1.add(txtCMT);
		txtCMT.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nhập số chứng minh thư khách hàng");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 10, 298, 40);
		panel_1.add(lblNewLabel);

		btnTim = new JButton("Tìm phiếu thuê");
		btnTim.setBounds(260, 70, 120, 30);
		panel_1.add(btnTim);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(410, 10, 720, 760);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Thông tin phiếu thuê");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 10, 298, 40);
		panel_2.add(lblNewLabel_1);

		JLabel lblNewLabel_5_2 = new JLabel("Ngày Lập: ");
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_2.setBounds(10, 70, 130, 40);
		panel_2.add(lblNewLabel_5_2);

		JLabel lblNewLabel_5_2_1 = new JLabel("Ngày Đặt: ");
		lblNewLabel_5_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_2_1.setBounds(10, 120, 130, 40);
		panel_2.add(lblNewLabel_5_2_1);

		JLabel lblNewLabel_5_2_1_1 = new JLabel("Ngày Kết Thúc: ");
		lblNewLabel_5_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_2_1_1.setBounds(10, 170, 130, 40);
		panel_2.add(lblNewLabel_5_2_1_1);

		JLabel lblNewLabel_5_2_1_1_1 = new JLabel("Ngày Nhận Phòng: ");
		lblNewLabel_5_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_2_1_1_1.setBounds(10, 220, 160, 40);
		panel_2.add(lblNewLabel_5_2_1_1_1);

		JLabel lblNewLabel_5_2_1_1_1_1 = new JLabel("Ngày Trả Phòng: ");
		lblNewLabel_5_2_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_2_1_1_1_1.setBounds(10, 270, 160, 40);
		panel_2.add(lblNewLabel_5_2_1_1_1_1);

		JLabel lblNewLabel_5_2_1_1_1_1_1 = new JLabel("Số ngày thuê: ");
		lblNewLabel_5_2_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_2_1_1_1_1_1.setBounds(10, 320, 160, 30);
		panel_2.add(lblNewLabel_5_2_1_1_1_1_1);

		JLabel lblNewLabel_1_1 = new JLabel("Thông tin phòng thuê");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(10, 390, 298, 40);
		panel_2.add(lblNewLabel_1_1);

		JLabel lblNewLabel_5_2_2 = new JLabel("Tên Phòng: ");
		lblNewLabel_5_2_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_2_2.setBounds(10, 440, 130, 30);
		panel_2.add(lblNewLabel_5_2_2);

		JLabel lblNewLabel_5_2_2_1 = new JLabel("Loại Phòng: ");
		lblNewLabel_5_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_2_2_1.setBounds(10, 490, 130, 40);
		panel_2.add(lblNewLabel_5_2_2_1);

		btnNhanPhong = new JButton("Nhận Phòng");
		btnNhanPhong.setBounds(270, 700, 240, 30);
		panel_2.add(btnNhanPhong);

		lblNgayLapPT = new JLabel("............................");
		lblNgayLapPT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNgayLapPT.setBounds(215, 70, 210, 40);
		lstComponent.add(lblNgayLapPT);
		panel_2.add(lblNgayLapPT);

		lblNgayDatPT = new JLabel("............................");
		lblNgayDatPT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNgayDatPT.setBounds(215, 120, 210, 40);
		lstComponent.add(lblNgayDatPT);
		panel_2.add(lblNgayDatPT);

		lblNgayKetPT = new JLabel("............................");
		lblNgayKetPT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNgayKetPT.setBounds(215, 170, 210, 40);
		lstComponent.add(lblNgayKetPT);
		panel_2.add(lblNgayKetPT);

		lblNgayNhanPT = new JLabel("............................");
		lblNgayNhanPT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNgayNhanPT.setBounds(215, 220, 210, 40);
		lstComponent.add(lblNgayNhanPT);
		panel_2.add(lblNgayNhanPT);

		lblNgayTraPT = new JLabel("............................");
		lblNgayTraPT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNgayTraPT.setBounds(215, 270, 210, 40);
		lstComponent.add(lblNgayTraPT);
		panel_2.add(lblNgayTraPT);

		lblSoNgay = new JLabel("............................");
		lblSoNgay.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSoNgay.setBounds(215, 320, 210, 40);
		lstComponent.add(lblSoNgay);
		panel_2.add(lblSoNgay);

		lblTenPhong = new JLabel("............................");
		lblTenPhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTenPhong.setBounds(215, 440, 210, 40);
		lstComponent.add(lblTenPhong);
		panel_2.add(lblTenPhong);

		lblLoaiPhong = new JLabel("............................");
		lblLoaiPhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLoaiPhong.setBounds(215, 490, 210, 40);
		lstComponent.add(lblLoaiPhong);
		panel_2.add(lblLoaiPhong);

		btnXoaTrang = new JButton("Xóa Trắng");
		btnXoaTrang.setBounds(10, 700, 240, 30);
		panel_2.add(btnXoaTrang);

		JPanel pKhachHang = new JPanel();
		pKhachHang.setBounds(10, 150, 390, 620);
		panel.add(pKhachHang);
		pKhachHang.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Thông tin khách hàng");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 10, 370, 30);
		pKhachHang.add(lblNewLabel_2);

		JLabel lblNewLabel_5 = new JLabel("Tên Khách: ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setBounds(10, 150, 130, 40);
		pKhachHang.add(lblNewLabel_5);

		lblTenKH = new JLabel("............................");
		lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTenKH.setBounds(180, 150, 210, 40);
		lstComponent.add(lblTenKH);
		pKhachHang.add(lblTenKH);

		JLabel lblNewLabel_5_1 = new JLabel("Số CMT: ");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_1.setBounds(10, 200, 130, 40);
		pKhachHang.add(lblNewLabel_5_1);

		lblCmtKH = new JLabel("............................");
		lblCmtKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCmtKH.setBounds(180, 200, 210, 40);
		lstComponent.add(lblCmtKH);
		pKhachHang.add(lblCmtKH);

		JLabel lblNewLabel_5_1_1 = new JLabel("Số Điện Thoại: ");
		lblNewLabel_5_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_1_1.setBounds(10, 250, 130, 40);
		pKhachHang.add(lblNewLabel_5_1_1);

		lblSdtKH = new JLabel("............................");
		lblSdtKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSdtKH.setBounds(180, 250, 210, 40);
		lstComponent.add(lblSdtKH);
		pKhachHang.add(lblSdtKH);

		JLabel lblNewLabel_5_1_1_1 = new JLabel("Địa Chỉ: ");
		lblNewLabel_5_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_1_1_1.setBounds(10, 300, 130, 40);
		pKhachHang.add(lblNewLabel_5_1_1_1);

		lblDiaChiKH = new JLabel("............................");
		lblDiaChiKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDiaChiKH.setBounds(180, 300, 210, 40);
		lstComponent.add(lblDiaChiKH);
		pKhachHang.add(lblDiaChiKH);

		JLabel lblNewLabel_5_1_1_1_1 = new JLabel("Email: ");
		lblNewLabel_5_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_1_1_1_1.setBounds(10, 350, 130, 40);
		pKhachHang.add(lblNewLabel_5_1_1_1_1);

		lblEmailKH = new JLabel("............................");
		lblEmailKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmailKH.setBounds(180, 350, 210, 40);
		lstComponent.add(lblEmailKH);
		pKhachHang.add(lblEmailKH);

		// add action
		btnTim.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnNhanPhong.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnXoaTrang)) {
			XoaTrang();
		}
		if (o.equals(btnTim)) {
			TimPhieuThue();
		}
		if (o.equals(btnNhanPhong)) {
			NhanPhong();
		}

	}

	private void NhanPhong() {
		phieuthue.setNgayNhan(new Date());
		phieuthue.setTrangThai("CHECKED");
		boolean kq = PhieuThueService.getInstance().updatePhieuThue(phieuthue);
		if (kq) {
			JOptionPane.showMessageDialog(null, "Nhận phòng thành công.");
			XoaTrang();
			return;
		}
		JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi nhận phòng có mã: " + phieuthue.getMaPT());
	}

	private void TimPhieuThue() {
		String cmt = txtCMT.getText().toString().trim();
		if (!(cmt.length() > 0 && cmt.matches("^[0-9]{12}$"))) {
			if (cmt.length() == 0) {
				JOptionPane.showMessageDialog(this,	"Hãy nhập số chứng minh của khách hàng.");
			} else if (cmt.length() != 10) {
				JOptionPane.showMessageDialog(this,	"Số chứng mính có 12 số.");
			}
			txtCMT.selectAll();
			txtCMT.requestFocus();			
			return;
		}

		phieuthue = PhieuThueService.getInstance().getPhieuThueByCMT(cmt);
		if (phieuthue == null) {
			JOptionPane.showMessageDialog(null, "Không có phiếu thuê mới nào của khách cả");
			return;
		}
		KhachHangDTO khachhang = KhachHangService.getInstance().getKhachHangById(phieuthue.getKhachHang_id());
		PhongDTO phong = PhongService.getInstance().getPhongById(phieuthue.getPhong_id());

		loadKhachHangInfo(khachhang);
		LoadPhieuThueInfo(phieuthue);
		LoadPhongInfo(phong);
	}

	private void XoaTrang() {
		txtCMT.setText("");
		for (Component component : lstComponent) {
			if (component instanceof JLabel) {
				((JLabel) component).setText("............................");
			}
		}
	}

	private void loadKhachHangInfo(KhachHangDTO khachhang) {
		lblTenKH.setText(khachhang.getTen());
		lblCmtKH.setText(khachhang.getSoCMND());
		lblDiaChiKH.setText(khachhang.getDiaChi());
		lblEmailKH.setText(khachhang.getEmail());
		lblSdtKH.setText(khachhang.getSdt());
	}

	private void LoadPhieuThueInfo(PhieuThueDTO phieuthue) {
		lblNgayDatPT.setText(dateFormat.format(phieuthue.getNgayDat()));
		lblNgayKetPT.setText(dateFormat.format(phieuthue.getNgayKetThuc()));
		lblNgayLapPT.setText(dateFormat.format(phieuthue.getNgayLap()));

		long diff = phieuthue.getNgayKetThuc().getTime() - phieuthue.getNgayDat().getTime();
		TimeUnit time = TimeUnit.DAYS;
		long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
		lblSoNgay.setText(diffrence + " ( Ngày )");
	}

	private void LoadPhongInfo(PhongDTO phong) {
		LoaiPhongDTO loaiphong = LoaiPhongService.getInstance().getLoaiPhongById(phong.getLoaiPhong_id());
		lblTenPhong.setText(phong.getTen());
		lblLoaiPhong.setText(loaiphong.getTenLP());
	}
}
