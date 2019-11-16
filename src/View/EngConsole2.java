package View;

import java.util.Scanner;

import Controller.User;
import Model.Boat;
import Model.BoatList;
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
	
	public boolean showCompactList(MemberList mbrList) {
		Screen sc = new Screen("Member Compact List");
		sc.addTextLine("\t::: Member List Compact :::");
		sc.addTextLine("");
		sc.addTextLine("id  -  name  -  personal numeber");
		for (Member m : mbrList) {
			sc.addItemLine(String.valueOf(m.getUID()), m.getName(), m.getPersonalNumber());
		}
		sc.addTextLine("");
		sc.addTextLine("#id-Detail, a-Add, b-Back, q-Quit");
		System.out.print(sc.getText());
		return true;
	}

	public boolean wantsToQuit(String userInput) {
		return userInput.equals("q");
	}
	
	public void showMemberDoesNoetExistError(String UID) {
		System.err.println("Error occured: Member " + UID + " does not exist");
	}

	public void showMemDetail(Member mbr) {
		Screen sc = new Screen("showMemDetail");
		sc.addTextLine("\t:::Member's Detail:::");
		sc.addTextLine("");
		sc.addTextLine("id  -  name  -  person number");
		sc.addItemLine(String.valueOf(mbr.getUID()), mbr.getName(), mbr.getPersonalNumber());
		sc.addTextLine("\tAttached boats:");
		sc.addTextLine("\tid  -  type  -  size");
		sc.addTextLine("e-Edit, d-Delete, b-Back, q-Quit");
		sc.addTextLine("");
		sc.addCommandLine();
		System.out.println(sc.getText());
		
	}
	

	public boolean wantsBoatList(String userInput) {
		if (userInput.equals("2")) {
			return true;
		}
		return false;
	}

	public void showGoodbyeMessage() {
		System.out.println("Application closed");
	}
	
	
	
	public boolean userInputIsAnumber(String input){
		return input.matches("\\d+");
	}
	
	public boolean wantsToEdit(String userInput) {
		return (userInput.equals("e"));
	}
	
	public boolean wantsToAdd(String userInput) {
		return (userInput.equals("a"));
	}

	public boolean wantsToDelete(String userInput) {
		return (userInput.equals("d"));
	}

	public void askForMemberName() {
		System.out.println("Provide Member Name:");
		
	}

	public void askForMemberPersonNum() {
		System.out.println("Provide Member Personal Number:");
		
	}

	public void askforAprrove() {
		System.out.println("Are you sure ? y-Yes, any other-No");
	}

	public boolean doesUserAprrove(String input) {
		return input.equals("y");
	}

	public boolean wantsGoBack(String input) {
		return input.equals("b");
	}

	public void showBoatDetail(Boat bt) {
		Screen sc = new Screen("showBoatDetail");
		sc.addTextLine("\t:::Boat's Detail:::");
		sc.addTextLine("");
		sc.addTextLine("id  -  type  -  length");
		sc.addItemLine(String.valueOf(bt.getBID()), bt.getType(), String.valueOf(bt.getLength()));
		sc.addTextLine("\tOwner:");
		sc.addTextLine("\tid  -  type  -  size");
		sc.addTextLine("");
		sc.addTextLine("e-Edit, d-Delete, b-Back, q-Quit");
		sc.addTextLine("");
		sc.addCommandLine();
		System.out.println(sc.getText());
	}

	public void showBoatDoesNoetExistError(String userInput) {
		System.err.println("Boat " +  userInput + " does not exist");
		
	}

	public void showBoatList(BoatList boaList) {
		Screen sc = new Screen("Boat List");
		sc.addTextLine("\t::: Boat List :::");
		sc.addTextLine("");
		sc.addTextLine("id  -  type  -  length");
		for (Boat b : boaList) {
			sc.addItemLine(String.valueOf(b.getBID()), b.getType(), String.valueOf(b.getLength()));
		}
		sc.addTextLine("");
		sc.addTextLine("#id-Detail, a-Add, b-Back, q-Quit");
		System.out.print(sc.getText());
	}

	
}
