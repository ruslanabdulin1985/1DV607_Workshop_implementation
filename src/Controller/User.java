package Controller;

import Model.Application;
import View.Console;


public class User {
	
	Action act;
	
	// Status shows at which point of interaction User is at that point 
	 public enum Statuses {
		exit, main, start, bufferCollect, boatDetail,
		compactMemberList, boatList, verboseMemberList, memDetail, 
	}
	
	int tmpID = 0;
	
	private Statuses status;

	public User() {
		status = Statuses.start;
		act = new Action();
	}
	
	
	public void run(Application app, Console con) {
		String userInput = "";
		
		// Begining
		if (status == Statuses.start) {
			this.status = act.start(con);
		}
		
		//Gets user's input
		userInput = con.getInput();
		
		
		// depending on the status of kontorller proceeds user's input differently
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
		
		else if (status == Statuses.boatDetail) {
			this.status = act.actionsAgainstBoats(app, con, userInput);
		}
			
		
		if (con.wantsToQuit(userInput)) {
			act.exitApp(con, app);
			
		}
		
		
	}

	
}