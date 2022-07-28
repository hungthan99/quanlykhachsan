package CustomControll;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class ColorButton extends JButton {
	private static final long serialVersionUID = 1L;
	
	private boolean over;
	private Color color, colorOver, colorClick, borderColor;
	private int radius = 0;

	public ColorButton() {
		super();
		//setColor(new Color(255, 107, 120));
		setColor(Color.decode("#3a71fc"));
		colorOver = new Color(255, 125, 136);
		colorClick = new Color(217, 67, 80);
		borderColor = new Color(237, 59, 74);
		setBorder(null);
		setContentAreaFilled(false);
		setBorderPainted(false);

		// Add event mouse
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent me) {
				setBackground(colorOver);
				over = true;
			}

			@Override
			public void mouseExited(MouseEvent me) {
				setBackground(color);
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
					setBackground(color);
				}
			}
		});
	}

	@Override
	protected void paintComponent(Graphics grphcs) {
		Graphics2D g2 = (Graphics2D) grphcs;
		// Paint Border
		g2.setColor(borderColor);
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
		g2.setColor(getBackground());
		// Border set 2 Pix
		g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);
		super.paintComponent(grphcs);
	}

	public boolean isOver() {
		return over;
	}

	public void setOver(boolean over) {
		this.over = over;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColorOver() {
		return colorOver;
	}

	public void setColorOver(Color colorOver) {
		this.colorOver = colorOver;
	}

	public Color getColorClick() {
		return colorClick;
	}

	public void setColorClick(Color colorClick) {
		this.colorClick = colorClick;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

}
