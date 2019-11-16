package Controller;

import Model.Application;
import Model.Boat;
import Model.Member;
import View.EngConsole2;

public class User {
	
	
	private enum Statuses {
		exit,
		main,
		start, bufferCollect,
		compactMemberList, boatList, verboseMemberList, memDetail, boatDetail,
	}
	
	int tmpID = 0;
	
	private Statuses status;

	
//	Application runningApp;
	public User() {
		status = Statuses.start;
		
	}

	
	public void run(Application app, EngConsole2 con) {
		// TODO Auto-generated method stub
		String userInput = "";
		
		if (status == Statuses.start) {
			con.showGreetingMessage();
			con.showMainScreen();
			status = Statuses.main;
		}
		
		userInput = con.getInput();
		
		if (status == Statuses.main) {
			if (con.wantsCompactList(userInput)) {
				status = Statuses.compactMemberList;
				con.showCompactList(app.getMemberList());
			}
			else if (con.wantsBoatList(userInput)) {
				status = Statuses.boatList;
				con.showBoatList(app.getBoaList());
				System.out.println("PrintBoats");
			}
		}
		
		else if (status == Statuses.boatList) {
			if (con.userInputIsAnumber(userInput)) {
				Boat bt = app.getBoaList().getBoatById(Integer.valueOf(userInput));
				if(bt!=null) {
					tmpID = Integer.valueOf(userInput);
					con.showBoatDetail(bt);
					status = Statuses.boatDetail;
				}
				else
					con.showBoatDoesNoetExistError(userInput);
				
			}
			else if(con.wantsToAdd(userInput)) {
				BufferMemberInfo buffer = new BufferMemberInfo(con);
				app.addMember(buffer.getName(), buffer.getPersonNum());
				status = Statuses.compactMemberList;
				con.showCompactList(app.getMemberList());
			}

		}
		
		else if ((status == Statuses.compactMemberList) || (status == Statuses.verboseMemberList)) {
			if (con.userInputIsAnumber(userInput)) {
				Member mbr = app.getMemberList().getMemberById(Integer.valueOf(userInput));
				if(mbr!=null) {
					tmpID = Integer.valueOf(userInput);
					con.showMemDetail(mbr);
					status = Statuses.memDetail;
				}
				else
					con.showMemberDoesNoetExistError(userInput);
				
			}
			else if(con.wantsToAdd(userInput)) {
				BufferMemberInfo buffer = new BufferMemberInfo(con);
				app.addMember(buffer.getName(), buffer.getPersonNum());
				status = Statuses.compactMemberList;
				con.showCompactList(app.getMemberList());
			}
			
		}
		
		else if (status == Statuses.memDetail) {
			if (con.wantsToDelete(userInput)) {
				con.askforAprrove();
				if (con.doesUserAprrove(con.getInput())) {
					app.deleteMember(tmpID);
					status = Statuses.compactMemberList;
					con.showCompactList(app.getMemberList());
				}
				
				else
					status = Statuses.compactMemberList;
					con.showCompactList(app.getMemberList());
			}
			
			else if (con.wantsToEdit(userInput)) {
				BufferMemberInfo buffer = new BufferMemberInfo(con);
				app.editMember(tmpID, buffer.getName(), buffer.getPersonNum());
				status = Statuses.compactMemberList;
				con.showCompactList(app.getMemberList());
			}
			
			else if (con.wantsGoBack(userInput)) {
				status = Statuses.compactMemberList;
				con.showCompactList(app.getMemberList());
			}
			
		
		}
		
		
		
		if (con.wantsToQuit(userInput)) {
			con.showGoodbyeMessage();
			app.exit();
		}
		
		
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



