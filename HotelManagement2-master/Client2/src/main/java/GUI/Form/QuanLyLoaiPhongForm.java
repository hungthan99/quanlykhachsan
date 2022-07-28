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
import ClientService.LoaiPhongService;
import ClientService.NhanVienService;
import ClientService.TinhTrangPhongService;
import CustomControll.ColorButton2;
import GUI.Dialog.AddLoaiPhongDialog;
import GUI.Dialog.AddPhongDialog;
import GUI.Dialog.UpdateLoaiPhongDialog;
import Model.PageList;
import Rmi.DTO.KhachHangDTO;
import Rmi.DTO.LoaiPhongDTO;
import Rmi.DTO.PhongDTO;
import Rmi.DTO.TinhTrangPhongDTO;

public class QuanLyLoaiPhongForm extends JPanel implements ActionListener {

	private ColorButton2 btnThem;
	private ColorButton2 btnSua;
	private ColorButton2 btnXoa;
	private JTextField txtSearchText;
	private JButton btnSearch;
	private JTable tblDsLoaiPhong;
	private JButton btnPrev;
	private JLabel lblPage;
	private JButton btnNext;
	private PageList<LoaiPhongDTO> lstLoaiPhong;
	private LoaiPhongDTO selectedLoaiPhong = null;
	private int currentPage, maxPage;
	private static int maxRow = 10;

	public QuanLyLoaiPhongForm() {
		setBackground(Color.decode("#d4d5d6"));
		setBounds(0, 0, 1180, 820);
		setLayout(null);

		JPanel pSearch = new JPanel();
		pSearch.setBackground(Color.WHITE);
		pSearch.setBounds(10, 10, 1160, 120);
		add(pSearch);
		pSearch.setLayout(null);

		btnThem = new ColorButton2(Color.decode("#34e039"), Color.decode("#38f53e"), Color.decode("#32cf37"),
				Color.decode("#34e039"));
		btnThem.setText("Thêm Loại Phòng");
		btnThem.setBounds(10, 80, 100, 30);
		pSearch.add(btnThem);

		btnSua = new ColorButton2(Color.decode("#f0f03a"), Color.decode("#fafa3c"), Color.decode("#e0e034"),
				Color.decode("#f0f03a"));
		btnSua.setBounds(120, 80, 150, 30);
		btnSua.setText("Sửa Loại Phòng");
		pSearch.add(btnSua);

		btnXoa = new ColorButton2(Color.decode("#ed3752"), Color.decode("#ff425e"), Color.decode("#e63c55"),
				Color.decode("#ed3752"));
		btnXoa.setBounds(280, 80, 150, 30);
		btnXoa.setText("Xóa Loại Phòng");
		pSearch.add(btnXoa);

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

		tblDsLoaiPhong = new JTable();
		tblDsLoaiPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = tblDsLoaiPhong.getSelectedRow();
				selectedLoaiPhong = lstLoaiPhong.getListData().get(selectedRow);
			}
		});
		tblDsLoaiPhong.setRowHeight(tblDsLoaiPhong.getRowHeight() + 10);
		scrollPane.setViewportView(tblDsLoaiPhong);

		JLabel lblNewLabel = new JLabel("Danh Loại Phòng");
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

		// === Add acction ======
		btnPrev.addActionListener(this);
		btnNext.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSearch.addActionListener(this);
		txtSearchText.addActionListener(this);
		
		// === Load Ds LOai Phong ======
		lstLoaiPhong = LoaiPhongService.getInstance().getListLoaiPhongByPage(1, maxRow, "");
		LoadDsLoaiPhong(lstLoaiPhong);
	}

	private void LoadDsLoaiPhong(PageList<LoaiPhongDTO> lst) {
		String[] tieude = { "Mã Loại Phòng", "Tên Loại Phòng", "Đơn Giá" };
		DefaultTableModel model = new DefaultTableModel(tieude, 0);

		for (LoaiPhongDTO loaiPhong : lst.getListData()) {

			Object[] o = { loaiPhong.getMaLP(), loaiPhong.getTenLP(), loaiPhong.getDonGia() };
			model.addRow(o);
		}
		tblDsLoaiPhong.setModel(model);

		currentPage = lst.getCurrentPage();
		maxPage = lst.getMaxPage();

		showPageNumber();
		selectedLoaiPhong = null;
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
		if (o.equals(btnThem)) {
			OpenAddLoaiPhongDialog();
		}
		if (o.equals(btnXoa)) {
			DeleteSelectedLoaiPhong();
		}
		if (o.equals(btnSua)) {
			OpenUpdateLoaiPhongDialog();
		}
		if (o.equals(btnSearch) || o.equals(txtSearchText)) {
			SearchDsLoaiPhong();
		}

	}

	private void DeleteSelectedLoaiPhong() {
		if (selectedLoaiPhong == null) {
			JOptionPane.showMessageDialog(null, "Oops!, Bạn chưa chọn loại phòng nào cả");
			return;
		}

		if (!LoaiPhongService.getInstance().isDeleteAble(selectedLoaiPhong.getMaLP())) {
			JOptionPane.showMessageDialog(null, "Có phòng hiện tại đang sử dụng loại phòng này");
			return;
		}
		if (LoaiPhongService.getInstance().deleteLoaiPhongById(selectedLoaiPhong.getMaLP())) {
			JOptionPane.showMessageDialog(null, "Đã xóa Loại Phòng : " + selectedLoaiPhong.getTenLP());
		} else {
			JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi xóa loại phòng : " + selectedLoaiPhong.getTenLP());
		}
		ReloadDsLoaiPhong();
		return;

	}

	private void OpenAddLoaiPhongDialog() {
		AddLoaiPhongDialog dialog = new AddLoaiPhongDialog();
		dialog.setVisible(true);
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				ReloadDsLoaiPhong();
			}
		});
	}

	private void OpenUpdateLoaiPhongDialog() {
		if (selectedLoaiPhong == null) {
			JOptionPane.showMessageDialog(null, "Oops!, Bạn chưa chọn loại phòng nào cả");
			return;
		}

		UpdateLoaiPhongDialog dialog = new UpdateLoaiPhongDialog(selectedLoaiPhong);
		dialog.setVisible(true);
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				ReloadDsLoaiPhong();
			}
		});
	}

	protected void ReloadDsLoaiPhong() {
		lstLoaiPhong = LoaiPhongService.getInstance().getListLoaiPhongByPage(1, maxRow, "");
		LoadDsLoaiPhong(lstLoaiPhong);
		selectedLoaiPhong = null;
	}

	private void LoadNextPage() {
		currentPage++;
		if (currentPage > maxPage) {
			currentPage = maxPage;
			return;
		}

		int nextPageNumb = lstLoaiPhong.getCurrentPage() + 1;
		String nameSearch = txtSearchText.getText().toString();
		lstLoaiPhong = LoaiPhongService.getInstance().getListLoaiPhongByPage(nextPageNumb, maxRow,
				nameSearch.length() > 0 ? nameSearch : "");
		LoadDsLoaiPhong(lstLoaiPhong);

	}

	private void LoadPrevPage() {
		currentPage--;
		if (currentPage < 1) {
			currentPage = 1;
			return;
		}
		int PrevPageNumb = lstLoaiPhong.getCurrentPage() - 1;
		String nameSearch = txtSearchText.getText().toString();
		lstLoaiPhong = LoaiPhongService.getInstance().getListLoaiPhongByPage(PrevPageNumb, maxRow,
				nameSearch.length() > 0 ? nameSearch : "");
		LoadDsLoaiPhong(lstLoaiPhong);
	}

	private void SearchDsLoaiPhong() {
		String nameSearch = txtSearchText.getText().toString();
		if (nameSearch.length() <= 0) {
			JOptionPane.showMessageDialog(null, "Oops!, bạn chưa nhập tên phòng cần tìm");
		}
		txtSearchText.selectAll();
		txtSearchText.requestFocus();

		lstLoaiPhong = LoaiPhongService.getInstance().getListLoaiPhongByPage(1, maxRow, nameSearch);
		LoadDsLoaiPhong(lstLoaiPhong);
		selectedLoaiPhong = null;
	}
}
