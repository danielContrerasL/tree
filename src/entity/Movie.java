package entity;

import java.awt.Image;

import javax.swing.ImageIcon;

import constant.ConstantGui;

public class Movie {

	private Coordinate coordinate;
	private String pathImage;
	private String name;
	private String description;
	private Image image;
	private int calification;

	public Movie(String pathImage, String name, String description, int calification) {
		this.pathImage = pathImage;
		this.name = name;
		this.calification = calification;
		this.description = description;
		this.image = new ImageIcon(getClass().getResource(pathImage)).getImage();
		coordinate = new Coordinate(0, 0);
	}
	
	public Movie( String name, String description, Image image,int calification) {
		this.pathImage = ConstantGui.DF_PATH;
		this.name = name;
		this.calification = calification;
		this.description = description;
		this.image = image;
		coordinate = new Coordinate(0, 0);
	}

	public Movie(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCalification() {
		return calification;
	}

	public void setCalification(int calification) {
		this.calification = calification;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return this.name + " -> X = " + coordinate.getX() + " Y= " + coordinate.getY();
	}
}
