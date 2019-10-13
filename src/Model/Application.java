package Model;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Map;

public class Application {
	ArrayList<Member> memberList; // List of Members
	ArrayList<Model.Boat> boatList;// List of Boats
	Database.Operations db;// Connection to database
	String status;  //current status
	
	public Application() {
		
		memberList = new ArrayList<Member>(); // List of Members
		boatList = new ArrayList<Model.Boat>(); // List of Boats
		db = new Database.Operations(); // Conection to database
		
		this.fillLists();  // download data from database
		status = "mainMenu"; // set default status
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String stts) {
		status = stts;
	}
	
	// take the data from database and put it in memory
	private void fillLists() {
		String[] filenames = db.getMemFiles();
		
		// fill members
		for (String filename : filenames) {
			String[] strArrMem = db.readMemberFile(filename);
			Member mbr = new Member(Integer.valueOf(strArrMem[0]), strArrMem[1], strArrMem[2]);
			this.memberList.add(mbr);
		}
		
		// fill boats
		filenames = db.getBoatFiles();
		for (String filename : filenames) {
			String[] strArrBoat = db.readBoatFile(filename);
			Boat bt = new Boat(Integer.valueOf(strArrBoat[0]), strArrBoat[1], Integer.valueOf(strArrBoat[2]), getMemberById(Integer.valueOf(strArrBoat[3]) ));
			boatList.add(bt);
		}
	}
	
	public ArrayList<Member> getMemberList(){
		return memberList;
	}
	
	// Add a boat to the list of boats
	public void addBoat(String bType, int bLength, int mUID) {
		int bUID = db.getNextBID();
		Model.Boat bt = new Boat(bUID, bType, bLength,  getMemberById(mUID));
		boatList.add(bt);
		db.AddBoatFile(bt.toArr()); //add to database
	}
	
	//Change a Boat by Bot ID
	public void changeBoat(String BID, String[] arr) {
		arr[0] = BID;
		Boat bt = new Boat(Integer.valueOf(arr[0]), arr[1], Integer.valueOf(arr[2]), getMemberById(Integer.valueOf(arr[3]) ));
		for (int i=0; i<boatList.size(); i++)
			if (Integer.valueOf(BID) == boatList.get(i).getBID())
				boatList.set(i, bt);
		db.AddBoatFile(bt.toArr()); // save changes to database
	}
	
	//Add a member to the list of member
	public void addMember(String[] arr) {
		arr[0] = String.valueOf(db.getNextUID());
		Member mbr = new Member(Integer.valueOf(arr[0]), arr[1], arr[2]);
		memberList.add(mbr);
		db.AddMemberFile(mbr.toArr()); //add to database
	}
	
	//Change a member by ID
	public void changeMember(String UID, String[] arr) {
		arr[0] = UID;
		Member mbr =  new Member(Integer.valueOf(arr[0]), arr[1], arr[2]);
		
		for (int i=0; i<memberList.size(); i++)
			if (Integer.valueOf(UID) == memberList.get(i).getUID())
				memberList.set(i, mbr);
		db.AddMemberFile(mbr.toArr());  // save changes to database
	}
	
	// Array List represenatation of Member List
	public ArrayList<String[]>getMembersAsStringArrays(){
		
		ArrayList<String[]> toReturn = new ArrayList<String[]>();
		
		for (Member n : memberList) {
			String[] arr = {String.valueOf(n.getUID()), n.getName(), n.getPersonalNumber()};
			toReturn.add(arr);
		}
		
		return toReturn;
	}
	
	// Array List representation of Boats
	public ArrayList<String[]>getBoatsAsStringArrays(){
		
		ArrayList<String[]> toReturn = new ArrayList<String[]>();
		
		for (Model.Boat n : boatList) {
			String[] arr = {String.valueOf(n.getBID()), n.getType(), String.valueOf(n.getLength()), String.valueOf(n.getOwner().getUID())};
			toReturn.add(arr);
		}
		
		return toReturn;
	}
	
	// fobiden!!!!
	public String getBoatsByUID(String UID) {
		String toReturn = "\n";
		
		for (int i=0; i<memberList.size(); i++) {
			if (String.valueOf(memberList.get(i).getUID()).equals(UID)) {
				for (int j=0; j<boatList.size(); j++) {
					if (String.valueOf(boatList.get(j).getOwner().getUID()).equals(UID))
						toReturn = toReturn + "\t" + String.valueOf(boatList.get(j).getBID()) + " - " + boatList.get(j).getType() + " - " + String.valueOf(boatList.get(j).getLength() + "\n");
				}
			}
		}
		
		return toReturn;	
	}
	
	
	public Member getMemberById(int mid) {
		Member toReturn = null;
		for (Member member : memberList) {
			if (member.getUID() == mid) 
				return member;
		}
		return toReturn;
	}
	
	public String[] getMemberArrById(int mid) {
		String[] toReturn = null;
		for (Member member : memberList) {
			if (member.getUID() == mid) 
				return member.toArr();
		}
		return toReturn;
	}
		
	public String[] getBoatById(String id) {
		String[] toReturn = null;
		int BID = Integer.valueOf(id);
		for (Model.Boat bt : boatList) {
			if (bt.getBID() == BID) 
				return bt.toArr();
		}
		
		return toReturn;
	}

	public void exit() {
		Runtime.getRuntime().exit(10);
	}
	
	
}
