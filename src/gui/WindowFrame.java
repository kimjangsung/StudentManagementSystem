package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowFrame extends JFrame {
	MenuSelection menuselection;
	SongAdder songadder;
	SongViewer songviewer;
	
	public WindowFrame() {
		this.menuselection = new MenuSelection(this);
		this.songadder = new SongAdder(this);
		this.songviewer = new SongViewer(this);
		
		this.setSize(500, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setupPanel(menuselection);
		this.setVisible(true);
	}
	public void setupPanel(JPanel panel) {
		this.getContentPane().removeAll();
		this.getContentPane().add(panel);
		this.revalidate();
		this.repaint();
	}
	public MenuSelection getMenuselection() {
		return menuselection;
	}
	public void setMenuselection(MenuSelection menuselection) {
		this.menuselection = menuselection;
	}
	public SongAdder getSongadder() {
		return songadder;
	}
	public void setSongadder(SongAdder songadder) {
		this.songadder = songadder;
	}
	public SongViewer getSongviewer() {
		return songviewer;
	}
	public void setSongviewer(SongViewer songviewer) {
		this.songviewer = songviewer;
	}
}
