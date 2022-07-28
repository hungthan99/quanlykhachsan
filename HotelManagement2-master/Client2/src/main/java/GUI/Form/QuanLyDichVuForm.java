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

import ClientService.DichVuService;
import CustomControll.ColorButton2;
import GUI.Dialog.AddDichVuDialog;
import GUI.Dialog.AddPhongDialog;
import GUI.Dialog.UpdateDichVuDialog;
import Model.PageList;
import Rmi.DTO.DichVuDTO;

public class QuanLyDichVuForm extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private ColorButton2 btnThemDichVu;
	private ColorButton2 btnSuaDichVu;
	private ColorButton2 btnXoaDichVu;
	private JTextField txtSearchText;
	private JButton btnSearch;
	private JTable tblDsDichVu;
	private JButton btnPrev;
	private JLabel lblPage;
	private JButton btnNext;
	private PageList<DichVuDTO> lstDichVu;
	private int currentPage, maxPage;
	private static int maxRow = 10;
	private DichVuDTO selectedDichVu;

	public QuanLyDichVuForm() {
		setBackground(Color.decode("#d4d5d6"));
		setBounds(0, 0, 1180, 820);
		setLayout(null);

		JPanel pSearch = new JPanel();
		pSearch.setBackground(Color.WHITE);
		pSearch.setBounds(10, 10, 1160, 120);
		add(pSearch);
		pSearch.setLayout(null);

		btnThemDichVu = new ColorButton2(Color.decode("#34e039"), Color.decode("#38f53e"), Color.decode("#32cf37"),
				Color.decode("#34e039"));
		btnThemDichVu.setText("Thêm Dịch Vụ");
		btnThemDichVu.setBounds(10, 80, 100, 30);
		pSearch.add(btnThemDichVu);

		btnSuaDichVu = new ColorButton2(Color.decode("#f0f03a"), Color.decode("#fafa3c"), Color.decode("#e0e034"),
				Color.decode("#f0f03a"));
		btnSuaDichVu.setBounds(120, 80, 150, 30);
		btnSuaDichVu.setText("Sửa Dịch Vụ");
		pSearch.add(btnSuaDichVu);

		btnXoaDichVu = new ColorButton2(Color.decode("#ed3752"), Color.decode("#ff425e"), Color.decode("#e63c55"),
				Color.decode("#ed3752"));
		btnXoaDichVu.setBounds(280, 80, 150, 30);
		btnXoaDichVu.setText("Xóa Dịch Vụ");
		pSearch.add(btnXoaDichVu);

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

		tblDsDichVu = new JTable();
		tblDsDichVu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = tblDsDichVu.getSelectedRow();
				selectedDichVu = lstDichVu.getListData().get(selectedRow);
			}
		});
		tblDsDichVu.setRowHeight(tblDsDichVu.getRowHeight() + 10);
		scrollPane.setViewportView(tblDsDichVu);

		JLabel lblNewLabel = new JLabel("Danh khách hàng");
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
		btnThemDichVu.addActionListener(this);
		btnSuaDichVu.addActionListener(this);
		btnXoaDichVu.addActionListener(this);
		btnSearch.addActionListener(this);
		txtSearchText.addActionListener(this);

		// == load DS =====
		lstDichVu = DichVuService.getInstance().getListDichVuByPage(1, maxRow, "");
		LoadDsDichVu(lstDichVu);
	}

	private void LoadDsDichVu(PageList<DichVuDTO> lstDichVu) {
		String[] tieude = { "Mã Dịch Vụ", "Tên Dịch Vụ", "Đơn Giá", "Đơn Vị" };
		DefaultTableModel model = new DefaultTableModel(tieude, 0);

		for (DichVuDTO dichvu : lstDichVu.getListData()) {
			Object[] o = { dichvu.getMaDv(), dichvu.getTenDv(), dichvu.getDonGia(), dichvu.getDonVi() };
			model.addRow(o);
		}
		tblDsDichVu.setModel(model);

		currentPage = lstDichVu.getCurrentPage();
		maxPage = lstDichVu.getMaxPage();

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
		if (o.equals(btnThemDichVu)) {
			OpenAddDichVuDialog();
		}
		if (o.equals(btnXoaDichVu)) {
			XoaDichVu();
		}
		if (o.equals(btnSuaDichVu)) {
			OpenUpdateDichVuDialog();
		}
		if (o.equals(btnSearch) || o.equals(txtSearchText)) {
			SearchDsDichVu();
		}
	}

	private void OpenUpdateDichVuDialog() {
		if (selectedDichVu == null) {
			JOptionPane.showMessageDialog(null, "Oops!, Bạn chưa chọn dịch vụ nào cả");
			return;
		}
		if (!DichVuService.getInstance().isDeleteAble(selectedDichVu.getMaDv())) {
			JOptionPane.showMessageDialog(null, "Dịch vụ đang có người sử dụng");
			return;
		}

		UpdateDichVuDialog dialog = new UpdateDichVuDialog(selectedDichVu);
		dialog.setVisible(true);
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				ReloadDsDichVu();
			}
		});
	}

	private void XoaDichVu() {
		if (selectedDichVu == null) {
			JOptionPane.showMessageDialog(null, "Oops!, Bạn chưa chọn dịch vụ nào cả");
			return;
		}
		if (!DichVuService.getInstance().isDeleteAble(selectedDichVu.getMaDv())) {
			JOptionPane.showMessageDialog(null, "Dịch vụ đang có người sử dụng");
			return;
		}
		if (JOptionPane.showConfirmDialog(this,
				"Bạn có muốn xóa dịch vụ " + selectedDichVu.getTenDv() + " có giá là là: " + selectedDichVu.getDonGia()
						+ " trên " + selectedDichVu.getDonVi() + " không?",
				"Cảnh báo.", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {

			if (DichVuService.getInstance().deleteDichVuById(selectedDichVu.getMaDv())) {
				JOptionPane.showMessageDialog(null, "Đã xóa dịch vụ : " + selectedDichVu.getTenDv());
			} else {
				JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi xóa dịch vụ : " + selectedDichVu.getTenDv());
			}
		}
		ReloadDsDichVu();
		return;
	}

	private void OpenAddDichVuDialog() {
		AddDichVuDialog dialog = new AddDichVuDialog();
		dialog.setVisible(true);
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				ReloadDsDichVu();
			}
		});

	}

	private void ReloadDsDichVu() {
		lstDichVu = DichVuService.getInstance().getListDichVuByPage(1, maxRow, "");
		LoadDsDichVu(lstDichVu);
		selectedDichVu = null;
	}

	private void SearchDsDichVu() {
		String nameSearch = txtSearchText.getText().toString();
		if (nameSearch.length() <= 0) {
			JOptionPane.showMessageDialog(null, "Oops!, bạn chưa nhập tên khách hàng cần tìm");
		}
		txtSearchText.selectAll();
		txtSearchText.requestFocus();

		lstDichVu = DichVuService.getInstance().getListDichVuByPage(currentPage, maxRow,
				nameSearch.length() > 0 ? nameSearch : "");
		LoadDsDichVu(lstDichVu);
	}

	private void LoadNextPage() {
		currentPage++;
		if (currentPage > maxPage) {
			currentPage = maxPage;
			return;
		}

		int nextPageNumb = lstDichVu.getCurrentPage() + 1;
		String nameSearch = txtSearchText.getText().toString();
		lstDichVu = DichVuService.getInstance().getListDichVuByPage(nextPageNumb, maxRow,
				nameSearch.length() > 0 ? nameSearch : "");
		LoadDsDichVu(lstDichVu);
	}

	private void LoadPrevPage() {
		currentPage--;
		if (currentPage < 1) {
			currentPage = 1;
			return;
		}

		int PrevPageNumb = lstDichVu.getCurrentPage() - 1;
		String nameSearch = txtSearchText.getText().toString();
		lstDichVu = DichVuService.getInstance().getListDichVuByPage(PrevPageNumb, maxRow,
				nameSearch.length() > 0 ? nameSearch : "");
		LoadDsDichVu(lstDichVu);
	}

}
