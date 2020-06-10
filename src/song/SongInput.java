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
