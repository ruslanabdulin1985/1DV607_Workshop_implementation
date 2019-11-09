package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Application;
import View.EngConsole2;

public class User {
	
	
	private enum Statuses {
		exit,
		main,
		start,
		compactMemberList,
	}
	
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
				
			}
		}
		
		else if (status == Statuses.compactMemberList ) {
			
			con.showMemDetail(userInput);
				
		}
		
		if (con.wantsToQuit(userInput)) {
			app.exit();
		}
		
		
	}
	

}
