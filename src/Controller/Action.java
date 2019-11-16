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
				//status = Statuses.compactMemberList;
				con.showCompactList(app.getMemberList());
			}
			
			else if (con.wantsToEdit(userInput)) {
				BufferMemberInfo buffer = new BufferMemberInfo(con);
				app.editMember(tmpID, buffer.getName(), buffer.getPersonNum());
//				status = Statuses.compactMemberList;
				con.showCompactList(app.getMemberList());
			}
			
			else if (con.wantsGoBack(userInput)) {
				con.showCompactList(app.getMemberList());
			}
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
		return Statuses.compactMemberList;
	}

	Statuses goIntoBoatDetail(Application app, EngConsole2 con, String userInput) {
		if (con.userInputIsAnumber(userInput)) {
			Boat bt = app.getBoaList().getBoatById(Integer.valueOf(userInput));
			if(bt!=null) {
				tmpID = Integer.valueOf(userInput);
				con.showBoatDetail(bt);
				return Statuses.boatDetail;
			}
			else
				con.showBoatDoesNoetExistError(userInput);
			
		}
		else if(con.wantsToAdd(userInput)) {
			BufferMemberInfo buffer = new BufferMemberInfo(con);
			app.addMember(buffer.getName(), buffer.getPersonNum());
			con.showCompactList(app.getMemberList());
		}
		return Statuses.compactMemberList;
	}

	// INNER CLASS TO STORE BUFFER BOAT DATA
	private class BufferBoatInfo{
		String type;
		String length;	
		public BufferBoatInfo(EngConsole2 con){
			
		}
	}
	
	// INNTER CLASS TO STORE BUFFER MEMBER DATA
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
	
}


