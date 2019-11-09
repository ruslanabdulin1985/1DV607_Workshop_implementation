package View;

import java.util.Scanner;

import Controller.User;
import Model.Member;
import Model.MemberList;

public class EngConsole2 {
	
	
	public EngConsole2() {
		
		
	}
	
	public String getInput() {
		
		Scanner sc = new Scanner(System.in);
		String Input = sc.nextLine();
		return Input;
	}
	
	public boolean wantsCompactList(String input) {
		return (input.equals("1"));
	}
	

	
	
	public void showMainScreen() {
		//main menu
				Screen mainMenu = new Screen("mainMenu");
				mainMenu.addTextLine(":::Main Menu:::");
				mainMenu.addTextLine("");
				mainMenu.addTextLine("1. Members");
				mainMenu.addTextLine("2. Boats");
				mainMenu.addTextLine("3. Exit");
				mainMenu.addTextLine("");
				mainMenu.addTextLine("Provide a number and press 'Enter', q - Quit");
				mainMenu.addCommandLine();
				System.out.print(mainMenu.getText());
	}

	public boolean showGreetingMessage() {
		System.out.println("\tJolly Bay Administration");
		System.out.println("\tversion 2.0");
		System.out.println("");
		System.out.println("\t    |\\");
		System.out.println("\t    |  \\");
		System.out.println("\t    |___\\");
		System.out.println("\t  __|_____   ");
		System.out.println("\t  \\______/    ");
		System.out.println("");
		return true;
	}
	
	public boolean showCompactList(MemberList<Member> mbrList) {
		Screen sc = new Screen("Member Compact List");
		sc.addTextLine("\t::: Member List Compact :::");
		sc.addTextLine("");
		sc.addTextLine("id  -  name  -  personal numeber");
		for (Member m : mbrList) {
			sc.addItemLine(String.valueOf(m.getUID()), m.getName(), m.getPersonalNumber());
		}
		sc.addTextLine("");
		sc.addTextLine("e-Edit, d-Delete, b-Back, q-Quit");
		System.out.print(sc.getText());
		return true;
	}

	public boolean wantsToQuit(String userInput) {
		return userInput.equals("q");
	}

	public void showMemDetail(String userInput) {
		System.out.println("Mem Detail ZAGLUSHKA");
	}

	public boolean wantsBoatList(String userInput) {
		// TODO Auto-generated method stub
		return false;
	}
}
