package gui.moviePanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import constant.ConstantGui;
import entity.Movie;

@SuppressWarnings("serial")
public class MoviePanel extends JPanel {

	private TitledBorder seriesTBorder;
	private Border loweredbevelBorder;
	private GridBagConstraints c;
	private DrawImagePanel imagePanel;
	private JTextField description;
	private JLabel jLCalification;

	public MoviePanel() {
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
		initPanel();
	}
	
	public void updatePanel(Movie movie) {
		description.setText(movie.getDescription());
		seriesTBorder.setTitle(movie.getName());
		jLCalification.setText(String.valueOf(movie.getCalification()));
		imagePanel.updateBg(movie.getImage());
		revalidate();
		
	}

	private void initPanel() {
		imagePanel = new DrawImagePanel();
		loweredbevelBorder = BorderFactory.createLoweredBevelBorder();
		seriesTBorder = BorderFactory.createTitledBorder(loweredbevelBorder, "Totoro-San");
		seriesTBorder.setTitlePosition(TitledBorder.ABOVE_TOP);
		imagePanel.setBorder(seriesTBorder);
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = 5;
		c.gridheight = 7;
		add(imagePanel, c);
		jLCalification = new JLabel("(^.^ )");
		TitledBorder title = BorderFactory.createTitledBorder("Calificacion");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleFont(ConstantGui.FONT);
		jLCalification.setBorder(title);
		jLCalification.setFont(ConstantGui.FONT);
		c.gridx = 5;
		c.gridy = 8;
		c.gridwidth = 2;
		c.gridheight = 1;
		add(jLCalification, c);
		description = new JTextField();
		description.setText("Soy una descripcion");
		description.setFont(ConstantGui.FONT);
		description.setEditable(false);
		c.gridx = 2;
		c.gridy = 9;
		c.gridwidth = 8;
		c.gridheight = 3;
		add(new JScrollPane(description), c);
	}

}
