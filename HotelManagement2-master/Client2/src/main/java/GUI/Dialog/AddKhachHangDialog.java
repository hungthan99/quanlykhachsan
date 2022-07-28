package GUI.Dialog;

import java.awt.BorderLayout;
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

import ClientService.KhachHangService;
import Rmi.DTO.KhachHangDTO;

public class AddKhachHangDialog extends JDialog implements ActionListener {
	private JTextField txtName;
	private JButton btnThem;
	private JButton btnHuy;
	private JTextField txtCMT;
	private JTextField txtSDT;
	private JTextField txtMail;
	private JTextField txtDiaChi;

	public AddKhachHangDialog() {
		setModal(true);
		setSize(570, 420);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);
		panel.setBounds(0, 0, 556, 70);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Thêm Khách Hàng");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblNewLabel, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 98, 556, 250);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		txtName = new JTextField();
		txtName.setBounds(166, 10, 380, 30);
		panel_1.add(txtName);
		txtName.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Tên khách");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 10, 134, 30);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Số điện thoại");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(10, 90, 134, 30);
		panel_1.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Email");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_1.setBounds(10, 130, 134, 30);
		panel_1.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Số CMT");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_1_1.setBounds(10, 50, 134, 30);
		panel_1.add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Địa chỉ");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_1_1_1.setBounds(10, 170, 134, 30);
		panel_1.add(lblNewLabel_1_1_1_1_1);

		btnThem = new JButton("Thêm");
		btnThem.setBounds(461, 210, 85, 30);
		panel_1.add(btnThem);

		btnHuy = new JButton("Hủy");
		btnHuy.setBounds(366, 210, 85, 30);
		panel_1.add(btnHuy);

		txtCMT = new JTextField();
		txtCMT.setColumns(10);
		txtCMT.setBounds(166, 50, 380, 30);
		panel_1.add(txtCMT);

		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(166, 90, 380, 30);
		panel_1.add(txtSDT);

		txtMail = new JTextField();
		txtMail.setColumns(10);
		txtMail.setBounds(166, 130, 380, 30);
		panel_1.add(txtMail);

		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(166, 170, 380, 30);
		panel_1.add(txtDiaChi);

		// === add action /// =
		btnHuy.addActionListener(this);
		btnThem.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnHuy)) {
			this.dispose();
		}
		if (o.equals(btnThem)) {
			ThemKhachHang();
		}
	}

	private void ThemKhachHang() {

		if (!check_data()) {
			return;
		}
		String name = txtName.getText().toString();
		String soCMT = txtCMT.getText().toString();
		String phone = txtSDT.getText().toString();
		String mail = txtMail.getText().toString();
		String diachi = txtDiaChi.getText().toString();

		KhachHangDTO addObj = new KhachHangDTO(name, mail, phone, diachi, soCMT);
		if (!KhachHangService.getInstance().addKhachHang(addObj)) {
			JOptionPane.showMessageDialog(null, "Có lỗi xảy ra trong quá trình thêm khách hàng : " + addObj.getTen());
			return;
		}

		JOptionPane.showMessageDialog(null, "Thêm thành công khách hàng: " + addObj.getTen());
		this.dispose();
	}

	private boolean check_data() {
		String maCheck = txtName.getText().trim();
		String mess = "";

		if (!(maCheck.length() > 0 && maCheck.matches(
				"^([ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴA-Z]{1}[ắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵa-z]*\\s)+([ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴA-Z]{1}[ắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵa-z]*)$"))) {
			if (maCheck.length() == 0) {
				mess = "Hãy nhập tên khách hàng.";
			} else {
				mess = "Tên khách hàng có chữ hoa ở đầu mỗi từ, cách nhau bởi đấu cách. \nVD: Nguyễn Văn A";
			}
			getMess(txtName, mess);
			return false;
		}
		//

		maCheck = txtCMT.getText().trim();
		if (!(maCheck.length() > 0 && maCheck.matches("^[0-9]{12}$"))) {
			if (maCheck.length() == 0) {
				mess = "Hãy nhập số chứng minh của khách hàng.";
			} else if (maCheck.length() != 10) {
				mess = "Số chứng mính có 12 số.";
			}
			getMess(txtCMT, mess);
			return false;
		}
		//

		maCheck = txtSDT.getText().trim();
		if (!(maCheck.length() > 0 && maCheck.matches("^0[0-9]{9}$"))) {
			if (maCheck.length() == 0) {
				mess = "Hãy nhập số điện thoại của khách hàng.";
			} else if (maCheck.length() != 10) {
				mess = "Số điện thoại có 10 số và bắt đầu bằng số 0.";
			}
			getMess(txtSDT, mess);
			return false;
		}

		//
		maCheck = txtMail.getText().trim();
		if (!(maCheck.matches("^[A-Za-z0-9._]+@[A-Za-z0-9.]+\\.[a-z]{2,4}$"))) {
			if (maCheck.length() == 0) {
				mess = "Hãy nhập Email của khách hàng";
			} else {
				mess = "Email phải đúng theo định dạng (VD: Abc@gmail.com)";

			}
			getMess(txtMail, mess);
			return false;
		}
		maCheck = txtDiaChi.getText().trim();
		if (maCheck.length() == 0) {
			JOptionPane.showMessageDialog(this, "Hãy nhập địa chỉ.");
			txtDiaChi.selectAll();
			txtDiaChi.requestFocus();
			return false;
		}
		//
		return true;
	}

	private void getMess(JTextField txt, String mess) {
		JOptionPane.showMessageDialog(this, mess);
		txt.selectAll();
		txt.requestFocus();
	}
}
