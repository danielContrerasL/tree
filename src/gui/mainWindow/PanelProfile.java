package gui.mainWindow;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constant.ConstantGui;
import controller.Action;
import controller.Controller;
import entity.Movie;
import gui.moviePanel.MoviePanel;

@SuppressWarnings("serial")
public class PanelProfile extends JPanel {

	private JButton jBDeleteMovie;
	private JButton jBlessAvg;
	private MoviePanel moviePanel;
	private GridBagConstraints c;

	public PanelProfile(int w, int h, Controller controller) {
		this.setSize(w,h);
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 0.01;
		c.gridheight = 1;
		for (int i = 0; i < 12; i++) {
			c.gridx = i;
			c.gridy = i;
			add(new JLabel(), c);
		}
		initPanel(controller);
	}

	private void initPanel(Controller controller) {
		initPanelMovie();
		initButton(controller);
	}
	
	public void updateSelectedMovie(Movie actualMovie) {
		moviePanel.updatePanel(actualMovie);
	}

	private void initPanelMovie() {
		moviePanel = new MoviePanel();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth= 12;
		c.gridheight = 11;
		this.add(moviePanel, c);

	}

	private void initButton(Controller controller) {
		jBDeleteMovie = new JButton("Borrar");
		jBDeleteMovie.setFont(ConstantGui.FONT);
		jBDeleteMovie.setActionCommand(Action.ACTION_DELTE.toString());
		jBDeleteMovie.addActionListener(controller);
		c.gridx = 2;
		c.gridy = 11;
		c.gridwidth= 4;
		c.gridheight = 1;
		add(jBDeleteMovie, c);
		
		
		
		jBlessAvg = new JButton("Resaltar promedio");
		jBlessAvg.setFont(ConstantGui.FONT);
		jBlessAvg.setActionCommand(Action.ACTION_SHOW_AVG.toString());
		jBlessAvg.addActionListener(controller);
		c.gridx = 7;
		c.gridy = 11;
		c.gridwidth= 4;
		c.gridheight = 1;
		add(jBlessAvg, c);
	}

}
