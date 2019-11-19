package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BoatList implements Iterable<Boat>, Cloneable {
	List<Boat>  boats; 
	
	public BoatList() {
		boats = new ArrayList<Boat>();
	}
	
	public int size() {
		return this.boats.size();
	}

	
	public BoatList clone() {
		BoatList btls = new BoatList();
		for (Boat bt : this.boats) {
			btls.add(bt);
		}
		return btls;
	}
	
	public boolean add(Boat b) {
		this.boats.add(b);
		return true;
	}

	
	public Boat getBoatById(int bid) {
		for (Boat b : this.boats) {
			if (b.getBID()==bid) {
				return b;
			}
		}
		return null;
	}
	
	public void delete(Boat b) {
		this.boats.remove(b);
	}
	
	
	public void edit(Boat oldB, Boat newB) {
		this.boats.remove(oldB);
		this.boats.add(newB);
	}

	@Override
	public Iterator<Boat>  iterator() {
		return this.boats.iterator();
	}
	
}

	
