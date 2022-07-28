package CustomControll;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GradientPanel extends JPanel {
	Color color1, color2;
	float doNghieng,doCao;
	 public GradientPanel(Color color1, Color color2,float doNghieng, float doCao) {
		super();
		this.color1 = color1;
		this.color2 = color2;
		this.doNghieng = doNghieng;
		this.doCao = doCao;
	}

	@Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Graphics2D g2d = (Graphics2D) g;
	        int w = getWidth();
	        int h = getHeight();
	        GradientPaint gp = new GradientPaint(0, 0, color1, doNghieng, doCao, color2);
	        g2d.setPaint(gp);
	        g2d.fillRect(0, 0, w, h);
	    }
}
