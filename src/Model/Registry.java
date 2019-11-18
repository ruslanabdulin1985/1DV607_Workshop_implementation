package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Registry{
	
	// Class to work with filesystem
	
	private final String dataBaseBoatsPath = "db/boats/";
	private final String dataBaseMembersPath = "db/members/";
	private int nextUID = loadNextUID();
	private int nextBID = loadNextBID();

	public int getNextBID(){
		return nextBID;
	}
	
	public int getNextUID(){
		return nextUID;
	}
	
	public void saveNextUID() {
		saveNextID(dataBaseMembersPath+"max.id", nextUID);
	}
	
	public void saveNextBID() {
		saveNextID(dataBaseBoatsPath+"max.id", nextUID);
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
		return loadNextID(dataBaseMembersPath+"max.id");
	}
	
	public int loadNextBID() {
		return loadNextID(dataBaseBoatsPath+"max.id");	
	}
	
	
	public void RemoveBoatFile (Boat bt) {
		File toDelete = new File(dataBaseBoatsPath + String.valueOf(bt.getBID()) + ".boat");
		try {
			toDelete.delete();		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void AddBoatFile(Boat bt) {
		File saveFile = new File(dataBaseBoatsPath + bt.getBID()+ ".boat");
		
		try {
			BufferedWriter writer;
			writer = new BufferedWriter(new FileWriter(saveFile.getPath()));
			String toFile = bt.getBID() + "," + bt.getType() + "," + bt.getLength() + "," + bt.getOwner().getUID();
			writer.write(toFile);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		increaseNextBID();
		saveNextBID();
	}
	
	
	public void AddMemberFile(Member mbr) {
		File saveFile = new File(dataBaseMembersPath + mbr.getUID()+ ".mem");
		
		try {
			BufferedWriter writer;
			writer = new BufferedWriter(new FileWriter(saveFile.getPath()));
			String toFile = mbr.getUID() + "," + mbr.getName()+ "," + mbr.getPersonalNumber();
			writer.write(toFile);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.increaseNextUID();
		this.saveNextUID();
	}
	
	public void RemoveMemberFile(Member mbr) {
		File toDelete = new File(dataBaseMembersPath + String.valueOf(mbr.getUID()) + ".mem");
		try {
			toDelete.delete();		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MemberList getMembers() {
		MemberList toReturn = new MemberList();
		for (String filename : getFiles(dataBaseMembersPath)) {
			toReturn.add(readMemberFile(filename));
		}
		return toReturn;
	}
	
	private String[] readFile(String folder, String filename) {
		File file = new File(folder+filename);
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
	
	public Boat readBoatFile(String filename) {
		String[] strBoat = readFile(dataBaseBoatsPath, filename);
		Boat toReturn = new Boat(Integer.valueOf(strBoat[0]), BoatTypes.boatTypes.valueOf(strBoat[1]), Integer.valueOf(strBoat[2]), new Member(Integer.valueOf(strBoat[3]), "EMPTY", "EMPTY"));			
		return toReturn;
		}
	
	private String[] getFiles(String path) {
		File dir = new File(path);
		String[] toReturn = new String[0];
		String[] total = dir.list();
		for (String f : total) {
			if(f.contains(".mem") || f.contains(".boat")) {
				toReturn = Arrays.copyOf(toReturn, toReturn.length + 1);
				toReturn[toReturn.length - 1] = f;
			}
		}
		return toReturn;
	}
	
	
	public BoatList getBoats() {
		BoatList toReturn = new BoatList();
		for (String filename : getFiles(dataBaseBoatsPath)) {
			toReturn.add(readBoatFile(filename));
		}
		return toReturn;
	}
	
	public Member readMemberFile(String filename){	
		String[] strMember = readFile(dataBaseMembersPath, filename);
		Member toReturn = new Member(Integer.valueOf(strMember[0]), strMember[1], strMember[2]);
		return toReturn;
		}
	
}