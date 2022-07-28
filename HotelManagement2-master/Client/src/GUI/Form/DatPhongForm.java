package GUI.Form;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

import Dao.LoaiPhongDao;
import Dao.PhongDao;
import GUI.Dialog.ChooseCustomerDialog;
import Rmi.DTO.KhachHangDTO;
import Rmi.DTO.LoaiPhongDTO;
import Rmi.DTO.PhongDTO;

import javax.swing.JComboBox;
import javax.swing.JDialog;

public class DatPhongForm extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private KhachHangDTO selectedKH = new KhachHangDTO();
	private JLabel lblTenKH, lblCmtKH, lblSdtKH, lblEmailKH, lblDiaChiKH, lblDonGia;
	private JLabel lblTenPhong;
	private JDateChooser ngayDen, ngayKetThuc;
	private JButton btnDatPhong, btnXoaTrang, btnTimPhongTrong;
	private JComboBox<String> cbxLoaiPhong;
	private List<LoaiPhongDTO> lstLoaiPhong;
	private LoaiPhongDTO selectedLoaiPhong;
	private PhongDTO selectedPhong;

	public DatPhongForm() {
		setBackground(Color.decode("#d4d5d6"));
		setLayout(null);
		setBounds(0, 0, 1180, 820);

		JPanel pKhachHang = new JPanel();
		pKhachHang.setBackground(Color.WHITE);
		pKhachHang.setBounds(10, 10, 429, 645);
		add(pKhachHang);
		pKhachHang.setLayout(null);

		JLabel lblNewLabel = new JLabel("Th\u00F4ng tin kh\u00E1ch h\u00E0ng");
		lblNewLabel.setBounds(10, 10, 123, 24);
		pKhachHang.add(lblNewLabel);

		JButton btnNewButton = new JButton("Ch\u1ECDn kh\u00E1ch h\u00E0ng");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				SampleOpenDialog();
			}
		});
		btnNewButton.setBounds(10, 53, 409, 24);
		pKhachHang.add(btnNewButton);

		JLabel lblNewLabel_5 = new JLabel("Tên Khách: ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setBounds(10, 150, 130, 40);
		pKhachHang.add(lblNewLabel_5);

		lblTenKH = new JLabel("............................");
		lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTenKH.setBounds(180, 150, 240, 40);
		pKhachHang.add(lblTenKH);

		JLabel lblNewLabel_5_1 = new JLabel("Số CMT: ");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_1.setBounds(10, 200, 130, 40);
		pKhachHang.add(lblNewLabel_5_1);

		lblCmtKH = new JLabel("............................");
		lblCmtKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCmtKH.setBounds(180, 200, 240, 40);
		pKhachHang.add(lblCmtKH);

		JLabel lblNewLabel_5_1_1 = new JLabel("Số Điện Thoại: ");
		lblNewLabel_5_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_1_1.setBounds(10, 250, 130, 40);
		pKhachHang.add(lblNewLabel_5_1_1);

		lblSdtKH = new JLabel("............................");
		lblSdtKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSdtKH.setBounds(180, 250, 240, 40);
		pKhachHang.add(lblSdtKH);

		JLabel lblNewLabel_5_1_1_1 = new JLabel("Địa Chỉ: ");
		lblNewLabel_5_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_1_1_1.setBounds(10, 300, 130, 40);
		pKhachHang.add(lblNewLabel_5_1_1_1);

		lblDiaChiKH = new JLabel("............................");
		lblDiaChiKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDiaChiKH.setBounds(180, 300, 240, 40);
		pKhachHang.add(lblDiaChiKH);

		JLabel lblNewLabel_5_1_1_1_1 = new JLabel("Email: ");
		lblNewLabel_5_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_1_1_1_1.setBounds(10, 350, 130, 40);
		pKhachHang.add(lblNewLabel_5_1_1_1_1);

		lblEmailKH = new JLabel("............................");
		lblEmailKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmailKH.setBounds(180, 350, 240, 40);
		pKhachHang.add(lblEmailKH);

		JPanel pThoiGian = new JPanel();
		pThoiGian.setBackground(Color.WHITE);
		pThoiGian.setBounds(449, 10, 700, 140);
		add(pThoiGian);
		pThoiGian.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Chọn ngày đến, ngày kết thúc:");
		lblNewLabel_1.setBounds(10, 10, 220, 23);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		pThoiGian.add(lblNewLabel_1);

		JLabel lblNewLabel_5_2 = new JLabel("Ngày đến: ");
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_2.setBounds(10, 43, 150, 30);
		pThoiGian.add(lblNewLabel_5_2);

		JLabel lblNewLabel_5_2_1 = new JLabel("Ngày kết thúc: ");
		lblNewLabel_5_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_2_1.setBounds(10, 83, 150, 30);
		pThoiGian.add(lblNewLabel_5_2_1);

		ngayDen = new JDateChooser();
		ngayDen.setDateFormatString("dd-MM-yyyy");
		ngayDen.setDate(new Date());
		ngayDen.setMinSelectableDate(new Date());
		ngayDen.setBounds(170, 43, 230, 30);
		pThoiGian.add(ngayDen);

		ngayKetThuc = new JDateChooser();
		ngayKetThuc.setDateFormatString("dd-MM-yyyy");
		Date nextDay = new Date(ngayDen.getDate().getTime() + 86400000);
		ngayKetThuc.setMinSelectableDate(nextDay);
		ngayKetThuc.setBounds(170, 83, 230, 30);
		pThoiGian.add(ngayKetThuc);

		JPanel pPhong = new JPanel();
		pPhong.setBackground(Color.WHITE);
		pPhong.setBounds(449, 160, 700, 180);
		add(pPhong);
		pPhong.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Ch\u1ECDn lo\u1EA1i ph\u00F2ng, c\u00E1c th\u00F4ng tin k\u00E8m theo:");
		lblNewLabel_2.setBounds(10, 10, 278, 25);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		pPhong.add(lblNewLabel_2);

		JLabel lblNewLabel_5_2_2 = new JLabel("Loại Phòng: ");
		lblNewLabel_5_2_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_2_2.setBounds(10, 44, 150, 30);
		pPhong.add(lblNewLabel_5_2_2);

		cbxLoaiPhong = new JComboBox<String>();
		cbxLoaiPhong.setBounds(170, 45, 230, 30);
		cbxLoaiPhong.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				int selectedIndex = cbxLoaiPhong.getSelectedIndex();
				if (selectedIndex != 0) {
					selectedLoaiPhong = lstLoaiPhong.get(selectedIndex - 1);
					lblDonGia.setText(selectedLoaiPhong.getDonGia() + " (VND)");
				} else {
					lblDonGia.setText("............................");
				}

			}
		});
		pPhong.add(cbxLoaiPhong);

		JLabel lblNewLabel_5_2_2_1 = new JLabel("Đơn Giá: ");
		lblNewLabel_5_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_2_2_1.setBounds(10, 84, 150, 30);
		pPhong.add(lblNewLabel_5_2_2_1);

		lblDonGia = new JLabel("............................");
		lblDonGia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDonGia.setBounds(170, 85, 230, 30);
		pPhong.add(lblDonGia);

		JLabel lblNewLabel_5_2_2_1_1 = new JLabel("Tên Phòng: ");
		lblNewLabel_5_2_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_2_2_1_1.setBounds(10, 124, 150, 30);
		pPhong.add(lblNewLabel_5_2_2_1_1);

		lblTenPhong = new JLabel("............................");
		lblTenPhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTenPhong.setBounds(170, 125, 118, 30);
		pPhong.add(lblTenPhong);

		btnTimPhongTrong = new JButton("Tìm Phòng Trống");
		btnTimPhongTrong.setBounds(300, 125, 130, 30);
		pPhong.add(btnTimPhongTrong);

		JPanel pThongTin = new JPanel();
		pThongTin.setBackground(Color.WHITE);
		pThongTin.setBounds(449, 350, 700, 213);
		add(pThongTin);
		pThongTin.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Thông tin về phiếu thuê :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(10, 10, 170, 24);
		pThongTin.add(lblNewLabel_3);

		JLabel lblNewLabel_5_2_3 = new JLabel("Tên Khách: ");
		lblNewLabel_5_2_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_2_3.setBounds(10, 44, 110, 30);
		pThongTin.add(lblNewLabel_5_2_3);

		JLabel lblTenKH_tt = new JLabel("............................");
		lblTenKH_tt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTenKH_tt.setBounds(130, 40, 190, 40);
		pThongTin.add(lblTenKH_tt);

		JLabel lblNewLabel_5_2_2_1_1_1 = new JLabel("Tên Phòng: ");
		lblNewLabel_5_2_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_2_2_1_1_1.setBounds(320, 44, 130, 30);
		pThongTin.add(lblNewLabel_5_2_2_1_1_1);

		JLabel lblTenPhong_tt = new JLabel("............................");
		lblTenPhong_tt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTenPhong_tt.setBounds(473, 44, 118, 30);
		pThongTin.add(lblTenPhong_tt);

		JLabel lblNewLabel_5_2_4 = new JLabel("Ngày đến: ");
		lblNewLabel_5_2_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_2_4.setBounds(10, 84, 110, 30);
		pThongTin.add(lblNewLabel_5_2_4);

		JLabel lblNewLabel_5_2_1_1 = new JLabel("Ngày kết thúc: ");
		lblNewLabel_5_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_2_1_1.setBounds(320, 84, 150, 30);
		pThongTin.add(lblNewLabel_5_2_1_1);

		JLabel lblNgayDen_tt = new JLabel("............................");
		lblNgayDen_tt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNgayDen_tt.setBounds(130, 84, 118, 30);
		pThongTin.add(lblNgayDen_tt);

		JLabel lblNgayKetThuc_tt = new JLabel("............................");
		lblNgayKetThuc_tt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNgayKetThuc_tt.setBounds(473, 84, 118, 30);
		pThongTin.add(lblNgayKetThuc_tt);

		JLabel lblNewLabel_5_2_2_1_1_2 = new JLabel("Số Ngày: ");
		lblNewLabel_5_2_2_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_2_2_1_1_2.setBounds(10, 124, 110, 30);
		pThongTin.add(lblNewLabel_5_2_2_1_1_2);

		JLabel lblSoNgay = new JLabel("............................");
		lblSoNgay.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSoNgay.setBounds(130, 124, 60, 30);
		pThongTin.add(lblSoNgay);

		JLabel lblNewLabel_5_2_2_1_1_2_1 = new JLabel("Đơn Giá: ");
		lblNewLabel_5_2_2_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_2_2_1_1_2_1.setBounds(220, 124, 100, 30);
		pThongTin.add(lblNewLabel_5_2_2_1_1_2_1);

		JLabel lblDonGia_tt = new JLabel("............................");
		lblDonGia_tt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDonGia_tt.setBounds(330, 125, 100, 30);
		pThongTin.add(lblDonGia_tt);

		JLabel lblNewLabel_5_2_2_1_1_2_1_1 = new JLabel("Tổng tiền: ");
		lblNewLabel_5_2_2_1_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_2_2_1_1_2_1_1.setBounds(462, 124, 100, 30);
		pThongTin.add(lblNewLabel_5_2_2_1_1_2_1_1);

		JLabel lblTongTien = new JLabel("............................");
		lblTongTien.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTongTien.setBounds(572, 124, 118, 30);
		pThongTin.add(lblTongTien);

		JPanel pControll = new JPanel();
		pControll.setBackground(Color.WHITE);
		pControll.setBounds(449, 573, 700, 82);
		add(pControll);
		pControll.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("C\u00E1c n\u00FAt th\u00EAm. l\u00E0m m\u1EDBi ...");
		lblNewLabel_4.setBounds(10, 10, 141, 13);
		pControll.add(lblNewLabel_4);

		btnDatPhong = new JButton("Đặt Phòng");
		btnDatPhong.setBounds(10, 33, 120, 30);
		pControll.add(btnDatPhong);

		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setBounds(152, 33, 120, 30);
		pControll.add(btnXoaTrang);

		// == Action ===========================
		btnDatPhong.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnTimPhongTrong.addActionListener(this);

		// === LoadData ========================
		loadLoaiPhong();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnDatPhong)) {
			System.out.println("Click đặt phòng");
		}
		if (o.equals(btnXoaTrang)) {
			System.out.println("Click xóa trắng");
		}
		if (o.equals(btnTimPhongTrong)) {
			timPhongTrong();
		}
	}

	private void timPhongTrong() {
		if (selectedLoaiPhong == null) {
			JOptionPane.showMessageDialog(null, "Hãy chọn loại phòng muốn đặt trước ^.^");
			return;
		}
		selectedPhong = PhongDao.getInstance().getPhongTrong(selectedLoaiPhong.getMaLP());
		if (selectedPhong == null) {
			JOptionPane.showMessageDialog(null,
					"Hiện không còn phòng trống cho loại phòng: " + selectedLoaiPhong.getTenLP());
			lblTenPhong.setText("............................");
			return;
		}
		lblTenPhong.setText(selectedPhong.getTen());
	}

	private void SampleOpenDialog() {
		ChooseCustomerDialog dialog = new ChooseCustomerDialog();

		dialog.setVisible(true);

		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				selectedKH = dialog.getSelectdCustomer();
				setCustomerInfo(selectedKH);
			}
		});
	}

	private void setCustomerInfo(KhachHangDTO kh) {
		// JLabel lblTenKH, lblCmtKH, lblSdtKH, lblEmailKH;
		if (kh.getTen() != null) {
			lblTenKH.setText(kh.getTen());
			lblCmtKH.setText(kh.getSoCMND());
			lblSdtKH.setText(kh.getSdt());
			lblEmailKH.setText(kh.getEmail());
			lblDiaChiKH.setText(kh.getDiaChi());
		}
	}

	private void loadLoaiPhong() {
		DefaultComboBoxModel<String> dcm = new DefaultComboBoxModel<String>();
		dcm.addElement(" -- Chọn loại phòng --");

		lstLoaiPhong = LoaiPhongDao.getInstance().getAllLoaiPhong();
		// cai nay se ko phai try catch nua
		for (LoaiPhongDTO loaiPhongDTO : lstLoaiPhong) {
			dcm.addElement(loaiPhongDTO.getTenLP());
		}

		cbxLoaiPhong.setModel(dcm);

	}

}
