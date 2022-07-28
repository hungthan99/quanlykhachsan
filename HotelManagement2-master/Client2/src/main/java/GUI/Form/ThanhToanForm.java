package GUI.Form;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ClientService.LoaiPhongService;
import ClientService.PhieuDichVuService;
import ClientService.PhieuThueService;
import Model.PageList;
import Rmi.DTO.LoaiPhongDTO;
import Rmi.DTO.PhieuDichVuInfoDTO;
import Rmi.DTO.PhieuThuePhongInfoDTO;
import Rmi.DTO.PhongDTO;

public class ThanhToanForm extends JPanel implements ActionListener {
	private JTable tblDsPhieuThue;
	private JTable tblDsDichVu;
	private PageList<PhieuThuePhongInfoDTO> lstPhieuThue;
	private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	private DecimalFormat Currentcyformatter = new DecimalFormat("###,###,###.00 VND");
	private List<Component> lstComponent = new ArrayList<>();
	private PhieuThuePhongInfoDTO selectedPT = null;
	private List<PhieuDichVuInfoDTO> lstPdv = null;
	private JButton btnThanhToan;
	private JButton btnNext;
	private JButton btnPrev;
	private int maxPage;
	private int currentPage;
	private JLabel lblPage;
	private int maxRow = 5;
	private JLabel lblTenKH;
	private JLabel lblTenPhong;
	private JLabel lblNgayNhan;
	private JLabel lblNgayTra;
	private JLabel lblSoNgay;
	private JLabel lblTienPhong;
	private JLabel lblTienDichVu;
	private JLabel lblTongTien;
	private long diffrence;
	private double tongtiendv;
	private double tienphong;
	private JButton btnXoaTrang;

	public ThanhToanForm() {
		setBackground(Color.decode("#d4d5d6"));
		setBounds(0, 0, 1180, 820);
		setLayout(null);

		JPanel pTable = new JPanel();
		pTable.setBackground(Color.WHITE);
		pTable.setBounds(20, 20, 1160, 790);
		add(pTable);
		pTable.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 650, 400);
		pTable.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 630, 310);
		panel.add(scrollPane);

		tblDsPhieuThue = new JTable();
		tblDsPhieuThue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = tblDsPhieuThue.getSelectedRow();
				selectedPT = lstPhieuThue.getListData().get(selectedRow);
				ShowDichVuTable();
				ShowInfoLabel();
				ShowDichVuInfo();
				ShowTongTien();
			}
		});
		tblDsPhieuThue.setRowHeight(tblDsPhieuThue.getRowHeight() + 5);
		scrollPane.setViewportView(tblDsPhieuThue);

		btnPrev = new JButton("<");
		btnPrev.setBounds(160, 365, 85, 25);
		panel.add(btnPrev);

		lblPage = new JLabel("New label");
		lblPage.setBounds(272, 365, 45, 25);
		panel.add(lblPage);

		btnNext = new JButton(">");
		btnNext.setBounds(359, 365, 85, 25);
		panel.add(btnNext);

		JLabel lblNewLabel_1 = new JLabel("Chọn Phiếu Thuê");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 10, 307, 25);
		panel.add(lblNewLabel_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(670, 10, 480, 770);
		pTable.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1_1 = new JLabel("Danh sách dịch vụ đã đặt");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(10, 10, 307, 25);
		panel_1.add(lblNewLabel_1_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 50, 460, 302);
		panel_1.add(scrollPane_1);

		tblDsDichVu = new JTable();

		scrollPane_1.setViewportView(tblDsDichVu);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 380, 460, 2);
		panel_1.add(separator);

		JLabel lblNewLabel = new JLabel("Tên khách");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 420, 91, 40);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("Tên Phòng");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 470, 91, 40);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Ngày nhận");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_1.setBounds(10, 520, 91, 40);
		panel_1.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("Ngày trả");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_1_1.setBounds(240, 520, 91, 40);
		panel_1.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_2_1_2 = new JLabel("Số ngày");
		lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_1_2.setBounds(10, 570, 91, 40);
		panel_1.add(lblNewLabel_2_1_2);

		JLabel lblNewLabel_2_1_2_1 = new JLabel("Tiền phòng");
		lblNewLabel_2_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_1_2_1.setBounds(240, 570, 91, 40);
		panel_1.add(lblNewLabel_2_1_2_1);

		JLabel lblNewLabel_2_1_2_1_1 = new JLabel("Tiền Dịch Vụ");
		lblNewLabel_2_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_1_2_1_1.setBounds(10, 620, 91, 40);
		panel_1.add(lblNewLabel_2_1_2_1_1);

		JLabel lblNewLabel_2_1_2_1_1_1 = new JLabel("Tổng tiền");
		lblNewLabel_2_1_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_1_2_1_1_1.setBounds(10, 670, 91, 40);
		panel_1.add(lblNewLabel_2_1_2_1_1_1);

		lblTenKH = new JLabel("......................................");
		lblTenKH.setBounds(111, 420, 242, 40);
		lstComponent.add(lblTenKH);
		panel_1.add(lblTenKH);

		lblTenPhong = new JLabel("......................................");
		lblTenPhong.setBounds(111, 470, 242, 40);
		lstComponent.add(lblTenPhong);
		panel_1.add(lblTenPhong);

		lblNgayNhan = new JLabel("......................................");
		lblNgayNhan.setBounds(111, 520, 119, 40);
		lstComponent.add(lblNgayNhan);
		panel_1.add(lblNgayNhan);

		lblNgayTra = new JLabel("......................................");
		lblNgayTra.setBounds(344, 520, 105, 40);
		lstComponent.add(lblNgayTra);
		panel_1.add(lblNgayTra);

		lblSoNgay = new JLabel("......................................");
		lblSoNgay.setBounds(111, 570, 119, 40);
		lstComponent.add(lblSoNgay);
		panel_1.add(lblSoNgay);

		lblTienPhong = new JLabel("......................................");
		lblTienPhong.setBounds(341, 570, 108, 40);
		lstComponent.add(lblTienPhong);
		panel_1.add(lblTienPhong);

		lblTienDichVu = new JLabel("......................................");
		lblTienDichVu.setBounds(111, 620, 338, 40);
		lstComponent.add(lblTienDichVu);
		panel_1.add(lblTienDichVu);

		lblTongTien = new JLabel("......................................");
		lblTongTien.setBounds(111, 670, 338, 40);
		lstComponent.add(lblTongTien);
		panel_1.add(lblTongTien);

		btnThanhToan = new JButton("Thanh Toán");
		btnThanhToan.setBounds(343, 720, 106, 30);
		panel_1.add(btnThanhToan);

		btnXoaTrang = new JButton("Xóa Trắng");
		btnXoaTrang.setBounds(246, 720, 85, 30);
		panel_1.add(btnXoaTrang);
		// == Add action ========================
		btnNext.addActionListener(this);
		btnPrev.addActionListener(this);
		btnThanhToan.addActionListener(this);
		btnXoaTrang.addActionListener(this);

		// === load ds =============
		lstPhieuThue = PhieuThueService.getInstance().getListPhieuThueByPage(1, maxRow);
		LoadDsPhieuThue(lstPhieuThue);

	}

	protected void ShowTongTien() {
		lblTongTien.setText(Currentcyformatter.format(tienphong + tongtiendv));

	}

	protected void ShowDichVuInfo() {
		tongtiendv = 0;
		for (PhieuDichVuInfoDTO phieuDichVuInfoDTO : lstPdv) {
			tongtiendv = phieuDichVuInfoDTO.getSoLuong() * phieuDichVuInfoDTO.getDichVu().getDonGia();
		}

		lblTienDichVu.setText(Currentcyformatter.format(tongtiendv));
	}

	protected void ShowInfoLabel() {
		lblTenKH.setText(selectedPT.getKhachHangDTO().getTen());
		lblTenPhong.setText(selectedPT.getPhongDTO().getTen());
		lblNgayNhan.setText(dateFormat.format(selectedPT.getNgayNhan()));
		lblNgayTra.setText(dateFormat.format(selectedPT.getNgayTra()));

		long diff = selectedPT.getNgayTra().getTime() - selectedPT.getNgayNhan().getTime();
		TimeUnit time = TimeUnit.DAYS;
		diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
		if (diffrence == 0) {
			diffrence++;
		}

		lblSoNgay.setText(diffrence + "( Ngày )");

		tienphong = TinhTienPhong(selectedPT.getPhongDTO(), diffrence);

		lblTienPhong.setText(Currentcyformatter.format(tienphong));
	}

	private double TinhTienPhong(PhongDTO phongDTO, long day) {
		LoaiPhongDTO loaiPhong = LoaiPhongService.getInstance().getLoaiPhongById(phongDTO.getLoaiPhong_id());
		return loaiPhong.getDonGia() * day;
	}

	protected void ShowDichVuTable() {
		if (selectedPT == null) {
			System.out.println("Chưa chọn dịch vụ");
		}

		lstPdv = PhieuDichVuService.getInstance().getListPhieuDichVuByMaPT(selectedPT.getMaPT());
		LoadDsDichVu(lstPdv);
	}

	private void LoadDsDichVu(List<PhieuDichVuInfoDTO> lstPdv2) {
		String[] tieude = { "Mã Dv", "Tên Dv", "Số lượng", "đơn giá" };
		DefaultTableModel model = new DefaultTableModel(tieude, 0);
		for (PhieuDichVuInfoDTO pdvInfo : lstPdv2) {

			Object[] o = { pdvInfo.getDichVu().getMaDv(), pdvInfo.getDichVu().getTenDv(), pdvInfo.getSoLuong(),
					pdvInfo.getDichVu().getDonGia() };
			model.addRow(o);
		}
		tblDsDichVu.setModel(model);
	}

	private void LoadDsPhieuThue(PageList<PhieuThuePhongInfoDTO> lstPhieuThue2) {
		String[] tieude = { "Mã Phiếu thuê", "Mã Phòng", "Tên Phòng", "Tên Khách Hàng", "Ngày Đến", "Ngày Trả" };
		DefaultTableModel model = new DefaultTableModel(tieude, 0);
		for (PhieuThuePhongInfoDTO DTO : lstPhieuThue2.getListData()) {
			Object[] o = { DTO.getMaPT(), DTO.getPhongDTO().getMaP(), DTO.getPhongDTO().getTen(),
					DTO.getKhachHangDTO().getTen(), dateFormat.format(DTO.getNgayNhan()),
					dateFormat.format(DTO.getNgayTra()) };
			model.addRow(o);
		}
		tblDsPhieuThue.setModel(model);

		currentPage = lstPhieuThue2.getCurrentPage();
		maxPage = lstPhieuThue2.getMaxPage();

		showPageNumber();
		selectedPT = null;
	}

	private void showPageNumber() {
		if (currentPage > maxPage) {
			currentPage = maxPage;
		}
		if (maxPage == 1) {
			lblPage.setText("1");
		} else {
			lblPage.setText(currentPage + "/" + maxPage);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnNext)) {
			LoadNextPhieuThuePage();
		}
		if (o.equals(btnPrev)) {
			LoadPrevPhieuThuePage();
		}
		if (o.equals(btnXoaTrang)) {
			XoaTrang();
		}
		if (o.equals(btnThanhToan)) {
			ThanhToan();
		}

	}

	private void ThanhToan() {
		if (selectedPT == null) {
			JOptionPane.showMessageDialog(null, "bạn hãy chọn 1 phiếu thuê mới");
			return;
		}

		boolean kq = PhieuDichVuService.getInstance().updateThanhToanDvByMaPT(selectedPT.getMaPT());
		if (kq) {
			JOptionPane.showMessageDialog(null, "Đã thanh toán tiền phòng và dịch vụ.");
			reloadDsPhieuThue();
			return;
		}
		JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi thanh toán Phiếu THuê: " + selectedPT.getMaPT());
	}

	private void XoaTrang() {
		for (Component component : lstComponent) {
			if (component instanceof JLabel) {
				((JLabel) component).setText("......................................");
			}
		}
		selectedPT = null;
	}

	private void LoadPrevPhieuThuePage() {
		currentPage--;
		if (currentPage < 1) {
			currentPage = 1;
			return;
		}

		int PrevPageNumb = lstPhieuThue.getCurrentPage() - 1;
		lstPhieuThue = PhieuThueService.getInstance().getListPhieuThueByPage(PrevPageNumb, maxRow);
		LoadDsPhieuThue(lstPhieuThue);

	}

	private void LoadNextPhieuThuePage() {
		currentPage++;
		if (currentPage > maxPage) {
			currentPage = maxPage;
			return;
		}

		int nextPageNumb = lstPhieuThue.getCurrentPage() + 1;
		lstPhieuThue = PhieuThueService.getInstance().getListPhieuThueByPage(nextPageNumb, maxRow);
		LoadDsPhieuThue(lstPhieuThue);
	}

	private void reloadDsPhieuThue() {

		lstPhieuThue = PhieuThueService.getInstance().getListPhieuThueByPage(1, maxRow);
		LoadDsPhieuThue(lstPhieuThue);

		DefaultTableModel dtm = (DefaultTableModel) tblDsDichVu.getModel();
		dtm.setRowCount(0);
	}
}
