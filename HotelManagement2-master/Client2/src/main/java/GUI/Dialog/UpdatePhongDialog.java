package GUI.Dialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ClientService.*;
import Rmi.DTO.LoaiPhongDTO;
import Rmi.DTO.PhongDTO;

public class UpdatePhongDialog extends JDialog implements ActionListener {

	private JTextField txttenPhong;
	private JComboBox<String> cbxLoaiPhong;
	private JButton btnHuy, btnSua;
	private List<LoaiPhongDTO> lstLoaiPhong;
	private PhongDTO phong;

	public UpdatePhongDialog(PhongDTO phongSelected) {
		setModal(true);
		setSize(350, 340);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		this.phong = phongSelected;

		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);
		panel.setBounds(10, 10, 316, 60);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Sửa Phòng");
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
		txttenPhong.setText(phong.getTen());
		panel_1.add(txttenPhong);
		txttenPhong.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Loại Phòng");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(10, 100, 100, 30);
		panel_1.add(lblNewLabel_1_1);

		cbxLoaiPhong = new JComboBox<String>();
		cbxLoaiPhong.setBounds(120, 100, 180, 30);
		panel_1.add(cbxLoaiPhong);

		btnSua = new JButton("Sửa");
		btnSua.setBounds(210, 150, 90, 30);
		panel_1.add(btnSua);

		btnHuy = new JButton("Hủy");
		btnHuy.setBounds(110, 150, 90, 30);
		panel_1.add(btnHuy);
		// === Action ========================
		btnSua.addActionListener(this);
		btnHuy.addActionListener(this);

		// === load dư liệu ===
		loadLoaiPhong();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnHuy)) {
			this.dispose();
		}
		if (o.equals(btnSua)) {
			UpdatePhong();
		}
	}

	private void UpdatePhong() {
		String txtName = txttenPhong.getText().toString();
		int selectedLpIndex = cbxLoaiPhong.getSelectedIndex();
		
		if (!validateInput(txtName,selectedLpIndex)) {
			return;
		}
		
		LoaiPhongDTO selectedLoaiPhong = lstLoaiPhong.get(selectedLpIndex - 1);
		phong.setLoaiPhong_id(selectedLoaiPhong.getMaLP());
		phong.setTen(txtName);
		
		boolean updateResult = PhongService.getInstance().UpdatePhong(phong);
		if (updateResult) {
			JOptionPane.showMessageDialog(null, "Đã cập nhật phòng : " + phong.getTen());
			this.dispose();
		}
	}

	private void loadLoaiPhong() {
		DefaultComboBoxModel<String> dcm = new DefaultComboBoxModel<String>();
		dcm.addElement(" -- Chọn loại phòng --");

		lstLoaiPhong = LoaiPhongService.getInstance().getAllLoaiPhong();
		for (LoaiPhongDTO loaiPhongDTO : lstLoaiPhong) {
			dcm.addElement(loaiPhongDTO.getTenLP());
		}

		cbxLoaiPhong.setModel(dcm);

		int selectedIndex = lstLoaiPhong.indexOf(new LoaiPhongDTO(phong.getLoaiPhong_id())) + 1;
		cbxLoaiPhong.setSelectedIndex(selectedIndex);
	}

	private boolean validateInput(String name, int selectedLpIndex) {
		
		if (name.length() < 0) {
			JOptionPane.showMessageDialog(null, "Bạn chưa nhập tên phòng");
			txttenPhong.requestFocus();
			return false;
		}
		if (selectedLpIndex <= 0 ) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn loại phòng");
			return false;
		}
		return true;
	}
}
