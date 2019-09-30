package UI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

import App.Application;
import Members.Member;


public class Console {
	
//	private static int iface = 0;
	
	static App.Application runningApp = new App.Application();
	
	
	
	public static void main (String[] args) {
		String userInput;
		Scanner sc = new Scanner(System.in);
		mainMenu();
		userInput = sc.nextLine();
		if (userInput.equals("1")) {
			
			compactList();
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void compactList() {
		Screen compactList = new Screen("compactList");
		compactList.addTextLine("Compact List of Members:");
		compactList.addTextLine("");
		compactList.addTextLine("ID " + " item");
		
		ArrayList<String[]> members = (ArrayList<String[]>) runningApp.getMembersAsStringArrays().clone();
		
		for (String[] member : members) 
		{
			compactList.addItemLine(member[0] , member[1]) ;
		}
		compactList.addTextLine("");

		compactList.addTextLine("a - Add, v-Verbose List, UID-Detail");
		compactList.addCommandLine();
		System.out.print(compactList.getText());
	}

	private static void mainMenu() {
		
		//main menu
		Screen mainMenu = new Screen("mainMenu");
		mainMenu.addTextLine("MainMenu");
		mainMenu.addTextLine("");
		mainMenu.addTextLine("1. Members");
		mainMenu.addTextLine("2. Boats");
		mainMenu.addTextLine("");
		mainMenu.addTextLine("Provide a number and press 'Enter'");
		mainMenu.addCommandLine();
		System.out.print(mainMenu.getText());	
	}

}
