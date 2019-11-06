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

	
	Application runningApp;
	public User() {
		status = Statuses.start;
		
	}
	
	public void takeInput() {
		
		String[] buffer = new String[0];
		
	}

	

	public void boatList() {
		// TODO Auto-generated method stub
		System.out.println("BtList:");
		
	}
	public void run(Application app, EngConsole2 con) {
		// TODO Auto-generated method stub
		
		
		if (status == Statuses.start) {
			con.showGreetingMessage();
			status = Statuses.main;
		}
		
		else if (status == Statuses.main) {
			con.showMainScreen();
			if (con.wantsCompactList(con.getInput())) {
				status = Statuses.compactMemberList;
//				con.showCompactList(Members[]);
			}
				
		}
		
		
	}
	

}
