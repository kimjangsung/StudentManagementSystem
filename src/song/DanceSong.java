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