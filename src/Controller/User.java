package Controller;

import Model.Application;
import Model.Boat;
import Model.Member;
import View.EngConsole2;

public class User {
	
	Action act;
	
	 public enum Statuses {
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
		act = new Action();
	}
	
	
	public void run(Application app, EngConsole2 con) {
		String userInput = "";
		
		if (status == Statuses.start) {
			this.status = act.start(con);
		}
		
		userInput = con.getInput();
		
		if (status == Statuses.main) {
			this.status = act.MainMenu(app, con, userInput);
		}
		
		else if (status == Statuses.boatList) {
			this.status = act.goIntoBoatDetail(app, con, userInput);
		}
		
		else if ((status == Statuses.compactMemberList) || (status == Statuses.verboseMemberList)) {
			this.status = act.goIntoMemberDetail(app, con, userInput);
		}
		
		else if (status == Statuses.memDetail) {
			this.status = act.actionsAgainstMember(app, con, userInput);
		}
			
		
		if (con.wantsToQuit(userInput)) {
			con.showGoodbyeMessage();
			app.exit();
		}
		
		
	}

	
}