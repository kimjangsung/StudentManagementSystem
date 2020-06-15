package gui;

import javax.swing.JFrame;

import github.SongManager;

public class WindowFrame extends JFrame {

	SongManager songManager;
	MenuSelection menuSelection;
	SongAdder songadder;
	SongViewer songviewer;
	
	public WindowFrame(SongManager songManager) {
		this.setSize(500, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("My Frame");
		
		this.songManager = songManager;
		menuSelection = new MenuSelection(this);
		songadder = new SongAdder(this);
		songviewer = new SongViewer(this, this.songManager);

		
		this.add(menuSelection);
		
		this.setVisible(true);
	}
	
	public MenuSelection getMenuSelection() {
		return menuSelection;
	}

	public void setMenuSelection(MenuSelection menuSelection) {
		this.menuSelection = menuSelection;
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
