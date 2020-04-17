package gui.mainWindow;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;

import entity.Movie;
import gui.moviePanel.MoviePanel;

@SuppressWarnings("serial")
public class LowerHigherPanel extends JPanel{
	
	private MoviePanel higherPanel;
	private MoviePanel lowerPanel;
	
	public LowerHigherPanel() {
		setLayout(new GridLayout(2, 1));
		setBackground(Color.BLUE);
		initPanel();
	}
	
	private void initPanel() {
		higherPanel = new MoviePanel();
		add(higherPanel);
		lowerPanel = new MoviePanel();
		add(lowerPanel);
	}
	
	public void updateLowerHigherPanel(Movie higher, Movie lower) {
		higherPanel.updatePanel(higher);
		lowerPanel.updatePanel(lower);
	}

}
