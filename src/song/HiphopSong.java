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
