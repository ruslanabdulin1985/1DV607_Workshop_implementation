package Database;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Object;

public class Operations {
	
	String defaultPathMembers = "db/members/";
	
	String defaultPathBoats = "db/boats/";
	
	public ArrayList<Members.Member> getMemberList() {
		return null;
	}
	
	public int getNextUID(){
		File folder = new File(defaultPathMembers);
		String[] files = folder.list();
		Arrays.sort(files);
		String finID = files[files.length-1];
		//System.out.println(files.length);
		finID = finID.substring(0, finID.lastIndexOf('.'));
//		System.out.println(finID);
		return Integer.valueOf(finID)+1;
	}
	
	public int getNextBID(){
		File folder = new File(defaultPathBoats);
		String[] files = folder.list();
		Arrays.sort(files);
		String finID = files[files.length-1];
//		System.out.println(files.length);
		finID = finID.substring(0, finID.lastIndexOf('.'));
//		System.out.println(finID);
		return Integer.valueOf(finID)+1;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void AddBoatFile(String[] boat) {
		File saveFile = new File(defaultPathBoats + boat[0]+ ".boat");
		
		try {
			BufferedWriter writer;
//			System.out.println(saveFile.getPath());
			writer = new BufferedWriter(new FileWriter(saveFile.getPath()));
			String toFile = boat[0] + "," + boat [1] + "," + boat[2];
			writer.write(toFile);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String[] getMemFiles() {
	File dir = new File(defaultPathMembers);
	return dir.list();
	}
	
	public String[] getBoatFiles() {
		File dir = new File(defaultPathBoats);
		return dir.list();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] toReturn = data.split(",");
		return toReturn;
		}
	
	public String[] readMemberFile(String filename) {
	File file = new File("db/members/"+filename);
	String data = "";
	Scanner sc;
	try {
		sc = new Scanner(file);
		
		data = sc.nextLine(); //take the data from file
		sc.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	String[] toReturn = data.split(",");
	
	return toReturn;
	
	
	}
}