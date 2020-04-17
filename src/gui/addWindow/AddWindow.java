package gui.addWindow;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import constant.ConstantGui;
import controller.Action;
import controller.Controller;
import entity.Movie;
import gui.moviePanel.DrawImagePanel;

@SuppressWarnings("serial")
public class AddWindow extends JDialog {

	private JTextArea name;
	private JButton jBAddImage;
	private DrawImagePanel imagePanel;
	private JComboBox<Integer> calification;
	private ArrayList<Integer> value;
	private JTextArea description;
	private JButton jBACreateMovie;
	private Image auxImage;
	private String auxPath;
	private GridBagConstraints c;

	public AddWindow(Frame frame, Controller controller) {
		super(frame, true);
		setTitle("Agregar Contenido");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int) (screenSize.getWidth() * 0.3), (int) (screenSize.getHeight() * 0.78));
		setMinimumSize(getSize());
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
		initComponets(controller);
	}

	private int getCalification() {
		int exit = (int) calification.getSelectedItem();
		calification.removeItem(calification.getSelectedItem());
		return exit;
	}

	public void getImageFile() {
		auxPath = ConstantGui.DF_PATH;
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGE file", "jpg", "png", "gif");
		chooser.setFileFilter(filter);
		chooser.setFileHidingEnabled(false);
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			auxPath = chooser.getSelectedFile().getAbsolutePath();
			auxImage = new ImageIcon(auxPath).getImage();
			imagePanel.updateBg(auxImage);
		}
	}

	public Image getAuxImage() {
		return auxImage;
	}

	public Movie getMovie() throws FileNotFoundException, IOException, URISyntaxException {
//		 File f = new File(auxPath); //AUN EN PEUBAS
//		 System.out.println(auxPath);
//		 File ff = new File("E:/uptc/programacion_lll/tree/src/ima/a.jpg");//("/src/ima/");  
//		 Files.copy(new FileInputStream(f), ff.toPath(), StandardCopyOption.REPLACE_EXISTING);
//		 System.out.println(ff.getPath());
		return new Movie(name.getText(), description.getText(), auxImage, getCalification());
	}

	public void clear() {
		auxImage = new ImageIcon(getClass().getResource(ConstantGui.DF_PATH)).getImage();
		imagePanel.updateDfImage();
		calification.setSelectedIndex(0);
		description.setText("Descripcion");
		name.setText("Nombre");
	}

	private void initComponets(Controller controller) {
		initTextArea();
		initButons(controller);
		initPanel();
		initCalification();
	}

	private void initCalification() {
		value = new ArrayList<>();
		calification = new JComboBox<>();
		calification.setFont(ConstantGui.FONT);
		for (int i = 1; i <= 100; i++) {
			if (i != 50 && i != 75 && i != 25 && i != 38 && i != 13 && i != 90 && i != 60 && i != 30 && i != 45
					&& i != 55) {
				value.add(i);
			}
		}
		updateComboBox();
		c.gridx = 5;
		c.gridy = 7;
		c.gridwidth = 2;
		c.gridheight = 1;
		add(calification, c);
	}

	private void updateComboBox() {
		for (Integer i : value) {
			calification.addItem(i);
		}
	}

	private void initTextArea() {
		name = new JTextArea();
		name.setFont(ConstantGui.FONT);
		c.gridx = 4;
		c.gridy = 1;
		c.gridwidth = 4;
		c.gridheight = 1;
		add(new JScrollPane(name), c);
		description = new JTextArea();
		description.setFont(ConstantGui.FONT);
		c.gridx = 3;
		c.gridy = 8;
		c.gridwidth = 6;
		c.gridheight = 3;
		add(new JScrollPane(description), c);
	}

	private void initPanel() {
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		imagePanel = new DrawImagePanel();
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 8;
		c.gridheight = 4;
		imagePanel.setBorder(raisedbevel);
		add(imagePanel, c);
	}

	private void initButons(Controller controller) {
		jBAddImage = new JButton("Imagen");
		jBAddImage.setFont(ConstantGui.FONT);
		jBAddImage.setActionCommand(Action.ACTION_SELECT_IMAGE.toString());
		jBAddImage.addActionListener(controller);
		c.gridx = 4;
		c.gridy = 2;
		c.gridwidth = 3;
		c.gridheight = 1;
		add(jBAddImage, c);
		jBACreateMovie = new JButton("Agregar");
		jBACreateMovie.setFont(ConstantGui.FONT);
		jBACreateMovie.setActionCommand(Action.ACTION_CREATE_MOVIE.toString());
		jBACreateMovie.addActionListener(controller);
		c.gridx = 4;
		c.gridy = 11;
		c.gridwidth = 4;
		c.gridheight = 1;
		add(jBACreateMovie, c);
	}

}
