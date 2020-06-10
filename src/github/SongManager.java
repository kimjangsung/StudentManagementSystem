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

