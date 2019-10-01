package App;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Map;

import Boats.Boat;
import Members.Member;

public class Application {
	ArrayList<Members.Member> memberList = new ArrayList<Members.Member>();
	ArrayList<Boats.Boat> boatList = new ArrayList<Boats.Boat>();
	
	Database.Operations db = new Database.Operations();
	String status = "mainMenu";
	
	public Application() {
		this.fillLists();
//        addMember(memberList.get(0));
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String stts) {
		status = stts;
	}
	
	public void fillLists() {
		String[] filenames = db.getMemFiles();
		for (String filename : filenames) {
			String[] strArrMem = db.readMemberFile(filename);
			Members.Member mbr = new Members.Member1(strArrMem);
			memberList.add(mbr);
		}
		
		filenames = db.getBoatFiles();
		for (String filename : filenames) {
			String[] strArrBoat = db.readBoatFile(filename);
			Boats.Boat bt = new Boats.Boat1(strArrBoat);
			boatList.add(bt);
			System.out.println(bt.toString());
		}
	}
	
	public ArrayList<Members.Member> getMemberList(){
		return memberList;
	}
	
	public void addBoat(String[] arr) {
		arr[0] = String.valueOf(db.getNextBID());
		Boats.Boat bt = new Boats.Boat1(arr);
		boatList.add(bt);
		db.AddBoatFile(bt.toArr());
	}
	
	public void addMember(String[] arr) {
		arr[0] = String.valueOf(db.getNextUID());
		Members.Member mbr = new Members.Member1(arr);
		memberList.add(mbr);
		db.AddMemberFile(mbr.toArr());
	}
	
	public ArrayList<String[]>getMembersAsStringArrays(){
		
		ArrayList<String[]> toReturn = new ArrayList<String[]>();
		
		for (Members.Member n : memberList) {
			String[] arr = {String.valueOf(n.getUID()), n.getName(), n.getPersonalNumber()};
			toReturn.add(arr);
		}
		
		return toReturn;
	}
	
	public ArrayList<String[]>getBoatsAsStringArrays(){
		
		ArrayList<String[]> toReturn = new ArrayList<String[]>();
		
		for (Boats.Boat n : boatList) {
			String[] arr = {String.valueOf(n.getBID()), n.getType(), String.valueOf(n.getLength()), String.valueOf(n.getUID())};
			toReturn.add(arr);
		}
		
		return toReturn;
	}
	
	public String getBoatsByUID(String UID) {
		String toReturn = "\n";
		
		for (int i=0; i<memberList.size(); i++) {
			if (String.valueOf(memberList.get(i).getUID()).equals(UID)) {
				for (int j=0; j<boatList.size(); j++) {
					if (String.valueOf(boatList.get(j).getUID()).equals(UID))
						toReturn = toReturn + "\t" + String.valueOf(boatList.get(j).getBID()) + " - " + boatList.get(j).getType() + " - " + String.valueOf(boatList.get(j).getLength() + "\n");
				}
			}
		}
		
		return toReturn;	
	}
	
	
	
	
	public String[] getMemberById(String id) {
		String[] toReturn = null;
		int uid = Integer.valueOf(id);
		for (Members.Member member : memberList) {
			if (member.getUID() == uid) 
				return member.toArr();
		}
		
		return toReturn;
	}
		
	
	
}
