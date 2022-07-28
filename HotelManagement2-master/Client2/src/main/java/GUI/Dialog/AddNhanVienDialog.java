package GUI.Dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ClientService.NhanVienService;
import Rmi.DTO.NhanVienDTO;
import Rmi.DTO.TaiKhoanDTO;

public class AddNhanVienDialog extends JDialog implements ActionListener {
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtMail;
	private JTextField txtAccountName;
	private JPasswordField txtAccountPass;
	private JButton btnThem;
	private JButton btnHuy;
	private JRadioButton rdQuanLy;
	private JRadioButton rdNhanVien;
	private JComboBox<String> comboBox;

	public AddNhanVienDialog() {
		setModal(true);
		setSize(570, 475);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);
		panel.setBounds(0, 0, 556, 70);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Thêm Nhân Viên");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblNewLabel, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 98, 556, 330);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		txtName = new JTextField();
		txtName.setBounds(166, 10, 380, 30);
		panel_1.add(txtName);
		txtName.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Tên nhân viên");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 10, 134, 30);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Số điện thoại");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(10, 50, 134, 30);
		panel_1.add(lblNewLabel_1_1);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(166, 50, 380, 30);
		panel_1.add(txtPhone);

		JLabel lblNewLabel_1_1_1 = new JLabel("Email");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_1.setBounds(10, 90, 134, 30);
		panel_1.add(lblNewLabel_1_1_1);

		txtMail = new JTextField();
		txtMail.setColumns(10);
		txtMail.setBounds(166, 90, 380, 30);
		panel_1.add(txtMail);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Giới tính");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_1_1.setBounds(10, 130, 134, 30);
		panel_1.add(lblNewLabel_1_1_1_1);

		String gioiTinh[] = { "Nam", "Nữ" };
		comboBox = new JComboBox<String>(gioiTinh);
		comboBox.setBounds(166, 130, 380, 21);
		panel_1.add(comboBox);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Tên tài khoản");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_1_1_1.setBounds(10, 170, 134, 30);
		panel_1.add(lblNewLabel_1_1_1_1_1);

		txtAccountName = new JTextField();
		txtAccountName.setColumns(10);
		txtAccountName.setBounds(166, 161, 380, 30);
		panel_1.add(txtAccountName);

		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Mật khẩu");
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_1_1_1_1.setBounds(10, 210, 134, 30);
		panel_1.add(lblNewLabel_1_1_1_1_1_1);

		txtAccountPass = new JPasswordField();
		txtAccountPass.setColumns(10);
		txtAccountPass.setBounds(166, 201, 380, 30);
		panel_1.add(txtAccountPass);

		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Loại tài khoản");
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_1_1_1_1_1.setBounds(10, 250, 134, 30);
		panel_1.add(lblNewLabel_1_1_1_1_1_1_1);

		rdNhanVien = new JRadioButton("Nhân viên");
		rdNhanVien.setBounds(166, 250, 120, 30);
		panel_1.add(rdNhanVien);

		rdQuanLy = new JRadioButton("Quản lý");
		rdQuanLy.setBounds(306, 250, 120, 30);
		panel_1.add(rdQuanLy);

		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(rdNhanVien);
		bgroup.add(rdQuanLy);
		rdNhanVien.setSelected(true);

		btnThem = new JButton("Thêm");
		btnThem.setBounds(460, 290, 85, 30);
		panel_1.add(btnThem);

		btnHuy = new JButton("Hủy");
		btnHuy.setBounds(366, 290, 85, 30);
		panel_1.add(btnHuy);

		// == Add Action ====
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
			ThemNhanVien();
		}
	}

	private boolean check_data() {

		String maCheck = txtName.getText().trim();
		String mess = "";

		if (!(maCheck.length() > 0 && maCheck.matches(
				"^([ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴA-Z]{1}[ắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵa-z]*\\s)+([ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴA-Z]{1}[ắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵa-z]*)$"))) {
			if (maCheck.length() == 0) {
				mess = "Hãy nhập tên nhân viên.";
			} else {
				mess = "Tên nhân viên có chữ hoa ở đầu mỗi từ, cách nhau bởi đấu cách. \nVD: Nguyễn Văn A";
			}
			getMess(txtName, mess);
			return false;
		}
		//

		maCheck = txtPhone.getText().trim();
		if (!(maCheck.length() > 0 && maCheck.matches("^0[0-9]{9}$"))) {
			if (maCheck.length() == 0) {
				mess = "Hãy nhập số điện thoại của nhân viên.";
			} else if (maCheck.length() != 10) {
				mess = "Số điện thoại có 10 số và bắt đầu bằng số 0.";
			}
			getMess(txtPhone, mess);
			return false;
		}

		//
		maCheck = txtMail.getText().trim();
		if (!(maCheck.matches("^[A-Za-z0-9._]+@[A-Za-z0-9.]+\\.[a-z]{2,4}$"))) {
			if (maCheck.length() == 0) {
				mess = "Hãy nhập Email của nhân viên";
			} else {
				mess = "Email phải đúng theo định dạng (VD: Abc@gmail.com)";
			}
			getMess(txtMail, mess);
			return false;
		}
		//

		maCheck = txtAccountName.getText().trim();
		if (!(maCheck.matches("^[A-Za-z0-9_]{4,}$"))) {
			if (maCheck.length() == 0) {
				mess = "Hãy nhập tài khoản.";
			} else {
				mess = "Tài khoản phải trên 4 ký tự và không có ký tự đặc biệt. ";
			}
			getMess(txtAccountName, mess);
			return false;
		}
		//
		maCheck = txtAccountPass.getText().trim();
		if (!(maCheck.length() > 0 && maCheck
				.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[-_+=@$!%*#?&])[A-Za-z\\d-_+=@$!%*#?&]{6,20}$"))) {
			if (maCheck.length() <= 0) {
				mess = "Hãy nhập mật khẩu.";
			} else {
				mess = "Mật khẩu phải trên 6 ký tự trong dó có một chữ số, một chữ cái thường, một chữ hoa và một ký tự đặc biệt";
			}
			getMess(txtAccountPass, mess);
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

	private void ThemNhanVien() {
		System.out.println("btnThem");
		if (!check_data())
			return;
		String name = txtName.getText().toString();
		String gender = comboBox.getSelectedItem().toString();
		String phone = txtPhone.getText().toString();
		String mail = txtMail.getText().toString();

		String accountName = txtAccountName.getText().toString();
		String accountPass = txtAccountPass.getText().toString();
		boolean isAdmin = rdQuanLy.isSelected();

		NhanVienDTO addObj = new NhanVienDTO(name, mail, gender, phone);
		TaiKhoanDTO tkDTO = new TaiKhoanDTO(accountName, accountPass, isAdmin);
		addObj.setTaiKhoan(tkDTO);

		if (!NhanVienService.getInstance().addNhanVien(addObj)) {
			JOptionPane.showMessageDialog(null, "Tên tài khoản đã được sử dụng bởi nhân viên khác");
			txtAccountName.requestFocus();
			return;
		}

		JOptionPane.showMessageDialog(null, "Đã thêm thành công nhân viên : " + name);
		
		this.dispose();
	}

}
