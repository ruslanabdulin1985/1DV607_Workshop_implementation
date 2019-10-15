package Model;

import java.util.ArrayList;
import java.util.Arrays;

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
	private void fillMembersList(String [][] memberListArr) {
		for (String[] memarr : memberListArr) {
			Member newMember = new Member(Integer.valueOf(memarr[0]), memarr[1], memarr[2]);
			this.memberList.add(newMember);
		}
	}
	// take the data from database and put it in memory
	private void fillBoatsList(String [][] boatsListArr) {
		for (String[] boatarr : boatsListArr) {
			System.out.print(Arrays.toString(boatsListArr));
			Boat bt = new Boat(Integer.valueOf(boatarr[0]), boatarr[1], Integer.valueOf(boatarr[2]), getMemberById(Integer.valueOf(boatarr[3])));
			this.boatList.add(bt);
		}
	}
	
	// fill both member and boat lists
	private void fillLists() {
		this.fillMembersList(db.getMembers());
		this.fillBoatsList(db.getBoats());
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
	
	// Array List representation of Member List
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
		System.out.print(boatList.size());
		for (Model.Boat n : boatList) {
			String[] arr = {String.valueOf(n.getBID()), n.getType(), String.valueOf(n.getLength()), String.valueOf(n.getOwner().getUID())};
			toReturn.add(arr);
		}
		
		return toReturn;
	}
	
	public String[][] getBoatsByUID(int mUID) {
		Member mem = getMemberById(mUID);
		ArrayList<Boat> userSBoats = new ArrayList<Boat>();
		for (int i=0; i<boatList.size(); i++) {
			if (boatList.get(i).getOwner().equals(mem)) {
				userSBoats.add(boatList.get(i));
			}
		}
		String [][] toReturn = new String[userSBoats.size()][3];
		for (int i=0; i<userSBoats.size(); i++)
			toReturn[i] = userSBoats.get(i).toArr();
		
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
	
	public void deleteMember(int mUID) {
		for (int i=0; i<this.memberList.size(); i++) {
			if (this.memberList.get(i).getUID() == mUID) {
				// First Delete all the connected boats
				ArrayList<Boat> boatsToDelete = new ArrayList<Boat>();
				for(Boat b : this.boatList) {
					if (b.getOwner().equals(memberList.get(i)))
						boatsToDelete.add(boatList.get(i));
				}
				
				for (Boat b: boatsToDelete)
					deleteBoat(b.getBID());
				// Second - remove the member
				db.RemoveMemberFile(memberList.get(i).getUID());
				this.memberList.remove(i);
			}
		}
	}

	public void deleteBoat(int bUID) {
		for (int i=0; i<this.boatList.size(); i++) {
			if (this.boatList.get(i).getBID() == bUID) {
				db.RemoveBoatFile(boatList.get(i).getBID());  // remove file
				this.boatList.remove(i); // Delete 
			}
		}
	}
	
}
