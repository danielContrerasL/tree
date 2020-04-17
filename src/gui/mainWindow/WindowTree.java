package gui.mainWindow;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import constant.ConstantGui;
import controller.Action;
import controller.Controller;
import entity.Coordinate;
import entity.Movie;
import structure.NodeTree;

@SuppressWarnings("serial")
public class WindowTree extends JFrame {

	private PanleTree panleTree;
	private PanelProfile panelProfile;
	private LowerHigherPanel lowerHigherPanel;
	private JScrollPane scroll;
	
	private JMenuBar jMenuBar;
	private JMenu jMenuStartCapsule;
	private JMenuItem jMenuAddMovie;

	private GridBagConstraints c;
	
	public WindowTree(Controller controller) {
		initSizeWindow();
		panleTree = new PanleTree(controller);
		panelProfile = new PanelProfile((int) (getWidth() * 0.3), getHeight(), controller);
		lowerHigherPanel = new LowerHigherPanel();
		initLayout();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Social network is Better for tv");
		setIconImage(new ImageIcon(getClass().getResource(ConstantGui.ICO_PATH)).getImage());
		initWindow();
		initMenuBar(controller);

	}
	
	public void showAvg() {
		panleTree.showAvg();
		repaint();
	}
	
	private void initLayout() {
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
	}
	
	private void initMenuBar(Controller controller) {
		jMenuBar = new JMenuBar();
		addMenuCapsule(controller);
		initFontMenu();
		setJMenuBar(jMenuBar);
	}
	
	private void addMenuCapsule(Controller controller) {
		jMenuStartCapsule = new  JMenu("Inicio");
		addMenuItem(controller);
		jMenuBar.add(jMenuStartCapsule);
	}
	
	private void addMenuItem(Controller controller) {
		jMenuAddMovie = new JMenuItem("Agregar");
		jMenuAddMovie.setActionCommand(Action.OPEN_ADD_WINDOW.toString());
		jMenuAddMovie.addActionListener(controller);
		jMenuStartCapsule.add(jMenuAddMovie);
	}
	
	private void initFontMenu() {
		jMenuBar.setFont(ConstantGui.FONT);
		jMenuStartCapsule.setFont(ConstantGui.FONT);
		jMenuAddMovie.setFont(ConstantGui.FONT);
	}
	
	private void initWindow() {
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.gridheight = 12;
		add(panelProfile, c);
		scroll = new JScrollPane(panleTree);
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 7;
		c.gridheight = 12;
		add(scroll, c);
		c.gridx = 10;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 12;
		add(lowerHigherPanel, c);
	}
	
	public void updateActualMovie( Movie actual, Movie higher, Movie lower) {
		panelProfile.updateSelectedMovie(actual);
		lowerHigherPanel.updateLowerHigherPanel(higher, lower);
	}
	
	public void updateTree(NodeTree<Movie> tree) {
		panleTree.updateTree(tree);
	}
	
	public void paintARectangle(Coordinate coordinate) {
 		panleTree.getViewport().setSize(panleTree.getSize().height * 2, panleTree.getSize().width * 2);
		scroll.revalidate();
	}
	
	private void initSizeWindow() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)(screenSize.getWidth()* 0.7), (int)(screenSize.getHeight() * 0.8));
		setLocation((int)(screenSize.getWidth()* 0.10), (int)(screenSize.getHeight() * 0.2));
		setMinimumSize(getSize());
	}

}
