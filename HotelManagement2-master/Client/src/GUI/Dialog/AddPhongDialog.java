package GUI.Dialog;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Dao.LoaiPhongDao;
import Dao.PhongDao;
import Rmi.DTO.LoaiPhongDTO;
import Rmi.DTO.PhongDTO;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class AddPhongDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txttenPhong;
	private JComboBox<String> cbxLoaiPhong;
	private JButton btnThem, btnHuy;
	private List<LoaiPhongDTO> lstLoaiPhong;

	public AddPhongDialog() {
		setModal(true);
		setSize(350, 340);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);
		panel.setBounds(10, 10, 316, 60);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Thêm Phòng");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 0, 157, 60);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 80, 316, 201);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Tên Phòng");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 60, 100, 30);
		panel_1.add(lblNewLabel_1);

		txttenPhong = new JTextField();
		txttenPhong.setBounds(120, 60, 180, 30);
		panel_1.add(txttenPhong);
		txttenPhong.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Loại Phòng");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(10, 100, 100, 30);
		panel_1.add(lblNewLabel_1_1);

		cbxLoaiPhong = new JComboBox<String>();
		cbxLoaiPhong.setBounds(120, 100, 180, 30);
		panel_1.add(cbxLoaiPhong);

		btnThem = new JButton("Thêm");
		btnThem.setBounds(210, 150, 90, 30);
		panel_1.add(btnThem);

		btnHuy = new JButton("Hủy");
		btnHuy.setBounds(110, 150, 90, 30);
		panel_1.add(btnHuy);
		// === Action ========================
		btnThem.addActionListener(this);
		btnHuy.addActionListener(this);

		// == Load Dữ liệu ==
		loadLoaiPhong();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnHuy)) {
			this.dispose();
		}
		if (o.equals(btnThem)) {
			ThemPhong();
		}
	}

	private void ThemPhong() {
		String txtName = txttenPhong.getText().toString();
		if (txtName.length() < 0 ) {
			JOptionPane.showMessageDialog(null, "Bạn chưa nhập tên phòng");
			txttenPhong.requestFocus();
			return;
		}
		int selectedLpIndex = cbxLoaiPhong.getSelectedIndex();
		if (selectedLpIndex != 0) {
			LoaiPhongDTO selectedLoaiPhong = lstLoaiPhong.get(selectedLpIndex - 1);
			PhongDTO addObj = new PhongDTO(txtName, selectedLoaiPhong.getMaLP(), 1);
			try {
				PhongDao.getInstance().getService().addObject(addObj);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn loại phòng");
			return;
		}
		this.dispose();
	}

	private void loadLoaiPhong() {
		DefaultComboBoxModel<String> dcm = new DefaultComboBoxModel<String>();
		dcm.addElement(" -- Chọn loại phòng --");

		lstLoaiPhong = LoaiPhongDao.getInstance().getAllLoaiPhong();
		for (LoaiPhongDTO loaiPhongDTO : lstLoaiPhong) {
			dcm.addElement(loaiPhongDTO.getTenLP());
		}

		cbxLoaiPhong.setModel(dcm);
	}

}
