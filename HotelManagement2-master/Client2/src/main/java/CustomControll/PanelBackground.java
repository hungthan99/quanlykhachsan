package CustomControll;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PanelBackground extends JPanel {
	public PanelBackground() {
		setLayout(null);
		setBounds(0, 0, 1180, 820);

		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon("img/bg4.jpg").getImage().getScaledInstance(1180, 820, Image.SCALE_DEFAULT));
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 1180, 820);
		lblNewLabel.setIcon(imageIcon);
		add(lblNewLabel);
	}
}
