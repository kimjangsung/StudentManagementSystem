package exceptions;

public class DateFormatException extends Exception {

}

package github;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import log.EventLogger;

public class MenuManager {
	
	static EventLogger logger = new EventLogger("log.txt");
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		SongManager songManager = getObject("songmanager.ser");
		if (songManager == null) {
			songManager = new SongManager(input);
		}
		else {
			songManager.setScanner(input);
		}
		selectMenu(input, songManager );
		putObject(songManager, "songmanager.ser");
	}

	public static void selectMenu(Scanner input, SongManager songManager) {
		int num = -1;
		while(num !=5) {
			try {
				showMenu();
				num = input.nextInt();
				switch(num) {
				case 1:
					songManager.addsong();
					logger.log("add a song");
					break;
				case 2:
					songManager.deletesong();
					logger.log("delete a song");
					break;
				case 3:
					songManager.editsong();
					logger.log("edit a song");
					break;
				case 4:
					songManager.viewsongs();
					logger.log("view a list of songs");
					break;
				default:
					continue;
				}
			}
			catch(InputMismatchException e) {
				System.out.println("please put an integer between 1 and 5!");
				if(input.hasNext()) {
					input.next();
				}
				num = -1;
			}
		}
	}
	public static void showMenu() {
		System.out.println("*** Song Management System meny ***");
		System.out.println("  1. Add Song");
		System.out.println("  2. Delete Song");
		System.out.println("  3. Edit Song");
		System.out.println("  4. View Song");
		System.out.println("  5. Exit");
		System.out.println("Select one number between 1 - 5:");
	}
	public static SongManager getObject(String fileartist) {
		SongManager songManager = null;
		try {
			FileInputStream file = new FileInputStream(fileartist);
			ObjectInputStream in = new ObjectInputStream(file);
			songManager = (SongManager) in.readObject();
			in.close();
			file.close();
		} catch (FileNotFoundException e) {
			return songManager;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return songManager;
	}
	public static void putObject(SongManager songManager, String fileartist) {

		try {
			FileOutputStream file = new FileOutputStream(fileartist);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(songManager);
			out.close();
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package github;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import song.DanceSong;
import song.BalladeSong;
import song.SongInput;
import song.SongKind;
import song.HiphopSong;

public class SongManager implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2684755737374697795L;
	ArrayList<SongInput> songs = new ArrayList<SongInput>();
	transient Scanner input;
	SongManager(Scanner input){
		this.input = input;
	}
	
	public void setScanner(Scanner input) {
		this.input = input;
	}
	
	public void addsong() {
		int kind = 0;
		SongInput songInput;
		while(kind < 1 || kind > 3) {
			try {
				System.out.println("1 for Hiphop");
				System.out.println("2 for Ballade");
				System.out.println("3 for Dance");
				System.out.println("Select num 1, 2, or 3 for Song Kind : ");
				kind = input.nextInt();
				if (kind == 1) {
					songInput =new HiphopSong(SongKind.Hiphop);
					songInput.getUserInput(input);
					songs.add(songInput);
					break;
				}
				else if (kind ==2) {
					songInput =new BalladeSong(SongKind.Ballade);
					songInput.getUserInput(input);
					songs.add(songInput);
					break;

				}
				else if (kind ==3) {
					songInput =new DanceSong(SongKind.Dance);
					songInput.getUserInput(input);
					songs.add(songInput);
					break;

				}
				else {
					System.out.print("Select num for Song Kind between 1 and 2:");
				}
			}
			catch(InputMismatchException e) {
				System.out.println("please put an integer between 1 and 3!");
				if(input.hasNext()) {
					input.next();
				}
				kind = -1;
			}
		}
	}

	public void deletesong() {
		System.out.print("song Name:");
		String songName = input.next();
		int index = findIndex(songName);
		removefromSongs(index, songName);
	}
	public int findIndex(String songName) {
		int index = -1;
		for (int i = 0; i<songs.size(); i++) {
			if (songs.get(i).getName() == songName) {
				index = i;
				break;
			}
		}
		return index;
	}
	public int removefromSongs(int index, String songName) {
		if(index >= 0) {
			songs.remove(index);
			System.out.println("the song" + songName +" is deleted");
			return 1;
		}
		else {
			System.out.println("the song has not been registered");
			return -1;
		}
	}
	public void editsong() {
		System.out.print("song Name:");
		String songName = input.next();
		for (int i = 0; i<songs.size(); i++) {
			SongInput song = songs.get(i);
			if (song.getName() == songName) {
				int num = -1;
				while(num !=5) {
					showEditMenu();
					num = input.nextInt();
					switch(num) {
					case 1:
						song.setSongName(input);
						break;
					case 2:
						song.setSongArtist(input);
						break;
					case 3:
						song.setSongDate(input);
						break;
					case 4:
						song.setSongAlbum(input);
						break;
					default:
						continue;
					}
				}//while
				break;
			}//if
		}//for
	}
	public void viewsongs() {
		System.out.println("# of registerd songs:"+ songs.size());
		for (int i = 0; i<songs.size(); i++) {
			songs.get(i).printInfo();
		}
	}


	public void showEditMenu() {
		System.out.println("** song Info Edit Menu **");
		System.out.println("  1. Edit Name");
		System.out.println("  2. Edit Artist");
		System.out.println("  3. Edit Date");
		System.out.println("  4. Edit Album");
		System.out.println("  5. Exit");
		System.out.println("Select one number between 1 - 5:");
	}
}

package gui;

public class GuiTest {
	public static void main(String[] args) {

		WindowFrame frame = new WindowFrame();
		
	}
}

package gui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import listeners.ButtonAddListener;
import listeners.ButtonViewListener;

public class MenuSelection extends JPanel {

	WindowFrame frame;
	
	public MenuSelection(WindowFrame frame) {
		
		this.frame = frame;
		this.setLayout(new BorderLayout());
		
		JPanel panel1 = new JPanel();		
		JPanel panel2 = new JPanel();
		JLabel label = new JLabel("Menu Selection");
		JButton button1 = new JButton("Add Song");
		JButton button2 = new JButton("View Song");
		JButton button3 = new JButton("Edit Song");
		JButton button4 = new JButton("Delete Song");
		JButton button5 = new JButton("Exit Program");

		button1.addActionListener(new ButtonAddListener(frame));
		button2.addActionListener(new ButtonViewListener(frame));
		
		panel1.add(label);
		panel2.add(button1);
		panel2.add(button2);
		panel2.add(button3);
		panel2.add(button4);
		panel2.add(button5);

		this.add(panel1, BorderLayout.NORTH);
		this.add(panel2, BorderLayout.CENTER);
	}
}

package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class SongAdder extends JPanel {

	WindowFrame frame;
	
	public SongAdder(WindowFrame frame) {
		this.frame = frame;
		
		JPanel panel = new JPanel();
		panel.setLayout(new SpringLayout());
		
		JLabel labelID = new JLabel("Name: ", JLabel.TRAILING);
		
		JTextField fieldID = new JTextField(10);
		
		labelID.setLabelFor(fieldID);
		panel.add(labelID);
		panel.add(fieldID);
		
		JLabel labelName = new JLabel("Artist: ", JLabel.TRAILING);
		
		JTextField fieldName = new JTextField(10);
		
		labelName.setLabelFor(fieldName);
		panel.add(labelName);
		panel.add(fieldName);
		
		JLabel labelEmail = new JLabel("Date: ", JLabel.TRAILING);
		
		JTextField fieldEmail = new JTextField(10);
		
		labelEmail.setLabelFor(fieldEmail);
		panel.add(labelEmail);
		panel.add(fieldEmail);
		
		JLabel labelPhone = new JLabel("Album: ", JLabel.TRAILING);
		JTextField fieldPhone = new JTextField(10);
		
		labelPhone.setLabelFor(fieldPhone);
		panel.add(labelPhone);
		panel.add(fieldPhone);
		panel.add(new JButton("save"));
		panel.add(new JButton("cancel"));

		SpringUtilities.makeCompactGrid(panel, 5, 2, 6, 6, 6, 6);

		this.add(panel);
		this.setVisible(true);

	}
}

package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class SongViewer extends JPanel {
	
	WindowFrame frame;
	
	public SongViewer(WindowFrame frame) {
		this.frame = frame;
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Name");
		model.addColumn("Artist");
		model.addColumn("Date");
		model.addColumn("Contact Info.");
		
		JTable table = new JTable(model);
		JScrollPane sp = new JScrollPane(table);

		this.add(sp);	
	}
}

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

package gui;

import javax.swing.*;

import javax.swing.SpringLayout;

import java.awt.*;


/**
 * A 1.4 file that provides utility methods for
 * creating form- or grid-style layouts with SpringLayout.
 * These utilities are used by several programs, such as
 * SpringBox and SpringCompactGrid.
 */

public class SpringUtilities {

    /**
     * A debugging utility that prints to stdout the component's
     * minimum, preferred, and maximum sizes.
     */

    public static void printSizes(Component c) {
        System.out.println("minimumSize = " + c.getMinimumSize());
        System.out.println("preferredSize = " + c.getPreferredSize());
        System.out.println("maximumSize = " + c.getMaximumSize());
    }
    
    /**
     * Aligns the first <code>rows</code> * <code>cols</code>
     * components of <code>parent</code> in
     * a grid. Each component is as big as the maximum
     * preferred width and height of the components.
     * The parent is made just big enough to fit them all.
     *
     * @param rows number of rows
     * @param cols number of columns
     * @param initialX x location to start the grid at
     * @param initialY y location to start the grid at
     * @param xPad x padding between cells
     * @param yPad y padding between cells
     */

    public static void makeGrid(Container parent,
                                int rows, int cols,
                                int initialX, int initialY,
                                int xPad, int yPad) {

        SpringLayout layout;
        try {
            layout = (SpringLayout)parent.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }

        Spring xPadSpring = Spring.constant(xPad);
        Spring yPadSpring = Spring.constant(yPad);
        Spring initialXSpring = Spring.constant(initialX);
        Spring initialYSpring = Spring.constant(initialY);
        int max = rows * cols;

        //Calculate Springs that are the max of the width/height so that all
        //cells have the same size.

        Spring maxWidthSpring = layout.getConstraints(parent.getComponent(0)).
                                    getWidth();
        Spring maxHeightSpring = layout.getConstraints(parent.getComponent(0)).
                                    getHeight();

        for (int i = 1; i < max; i++) {
            SpringLayout.Constraints cons = layout.getConstraints(
                                            parent.getComponent(i));

            maxWidthSpring = Spring.max(maxWidthSpring, cons.getWidth());
            maxHeightSpring = Spring.max(maxHeightSpring, cons.getHeight());

        }

        //Apply the new width/height Spring. This forces all the
        //components to have the same size.

        for (int i = 0; i < max; i++) {
            SpringLayout.Constraints cons = layout.getConstraints(
                                            parent.getComponent(i));
            cons.setWidth(maxWidthSpring);
            cons.setHeight(maxHeightSpring);
        }

        //Then adjust the x/y constraints of all the cells so that they
        //are aligned in a grid.

        SpringLayout.Constraints lastCons = null;
        SpringLayout.Constraints lastRowCons = null;

        for (int i = 0; i < max; i++) {
            SpringLayout.Constraints cons = layout.getConstraints(
                                                 parent.getComponent(i));

            if (i % cols == 0) { //start of new row
                lastRowCons = lastCons;
                cons.setX(initialXSpring);

            } else { //x position depends on previous component
                cons.setX(Spring.sum(lastCons.getConstraint(SpringLayout.EAST),
                                     xPadSpring));

            }
            
            if (i / cols == 0) { //first row
                cons.setY(initialYSpring);

            } else { //y position depends on previous row
                cons.setY(Spring.sum(lastRowCons.getConstraint(SpringLayout.SOUTH),
                                     yPadSpring));

            }
            
            lastCons = cons;
            
        }

        //Set the parent's size.

        SpringLayout.Constraints pCons = layout.getConstraints(parent);
        pCons.setConstraint(SpringLayout.SOUTH,
                            Spring.sum(
                                Spring.constant(yPad),
                                lastCons.getConstraint(SpringLayout.SOUTH)));
        
        pCons.setConstraint(SpringLayout.EAST,
                            Spring.sum(
                                Spring.constant(xPad),
                                lastCons.getConstraint(SpringLayout.EAST)));

    }

    /* Used by makeCompactGrid. */

    private static SpringLayout.Constraints getConstraintsForCell(
                                                int row, int col,
                                                Container parent,
                                                int cols) {
        SpringLayout layout = (SpringLayout) parent.getLayout();
        Component c = parent.getComponent(row * cols + col);
        return layout.getConstraints(c);

    }

    /**
     * Aligns the first <code>rows</code> * <code>cols</code>
     * components of <code>parent</code> in
     * a grid. Each component in a column is as wide as the maximum
     * preferred width of the components in that column;
     * height is similarly determined for each row.
     * The parent is made just big enough to fit them all.
     *
     * @param rows number of rows
     * @param cols number of columns
     * @param initialX x location to start the grid at
     * @param initialY y location to start the grid at
     * @param xPad x padding between cells
     * @param yPad y padding between cells
     */

    public static void makeCompactGrid(Container parent,
                                       int rows, int cols,
                                       int initialX, int initialY,
                                       int xPad, int yPad) {
        SpringLayout layout;
        
        try {

            layout = (SpringLayout)parent.getLayout();

        } catch (ClassCastException exc) {

            System.err.println("The first argument to makeCompactGrid must use SpringLayout.");

            return;

        }

        //Align all cells in each column and make them the same width.

        Spring x = Spring.constant(initialX);
        for (int c = 0; c < cols; c++) {
            Spring width = Spring.constant(0);
            
            for (int r = 0; r < rows; r++) {

                width = Spring.max(width,
                                   getConstraintsForCell(r, c, parent, cols).
                                       getWidth());

            }

            for (int r = 0; r < rows; r++) {

                SpringLayout.Constraints constraints =
                        getConstraintsForCell(r, c, parent, cols);
                constraints.setX(x);
                constraints.setWidth(width);

            }

            x = Spring.sum(x, Spring.sum(width, Spring.constant(xPad)));

        }

        //Align all cells in each row and make them the same height.

        Spring y = Spring.constant(initialY);

        for (int r = 0; r < rows; r++) {

            Spring height = Spring.constant(0);

            for (int c = 0; c < cols; c++) {

                height = Spring.max(height,
                                    getConstraintsForCell(r, c, parent, cols).
                                        getHeight());

            }

            for (int c = 0; c < cols; c++) {

                SpringLayout.Constraints constraints =
                        getConstraintsForCell(r, c, parent, cols);
                constraints.setY(y);
                constraints.setHeight(height);

            }

            y = Spring.sum(y, Spring.sum(height, Spring.constant(yPad)));

        }

        //Set the parent's size.

        SpringLayout.Constraints pCons = layout.getConstraints(parent);
        pCons.setConstraint(SpringLayout.SOUTH, y);
        pCons.setConstraint(SpringLayout.EAST, x);

    }

}

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

package listeners;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import gui.SongAdder;
import gui.SongViewer;
import gui.WindowFrame;

public class ButtonAddListener implements ActionListener {

	WindowFrame frame;

	public ButtonAddListener(WindowFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		SongAdder adder = frame.getSongadder();
		frame.setupPanel(adder);
	}

}

package listeners;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import gui.SongViewer;
import gui.WindowFrame;

public class ButtonViewListener implements ActionListener {

	WindowFrame frame;

	public ButtonViewListener(WindowFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		SongViewer viewer = frame.getSongviewer();
		frame.setupPanel(viewer);
	}

}

package log;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class EventLogger {
	FileHandler filehandler;
	LogManager logmanager;
	Logger logger;

	public EventLogger(String fileName){
		try {
			logmanager = LogManager.getLogManager();
			logger = logmanager.getLogger(Logger.GLOBAL_LOGGER_NAME);
			filehandler = new FileHandler(fileName);
			filehandler.setFormatter(new SimpleFormatter());
			logger.addHandler(filehandler);
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void log(String logMessage) {
		logger.info(logMessage);
	}
}

package log;

public class LoggerTester {

	public static void main(String[] args) {
		EventLogger logger = new EventLogger("log.txt");
		logger.log("test");
	}
}

package song;

import java.util.Scanner;

public class BalladeSong extends Song{
	
	public BalladeSong(SongKind kind) {
		super(kind);
	}
	
	public void getUserInput(Scanner input) {
		setSongName(input);
		setSongArtist(input);
		setSongDate(input);
		setSongAlbum(input);
	}
	
	public void printInfo() {
		String skind = getKindString();
		System.out.println("kind : "+skind+" artist : " + artist + "  name : " + name + "  date : " + date + "  album : " + album);
	}
}

package song;

import java.util.Scanner;

import exceptions.DateFormatException;

public class DanceSong extends Song{

	public DanceSong(SongKind kind) {
		super(kind);
	}

	public void getUserInput(Scanner input) {
		setSongName(input);
		setSongArtist(input);
		setSongDate(input);
		setSongAlbum(input);
	}
	
	public void printInfo() {
		String skind = getKindString();
		System.out.println("kind : "+skind+" artist : " + artist + "  name : " + name + "  date : " + date + "  album : " + album);
	}
}

package song;

import java.util.Scanner;

public class HiphopSong extends Song{
	
	public HiphopSong(SongKind kind) {
		super(kind);
	}
	
	public void getUserInput(Scanner input) {
		setSongName(input);
		setSongArtist(input);
		setSongDate(input);
		setSongAlbum(input);
	}
	
	public void printInfo() {
		String skind = getKindString();
		System.out.println("kind : "+skind+" artist : " + artist + "  name : " + name + "  date : " + date + "  album : " + album);
	}
}

package song;

import java.io.Serializable;
import java.util.Scanner;

import exceptions.DateFormatException;

public abstract class Song implements SongInput, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8574706114587752217L;
	protected SongKind kind = SongKind.Hiphop;
	protected String artist;
	protected String name;
	protected String date;
	protected String album;

	public Song() {
	}
	public Song(SongKind kind) {
		this.kind = kind;
	}
	public Song(String artist, String name) {
		this.artist = artist;
		this.name = name;
	}

	public Song(String artist, String name, String date, String album) {
		this.artist = artist;
		this.name = name;
		this.date = date;
		this.album = album;
	}
	public Song(SongKind kind, String artist, String name, String date, String album) {
		this.kind = kind;
		this.artist = artist;
		this.name = name;
		this.date = date;
		this.album = album;
	}
	public SongKind getKind() {
		return kind;
	}
	public void setKind(SongKind kind) {
		this.kind = kind;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) throws DateFormatException {
		if(!date.contains(".") && !date.equals("")) {
			throw new DateFormatException();
		}
		this.date = date;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}

	public abstract void printInfo();

	public void setSongName(Scanner input) {
		System.out.print("song Name:");
		String name = input.next();
		this.setName(name);
	}
	public void setSongArtist(Scanner input) {
		System.out.print("song artist:");
		String artist = input.next();
		this.setArtist(artist);
	}
	public void setSongDate(Scanner input) {
		String date = "";
		while (!date.contains(".")) {
			System.out.print("Song release date:");
			date = input.next();
			try {
				this.setDate(date);
			} catch (DateFormatException e) {
				System.out.println("Incorrect Date Format, put the release date that contains .");
			}
		}
	}
	public void setSongAlbum(Scanner input) {
		System.out.print("Album collection:");
		String album = input.next();
		this.setAlbum(album);
	}
	public String getKindString() {
		String skind = "none";
		switch(this.kind) {
		case Hiphop :
			skind = "Hip.";
			break;
		case Ballade : 
			skind = "Bal.";
			break;
		case Dance :
			skind = "Dan.";
			break;
		default:		
		}
		return skind;
	}
}

package song;

import java.util.Scanner;

import exceptions.DateFormatException;

public interface SongInput {

	public String getName();
	public void setName(String name);
	public void setArtist(String artist);
	public void setDate(String date) throws DateFormatException;
	public void setAlbum(String album);
	public void getUserInput(Scanner input);
	public void printInfo();
	public void setSongName(Scanner input);
	public void setSongArtist(Scanner input);
	public void setSongDate(Scanner input);
	public void setSongAlbum(Scanner input);
}

package song;

public enum SongKind {
	Hiphop,
	Ballade,
	Dance,
}
