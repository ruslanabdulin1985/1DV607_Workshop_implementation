package Model;

public class Boat{

	String type = "";
	int length =0;
	int UID =0;
	int BID = 0;
	Member owner = new Member();
	
	public Boat(String[] arr) {
		UID = Integer.valueOf(arr[3]);
		type = arr[1];
		length =Integer.valueOf(arr[2]);
		BID = Integer.valueOf(arr[0]);
	}
	
	
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}


	public void setType(String typeToBeSet) {
		// TODO Auto-generated method stub
		type = typeToBeSet;
	}

	public int getLength() {
		// TODO Auto-generated method stub
		return length;
	}

	
	public void setLength(String persunNumber) {
		// TODO Auto-generated method stub
		
	}

	
	public int getUID() {
		// TODO Auto-generated method stub
		return UID;
	}
	
	
	public String[] toArr() {
		// TODO Auto-generated method stub
		String [] toReturn = {String.valueOf(BID), type, String.valueOf(length), String.valueOf(UID)};
		return toReturn;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(BID) + type + String.valueOf(length) + String.valueOf(UID);
	
	}
	
	public void setUID(int userID) {
		// TODO Auto-generated method stub
		UID = userID;
	}
	
	
	public int getBID() {
		// TODO Auto-generated method stub
		return BID;
	}
	
}
