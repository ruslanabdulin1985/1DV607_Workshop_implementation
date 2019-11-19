package Model;

import java.util.ArrayList;

import Model.BoatTypes.boatTypes;

public class Application {

	private MemberList memberList; // List of Members
	private BoatList boatList;// List of Boats
	private Registry db = new Model.Registry(); // Initializing a database

	
	public Application() {
		this.memberList = db.getMembers();
		this.boatList=db.getBoats();
		ConnectBoatsAndMembers();
		}
	
	private void ConnectBoatsAndMembers() {
		for (Boat b : this.boatList) {
			int ownerID = b.getOwner().getUID();
			Member owner = this.memberList.getMemberById(ownerID);
			b.setOwner(owner);
		}
	}
	
	public MemberList getCopyOfMemberList() {
		// 
		return this.memberList.clone();
	}

	MemberList getMemberList() { // This function reachable only within the package
		// in order to protect data from accidental change by controller or view
		return this.memberList;
	}
	
	public BoatList getCopyOfBoatList() {
		return this.boatList.clone();
	}
	
	BoatList getBoaList() {
		return this.boatList;
	}
		
	
//	 Add a boat to the list of boats
	public void addBoat(BoatTypes.boatTypes bType, int bLength, Member m) {	
		int bUID = db.getNextBID();

		Model.Boat bt = new Boat(bUID, bType, bLength,  m);
		boatList.add(bt);
		db.AddBoatFile(bt, true); //add to database
	}
	
	
	//Add a member to the list of member
	public void addMember(String name, String personNumber) {
		Member mbr = new Member(db.getNextUID(), name, personNumber);
		memberList.add(mbr);
		db.AddMemberFile(mbr, true); //add to database
	}
	
	// Array List representation of Boats
	public ArrayList<String[]>getBoatsAsStringArrays(){
		
		ArrayList<String[]> toReturn = new ArrayList<String[]>();
		
		for (Model.Boat n : boatList) {
			
			String[] arr = {String.valueOf(n.getBID()), String.valueOf(n.getType()), String.valueOf(n.getLength()), String.valueOf(n.getOwner().getUID())};
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
		Member mem = this.memberList.getMemberById(mUID);
		db.RemoveMemberFile(mem);
		this.memberList.delete(mem);
	}

	public void deleteBoat(int bUID) {
		Boat bt = this.boatList.getBoatById(bUID);
		db.RemoveBoatFile(bt);
		this.boatList.delete(bt);
	}

	public void editMember(int tmpID, String name, String personNum) {
		Member oldMem = this.memberList.getMemberById(tmpID);
		Member newMem = new Member(tmpID, name, personNum);
		this.memberList.edit(oldMem, newMem);
		db.AddMemberFile(newMem, false);	
	}
	
	public void editBoat(int tmpID, boatTypes type, int length, Member m) {
		Boat oldBoat = this.boatList.getBoatById(tmpID);
		Boat newBoat = new Boat(tmpID, type, length,  m);
		this.boatList.edit(oldBoat, newBoat);
		db.AddBoatFile(newBoat, false);	
	}

	public BoatList getAttachedBoats(Member mbr) {
		BoatList btls = new BoatList();
		for (Boat bt : this.boatList)
			if (bt.getOwner().equals(mbr))
				btls.add(bt);
		
		return btls;
	}	
}
