package GUI.Dialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ClientService.DichVuService;
import Rmi.DTO.DichVuDTO;
import javax.swing.JComboBox;

public class UpdateDichVuDialog extends JDialog implements ActionListener {
	private JTextField txtTenDV;
	private JTextField txtDonGia;
	private JButton btnSua;
	private JButton btnHuy;
	private DichVuDTO selectedDv;
	private JComboBox cbbDonVi;

	public UpdateDichVuDialog(DichVuDTO dichVuDTO) {
		setModal(true);
		setSize(360, 350);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);
		panel.setBounds(10, 10, 326, 60);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Sửa Dịch Vụ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 0, 157, 60);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 80, 326, 230);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Tên Dịch Vụ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 30, 100, 30);
		panel_1.add(lblNewLabel_1);

		txtTenDV = new JTextField();
		txtTenDV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTenDV.setColumns(10);
		txtTenDV.setBounds(120, 30, 196, 30);
		txtTenDV.setText(dichVuDTO.getTenDv());
		panel_1.add(txtTenDV);

		JLabel lblNewLabel_1_1 = new JLabel("Đơn Giá");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(10, 80, 100, 30);
		panel_1.add(lblNewLabel_1_1);

		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDonGia.setColumns(10);
		txtDonGia.setText("0");
		txtDonGia.setBounds(120, 80, 196, 30);
		panel_1.add(txtDonGia);

		btnSua = new JButton("Sửa");
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSua.setBounds(226, 186, 90, 30);
		panel_1.add(btnSua);

		btnHuy = new JButton("Hủy");
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnHuy.setBounds(120, 186, 90, 30);
		panel_1.add(btnHuy);

		selectedDv = dichVuDTO;

		JLabel lblNewLabel_1_1_1 = new JLabel("Đơn Vị");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(10, 130, 100, 30);
		panel_1.add(lblNewLabel_1_1_1);
		
		cbbDonVi = new JComboBox();
		cbbDonVi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbbDonVi.setBounds(120, 130, 196, 30);
		panel_1.add(cbbDonVi);
		cbbDonVi.addItem("1 Chai");
		cbbDonVi.addItem("1 Lon");
		cbbDonVi.addItem("1 Gói");
		cbbDonVi.addItem("1 Hộp");
		cbbDonVi.addItem("1 Đĩa");

		// === Action ========================
		btnSua.addActionListener(this);
		btnHuy.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnHuy)) {
			this.dispose();
		}
		if (o.equals(btnSua)) {
			SuaDichVu();
		}

	}

	private void SuaDichVu() {
		if (!ValidateInPut())
			return;
		
		String tenDv = txtTenDV.getText().toString().trim();
		String txtDongia = txtDonGia.getText().toString().trim();
		String donVi = cbbDonVi.getSelectedItem().toString();

		Double dongia = Double.parseDouble(txtDongia);

		selectedDv.setTenDv(tenDv);
		selectedDv.setDonGia(dongia);
		selectedDv.setDonVi(donVi);

		if (DichVuService.getInstance().UpdateDichVu(selectedDv)) {
			JOptionPane.showMessageDialog(null, "Đã cập nhật dịch vụ :" + selectedDv.getTenDv());
		} else {
			JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi cập nhật dịch vụ :" + selectedDv.getTenDv());
		}

		this.dispose();
	}

	private boolean ValidateInPut() {
		if (txtTenDV.getText().trim().length() <= 0) {
			JOptionPane.showMessageDialog(null, "Bạn chưa nhập tên cho dịch vụ");
			txtTenDV.selectAll();
			txtTenDV.requestFocus();
			return false;
		}
		String gia = txtDonGia.getText().trim();
		if (gia.length() <= 0) {
			JOptionPane.showMessageDialog(null, "Bạn chưa nhập giá cho dịch vụ");
			txtDonGia.selectAll();
			txtDonGia.requestFocus();
			return false;
		}
		
		if (!gia.matches("^[1-9]{1}[0-9]{3,}$")) {
			JOptionPane.showMessageDialog(null, "Giá dịch vụ phải là số và phải lớn hơn 1000 VND. \n Please.");
			txtDonGia.selectAll();
			txtDonGia.requestFocus();
			return false;
		}
		return true;
	}
}
