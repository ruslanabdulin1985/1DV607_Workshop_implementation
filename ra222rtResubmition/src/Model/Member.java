package Model;

public class Member implements Comparable<Member>{

	
	private String name;
	private String personalNumber;
	private int UID;
	
	public Member() {
		name = "";
		UID = 0;
		personalNumber = "";
	}
	
	public Member(int mUID, String mName, String mPersonNum ) {
		UID = mUID;
		name = mName;
		personalNumber = mPersonNum;
	}
	
	public String getName() {
		return name;
	}
	
	// package only
	void setName(String nameToBeSet) {
		name = nameToBeSet;
	}
	
	public String getPersonalNumber() {
		return personalNumber;
	}
	
	public void setPersonalNumber(String persunNumber) {
		personalNumber = persunNumber;
	}
	
	public int getUID() {
		return UID;
	}
	
	
	public String[] toArr() {
		String [] arr = {String.valueOf(UID), name, personalNumber};
		return arr;
	}

	@Override
	public int compareTo(Member m) {
		return this.getUID()-(m.getUID());
	}
	
}