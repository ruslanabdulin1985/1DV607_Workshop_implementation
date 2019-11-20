package Model;

public class Boat implements Comparable<Boat>{

	private BoatTypes.boatTypes type;
	private int length;
	private int BID;
	private Member owner;

	
	public Boat(int bBID, BoatTypes.boatTypes btype, int bLength, Member m) {
		BID = bBID;
		type = btype;
		length = bLength;
		owner = m;
	}
	
	public BoatTypes.boatTypes getType() {
		return type;
	}

	public void setType(BoatTypes.boatTypes typeToBeSet) {
		type = typeToBeSet;
	}

	public int getLength() {
		return length;
	}
	
	public String[] toArr() {
		String [] toReturn = {String.valueOf(BID), String.valueOf(type), String.valueOf(length), String.valueOf(owner.getUID())};
		return toReturn;
	}

	public String toString() {
		return String.valueOf(BID) + type + String.valueOf(length) + String.valueOf(owner.getUID());
	}	
	
	public int getBID() {
		return BID;
	}

	public Member getOwner() {
		return owner;
	}

	public void setOwner(Member o) {
		this.owner = o;
	}

	public int compareTo(Boat b) {
		return this.BID - b.getBID();
	}
}
