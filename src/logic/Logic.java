package logic;

import java.util.Comparator;
import constant.ConstantGui;
import entity.Coordinate;
import entity.Movie;
import structure.NodeTree;
import structure.Tree;

public class Logic {

	public static int AVG_TREE = 0;;
	private Tree<Movie> moviesTree;
	private NodeTree<Movie> selectedMovie;
	private Comparator<Movie> compare;

	public Logic() {
		initComparator();
		moviesTree = new Tree<>(compare);
		iniTree();
		Logic.AVG_TREE = calculateTreeAVG();
	}

	private void initComparator() {
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

	// ------------------PARCIAL-----------------------
	
	public String getMessage() {
		String message = "";
		if (compare.compare(selectedMovie.getInfo(), moviesTree.getRoot().getInfo()) == 0) {
			message = selectedMovie.getInfo().getName() + " Es un buen incio \nCalificacion " + selectedMovie.getInfo().getCalification();
		}else {
			message = search() + "\nCalificaion Promedio, " + calculateAVG()/(getlevel() -1);
		}
		return message;
	}
	
	private String search() {
		return this.search(this.moviesTree.getRoot(), selectedMovie.getInfo());
	}

	private String search(NodeTree<Movie> actualNode, Movie info) {
		String message = "";
		if (actualNode != null) {
			if (compare.compare(info, actualNode.getInfo()) == 0) {
				return message;
			} else if (compare.compare(info, actualNode.getInfo()) < 0) {
				if (actualNode.getLeft() != null) {
					message += actualNode.getInfo().getName() + "\n";
					return this.search(actualNode.getLeft(), info) + message;
				}
			} else {
				if (actualNode.getRight() != null) {
					message += actualNode.getInfo().getName() + "\n";
					return this.search(actualNode.getRight(), info) + message;
				}
			}
		}
		return message;
	}
	
	private int getlevel() {
		return moviesTree.level(selectedMovie.getInfo());
	}
	
	private int calculateAVG() {
		return this.calculateAVG(this.moviesTree.getRoot(), selectedMovie.getInfo());
	}

	private int calculateAVG(NodeTree<Movie> actualNode, Movie info) {
		int avg = 0;
		if (actualNode != null) {
			if (compare.compare(info, actualNode.getInfo()) == 0) {
				return avg;
			} else if (compare.compare(info, actualNode.getInfo()) < 0) {
				if (actualNode.getLeft() != null) {
					avg += actualNode.getInfo().getCalification();
					return this.calculateAVG(actualNode.getLeft(), info) + avg;
				}
			} else {
				if (actualNode.getRight() != null) {
					avg += actualNode.getInfo().getCalification();
					return this.calculateAVG(actualNode.getRight(), info) + avg;
				}
			}
		}
		return avg;
	}
	
	
	public int calculateTreeAVG() {
		return calculateTreeAVG(moviesTree.getRoot()) / moviesTree.size();
	}

	private int calculateTreeAVG(NodeTree<Movie> actualNode) {
		int avg = 0;
		if (actualNode != null) {
			avg += this.calculateTreeAVG(actualNode.getLeft());
			avg += actualNode.getInfo().getCalification() ;
			avg += this.calculateTreeAVG(actualNode.getRight());
		}
		return avg;
	}
	// ------------------PARCIAL-----------------------

	public void addMovie(Movie movie) {
		moviesTree.addNode(new NodeTree<Movie>(movie));
	}

	public Movie getSelectedMovie(int x, int y) {
		searchMovie(this.moviesTree.getRoot(), new Coordinate(x, y));
		return selectedMovie.getInfo();
	}

	private void searchMovie(NodeTree<Movie> actual, Coordinate coordinate) {
		if (actual != null) {
			this.searchMovie(actual.getLeft(), coordinate);
			int x = actual.getInfo().getCoordinate().x;
			int y = actual.getInfo().getCoordinate().y;
			if (x < coordinate.x && (x + ConstantGui.WIGHT_IMAGE) > coordinate.x) {
				if (y < coordinate.y && (y + ConstantGui.HEIGHT_IMAGE) > coordinate.y) {
					selectedMovie = actual;
					return;
				}
			}
			this.searchMovie(actual.getRight(), coordinate);
		}
	}

	public Movie getLessMovie() {
		NodeTree<Movie> aux = selectedMovie;
		if (aux.getLeft() != null) {
			while (aux.getLeft() != null) {
				aux = aux.getLeft();
			}
		}
		return aux.getInfo();
	}

	public Movie getHigherMovie() {
		NodeTree<Movie> aux = selectedMovie;
		if (aux != null) {
			while (aux.getRight() != null) {
				aux = aux.getRight();
			}
		}
		return aux.getInfo();
	}

	public NodeTree<Movie> getMoviesTree() {
		return moviesTree.getRoot();
	}

	public void delete(Movie info) {
		if (moviesTree.search(info))
			this.delete(moviesTree.getRoot(), info);
	}

	public void deleteSelectedMovie() {
		if (moviesTree.search(selectedMovie.getInfo()))
			this.delete(moviesTree.getRoot(), selectedMovie.getInfo());
		selectedMovie = moviesTree.getRoot();
	}

	private void delete(NodeTree<Movie> actualNode, Movie info) {
		if (actualNode != null) {
			if (compare.compare(info, actualNode.getInfo()) < 0) {
				if (actualNode.getLeft() != null) {
					if (compare.compare(info, actualNode.getLeft().getInfo()) == 0) {
						if (isSheet(actualNode.getLeft())) {
							actualNode.setLeft(null);
							return;
						} else if (isASheetWithSon(actualNode))
							actualNode.setLeft(deleteSheetWithSon(actualNode.getLeft()));
						else {
							deleteNode(actualNode.getLeft());
							return;
						}
					}
				}
			} else if (compare.compare(info, actualNode.getInfo()) > 0) {
				if (actualNode.getRight() != null) {
					if (compare.compare(info, actualNode.getRight().getInfo()) == 0) {
						if (isSheet(actualNode.getRight())) {
							actualNode.setRight(null);
							return;
						} else if (isASheetWithSon(actualNode))
							actualNode.setRight(deleteSheetWithSon(actualNode.getRight()));
						else {
							deleteNode(actualNode.getRight());
							return;
						}
					}
				}
			} else if (compare.compare(moviesTree.getRoot().getInfo(), actualNode.getInfo()) == 0) {
				deleteNode(moviesTree.getRoot());
				return;
			}

			if (compare.compare(info, actualNode.getInfo()) < 0)
				delete(actualNode.getLeft(), info);
			else
				delete(actualNode.getRight(), info);
		}
	}

	private void deleteNode(NodeTree<Movie> actualNode) {
		Movie auxLessInfo = searchLess(actualNode);
		Movie auxHigherInfo = searchHigher(actualNode);
		int base = actualNode.getInfo().getCalification();
		int less = auxLessInfo.getCalification();
		int higher = auxHigherInfo.getCalification();
		int base_less = Math.abs(base - less);
		int base_higher = Math.abs(base - higher);
		if (base_higher < base_less) {
			delete(actualNode, auxHigherInfo);
			actualNode.setInfo(auxHigherInfo);
		} else {
			delete(actualNode, auxLessInfo);
			actualNode.setInfo(auxLessInfo);
		}

	}

	private NodeTree<Movie> deleteSheetWithSon(NodeTree<Movie> actualNode) {
		Tree<Movie> auxTree = new Tree<>(compare);
		auxTree.addNode(actualNode.getRight());
		auxTree.addNode(actualNode.getLeft());
		return auxTree.getRoot();
	}

	private boolean isASheetWithSon(NodeTree<Movie> actualNode) {
		return (actualNode.getLeft() == null && actualNode.getRight() != null)
				|| (actualNode.getLeft() != null && actualNode.getRight() == null);
	}

	private boolean isSheet(NodeTree<Movie> actualNode) {
		return actualNode.getLeft() == null && actualNode.getRight() == null;
	}

	public Movie searchLess(NodeTree<Movie> actualNode) {
		NodeTree<Movie> aux = actualNode.getRight();
		if (aux != null) {
			while (aux.getLeft() != null) {
				aux = aux.getLeft();
			}
			return aux.getInfo();
		}
		return actualNode.getInfo();
	}

	public Movie searchHigher(NodeTree<Movie> actualNode) {
		NodeTree<Movie> aux = actualNode.getLeft();
		if (aux != null) {
			while (aux.getRight() != null) {
				aux = aux.getRight();
			}
			return aux.getInfo();
		}
		return actualNode.getInfo();
	}

	private void iniTree() {
		moviesTree.addNode(new NodeTree<Movie>(
				new Movie("/ima/2.png", "nanatsu no taisai", "Relata la historia de los 7 pecados capitales", 50)));
		moviesTree.addNode(new NodeTree<Movie>(
				new Movie("/ima/4.jpg", "trinity blood", "Vampiros que se alimentan de vampiros", 75)));
		moviesTree.addNode(new NodeTree<Movie>(
				new Movie("/ima/6.jpg", "achi cochi", "Un divertido romance entre una neko y un chico retraido", 25)));
		moviesTree.addNode(new NodeTree<Movie>(new Movie("/ima/10.jpg", "yumekui merry",
				"Esta serie relata la historia de demonios del sueño que quieren poseer humanos", 38)));
		moviesTree.addNode(new NodeTree<Movie>(
				new Movie("/ima/9.jpg", "helsing", "Relta la historia de Alucard un vampiro muy poderoso", 13)));
		moviesTree.addNode(new NodeTree<Movie>(new Movie("/ima/8.jpg", "hellsing utimate",
				"La historia de la familia Helsing y su mas poderoso siervo, Alucard un vampiro perfecto", 90)));
		moviesTree.addNode(new NodeTree<Movie>(new Movie("/ima/5.jpg", "ERGO PROXY",
				"En un futuro distopico donde el gobierno controla todo, algo sale mal y personas empizan a morir",
				60)));
		moviesTree.addNode(new NodeTree<Movie>(new Movie("/ima/7.jpg", "GULTY CROWN",
				"La historia de Guilty Crown empieza en el 2029 cuando el virus extraterrestre llamado Apocalipsis se extiende y hunde Japón en un estado de caos conocido como Lost Christmas (La Navidad Perdida). Después de la tragedia una organización internacional conocida como GHQ (Cuartel General) interviene aplicando la ley marcial y restaura el orden a Japón a costa de su independencia.",
				30)));
		moviesTree.addNode(new NodeTree<Movie>(new Movie("/ima/3.jpg", "angel beats",
				"Trata la histora de unos jovenes atrapados en el limbo y un angel que los busca para destruirlos",
				45)));
		moviesTree.addNode(new NodeTree<Movie>(new Movie("/ima/1.jpg", "mai-HIME",
				"Princesas guerreras que hacen unso de poderosas vestia para librar batallas", 55)));
		selectedMovie = moviesTree.getRoot();
	}

}
