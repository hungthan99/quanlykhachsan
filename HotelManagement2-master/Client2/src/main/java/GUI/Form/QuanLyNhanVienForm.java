package GUI.Form;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import CustomControll.ColorButton2;
import GUI.Dialog.AddNhanVienDialog;
import GUI.Dialog.UpdateNhanVienDialog;
import ClientService.*;
import Model.PageList;
import Rmi.DTO.KhachHangDTO;
import Rmi.DTO.NhanVienDTO;

public class QuanLyNhanVienForm extends JPanel implements ActionListener {
	private ColorButton2 btnThemNhanVien;
	private ColorButton2 btnSuaNhanVien;
	private ColorButton2 btnXoaNhanVien;
	private JTextField txtSearchText;
	private JButton btnSearch;
	private JTable tblDsNhanVien;
	private JButton btnPrev;
	private JLabel lblPage;
	private JButton btnNext;
	private PageList<NhanVienDTO> lstNhanVien;
	private int currentPage, maxPage;
	private static int maxRow = 10;
	private NhanVienDTO selectedNhanVien = null;
	private NhanVienService nhanVienService = null;

	public QuanLyNhanVienForm() {
		setBackground(Color.decode("#d4d5d6"));
		setBounds(0, 0, 1180, 820);
		setLayout(null);

		JPanel pSearch = new JPanel();
		pSearch.setBackground(Color.WHITE);
		pSearch.setBounds(10, 10, 1160, 120);
		add(pSearch);
		pSearch.setLayout(null);

		btnThemNhanVien = new ColorButton2(Color.decode("#34e039"), Color.decode("#38f53e"), Color.decode("#32cf37"),
				Color.decode("#34e039"));
		btnThemNhanVien.setText("Thêm Nhân Viên");
		btnThemNhanVien.setBounds(10, 80, 129, 30);
		pSearch.add(btnThemNhanVien);

		btnSuaNhanVien = new ColorButton2(Color.decode("#f0f03a"), Color.decode("#fafa3c"), Color.decode("#e0e034"),
				Color.decode("#f0f03a"));
		btnSuaNhanVien.setBounds(153, 80, 171, 30);
		btnSuaNhanVien.setText("Sửa Thông Tin Nhân Viên");
		pSearch.add(btnSuaNhanVien);

		btnXoaNhanVien = new ColorButton2(Color.decode("#ed3752"), Color.decode("#ff425e"), Color.decode("#e63c55"),
				Color.decode("#ed3752"));
		btnXoaNhanVien.setBounds(334, 80, 129, 30);
		btnXoaNhanVien.setText("Xóa Nhân Viên");
		pSearch.add(btnXoaNhanVien);

		txtSearchText = new JTextField();
		txtSearchText.setBounds(10, 10, 310, 30);
		pSearch.add(txtSearchText);
		txtSearchText.setColumns(10);

		btnSearch = new JButton("Tìm Kiếm");
		btnSearch.setBounds(330, 10, 110, 30);
		pSearch.add(btnSearch);

		JPanel pTable = new JPanel();
		pTable.setBackground(Color.WHITE);
		pTable.setBounds(10, 170, 1160, 640);
		add(pTable);
		pTable.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 1140, 508);
		pTable.add(scrollPane);

		tblDsNhanVien = new JTable();
		tblDsNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = tblDsNhanVien.getSelectedRow();
				selectedNhanVien = lstNhanVien.getListData().get(selectedRow);
			}
		});
		tblDsNhanVien.setRowHeight(tblDsNhanVien.getRowHeight() + 10);
		scrollPane.setViewportView(tblDsNhanVien);

		JLabel lblNewLabel = new JLabel("Danh sách nhân viên");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 10, 150, 30);
		pTable.add(lblNewLabel);

		btnPrev = new JButton("<");
		btnPrev.setBounds(359, 589, 85, 21);
		pTable.add(btnPrev);

		lblPage = new JLabel("paging");
		lblPage.setBounds(450, 593, 45, 13);
		pTable.add(lblPage);

		btnNext = new JButton(">");
		btnNext.setBounds(500, 589, 85, 21);
		pTable.add(btnNext);

		// == action ============
		btnPrev.addActionListener(this);
		btnNext.addActionListener(this);
		btnThemNhanVien.addActionListener(this);
		btnSuaNhanVien.addActionListener(this);
		btnXoaNhanVien.addActionListener(this);
		btnSearch.addActionListener(this);
		txtSearchText.addActionListener(this);

		// == load ds nhan vien ====
		try {
			nhanVienService = NhanVienService.getInstance();
			lstNhanVien = nhanVienService.getListNhanVienByPage(1, maxRow, "");
			LoadDsNhanVien(lstNhanVien);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void LoadDsNhanVien(PageList<NhanVienDTO> lstNhanVien2) {
		String[] tieude = { "Mã Nhân Viên", "Tên Nhân Viên", "Giới Tính", "Email", "Số Điện Thoại" };
		DefaultTableModel model = new DefaultTableModel(tieude, 0);

		for (NhanVienDTO nhanvien : lstNhanVien.getListData()) {
			Object[] o = { nhanvien.getMaNV(), nhanvien.getTen(), nhanvien.getGioiTinh(), nhanvien.getEmail(),
					nhanvien.getSdt() };
			model.addRow(o);
		}
		tblDsNhanVien.setModel(model);

		currentPage = lstNhanVien.getCurrentPage();
		maxPage = lstNhanVien.getMaxPage();

		showPageNumber();
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
		if (o.equals(btnPrev)) {
			LoadPrevPage();
		}
		if (o.equals(btnNext)) {
			LoadNextPage();
		}
		if (o.equals(btnThemNhanVien)) {
			OpenAddNhanVienDialog();
		}
		if (o.equals(btnXoaNhanVien)) {
			DeleteNhanVienSelected();
		}
		if (o.equals(btnSuaNhanVien)) {
			OpenUpdateNhanVienDialog();
		}
		if (o.equals(btnSearch) || o.equals(txtSearchText)) {
			SearchDsNhanVien();
		}
	}

	private void OpenUpdateNhanVienDialog() {
		if (selectedNhanVien == null) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn nhân viên nào cả");
			return;
		}
		UpdateNhanVienDialog dialog = new UpdateNhanVienDialog(selectedNhanVien);
		dialog.setVisible(true);
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				reloadDsNhanVien();
			}
		});
	}

	private void DeleteNhanVienSelected() {
		if (selectedNhanVien == null) {
			JOptionPane.showMessageDialog(null, "Oops!, Bạn chưa chọn nhân viên nào cả");
			return;
		}
		if (!NhanVienService.getInstance().isDeleteAble(selectedNhanVien.getMaNV())) {
			JOptionPane.showMessageDialog(null, "Nhân viên hiện đang hoạt động");
			return;
		}
		if (JOptionPane.showConfirmDialog(this,
				"Bạn có muốn xóa nhân viên " + selectedNhanVien.getTen() + " có số điện thoại là: "
						+ selectedNhanVien.getSdt() + " không?",
				"Cảnh báo.", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
			deleteNhanvien();
		}
		reloadDsNhanVien();
		return;

	}

	private void deleteNhanvien() {
		if (NhanVienService.getInstance().deleteNhanVienById(selectedNhanVien.getMaNV())) {
			JOptionPane.showMessageDialog(null, "Đã xóa nhân viên : " + selectedNhanVien.getTen());
		} else {
			JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi xóa nhân viên : " + selectedNhanVien.getTen());
		}
	}

	private void OpenAddNhanVienDialog() {
		AddNhanVienDialog dialog = new AddNhanVienDialog();
		dialog.setVisible(true);
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				reloadDsNhanVien();
			}
		});
	}

	private void SearchDsNhanVien() {
		String nameSearch = txtSearchText.getText().toString();
		if (nameSearch.length() <= 0) {
			JOptionPane.showMessageDialog(null, "Oops!, bạn chưa nhập tên nhân viên cần tìm");
		}
		txtSearchText.selectAll();
		txtSearchText.requestFocus();
		
		try {
			lstNhanVien = nhanVienService.getListNhanVienByPage(1, maxRow, nameSearch);
			LoadDsNhanVien(lstNhanVien);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void LoadNextPage() {
		currentPage++;
		if (currentPage > maxPage) {
			currentPage = maxPage;
			return;
		}
		int nextPageNumb = lstNhanVien.getCurrentPage() + 1;
		try {
			String nameSearch = txtSearchText.getText().toString();
			lstNhanVien = nhanVienService.getListNhanVienByPage(nextPageNumb, maxRow,
					nameSearch.length() > 0 ? nameSearch : "");
			LoadDsNhanVien(lstNhanVien);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		selectedNhanVien = null;
	}

	private void LoadPrevPage() {
		currentPage--;
		if (currentPage < 1) {
			currentPage = 1;
			return;
		}
		int PrevPageNumb = lstNhanVien.getCurrentPage() - 1;
		try {
			String nameSearch = txtSearchText.getText().toString();
			lstNhanVien = nhanVienService.getListNhanVienByPage(PrevPageNumb, maxRow,
					nameSearch.length() > 0 ? nameSearch : "");
			LoadDsNhanVien(lstNhanVien);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		selectedNhanVien = null;
	}

	private void reloadDsNhanVien() {
		try {
			lstNhanVien = nhanVienService.getListNhanVienByPage(1, maxRow, "");
			LoadDsNhanVien(lstNhanVien);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		selectedNhanVien = null;
	}
}
