package github;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import gui.WindowFrame;
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
		
		WindowFrame frame = new WindowFrame(songManager);
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
	public static void putObject(SongManager songManager, String filename) {

		try {
			FileOutputStream file = new FileOutputStream(filename);
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
