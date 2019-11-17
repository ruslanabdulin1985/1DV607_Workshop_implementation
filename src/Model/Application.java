package Model;

import java.util.ArrayList;

public class Application {

	private MemberList memberList; // List of Members
	private BoatList boatList;// List of Boats
	private Registry db = new Model.Registry(); // Initializing a database

	
	public Application() {
		this.memberList = db.getMembers();
		this.boatList=db.getBoats();
		}
	
	public MemberList getMemberList() {
		return this.memberList;
	}
	
	public BoatList getBoaList() {
		return this.boatList;
	}
	
//	public String[] getBoatTypes() {
//		
//		String[] toReturn = new String[0]; 
//		for (BoatTypes.boatTypes type : BoatTypes.boatTypes.values()) { 
//			toReturn = new String[toReturn.length + 1];
//			type.toString();
//		}
//		return toReturn;
//	}
		
	
//	 Add a boat to the list of boats
	public void addBoat(String bType, int bLength, Member m) {	
		int bUID = db.getNextBID();
		Model.Boat bt = new Boat(bUID, bType, bLength,  m);
		boatList.add(bt);
		db.AddBoatFile(bt); //add to database
	}
	
	
	//Change a Boat by Boat ID
//	public void changeBoat(String BID, String[] arr) {
//		Boat bt = new Boat(Integer.valueOf(arr[0]), arr[1], Integer.valueOf(arr[2]), getMemberById(Integer.valueOf(arr[3]) ));
//		for (int i=0; i<boatList.size(); i++)
//			if (Integer.valueOf(BID) == boatList.get(i).getBID())
//				boatList.set(i, bt);
//		db.AddBoatFile(bt); // save changes to database
//	}
	
	//Add a member to the list of member
	public void addMember(String name, String personNumber) {
		Member mbr = new Member(db.getNextUID(), name, personNumber);
		memberList.add(mbr);
		db.AddMemberFile(mbr); //add to database
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
	
//	public String[][] getBoatsByUID(int mUID) {
//		Member mem = getMemberById(mUID);
//		ArrayList<Boat> userSBoats = new ArrayList<Boat>();
//		for (int i=0; i<boatList.size(); i++) {
//			if (boatList.get(i).getOwner().equals(mem)) {
//				userSBoats.add(boatList.get(i));
//			}
//		}
//		String [][] toReturn = new String[userSBoats.size()][3];
//		for (int i=0; i<userSBoats.size(); i++)
//			toReturn[i] = userSBoats.get(i).toArr();
//		
//		return toReturn;
//	}
	
//	private Member getMemberById(int mid) {
//		Member toReturn = null;
//		for (Member member : memberList) {
//			if (member.getUID() == mid) 
//				return member;
//		}
//		return toReturn;
//	}
	
//	public String[] getMemberArrById(int mid) {
//		String[] toReturn = null;
//		for (Member member : memberList) {
//			if (member.getUID() == mid) 
//				return member.toArr();
//		}
//		return toReturn;
//	}
	
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
//		boatsToDelete.add(boatList.get(i));
// deleteBoat(b.getBID());
		Member mem = this.memberList.getMemberById(mUID);
		db.RemoveMemberFile(mem);
		this.memberList.delete(mem);
	}

	public void deleteBoat(int bUID) {
		Boat bt = this.boatList.getBoatById(bUID);
		this.boatList.delete(bt);
		
	}

	public void editMember(int tmpID, String name, String personNum) {
		Member oldMem = this.memberList.getMemberById(tmpID);
		Member newMem = new Member(tmpID, name, personNum);
		this.memberList.edit(oldMem, newMem);
		db.AddMemberFile(newMem);	
	}
	
	public void editBoat(int tmpID, String type, int length) {
		Boat oldBoat = this.boatList.getBoatById(tmpID);
//		Boat newBoat = new Boat(tmpID, type, length);
//		this.memberList.edit(oldMem, newMem);
//		db.AddMemberFile(newMem);	
	}
	
	
}
