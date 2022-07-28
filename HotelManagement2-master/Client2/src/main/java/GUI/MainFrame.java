package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import CustomControll.GradientPanel;
import CustomControll.PanelBackground;
import CustomControll.PanelMenu;
import GUI.Form.DatPhongForm;
import GUI.Form.GoiDichVuForm;
import GUI.Form.NhanPhongForm;
import GUI.Form.QuanLyDichVuForm;
import GUI.Form.QuanLyKhachHangForm;
import GUI.Form.QuanLyLoaiPhongForm;
import GUI.Form.QuanLyNhanVienForm;
import GUI.Form.QuanLyPhongForm;
import GUI.Form.ThanhToanForm;
import GUI.Form.TraPhongForm;
import Rmi.DTO.NhanVienDTO;
import Rmi.DTO.TaiKhoanDTO;
import javax.swing.JButton;

public class MainFrame extends JFrame implements ActionListener {
	private Color colorEnter = Color.WHITE;
	private String username = "Sample";
	private String password;
	private boolean permission;
	private JPanel contentPane, panelMain;
	private JLabel titleLabel, lblIconTitle;
	private final JSeparator separator = new JSeparator();
	private NhanVienDTO nhanVien = null;
	private PanelMenu pQLLoaiPhong;
	private PanelMenu pQLNhanVien;
	private PanelMenu pQLDichVu;
	private PanelMenu pQLPhong;
	private JButton btnDangXuat;

	public static void main(String[] args) {
		NhanVienDTO nv = new NhanVienDTO(1);
		nv.setTen("Nguyễn Văn A");
		TaiKhoanDTO tk = new TaiKhoanDTO("abc", "abc", true);
		nv.setTaiKhoan(tk);

		MainFrame frame = new MainFrame(nv);
		frame.setVisible(true);
	}

	public MainFrame(NhanVienDTO nv) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1440, 900);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(null);

		setContentPane(contentPane);

		nhanVien = nv;

		JPanel panelContainer = new JPanel();
		panelContainer.setBounds(0, 0, 1436, 854);
		panelContainer.setLayout(null);
		contentPane.add(panelContainer);

		GradientPanel panelSideBar = new GradientPanel(Color.decode("#DC2424"),Color.decode("#4A569D"),180,580);
		panelSideBar.setBounds(0, 0, 250, 858);
		panelSideBar.setBackground(Color.decode("#3a71fc"));
		panelContainer.add(panelSideBar);
		panelSideBar.setLayout(null);

		JLabel lblUsername = new JLabel();
		lblUsername.setText("Hello: " + nhanVien.getTen());
		lblUsername.setBackground(Color.WHITE);
		lblUsername.setForeground(SystemColor.text);
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUsername.setBounds(0, 10, 250, 83);
		panelSideBar.add(lblUsername);

		ImageIcon pDieuKhienIcon = new ImageIcon(new ImageIcon("icon/monitor.png").getImage());
		PanelMenu pDieuKhien = new PanelMenu("Bảng điều khiển", pDieuKhienIcon);
		pDieuKhien.setBounds(0, 103, 250, 40);
		panelSideBar.add(pDieuKhien);

		separator.setBounds(0, 91, 250, 2);
		panelSideBar.add(separator);

		JLabel lbLeTan = new JLabel("Nghiệp vụ lễ tân");
		lbLeTan.setForeground(SystemColor.text);
		lbLeTan.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbLeTan.setBounds(0, 150, 250, 30);
		panelSideBar.add(lbLeTan);

		ImageIcon pDatPhongIcon = new ImageIcon(new ImageIcon("icon/iconDatPhong.png").getImage());
		PanelMenu pDatPhong = new PanelMenu("Đặt Phòng", pDatPhongIcon);
		pDatPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setMainPanel(new DatPhongForm(nhanVien), "Đặt Phòng", pDatPhongIcon);
			}
		});
		pDatPhong.setBounds(0, 190, 250, 40);
		panelSideBar.add(pDatPhong);
		pDatPhong.setLayout(null);

		ImageIcon pNhanPhongIcon = new ImageIcon(new ImageIcon("icon/iconNhanPhong.png").getImage());
		PanelMenu pNhanPhong = new PanelMenu("Nhận Phòng", pNhanPhongIcon);
		pNhanPhong.setBounds(0, 230, 250, 40);
		pNhanPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setMainPanel(new NhanPhongForm(), "Nhận Phòng", pNhanPhongIcon);
			}
		});
		panelSideBar.add(pNhanPhong);

		ImageIcon pTraPhongIcon = new ImageIcon(new ImageIcon("icon/iconTraPhong.png").getImage());
		PanelMenu pTraPhong = new PanelMenu("Trả Phòng", pTraPhongIcon);
		pTraPhong.setBounds(0, 270, 250, 40);
		pTraPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setMainPanel(new TraPhongForm(), "Trả Phòng", pTraPhongIcon);
			}
		});
		panelSideBar.add(pTraPhong);

		ImageIcon pGoiDichVuIcon = new ImageIcon(new ImageIcon("icon/iconGoiDv.png").getImage());
		PanelMenu pGoiDichVu = new PanelMenu("Gọi Dịch Vụ", pGoiDichVuIcon);
		pGoiDichVu.setBounds(0, 310, 250, 40);
		pGoiDichVu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setMainPanel(new GoiDichVuForm(), "Gọi Dịch Vụ", pGoiDichVuIcon);
			}
		});
		panelSideBar.add(pGoiDichVu);

		ImageIcon pThanhToanIcon = new ImageIcon(new ImageIcon("icon/iconThanhToan.png").getImage());
		PanelMenu pThanhToan = new PanelMenu("Thanh Toán", pThanhToanIcon);
		pThanhToan.setBounds(0, 350, 250, 40);
		pThanhToan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setMainPanel(new ThanhToanForm(), "Thanh Toán", pThanhToanIcon);
			}
		});
		panelSideBar.add(pThanhToan);

		JLabel LbQuanLy = new JLabel("Quản Lý");
		LbQuanLy.setForeground(Color.WHITE);
		LbQuanLy.setFont(new Font("Tahoma", Font.BOLD, 16));
		LbQuanLy.setBounds(0, 390, 250, 30);
		panelSideBar.add(LbQuanLy);

		ImageIcon pQLKhachIcon = new ImageIcon(new ImageIcon("icon/iconCustomer.png").getImage());
		PanelMenu pQLKhach = new PanelMenu("Quản Khách Hàng", pQLKhachIcon);
		pQLKhach.setBounds(0, 430, 250, 40);
		pQLKhach.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setMainPanel(new QuanLyKhachHangForm(), "Quản Khách Hàng", pQLKhachIcon);
			}
		});
		panelSideBar.add(pQLKhach);

		ImageIcon pQLPhongIcon = new ImageIcon(new ImageIcon("icon/iconRotelRoom.png").getImage());
		pQLPhong = new PanelMenu("Quản lý phòng", pQLPhongIcon);
		pQLPhong.setBounds(0, 510, 250, 40);
		pQLPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setMainPanel(new QuanLyPhongForm(), "Quản Lý Phòng", pQLPhongIcon);
			}
		});
		panelSideBar.add(pQLPhong);

		ImageIcon pQLNhanVienIcon = new ImageIcon(new ImageIcon("icon/iconQlNv.png").getImage());
		pQLNhanVien = new PanelMenu("Quản lý nhân viên", pQLNhanVienIcon);
		pQLNhanVien.setBounds(0, 590, 250, 40);
		pQLNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setMainPanel(new QuanLyNhanVienForm(), "Quản Lý Nhân Viên", pQLNhanVienIcon);
			}
		});
		panelSideBar.add(pQLNhanVien);

		ImageIcon pQLDichVuIcon = new ImageIcon(new ImageIcon("icon/room-service.png").getImage());
		pQLDichVu = new PanelMenu("Quản lý Dịch Vụ", pQLDichVuIcon);
		pQLDichVu.setBounds(0, 550, 250, 40);
		pQLDichVu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setMainPanel(new QuanLyDichVuForm(), "Quản lý Dịch Vụ", pQLDichVuIcon);
			}
		});
		panelSideBar.add(pQLDichVu);

		ImageIcon pQLLoaiPhongIcon = new ImageIcon(new ImageIcon("icon/iconQlLoaiPhong.png").getImage());
		pQLLoaiPhong = new PanelMenu("Quản lý Loại Phòng", pQLLoaiPhongIcon);
		pQLLoaiPhong.setBounds(0, 470, 250, 40);
		pQLLoaiPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setMainPanel(new QuanLyLoaiPhongForm(), "Quản lý Loại Phòng", pQLDichVuIcon);
			}
		});
		panelSideBar.add(pQLLoaiPhong);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 635, 170, 175);
		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon("img/bat.jpg").getImage().getScaledInstance(170, 175, Image.SCALE_DEFAULT));
		lblNewLabel.setIcon(imageIcon);
		panelSideBar.add(lblNewLabel);
		
		btnDangXuat = new JButton("Đăng xuất");
		btnDangXuat.setIcon(new ImageIcon("img/power.png"));
		btnDangXuat.setBounds(0, 815, 250, 39);
		panelSideBar.add(btnDangXuat);

		panelMain = new JPanel();
		panelMain.setBounds(250, 40, 1186, 818);
		panelMain.setLayout(new BorderLayout(0, 0));
		panelContainer.add(panelMain);
		

		JPanel pTitle = new JPanel();
		pTitle.setBackground(Color.decode("#ffa454"));
		pTitle.setBounds(250, 0, 1186, 40);
		pTitle.setLayout(null);

		lblIconTitle = new JLabel("");
		lblIconTitle.setBounds(10, 0, 40, 40);
		pTitle.add(lblIconTitle);

		titleLabel = new JLabel("");
		titleLabel.setForeground(Color.BLUE);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		titleLabel.setBounds(60, 0, 392, 40);
		pTitle.add(titleLabel);
		panelContainer.add(pTitle);
		// ==== action 
		btnDangXuat.addActionListener(this);
		
		// =====================================
		ShowAdminMenu();
		setMainPanel(new PanelBackground(), "Chào mừng đến với khách sạn Transylvania", null);
	}

	private void setMainPanel(JPanel panel, String text, ImageIcon icon) {
		titleLabel.setText(text);
		lblIconTitle.setIcon(icon);
		panelMain.removeAll();
		panelMain.add(panel, BorderLayout.CENTER);
	}

	private void ShowAdminMenu() {
		if (nhanVien.getTaiKhoan().isAdmin()) {
			pQLLoaiPhong.setVisible(true);
			pQLNhanVien.setVisible(true);
			pQLDichVu.setVisible(true);
			pQLPhong.setVisible(true);
		} else {
			pQLLoaiPhong.setVisible(false);
			pQLNhanVien.setVisible(false);
			pQLDichVu.setVisible(false);
			pQLPhong.setVisible(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnDangXuat)) {
			DangXuat();
		}
		
	}

	private void DangXuat() {
		this.dispose();
		LoginForm loginForm = new LoginForm();
		loginForm.setVisible(true);
	}
}
