package Database;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Operations {
	
	public ArrayList<Members.Member> getMemberList() {
		return null;
	}
	
	public void AddMemberFile(String mbr[]) {
		File saveFile = new File("db/members/" + mbr[0]+ ".mem");
		
		try {
			BufferedWriter writer;
			System.out.println(saveFile.getPath());
			writer = new BufferedWriter(new FileWriter(saveFile.getPath()));
			String toFile = mbr[0] + "," + mbr [1] + "," + mbr[2];
			writer.write(toFile);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String[] getMemFiles() {
	File dir = new File("db/members/");
	return dir.list();
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