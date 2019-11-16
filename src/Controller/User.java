package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Application;
import Model.Member;
import View.EngConsole2;

public class User {
	
	
	private enum Statuses {
		exit,
		main,
		start, bufferCollect,
		compactMemberList, boatList, verboseMemberList, memDetail,
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
				
				System.out.println(buffer.getName() + " " + buffer.getPersonNum());
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



