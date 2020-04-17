package gui.moviePanel;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import constant.ConstantGui;

@SuppressWarnings("serial")
public class DrawImagePanel extends JPanel {

	private Image bg;

	public DrawImagePanel() {
		bg = new ImageIcon(getClass().getResource(ConstantGui.DF_PATH)).getImage();
	}
	
	public void updateDfImage() {
		bg = new ImageIcon(getClass().getResource(ConstantGui.DF_PATH)).getImage();
		repaint();
	}

	public DrawImagePanel(Image bg) {
		this.bg = bg;
	}

	public void updateBg(Image bg) {
		this.bg = bg;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.bg, 5, 15, this.getWidth() - 10, this.getHeight() - 20, this);
	}

}
