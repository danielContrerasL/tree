package test;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

@SuppressWarnings("serial")
public class TestFont extends JDialog{
	private String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getAvailableFontFamilyNames();
	private int i = 0;//89 , 150
	
	public TestFont() {
		System.out.println(fontNames[150]);
		setSize(750,500);
		setUndecorated(true);
		setLocationRelativeTo(null);
		JButton button = new JButton(i + " soy un test " + fontNames[i]);
		button.setFont(new Font(fontNames[i], Font.PLAIN ,42));
		button.setText(i + " soy un test " + fontNames[i]);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					i++;
					button.setFont(new Font(fontNames[i], Font.PLAIN	,42));
					button.setText(i + " soy un test " + fontNames[i]);
					if (i == fontNames.length) 
						i = 0;
				}
			}
		}).start();
		add(button);
		setVisible(true);
	}
	
	
	
	public static void main(String[] args) {
		new TestFont();
	}

}
