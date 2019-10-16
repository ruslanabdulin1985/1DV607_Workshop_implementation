package Model;

public class Boat{

	private String type;
	private int length;
	private int BID;
	private Member owner;

	public Boat() {
	type = "";
	length =0;
	BID = 0;
	owner = new Member();
	}
	
	public Boat(int bBID, String bType, int bLength, Member m) {
		BID = bBID;
		type = bType;
		length = bLength;
		owner = m;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String typeToBeSet) {
		type = typeToBeSet;
	}

	public int getLength() {
		return length;
	}
	
	public String[] toArr() {
		String [] toReturn = {String.valueOf(BID), type, String.valueOf(length), String.valueOf(owner.getUID())};
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
}
