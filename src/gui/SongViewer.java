package gui;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import github.SongManager;
import song.SongInput;

public class SongViewer extends JPanel {
	
	WindowFrame frame;
	SongManager songManager;
	
	
	
	public SongViewer(WindowFrame frame, SongManager songManager) {
		this.frame = frame;
		this.songManager = songManager;
		
		System.out.println("***" + songManager.size() + "***");
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Name");
		model.addColumn("Artist");
		model.addColumn("Date");
		model.addColumn("Contact Info.");
		
		for (int i=0; i< songManager.size(); i++) {
			Vector row = new Vector();
			SongInput si = songManager.get(i);
			row.add(si.getName());
			row.add(si.getArtist());
			row.add(si.getDate());
			row.add(si.getAlbum());
			model.addRow(row);
		}
		
		JTable table = new JTable(model);
		JScrollPane sp = new JScrollPane(table);

		this.add(sp);	
	}
}
