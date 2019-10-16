package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.Arrays;


// REPRESENTS A DATABASE

public class Operations {
	int nextUID = loadNextUID();
	int nextBID = loadNextBID();
	
	String defaultPathMembers = "db/members/";
	
	String defaultPathBoats = "db/boats/";
	
	public int getNextUID() {
		return nextUID;
	}
	
	private void increaseNextUID() {
		nextUID++;
	}
	
	private void increaseNextBID() {
		nextBID++;
	}
	
	public void saveNextUID() {
		File maxID = new File("db/members/max.id");
		try {
			BufferedWriter writer;
//			System.out.println(saveFile.getPath());
			writer = new BufferedWriter(new FileWriter(maxID.getPath()));
			String toFile = String.valueOf(nextUID);
			writer.write(toFile);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int loadNextUID() {
		File maxID = new File("db/members/max.id");
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
	
	public int loadNextBID() {
		File maxID = new File("db/boats/max.id");
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
	
	
	public void saveNextBID() {
		File maxID = new File("db/boats/max.id");
		try {
			BufferedWriter writer;
			writer = new BufferedWriter(new FileWriter(maxID.getPath()));
			String toFile = String.valueOf(nextBID);
			writer.write(toFile);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public int getNextBID(){
		return nextBID;
	}
	
	public void AddMemberFile(String [] mbr) {
		File saveFile = new File(defaultPathMembers + mbr[0]+ ".mem");
		
		try {
			BufferedWriter writer;
//			System.out.println(saveFile.getPath());
			writer = new BufferedWriter(new FileWriter(saveFile.getPath()));
			String toFile = mbr[0] + "," + mbr [1] + "," + mbr[2];
			writer.write(toFile);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.increaseNextUID();
		this.saveNextUID();
	}
	public void RemoveMemberFile(int mUID) {
		File toDelete = new File(defaultPathMembers + String.valueOf(mUID) + ".mem");
		try {
			toDelete.delete();		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void RemoveBoatFile (int bUID) {
		File toDelete = new File(defaultPathBoats + String.valueOf(bUID) + ".boat");
		try {
			toDelete.delete();		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void AddBoatFile(String[] boat) {
		File saveFile = new File(defaultPathBoats + boat[0]+ ".boat");
		
		try {
			BufferedWriter writer;
			writer = new BufferedWriter(new FileWriter(saveFile.getPath()));
			String toFile = boat[0] + "," + boat [1] + "," + boat[2] + "," + boat[3];
			writer.write(toFile);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		increaseNextBID();
		saveNextBID();
	}
	
	public String[][] getMembers() {
		String [][] toReturn = new String[0][3];
		for (String filename : getMemFiles()) {
			toReturn = Arrays.copyOf(toReturn, toReturn.length + 1);
			toReturn[toReturn.length - 1] = readMemberFile(filename);
		}
		return toReturn;
	}
	
	private String[] getMemFiles() {
		File dir = new File(defaultPathMembers);
		String[] toReturn = new String[0];
		String[] total = dir.list();
		for (String f : total) {
			if(f.contains(".mem")) {
				toReturn = Arrays.copyOf(toReturn, toReturn.length + 1);
				toReturn[toReturn.length - 1] = f;
			}
		
		}
		return toReturn;
	}
	
	public String[] getBoatFiles() {
		
		File dir = new File(defaultPathBoats);
		String[] toReturn = new String[0];
		String[] total = dir.list();
		for (String f : total) {
			if(f.contains(".boat")) {
				toReturn = Arrays.copyOf(toReturn, toReturn.length + 1);
				toReturn[toReturn.length - 1] = f;
			}
		
		}
		return toReturn;
		
	}
	
	public String[] readBoatFile(String filename) {
		File file = new File("db/boats/"+filename);
		String data = "";
		Scanner sc;
		try {
			sc = new Scanner(file);
			
			data = sc.nextLine(); //take the data from file
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String[] toReturn = data.split(",");
		return toReturn;
		}
	
	public String[] readMemberFile(String filename){
	File file = new File("db/members/"+filename);
	String data = "";
	Scanner sc;
	try {
		sc = new Scanner(file);
		data = sc.nextLine(); //take the data from file
		sc.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	
	String[] toReturn = data.split(",");
	
	return toReturn;
	}

	public String[][] getBoats() {
		String [][] toReturn = new String[0][4];
		for (String filename : getBoatFiles()) {
			toReturn = Arrays.copyOf(toReturn, toReturn.length + 1);
			toReturn[toReturn.length - 1] = readBoatFile(filename);
		}
		return toReturn;
	}
}