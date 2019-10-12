package Members;

public class Member1 implements Member {

	
	private String name;
	private String personalNumber;
	private int UID;
	
	public Member1() {
		name = "";
		UID = 0;
		personalNumber = "";
	}
	
	public Member1(String[] arr) {
		UID = Integer.valueOf(arr[0]);
		name = arr[1];
		personalNumber = arr[2];
	}
	
	public Member1(String memberName, String memberPersonalNumber) {
		name = memberName;
		UID = 0;
		personalNumber = memberPersonalNumber;
	}
	
	
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	
	public void setName(String nameToBeSet) {
		name = nameToBeSet;
		
	}

	
	public String getPersonalNumber() {
		// TODO Auto-generated method stub
		return personalNumber;
	}

	
	public void setPersonalNumber(String persunNumber) {
		// TODO Auto-generated method stub
		personalNumber = persunNumber;
	}

	
	public int getUID() {
		// TODO Auto-generated method stub
		return UID;
	}
	
	public String toString() {
		String toReturn = "/n";
		toReturn = toReturn + "UID: " + String.valueOf(UID) + "\n";
		toReturn = toReturn + "name: " + name + "\n";
		toReturn = toReturn + "personal number: " + personalNumber + "\n";
		
		return name;
	}
	
	public String[] toArr() {
		
		String [] arr = {String.valueOf(UID), name, personalNumber};
		
		return arr;
		
	}
	
	
	
}
