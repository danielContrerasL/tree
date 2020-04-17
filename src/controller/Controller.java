package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import gui.addWindow.AddWindow;
import gui.mainWindow.WindowTree;
import logic.Logic;
import managerMessage.ManagerMessage;

public class Controller implements ActionListener, MouseListener {

	private Logic logic;
	private WindowTree treeWindow;
	private AddWindow addWindow;
	private ManagerMessage message;

	public Controller() {
		logic = new Logic();
		treeWindow = new WindowTree(this);
		treeWindow.updateTree(logic.getMoviesTree());
		message = new ManagerMessage();
		this.openMainWindow();

	}

	private void openMainWindow() {
		treeWindow.setVisible(true);
	}

	private void openAddWindow() {
		if (addWindow == null)
			addWindow = new AddWindow(treeWindow, this);
		addWindow.setLocationRelativeTo(treeWindow);
		addWindow.clear();
		addWindow.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		actionPerformedDoIt(e);
		actionPerformedOpen(e);
	}

	private void actionPerformedOpen(ActionEvent e) {
		switch (Action.valueOf(e.getActionCommand())) {
		case OPEN_ADD_WINDOW:
			openAddWindow();
			break;
		default:
			break;
		}
	}

	private void actionPerformedDoIt(ActionEvent e) {
		switch (Action.valueOf(e.getActionCommand())) {
		case ACTION_CREATE_MOVIE:
			try {
				logic.addMovie(addWindow.getMovie());
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
			addWindow.setVisible(false);
			treeWindow.updateTree(logic.getMoviesTree());
			break;
		case ACTION_DELTE:
			logic.deleteSelectedMovie();
			treeWindow.updateTree(logic.getMoviesTree());
			updateTreeWindow();
			break;
		case ACTION_SELECT_IMAGE:
			addWindow.getImageFile();
			break;
			case ACTION_SHOW_AVG:
				showAvg();
			break;
		default:
			break;
		}
	}
	
	private void showAvg() {
		message.showMesagge("Promedio de calificaciones \n  	" +logic.calculateTreeAVG(), treeWindow);
		treeWindow.showAvg();
	}
	
	private void updateTreeWindow() {
		treeWindow.updateActualMovie(logic.getSelectedMovie(0, 0), logic.getHigherMovie(), logic.getLessMovie());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			updateSelectedMovie(e.getX(), e.getY());
		}
	}

	public void updateSelectedMovie(int x, int y) {
		treeWindow.updateActualMovie(logic.getSelectedMovie(x, y), logic.getHigherMovie(), logic.getLessMovie());
		message.showMesagge(logic.getMessage(), treeWindow);
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
