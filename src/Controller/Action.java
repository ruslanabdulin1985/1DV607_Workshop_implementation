package Controller;


import Controller.User.Statuses;
import Model.Application;
import Model.Boat;
import Model.Member;
import View.EngConsole2;

public class Action {
	int tmpID = 0;
	
	Action(){	
		
	}
	
	// INNER CLASS TO STORE BUFFER BOAT DATA
	private class BufferBoatInfo{
		String type;
		String length;	
		String ownerID;
		
		public BufferBoatInfo(EngConsole2 con){
			con.askForBoatType();
			this.type = con.getInput();
			con.askForBoatLength();
			this.length = con.getInput();
			con.askForBoatOwnerID();
			this.ownerID = con.getInput();
		}
		
		private String getLength() {
			return this.length;
		}
		private String getType() {
			return this.type;
		}
		private String getOwnerID() {
			return this.ownerID;
		}
	}
	
//	 INNTER CLASS TO STORE BUFFER MEMBER DATA
	private class BufferMemberInfo{
		String name;
		String personNum;
		
		private BufferMemberInfo(EngConsole2 con){
			con.askForMemberName();
			this.name =  con.getInput();
			con.askForMemberPersonNum();
			this.personNum = con.getInput();
		}
		
		private String getName() {
			return this.name;
		}
		
		private String getPersonNum() {
			return this.personNum;
		}
	}


	Statuses MainMenu(Application app, EngConsole2 con, String userInput) {
		if (con.wantsCompactList(userInput)) {
			con.showCompactList(app.getMemberList());
			return Statuses.compactMemberList;
		}
		
		else if (con.wantsBoatList(userInput)) {
			con.showBoatList(app.getBoaList());
			return Statuses.boatList;
		}
		else
			return Statuses.main;
	}
	
	Statuses start(EngConsole2 con) {
		con.showGreetingMessage();
		con.showMainScreen();
		return Statuses.main;
	}
	
	
	Statuses actionsAgainstMember(Application app, EngConsole2 con, String userInput) {
		if (con.wantsToDelete(userInput)) {
			con.askforAprrove();
			if (con.doesUserAprrove(con.getInput())) {
				app.deleteMember(tmpID);
				con.showCompactList(app.getMemberList());
			}
			
			else if (con.wantsToEdit(userInput)) {
				BufferMemberInfo buffer = new BufferMemberInfo(con);
				app.editMember(tmpID, buffer.getName(), buffer.getPersonNum());
				con.showCompactList(app.getMemberList());
			}
		}
		if (con.wantsGoBack(userInput)) {
			con.showCompactList(app.getMemberList());
			return Statuses.compactMemberList;
		}
		
		return Statuses.compactMemberList;
	}

	Statuses goIntoMemberDetail(Application app, EngConsole2 con, String userInput) {
		if (con.userInputIsAnumber(userInput)) {
			Member mbr = app.getMemberList().getMemberById(Integer.valueOf(userInput));
			if(mbr!=null) {
				tmpID = Integer.valueOf(userInput);
				con.showMemDetail(mbr);
				return Statuses.memDetail;
			}
			else
				con.showMemberDoesNoetExistError(userInput);
			
		}
		else if(con.wantsToAdd(userInput)) {
			BufferMemberInfo buffer = new BufferMemberInfo(con);
			app.addMember(buffer.getName(), buffer.getPersonNum());
			con.showCompactList(app.getMemberList());
		}
		
		if (con.wantsGoBack(userInput)) {
			con.showMainScreen();
			return Statuses.main;
		}
		
		return Statuses.compactMemberList;
	}

	Statuses goIntoBoatDetail(Application app, EngConsole2 con, String userInput) {
		if (con.userInputIsAnumber(userInput)) { // If a number show boat detail
			Boat bt = app.getBoaList().getBoatById(Integer.valueOf(userInput));
			if(bt!=null) {
				tmpID = Integer.valueOf(userInput);
				con.showBoatDetail(bt);
				return Statuses.boatDetail;
			}
			else
				con.showBoatDoesNoetExistError(userInput);
			
		}
		else if(con.wantsToAdd(userInput)) { //else if an add character - add a boat
			BufferBoatInfo buffer = new BufferBoatInfo(con);
			Member m = app.getMemberList().getMemberById(Integer.valueOf(buffer.getOwnerID()));
			if (!con.userInputIsAnumber(buffer.getLength())) {
				con.showWrongLengthError();
			}
			
			else if (m!=null) { // else user exist - add a boat
				app.addBoat(buffer.getType(), Integer.valueOf(buffer.getLength()), m);	
			}
			
			else if(m==null) // else if user doesn't exist - show an error
				con.showMemberDoesNoetExistError(buffer.getOwnerID());	
		}
		
		if (con.wantsGoBack(userInput)) {
			con.showMainScreen();
			return Statuses.main;
		}
		con.showBoatList(app.getBoaList());
		return Statuses.boatList;
	}


	public Statuses actionsAgainstBoats(Application app, EngConsole2 con, String userInput) {
		if (con.wantsToDelete(userInput)) {
			con.askforAprrove();
			if (con.doesUserAprrove(con.getInput())) {
				app.deleteBoat(tmpID);
				con.showBoatList(app.getBoaList());
			}
			
			else if (con.wantsToEdit(userInput)) {
				BufferBoatInfo buffer = new BufferBoatInfo(con);
				app.editBoat(tmpID, buffer.getType(), Integer.valueOf(buffer.getLength()));
				con.showBoatList(app.getBoaList());
			}
			
			else if (con.wantsGoBack(userInput)) {
				con.showBoatList(app.getBoaList());
			}
		}
		return Statuses.boatList;
	}
	
}


