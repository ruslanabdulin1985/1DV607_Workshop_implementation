package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class General {

//	@Test
	void test() {
		Database.Operations db = new Database.Operations();
//		String [] arr = {"0001", "Ruslan", "19850116-6196"};
//		db.AddMemberFile(arr);
	
		String [] str = db.readMemberFile("db/members/0001.mem");
		System.out.println(Arrays.toString(str));
		
		
		fail("Not yet implemented");
	
	}
	

	@Test
	void listOfFiles() {
		Database.Operations db = new Database.Operations();
		db.getMemFiles();
	
	}

}
