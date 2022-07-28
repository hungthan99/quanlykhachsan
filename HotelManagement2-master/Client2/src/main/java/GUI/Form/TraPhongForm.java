package GUI.Form;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ClientService.LoaiPhongService;
import ClientService.PhieuThueService;
import ClientService.PhongService;
import ClientService.TinhTrangPhongService;
import Model.PageList;
import Rmi.DTO.LoaiPhongDTO;
import Rmi.DTO.PhongDTO;
import Rmi.DTO.TinhTrangPhongDTO;
import javax.swing.JTable;

public class TraPhongForm extends JPanel implements ActionListener {
	private JTextField txtName;
	private JButton btnTim, btnTraPhong;
	private JButton btnPrev, btnNext;
	private PhongDTO selectedPhong = null;
	private PageList<PhongDTO> lstPhong = null;
	private List<TinhTrangPhongDTO> lstTTP = null;
	private List<LoaiPhongDTO> lstLP = null;
	private int maxRow = 10;
	private int currentPage;
	private int maxPage;
	private JTable tblDsPhong;
	private JLabel lblPage;

	public TraPhongForm() {
		setBackground(Color.decode("#d4d5d6"));
		setBounds(0, 0, 1180, 820);
		setLayout(null);

		JPanel pTable = new JPanel();
		pTable.setLayout(null);
		pTable.setBackground(Color.WHITE);
		pTable.setBounds(20, 20, 1160, 790);
		add(pTable);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 70, 1140, 508);
		pTable.add(scrollPane);

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

		btnPrev = new JButton("<");
		btnPrev.setBounds(10, 590, 85, 25);
		pTable.add(btnPrev);

		lblPage = new JLabel("paging");
		lblPage.setBounds(105, 590, 40, 25);
		pTable.add(lblPage);

		btnNext = new JButton(">");
		btnNext.setBounds(150, 590, 85, 25);
		pTable.add(btnNext);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 700, 1140, 13);
		pTable.add(separator);

		txtName = new JTextField();
		txtName.setBounds(10, 30, 430, 30);
		pTable.add(txtName);
		txtName.setColumns(10);

		btnTim = new JButton("Tìm kiếm");
		btnTim.setBounds(455, 30, 135, 30);
		pTable.add(btnTim);

		btnTraPhong = new JButton("Trả Phòng");
		btnTraPhong.setBounds(1015, 585, 135, 30);
		pTable.add(btnTraPhong);

		// == add action ====
		btnNext.addActionListener(this);
		btnPrev.addActionListener(this);
		btnTim.addActionListener(this);
		btnTraPhong.addActionListener(this);

		// === Load ds Phong da thue =============
		lstPhong = PhongService.getInstance().getListPhongDaThue(1, maxRow, "");
		LoadDsPhong(lstPhong);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnNext)) {
			LoadNextPage();
		}
		if (o.equals(btnPrev)) {
			LoadPrevPage();
		}
		if (o.equals(btnTim)) {
			TimDsPhong();
		}
		if (o.equals(btnTraPhong)) {
			TraPhong();
		}
	}

	private void LoadDsPhong(PageList<PhongDTO> lstPhong) {
		String[] tieude = { "Mã Phòng", "Tên Phòng", "Trạng Thái", "Loại Phòng" };
		DefaultTableModel model = new DefaultTableModel(tieude, 0);

		try {
			lstTTP = TinhTrangPhongService.getInstance().getService().getAll();
			lstLP = LoaiPhongService.getInstance().getAllLoaiPhong();
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
		selectedPhong = null;

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

	private void TraPhong() {
		if (selectedPhong == null) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn phòng nào cả");
			return;
		}

		boolean kq = PhieuThueService.getInstance().traPhong(selectedPhong.getMaP());
		if (!kq) {
			JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi trả phòng: " + selectedPhong.getTen());
			return;
		}

		JOptionPane.showMessageDialog(null, "Trả phòng thành công : " + selectedPhong.getTen());
		reloadDsPhong();

	}

	private void TimDsPhong() {
		String nameSearch = txtName.getText().toString().trim();
		if (nameSearch.length() <= 0) {
			JOptionPane.showMessageDialog(null, "Oops!, bạn chưa nhập tên phòng cần tìm");
			txtName.requestFocus();
			return;
		}

		lstPhong = PhongService.getInstance().getListPhongDaThue(1, maxRow, nameSearch);
		LoadDsPhong(lstPhong);

		selectedPhong = null;
	}

	private void LoadPrevPage() {
		currentPage--;
		if (currentPage < 1) {
			currentPage = 1;
			return;
		}

		int PrevPageNumb = lstPhong.getCurrentPage() - 1;
		try {
			String nameSearch = txtName.getText().toString().trim();
			lstPhong = PhongService.getInstance().getListPhongPaged(PrevPageNumb, maxRow,
					nameSearch.length() > 0 ? nameSearch : "");
			selectedPhong = null;
			LoadDsPhong(lstPhong);
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

		int nextPageNumb = lstPhong.getCurrentPage() + 1;
		try {
			String nameSearch = txtName.getText().toString().trim();
			lstPhong = PhongService.getInstance().getListPhongPaged(nextPageNumb, maxRow,
					nameSearch.length() > 0 ? nameSearch : "");
			selectedPhong = null;
			LoadDsPhong(lstPhong);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	private void reloadDsPhong() {
		selectedPhong = null;
		lstPhong = PhongService.getInstance().getListPhongDaThue(1, maxRow, "");
		LoadDsPhong(lstPhong);
	}
}
