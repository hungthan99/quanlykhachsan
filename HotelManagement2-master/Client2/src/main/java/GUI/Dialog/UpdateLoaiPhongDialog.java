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

import ClientService.LoaiPhongService;
import Rmi.DTO.LoaiPhongDTO;

public class UpdateLoaiPhongDialog extends JDialog implements ActionListener {

	private JTextField txttenPhong;
	private JTextField txtDongia;
	private JButton btnSua;
	private JButton btnHuy;
	private LoaiPhongDTO selectedloaiPhong = null;

	public UpdateLoaiPhongDialog(LoaiPhongDTO lp) {
		setModal(true);
		setSize(405, 340);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		selectedloaiPhong = lp;

		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);
		panel.setBounds(0, 0, 381, 70);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Sửa Loại Phòng");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblNewLabel, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 80, 381, 200);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Tên Loại Phòng");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 60, 150, 30);
		panel_1.add(lblNewLabel_1);

		txttenPhong = new JTextField();
		txttenPhong.setBounds(170, 60, 180, 30);
		txttenPhong.setColumns(10);
		txttenPhong.setText(selectedloaiPhong.getTenLP());
		panel_1.add(txttenPhong);

		JLabel lblNewLabel_1_1 = new JLabel("Đơn Giá Phòng");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(10, 100, 150, 30);
		panel_1.add(lblNewLabel_1_1);

		txtDongia = new JTextField();
		txtDongia.setColumns(10);
		txtDongia.setText(String.valueOf(selectedloaiPhong.getDonGia()));
		txtDongia.setBounds(170, 100, 180, 30);
		panel_1.add(txtDongia);

		btnSua = new JButton("Sửa");
		btnSua.setBounds(260, 150, 90, 30);
		panel_1.add(btnSua);

		btnHuy = new JButton("Hủy");
		btnHuy.setBounds(160, 150, 90, 30);
		panel_1.add(btnHuy);

		// == Add Action ===================
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
			SuaLoaiPhong();
		}
	}

	private void SuaLoaiPhong() {
		if (!check_data())
			return;
		String name = txttenPhong.getText().toString().trim();
		String price = txtDongia.getText().toString().trim();

		LoaiPhongDTO updateObj = new LoaiPhongDTO(name, Double.parseDouble(price));
		updateObj.setMaLP(selectedloaiPhong.getMaLP());

		if (!LoaiPhongService.getInstance().updateLoaiPhong(updateObj)) {
			JOptionPane.showMessageDialog(null,
					"Có lỗi xảy ra trong quá trình cập nhật loại phong : " + updateObj.getTenLP());
			return;
		}

		JOptionPane.showMessageDialog(null, "Cập nhật thành công loại phòng: " + updateObj.getTenLP());
		this.dispose();
	}

	private boolean check_data() {
		return true;
	}
}
