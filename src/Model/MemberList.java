package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MemberList implements Iterable<Member>, Cloneable {
	List<Member>  members; 
	
	public MemberList() {
		members = new ArrayList<Member>();
	}
	
	public MemberList clone(){ 
		// Copy of a Member List
		MemberList toReturn = new MemberList();
		for (Member m : this.members)
			toReturn.add(m);
		
		return   (MemberList) toReturn;
	}
	
	public boolean add(Member m) {
		this.members.add(m);
		return true;
	}
	
	public Member getMemberById(int uid) {
		for (Member m : this.members) {
			if (m.getUID()==uid) {
				return m;
			}
		}
		return null;
	}
	
	public void delete(Member m) {
		this.members.remove(m);
	}
	

	public void edit(Member oldM, Member newM) {
		this.members.remove(oldM);
		this.members.add(newM);
	}

	@Override
	public Iterator<Member>  iterator() {
		return this.members.iterator();
	}

}

	
