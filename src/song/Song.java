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
