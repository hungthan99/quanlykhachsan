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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import CustomControll.ColorButton2;
import Dao.LoaiPhongDao;
import Dao.PhongDao;
import Dao.TinhTrangPhongDao;
import GUI.Dialog.AddPhongDialog;
import GUI.Dialog.UpdatePhongDialog;
import Model.PageList;
import Rmi.DTO.LoaiPhongDTO;
import Rmi.DTO.PhongDTO;
import Rmi.DTO.TinhTrangPhongDTO;

public class QuanLyPhongForm extends JPanel implements ActionListener {
	private PhongDao phongDao = null;
	private JTable tblDsPhong;
	private JButton btnPrev, btnNext;
	private JLabel lblPage;
	private PageList<PhongDTO> lstPhong;
	private int currentPage, maxPage;
	private static int maxRow = 3;
	private ColorButton2 btnThemPhong, btnSuaPhong, btnXoaPhong;
	private JTextField txtSearchText;
	private JButton btnSearch;
	private PhongDTO selectedPhong = null;
	private List<TinhTrangPhongDTO> lstTTP;
	private List<LoaiPhongDTO> lstLP;
	private String trangthaiSelected;

	public QuanLyPhongForm() {
		setBackground(Color.decode("#d4d5d6"));
		setBounds(0, 0, 1180, 820);
		setLayout(null);

		JPanel pSearch = new JPanel();
		pSearch.setBackground(Color.WHITE);
		pSearch.setBounds(10, 10, 1160, 120);
		add(pSearch);
		pSearch.setLayout(null);

		btnThemPhong = new ColorButton2(Color.decode("#34e039"), Color.decode("#38f53e"), Color.decode("#32cf37"),
				Color.decode("#34e039"));
		btnThemPhong.setText("Thêm Phòng");
		btnThemPhong.setBounds(10, 80, 100, 30);
		pSearch.add(btnThemPhong);

		btnSuaPhong = new ColorButton2(Color.decode("#f0f03a"), Color.decode("#fafa3c"), Color.decode("#e0e034"),
				Color.decode("#f0f03a"));
		btnSuaPhong.setBounds(120, 80, 150, 30);
		btnSuaPhong.setText("Sửa Thông Tin Phòng");
		pSearch.add(btnSuaPhong);

		btnXoaPhong = new ColorButton2(Color.decode("#ed3752"), Color.decode("#ff425e"), Color.decode("#e63c55"),
				Color.decode("#ed3752"));
		btnXoaPhong.setBounds(280, 80, 150, 30);
		btnXoaPhong.setText("Xóa Phòng");
		pSearch.add(btnXoaPhong);

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

		tblDsPhong = new JTable();
		tblDsPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = tblDsPhong.getSelectedRow();
				trangthaiSelected = tblDsPhong.getValueAt(selectedRow, 2).toString();
				selectedPhong = lstPhong.getListData().get(selectedRow);
			}
		});
		scrollPane.setViewportView(tblDsPhong);

		JLabel lblNewLabel = new JLabel("Danh sách phòng");
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
		btnThemPhong.addActionListener(this);
		btnSuaPhong.addActionListener(this);
		btnXoaPhong.addActionListener(this);
		btnSearch.addActionListener(this);

		// == load ds phong ====
		try {
			phongDao = PhongDao.getInstance();
			lstPhong = phongDao.getListPhongPaged(1, maxRow, "");
			LoadDsPhong(lstPhong);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	private void LoadDsPhong(PageList<PhongDTO> lstPhong) {
		String[] tieude = { "Mã Phòng", "Tên Phòng", "Trạng Thái", "Loại Phòng" };
		DefaultTableModel model = new DefaultTableModel(tieude, 0);

		try {
			lstTTP = TinhTrangPhongDao.getInstance().getService().getAll();
			lstLP = LoaiPhongDao.getInstance().getAllLoaiPhong();
		} catch (RemoteException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Có lỗi gì đó xảy ra vui lòng dừng ứng dụng lại");
		}

		for (PhongDTO phong : lstPhong.getListData()) {
			TinhTrangPhongDTO ttp = new TinhTrangPhongDTO(phong.getTinhTrangPhong_id());
			TinhTrangPhongDTO temp = lstTTP.get(lstTTP.indexOf(ttp));

			LoaiPhongDTO lp = new LoaiPhongDTO(phong.getLoaiPhong_id());
			LoaiPhongDTO tempLP = lstLP.get(lstLP.indexOf(lp));

			Object[] o = { phong.getMaP(), phong.getTen(), temp.getTenTTP(), tempLP.getTenLP() };
			model.addRow(o);
		}
		tblDsPhong.setModel(model);

		currentPage = lstPhong.getCurrentPage();
		maxPage = lstPhong.getMaxPage();

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
			loadNextPage();
		}
		if (o.equals(btnThemPhong)) {
			OpenAddPhongDialog();
		}
		if (o.equals(btnXoaPhong)) {
			DeleteSelectedPhong();
		}
		if (o.equals(btnSuaPhong)) {
			OpenUpdatePhongDialog();
		}
		if (o.equals(btnSearch)) {
			SearchDsPhong();
		}

	}

	private void DeleteSelectedPhong() {
		if (selectedPhong == null) {
			JOptionPane.showMessageDialog(null, "Oops!, Bạn chưa chọn phòng nào cả");
			return;
		}
		if (trangthaiSelected.equals("Trống")) {
			DeletePhong();
			ReloadDsPhong();
			selectedPhong = null;
		} else {
			JOptionPane.showMessageDialog(null, "Chỉ cho phép sửa phòng có trạng thái 'Trống'");
			return;
		}
	}

	private void DeletePhong() {
		boolean deleteResult = PhongDao.getInstance().DeletePhong(selectedPhong.getMaP());
		if (deleteResult) {
			JOptionPane.showMessageDialog(null, "Đã xóa phòng : " + selectedPhong.getTen());
		}
	}

	private void OpenUpdatePhongDialog() {
		if (selectedPhong == null) {
			JOptionPane.showMessageDialog(null, "Oops!, Bạn chưa chọn phòng nào cả");
			return;
		}
		if (trangthaiSelected.equals("Trống")) {
			OpenUpdateDialog();
		} else {
			JOptionPane.showMessageDialog(null, "Chỉ cho phép sửa phòng có trạng thái 'Trống'");
			return;
		}

	}

	private void OpenUpdateDialog() {
		UpdatePhongDialog dialog = new UpdatePhongDialog(selectedPhong);
		dialog.setVisible(true);
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				ReloadDsPhong();
			}
		});
	}

	private void OpenAddPhongDialog() {
		AddPhongDialog dialog = new AddPhongDialog();
		dialog.setVisible(true);
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				ReloadDsPhong();
			}
		});
	}

	private void SearchDsPhong() {
		String nameSearch = txtSearchText.getText().toString();
		if (nameSearch.length() <= 0) {
			JOptionPane.showMessageDialog(null, "Oops!, bạn chưa nhập tên phòng cần tìm");
			txtSearchText.requestFocus();
			return;
		}

		try {
			lstPhong = phongDao.getListPhongPaged(1, maxRow, nameSearch);
			LoadDsPhong(lstPhong);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void loadNextPage() {
		currentPage++;
		if (currentPage > maxPage) {
			currentPage = maxPage;
			return;
		}

		int nextPageNumb = lstPhong.getCurrentPage() + 1;
		try {
			String nameSearch = txtSearchText.getText().toString();
			lstPhong = phongDao.getListPhongPaged(nextPageNumb, maxRow, nameSearch.length() > 0 ? nameSearch : "");
			LoadDsPhong(lstPhong);
			selectedPhong = null;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void LoadPrevPage() {
		currentPage--;
		if (currentPage < 1) {
			currentPage = 1;
			return;
		}

		int PrevPageNumb = lstPhong.getCurrentPage() - 1;
		try {
			String nameSearch = txtSearchText.getText().toString();
			lstPhong = phongDao.getListPhongPaged(PrevPageNumb, maxRow, nameSearch.length() > 0 ? nameSearch : "");
			LoadDsPhong(lstPhong);
			selectedPhong = null;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void ReloadDsPhong() {
		try {
			lstPhong = phongDao.getListPhongPaged(1, maxRow, "");
			LoadDsPhong(lstPhong);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}
}
