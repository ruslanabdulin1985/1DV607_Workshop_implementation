package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MemberList implements Iterable<Member> {
	List<Member>  members; 
	
	public MemberList() {
		members = new ArrayList<Member>();
	}
	
	public int size() {
		return this.size();
	}
	
	public boolean set(int pos, Member element) {
		return this.set(pos, element);
	}
	
	public Member get(int pos) {
		return (Member)this.get(pos);
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
	
	public void edit(Member oldM, Member newM) {
		for (int i=0; i<this.size(); i++) {
			if (oldM.equals(this.get(i))) {
				this.set(i, newM);
			}
		}
	}

	@Override
	public Iterator<Member>  iterator() {
		return this.members.iterator();
	}

}

	
