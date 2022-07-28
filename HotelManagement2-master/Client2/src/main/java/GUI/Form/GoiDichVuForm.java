package GUI.Form;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ClientService.DichVuService;
import ClientService.PhieuDichVuService;
import ClientService.PhongService;
import Model.PageList;
import Rmi.DTO.DichVuDTO;
import Rmi.DTO.PhongDTO;

public class GoiDichVuForm extends JPanel implements ActionListener {
	private JTable tblDsPhong, tblDsDv;
	private JTextField txtSoLuong;
	private JButton btnPhongPrev, btnPhongNext;
	private JButton btnDvPrev, btnDvNext;
	private PageList<PhongDTO> lstPhong = null;
	private PageList<DichVuDTO> lstDichVu = null;
	private int currentPhongPage;
	private int maxPhongPage;
	private int currentDvPage;
	private int maxDvPage;
	private int maxRow = 10;

	private List<Component> lstComponent = new ArrayList<>();
	private DecimalFormat Currentcyformatter = new DecimalFormat("###,###,###.00 VND");
	private JLabel lblTenDV;
	private JLabel lblDonGia;
	private JLabel lblDonVi;
	private JLabel lblTongTien;
	private JButton btnThemDV;
	private JButton btnXoaTrang;
	private PhongDTO selectedPhong = null;
	private DichVuDTO selectedDichVu = null;
	private JLabel lblDichVuPage;
	private JLabel lblPhongPage;

	public GoiDichVuForm() {
		setBackground(Color.decode("#d4d5d6"));
		setBounds(0, 0, 1180, 820);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(20, 20, 1140, 780);
		add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 415, 520);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 65, 395, 385);
		panel_1.add(scrollPane);

		tblDsPhong = new JTable();
		tblDsPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = tblDsPhong.getSelectedRow();
				selectedPhong = lstPhong.getListData().get(selectedRow);
			}
		});
		tblDsPhong.setRowHeight(tblDsPhong.getRowHeight() + 10);
		scrollPane.setViewportView(tblDsPhong);

		btnPhongPrev = new JButton("<");
		btnPhongPrev.setBounds(10, 460, 130, 30);
		panel_1.add(btnPhongPrev);

		lblPhongPage = new JLabel("page");
		lblPhongPage.setBounds(150, 460, 90, 30);
		panel_1.add(lblPhongPage);

		btnPhongNext = new JButton(">");
		btnPhongNext.setBounds(250, 460, 130, 30);
		panel_1.add(btnPhongNext);

		JLabel lblNewLabel_3 = new JLabel("Chọn Phòng");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(10, 10, 286, 30);
		panel_1.add(lblNewLabel_3);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(440, 10, 690, 760);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 65, 670, 385);
		panel_2.add(scrollPane_1);

		tblDsDv = new JTable();
		tblDsDv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = tblDsDv.getSelectedRow();
				selectedDichVu = lstDichVu.getListData().get(selectedRow);
				ShowDichVuInfo();
			}
		});
		tblDsDv.setRowHeight(tblDsDv.getRowHeight() + 10);
		scrollPane_1.setViewportView(tblDsDv);

		btnDvPrev = new JButton("<");
		btnDvPrev.setBounds(10, 460, 130, 30);
		panel_2.add(btnDvPrev);

		lblDichVuPage = new JLabel("page");
		lblDichVuPage.setBounds(150, 460, 90, 30);
		panel_2.add(lblDichVuPage);

		btnDvNext = new JButton(">");
		btnDvNext.setBounds(242, 460, 130, 30);
		panel_2.add(btnDvNext);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 515, 670, 2);
		panel_2.add(separator);

		JLabel lblNewLabel_2 = new JLabel("Tên dịch vụ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(10, 525, 130, 30);
		panel_2.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Đơn vị");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(291, 525, 130, 30);
		panel_2.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("Đơn giá");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2_2.setBounds(10, 570, 130, 30);
		panel_2.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_2_1 = new JLabel("Nhập số lượng");
		lblNewLabel_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2_2_1.setBounds(291, 570, 130, 30);
		panel_2.add(lblNewLabel_2_2_1);

		JLabel lblNewLabel_2_2_2 = new JLabel("Tổng tiền");
		lblNewLabel_2_2_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2_2_2.setBounds(10, 620, 130, 30);
		panel_2.add(lblNewLabel_2_2_2);

		lblTenDV = new JLabel("............");
		lblTenDV.setBounds(140, 525, 140, 30);
		lstComponent.add(lblTenDV);
		panel_2.add(lblTenDV);

		lblDonGia = new JLabel("............");
		lblDonGia.setBounds(140, 565, 140, 30);
		lstComponent.add(lblDonGia);
		panel_2.add(lblDonGia);

		lblDonVi = new JLabel("............");
		lblDonVi.setBounds(431, 527, 140, 30);
		lstComponent.add(lblDonVi);
		panel_2.add(lblDonVi);

		lblTongTien = new JLabel("............");
		lblTongTien.setBounds(140, 620, 140, 30);
		lstComponent.add(lblTongTien);
		panel_2.add(lblTongTien);

		txtSoLuong = new JTextField();
		txtSoLuong.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (selectedDichVu == null) {
					return;
				}
				if (txtSoLuong.getText().toString().equals("")) {
					lblTongTien.setText("");
				}else {
					
				int soLuong = Integer.parseInt(txtSoLuong.getText().toString());
				lblTongTien.setText(Currentcyformatter.format(soLuong * selectedDichVu.getDonGia()));
				}
			}
		});
		txtSoLuong.setBounds(431, 570, 130, 30);
		txtSoLuong.setColumns(10);
		lstComponent.add(txtSoLuong);
		panel_2.add(txtSoLuong);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 688, 670, 2);
		panel_2.add(separator_1);

		btnThemDV = new JButton("Đặt Dịch Vụ");
		btnThemDV.setBounds(540, 700, 100, 30);
		panel_2.add(btnThemDV);

		btnXoaTrang = new JButton("Xóa Trắng");
		btnXoaTrang.setBounds(430, 700, 100, 30);
		panel_2.add(btnXoaTrang);

		JLabel lblNewLabel_3_1 = new JLabel("Chọn Dịch Vụ");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3_1.setBounds(10, 10, 286, 30);
		panel_2.add(lblNewLabel_3_1);

		// == add action =================
		btnDvNext.addActionListener(this);
		btnDvPrev.addActionListener(this);
		btnPhongNext.addActionListener(this);
		btnPhongPrev.addActionListener(this);
		btnThemDV.addActionListener(this);
		btnXoaTrang.addActionListener(this);

		// ==== load ds =======
		lstPhong = PhongService.getInstance().getListPhongDaThue(1, maxRow, "");
		LoadDsPhong(lstPhong);

		lstDichVu = DichVuService.getInstance().getListDichVuByPage(1, maxRow, "");
		LoadDsDichVu(lstDichVu);
	}

	protected void ShowDichVuInfo() {
		lblTenDV.setText(selectedDichVu.getTenDv());
		lblDonVi.setText(selectedDichVu.getDonVi());
		lblDonGia.setText(Currentcyformatter.format(selectedDichVu.getDonGia()));

		if (txtSoLuong.getText().toString().equals("")) {
			lblTongTien.setText("");
		}else {
			
		int soLuong = Integer.parseInt(txtSoLuong.getText().toString());
		lblTongTien.setText(Currentcyformatter.format(soLuong * selectedDichVu.getDonGia()));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnDvPrev)) {
			DichVuPrevPage();
		}
		if (o.equals(btnDvNext)) {
			DichVuNextPage();
		}
		if (o.equals(btnPhongNext)) {
			PhongNextPage();
		}
		if (o.equals(btnPhongPrev)) {
			PhongPrevPage();
		}
		if (o.equals(btnThemDV)) {
			DatDichVu();
			XoaTrang();
		}
		if (o.equals(btnXoaTrang)) {
			XoaTrang();
		}

	}

	private void DichVuPrevPage() {
		currentDvPage--;
		if (currentDvPage < 1) {
			currentDvPage = 1;
			return;
		}

		int PrevPageNumb = lstDichVu.getCurrentPage() - 1;
		lstDichVu = DichVuService.getInstance().getListDichVuByPage(PrevPageNumb, maxRow, "");
		LoadDsDichVu(lstDichVu);
	}

	private void DichVuNextPage() {
		currentDvPage++;
		if (currentDvPage > maxDvPage) {
			currentDvPage = maxDvPage;
			return;
		}

		int nextPageNumb = lstDichVu.getCurrentPage() + 1;
		lstDichVu = DichVuService.getInstance().getListDichVuByPage(nextPageNumb, maxRow, "");
		LoadDsDichVu(lstDichVu);

	}

	private void LoadDsDichVu(PageList<DichVuDTO> lstDichVu2) {
		String[] tieude = { "Mã Dịch Vụ", "Tên Dịch Vụ", "Đơn Giá", "Đơn Vị" };
		DefaultTableModel model = new DefaultTableModel(tieude, 0);

		for (DichVuDTO dichvu : lstDichVu.getListData()) {
			Object[] o = { dichvu.getMaDv(), dichvu.getTenDv(), dichvu.getDonGia(), dichvu.getDonVi() };
			model.addRow(o);
		}
		tblDsDv.setModel(model);

		currentDvPage = lstDichVu.getCurrentPage();
		maxDvPage = lstDichVu.getMaxPage();

		selectedDichVu = null;
		showDvPageNumber();

	}

	private void showDvPageNumber() {
		if (currentDvPage > maxDvPage) {
			currentDvPage = maxDvPage;
		}
		if (maxDvPage == 1) {
			lblDichVuPage.setText("1");
		} else {
			lblDichVuPage.setText(currentDvPage + "/" + maxDvPage);
		}

	}

	private void PhongNextPage() {
		currentPhongPage++;
		if (currentPhongPage > maxPhongPage) {
			currentPhongPage = maxPhongPage;
			return;
		}

		int nextPageNumb = lstPhong.getCurrentPage() + 1;
		try {
			lstPhong = PhongService.getInstance().getListPhongPaged(nextPageNumb, maxRow, "");
			LoadDsPhong(lstPhong);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	private void PhongPrevPage() {
		currentPhongPage--;
		if (currentPhongPage < 1) {
			currentPhongPage = 1;
			return;
		}

		int PrevPageNumb = lstPhong.getCurrentPage() - 1;
		try {
			lstPhong = PhongService.getInstance().getListPhongPaged(PrevPageNumb, maxRow, "");
			LoadDsPhong(lstPhong);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	private void DatDichVu() {
		if (selectedPhong == null) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn phòng nào cả");
			return;
		}
		if (selectedDichVu == null) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn dịch vụ nào cả");
			return;
		}
		int soLuong;
		try {
			soLuong = Integer.parseInt(txtSoLuong.getText().toString());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Số lượng nhập phải là số");
			return;
		}

		boolean kq = PhieuDichVuService.getInstance().addPhieuDichVuByMaPhong(selectedPhong.getMaP(),
				selectedDichVu.getMaDv(), soLuong);
		if (kq) {
			JOptionPane.showMessageDialog(null, "Đã đặt dịch vụ thành công cho phòng : " + selectedPhong.getTen());
			return;
		}
		JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi thêm phiếu dịch vụ : " + selectedPhong.getTen());

	}

	private void XoaTrang() {
		for (Component component : lstComponent) {
			if (component instanceof JLabel) {
				((JLabel) component).setText("...........");
			}
		}
	}

	private void LoadDsPhong(PageList<PhongDTO> lstPhong) {
		String[] tieude = { "Mã Phòng", "Tên Phòng" };
		DefaultTableModel model = new DefaultTableModel(tieude, 0);

		for (PhongDTO phong : lstPhong.getListData()) {
			Object[] o = { phong.getMaP(), phong.getTen() };
			model.addRow(o);
		}
		tblDsPhong.setModel(model);

		currentPhongPage = lstPhong.getCurrentPage();
		maxPhongPage = lstPhong.getMaxPage();

		selectedPhong = null;
		showPhongPageNumber();
	}

	private void showPhongPageNumber() {
		if (currentPhongPage > maxPhongPage) {
			currentPhongPage = maxPhongPage;
		}
		if (maxPhongPage == 1) {
			lblPhongPage.setText("1");
		} else {
			lblPhongPage.setText(currentPhongPage + "/" + maxPhongPage);
		}
	}
}
