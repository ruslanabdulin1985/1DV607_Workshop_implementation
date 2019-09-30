package App;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Map;

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
	}
	
	public ArrayList<Members.Member> getMemberList(){
		return memberList;
	}
	
	
	private void addMember(Member mbr) {
		
	
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
	
		
	
	
}
