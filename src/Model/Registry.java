package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.Arrays;

public class Registry{
	

	private int nextUID = loadNextUID();
	private int nextBID = loadNextBID();
	
	public int getNextBID(){
		return nextBID;
	}
	
	public int getNextUID(){
		return nextUID;
	}
	
	public void saveNextUID() {
		saveNextID("db/members/max.id", nextUID);
	}
	
	public void saveNextBID() {
		saveNextID("db/boats/max.id", nextUID);
	}
	
	private void saveNextID(String path, int nextID) {
		File maxID = new File(path);
		try {
			BufferedWriter writer;
			writer = new BufferedWriter(new FileWriter(maxID.getPath()));
			String toFile = String.valueOf(nextID);
			writer.write(toFile);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void increaseNextUID() {
		nextUID++;
	}
	
	private void increaseNextBID() {
		nextBID++;
	}
	
	private int loadNextID(String path) {
		File maxID = new File(path);
		Scanner sc;
		String data = "";
		int toReturn = 0;
		try {
			sc = new Scanner(maxID);
			data = sc.nextLine(); //take the data from file
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			toReturn = Integer.valueOf(data);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	
	public int loadNextUID() {
		return loadNextID("db/members/max.id");
	}
	
	public int loadNextBID() {
		return loadNextID("db/boats/max.id");	
	}
	
		

}