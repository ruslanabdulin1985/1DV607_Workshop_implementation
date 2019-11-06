package Model;

import java.util.ArrayList;
import java.util.List;

public class MemberList {
	List<Member>  members = new ArrayList<Member>();
	
	public boolean addMember(Member m) {
		this.members.add(m);
		return true;
	}
	
}
