package GUI.Dialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import ClientService.DichVuService;
import Rmi.DTO.DichVuDTO;

public class AddDichVuDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtTenDV;
	private JButton btnThem;
	private JButton btnHuy;
	private JTextField txtDonGia;
	private JComboBox cbbDonVi;

	public AddDichVuDialog() {
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		getContentPane().setBackground(new Color(240, 240, 240));
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

		JLabel lblNewLabel = new JLabel("Thêm Dịch Vụ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 0, 157, 60);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 80, 326, 220);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Tên Dịch Vụ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 30, 100, 30);
		panel_1.add(lblNewLabel_1);

		txtTenDV = new JTextField();
		txtTenDV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTenDV.setBounds(120, 30, 196, 30);
		panel_1.add(txtTenDV);
		txtTenDV.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Đơn Giá");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(10, 80, 100, 30);
		panel_1.add(lblNewLabel_1_1);

		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(120, 80, 196, 30);
		panel_1.add(txtDonGia);

		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThem.setBounds(226, 180, 90, 30);
		panel_1.add(btnThem);

		btnHuy = new JButton("Hủy");
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnHuy.setBounds(120, 180, 90, 30);
		panel_1.add(btnHuy);

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
		btnThem.addActionListener(this);
		btnHuy.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnHuy)) {
			this.dispose();
		}
		if (o.equals(btnThem)) {
			ThemDichVu();
		}

	}

	private void ThemDichVu() {
		if (!ValidateInPut())
			return;
		String tenDv = txtTenDV.getText().toString().trim();
		String txtdongia = txtDonGia.getText().toString().trim();
		String donVi = cbbDonVi.getSelectedItem().toString();
		
		Double dongia = Double.parseDouble(txtdongia);
		DichVuDTO addObj = new DichVuDTO(tenDv, dongia, donVi);
		DichVuService.getInstance().AddDichVu(addObj);
		
		JOptionPane.showMessageDialog(this, "Thêm thành công.");
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
