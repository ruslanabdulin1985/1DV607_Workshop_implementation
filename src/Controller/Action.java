package Controller;


import Controller.User.Statuses;
import Model.Application;
import Model.Boat;
import Model.BoatList;
import Model.BoatTypes.boatTypes;
import Model.Member;
import View.Console;


public class Action {
	int tmpID = 0;
	
	// INNER CLASS TO STORE BUFFER BOAT DATA
	private class BufferBoatInfo{
		String type;
		String length;	
		String ownerID;
		
		public BufferBoatInfo(String t, String l, String id) {
			this.type = t;
			this.length = l;
			this.ownerID = id;
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
	
//	 INNER CLASS TO STORE BUFFER MEMBER DATA
	private class BufferMemberInfo{
		String name;
		String personNum;
		
		private BufferMemberInfo(String n, String p) {
			name = n;
			personNum = p;
		}
		
		private String getName() {
			return this.name;
		}
		
		private String getPersonNum() {
			return this.personNum;
		}
	}

	// Main menu
	Statuses MainMenu(Application app, Console con, String userInput) {
		if (con.wantsCompactList(userInput)) {
			con.showCompactList(app.getCopyOfMemberList());
			return Statuses.compactMemberList;
		}
		
		else if (con.wantsBoatList(userInput)) {
			con.showBoatList(app.getCopyOfBoatList());
			return Statuses.boatList;
		}
		
		else
			return Statuses.main;
	}
	
	// Starting application
	Statuses start(Console con) {
		con.showGreetingMessage();
		con.showMainScreen();
		return Statuses.main;
	}
	
	// Inside member's detail
	Statuses actionsAgainstMember(Application app, Console con, String userInput) {
		if (con.wantsToDelete(userInput)) {
			con.askforAprrove();
			if (con.doesUserAprrove(con.getInput())) {
				app.deleteMember(tmpID);
				con.showCompactList(app.getCopyOfMemberList());
			}	
		}
		
		else if (con.wantsToEdit(userInput)) {
			System.out.println("Start Editing");
			BufferMemberInfo buffer = createMemberBufferInfo(con);
			
			app.editMember(tmpID, buffer.getName(), buffer.getPersonNum());
			con.showCompactList(app.getCopyOfMemberList());
		}
		
		if (con.wantsGoBack(userInput)) {
			con.showCompactList(app.getCopyOfMemberList());
			tmpID = 0;
			return Statuses.compactMemberList;
		}
		
		return Statuses.compactMemberList;
	}

	// Inside verbose member list and compact member list
	Statuses goIntoMemberDetail(Application app, Console con, String userInput) {
		if (con.userInputIsAnumber(userInput)) {
			Member mbr = app.getCopyOfMemberList().getMemberById(Integer.valueOf(userInput));
			if(mbr!=null) {
				BoatList btlst = app.getAttachedBoats(mbr);
				tmpID = Integer.valueOf(userInput);
				con.showMemDetail(mbr, btlst);
				return Statuses.memDetail;
			}
			else
				con.showMemberDoesNoetExistError(userInput);
			
		}
		
		
//		else if (con.wantsVerboseList(userInput)) {
//			con.showVerboseList(MemberList mbrlst, BoatList btlst){
//				
//			}
//		}
		
		else if(con.wantsToAdd(userInput)) {
			BufferMemberInfo buffer = createMemberBufferInfo(con); // creates buffer member
			app.addMember(buffer.getName(), buffer.getPersonNum());
			con.showCompactList(app.getCopyOfMemberList());
		}
		
		else if (con.wantsVerboseList(userInput)) {
			con.showVerbosetList(app.getCopyOfMemberList(), app.getCopyOfBoatList());
			return Statuses.verboseMemberList;
		}
		
		if (con.wantsGoBack(userInput)) {
			con.showMainScreen();
			tmpID = 0;
			return Statuses.main;
		}
		
		return Statuses.compactMemberList;
	}
	
	// Inside boat list
	Statuses goIntoBoatDetail(Application app, Console con, String userInput) {
		if (con.userInputIsAnumber(userInput)) { // If a number show boat detail
			Boat bt = app.getCopyOfBoatList().getBoatById(Integer.valueOf(userInput));
			if(bt!=null) {
				tmpID = Integer.valueOf(userInput);
				con.showBoatDetail(bt);
				return Statuses.boatDetail;
			}
			else
				con.showBoatDoesNoetExistError(userInput);
			
		}
		else if(con.wantsToAdd(userInput)) { //else if an add character - add a boat
			BufferBoatInfo buffer = createBufferBoatInfo(con, app);
			Member m = app.getCopyOfMemberList().getMemberById(Integer.valueOf(buffer.getOwnerID()));
			if (!con.userInputIsAnumber(buffer.getLength())) {
				con.showWrongLengthError();
			}
			
			else if (m!=null) { // else user exist - add a boat
				app.addBoat(boatTypes.valueOf(buffer.getType()), Integer.valueOf(buffer.getLength()), m);	
			}
			
			else if(m==null) // else if user doesn't exist - show an error
				con.showMemberDoesNoetExistError(buffer.getOwnerID());	
		}
		
		if (con.wantsGoBack(userInput)) {
			con.showMainScreen();
			tmpID = 0;
			return Statuses.main;
		}
		con.showBoatList(app.getCopyOfBoatList());
		return Statuses.boatList;
	}
  
	
	private BufferMemberInfo createMemberBufferInfo(Console con){	
		con.askForMemberName();
		String bname =  con.getInput();
		con.askForMemberPersonNum();
		String bpersonNum = con.getInput();
		BufferMemberInfo buffer = new BufferMemberInfo(bname, bpersonNum);
		return buffer;
	}
	
	private BufferBoatInfo createBufferBoatInfo(Console con, Application app){
		con.askForBoatType();
		String type = con.getInputBoatType();
		con.askForBoatLength();
		String length = con.getInput();
		con.showChooseMemberList(app.getCopyOfMemberList());
		con.askForBoatOwnerID();
		String ownerID = con.getInput();
		BufferBoatInfo buffer = new BufferBoatInfo(type, length, ownerID);
		return buffer;
	}

	// inside Boat's detail
	public Statuses actionsAgainstBoats(Application app, Console con, String userInput) {
		if (con.wantsToDelete(userInput)) {
			con.askforAprrove();
			if (con.doesUserAprrove(con.getInput())) {
				app.deleteBoat(tmpID);
				con.showBoatList(app.getCopyOfBoatList());
			}
		}
		
		else if (con.wantsToEdit(userInput)) {
			BufferBoatInfo buffer = createBufferBoatInfo(con, app);
			if (app.getCopyOfMemberList().getMemberById(Integer.valueOf(buffer.getOwnerID()))!=null) /* IF buffer boat has proper data*/ {
				app.editBoat(tmpID, boatTypes.valueOf(buffer.getType()), Integer.valueOf(buffer.getLength()), app.getCopyOfMemberList().getMemberById(Integer.valueOf(buffer.getOwnerID())));
				con.showBoatList(app.getCopyOfBoatList());
			}
			else
				con.showMemberDoesNoetExistError(buffer.getOwnerID());
		}
			
		else if (con.wantsGoBack(userInput)) {
			con.showBoatList(app.getCopyOfBoatList());
		}
	
		return Statuses.boatList;
	}

	public void exitApp(Console con, Application app) {
		con.showGoodbyeMessage();
		app.exit();
	}
	
}


