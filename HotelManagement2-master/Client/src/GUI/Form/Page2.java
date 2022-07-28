package GUI.Form;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class Page2 extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public Page2 () {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Page2");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(43, 76, 297, 118);
		add(lblNewLabel);
		
	}

}
