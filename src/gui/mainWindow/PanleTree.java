package gui.mainWindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Comparator;
import javax.swing.JScrollPane;
import constant.ConstantGui;
import controller.Controller;
import entity.Coordinate;
import entity.Movie;
import logic.Logic;
import structure.NodeTree;
import structure.Tree;

@SuppressWarnings("serial")
public class PanleTree extends JScrollPane {

	private Tree<Movie> listMovie;
	private int center;
	private int level;
	private Dimension actualDimension;
	private Comparator<Movie> compare;
	private boolean showAvg;

	public PanleTree(Controller controller) {
		addMouseListener(controller);
		showAvg = false;
		level = 20;
		actualDimension = new Dimension(1500, 1500);
		compare = new Comparator<Movie>() {
			
			@Override
			public int compare(Movie o1, Movie o2) {
				if (o1.getCalification() < o2.getCalification())
					return -1;
				else if (o1.getCalification() > o2.getCalification())
					return 1;
				return 0;
			}
		};
	}
	
	public void showAvg() {
		showAvg = !showAvg;
	}

	public void updateTree(NodeTree<Movie> tree) {
		listMovie = null;
		listMovie = new Tree<>(compare);
		listMovie.addNode(tree);
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		center = this.getWidth() / 2;
		drawNode(g);
		level = 20;
	}

	private void drawNode(Graphics g) {
		g.setFont(new Font("Courier New", Font.ITALIC, 12));
		if (listMovie.getRoot() != null) {
			listMovie.getRoot().getInfo().setCoordinate(new Coordinate(center, level));
			drawNode(g, listMovie.getRoot());
		}
	}

	private void drawNode(Graphics g, NodeTree<Movie> actual) {
		if (actual != null) {
			g.drawImage(actual.getInfo().getImage(), actual.getInfo().getCoordinate().getX(),
					actual.getInfo().getCoordinate().getY(), ConstantGui.WIGHT_IMAGE, ConstantGui.HEIGHT_IMAGE, this);
			if (showAvg) {
				if (actual.getInfo().getCalification() <= Logic.AVG_TREE && listMovie.isSheet(actual)) {
					g.setColor(Color.RED);
					g.drawRect(actual.getInfo().getCoordinate().getX() - 5,
					actual.getInfo().getCoordinate().getY() - 5, ConstantGui.WIGHT_IMAGE + 10, ConstantGui.HEIGHT_IMAGE + 10);
					g.drawString(String.valueOf(actual.getInfo().getCalification()),
							20 + actual.getInfo().getCoordinate().getX(), actual.getInfo().getCoordinate().getY() - 10);
					g.setColor(Color.BLACK);
				}
				
			}
			int y = actual.getInfo().getCoordinate().getY() + ConstantGui.SPACE;
			if (actual.getLeft() != null) {
				int x = actual.getInfo().getCoordinate().getX() - getGrid(actual.getLeft());
				g.drawLine(actual.getInfo().getCoordinate().getX() + ConstantGui.MID_WIGHT_IMAGE,
						actual.getInfo().getCoordinate().getY() + ConstantGui.HEIGHT_IMAGE,
						x + ConstantGui.MID_WIGHT_IMAGE, y);
				actual.getLeft().getInfo().setCoordinate(new Coordinate(x, y));
			}
			if (actual.getRight() != null) {
				int x = actual.getInfo().getCoordinate().getX() + getGrid(actual.getRight());
				g.drawLine(actual.getInfo().getCoordinate().getX() + ConstantGui.MID_WIGHT_IMAGE,
						actual.getInfo().getCoordinate().getY() + ConstantGui.HEIGHT_IMAGE,
						x + ConstantGui.MID_WIGHT_IMAGE, y);
				actual.getRight().getInfo().setCoordinate(new Coordinate(x, y));
			}
			drawNode(g, actual.getLeft());
			drawNode(g, actual.getRight());
		}
	}

	/**
	 * revalidar el tamaño del panel
	 * @param actual
	 * @return
	 */
//	private void validateDraw(int x, int y) {
//		if (x > this.getWidth() || x <= 0)
//			actualDimension = new Dimension(2 * this.getWidth(), this.getHeight());
//		if (y > this.getHeight() || y <= 0)
//			actualDimension = new Dimension(this.getWidth(), 2 * this.getHeight());
//	}

	private int getGrid(NodeTree<Movie> actual) {
		int auxPow = (int) Math.pow(2, (listMovie.level(actual.getInfo()) - 1));
		return (this.getWidth() / auxPow) / 2;
	}

	@Override
	public Dimension preferredSize() {
		return actualDimension;
	}

}
