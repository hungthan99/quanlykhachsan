package CustomControll;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelMenu extends JPanel {
	static Color colorWhite = Color.WHITE;
	static Color colorOver = Color.decode("#ffffff");
	static Color colorClick = Color.decode("#f2f2f2");
	static Color bg = Color.decode("#3a71fc");
	private boolean over;
	private JLabel lbText;
	private JLabel lbIcon;

	public PanelMenu(String text, ImageIcon menuIcon) {
		super();
		setLayout(null);
		
		setBackground(bg);
		setForeground(Color.WHITE);
		
		lbText = new JLabel("");
		lbText.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbText.setText(text);
		lbText.setForeground(colorWhite);
		lbText.setBounds(70, 0, 190, 40);
		add(lbText);

		lbIcon = new JLabel("");
		lbIcon.setIcon(menuIcon);
		lbIcon.setForeground(colorWhite);
		lbIcon.setBounds(10, 0, 45, 40);
		add(lbIcon);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent me) {
				setBackground(colorOver);
				lbText.setForeground(Color.BLACK);
				over = true;
			}

			@Override
			public void mouseExited(MouseEvent me) {
				setBackground(bg);
				lbText.setForeground(Color.WHITE);
				over = false;

			}

			@Override
			public void mousePressed(MouseEvent me) {
				setBackground(colorClick);
			}

			@Override
			public void mouseReleased(MouseEvent me) {
				if (over) {
					setBackground(colorOver);
				} else {
					setBackground(bg);
					lbText.setForeground(Color.WHITE);
				}
			}
		});
	}

	@Override
	protected void paintComponent(Graphics grphcs) {
		Graphics2D g2 = (Graphics2D) grphcs;
		// Paint Border
		g2.setColor(bg);
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
		g2.setColor(getBackground());
		// Border set 2 Pix
		g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 0, 0);
		super.paintComponent(grphcs);
	}
}
