package test;

import javax.swing.JDialog;

import gui.moviePanel.MoviePanel;

@SuppressWarnings("serial")
public class TestPanelMovie extends JDialog{
	
	public TestPanelMovie() {
		setSize(500,500);
		MoviePanel p = new MoviePanel();
		p.setBounds(100, 100, 120, 100);
		add(p);
		this.setVisible(true);
		
	}


	public  void review() {
		Thread t = new Thread(new Runnable() {
			boolean status = true;
			@Override
			public void run() {
				while (status) {
					if (!isVisible()) {
						status = false;
						System.exit(0);
					}
				}
				
			}
		});
		t.start();
	}
	

	public static void main(String[] args) {
		TestPanelMovie movie = new TestPanelMovie();
		movie.review();
	}
}
