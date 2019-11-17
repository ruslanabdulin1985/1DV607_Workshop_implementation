package View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Controller.User;
import Model.Boat;
import Model.BoatList;
import Model.BoatTypes.boatTypes;
import Model.Member;
import Model.MemberList;

public class EngConsole implements Console{


	public EngConsole() {
		
		
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
	
	public boolean showChooseMemberList(MemberList mbrList) {
		Screen sc = new Screen("Choose Member List");
		sc.addTextLine("\t::: Choose a member :::");
		sc.addTextLine("");
		sc.addTextLine("id  -  name  -  personal numeber");
		for (Member m : mbrList) {
			sc.addItemLine(String.valueOf(m.getUID()), m.getName(), m.getPersonalNumber());
		}
		sc.addTextLine("");
		System.out.print(sc.getText());
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
		sc.addCommandLine();
		System.out.print(sc.getText());
		return true;
	}

	public boolean wantsToQuit(String userInput) {
		return userInput.equals("q");
	}
	
	public void showMemberDoesNoetExistError(String UID) {
		System.err.println("Error occured: Member " + UID + " does not exist");
		System.out.println(">>>");
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
		System.out.println(">>>");
	}

	public void askForMemberPersonNum() {
		System.out.println("Provide Member Personal Number:");
		System.out.println(">>>");
	}

	public void askforAprrove() {
		System.out.println("Are you sure ? y-Yes, any other-No");
		System.out.println(">>>");
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
		sc.addItemLine(String.valueOf(bt.getBID()), String.valueOf(bt.getType()), String.valueOf(bt.getLength()));
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
		System.out.println(">>>");
		
	}

	public void showBoatList(BoatList boaList) {
		Screen sc = new Screen("Boat List");
		sc.addTextLine("\t::: Boats List :::");
		sc.addTextLine("");
		sc.addTextLine("id  -  type  -  length");
		for (Boat b : boaList) {
			sc.addItemLine(String.valueOf(b.getBID()), String.valueOf(b.getType()), String.valueOf(b.getLength()));
		}
		sc.addTextLine("");
		sc.addTextLine("#id-Detail, a-Add, b-Back, q-Quit");
		sc.addCommandLine();
		System.out.print(sc.getText());
	}

	public void askForBoatType() {
		System.out.println("Enter boat type number: \n");
		int i = 0;
		for (boatTypes bt : boatTypes.values()) {
				System.out.println(i + " " + bt);
				i++;
		}
		System.out.println(">>>");
	}

	public void askForBoatLength() {
		System.out.println("Enter boat length");
		System.out.println(">>>");
	}

	public void askForBoatOwnerID() {
		System.out.println("Enter boat owner's ID");
		System.out.println(">>>");
	}

	public void showWrongLengthError() {
		System.err.println("length doesn't seem to be a number");
		System.out.println(">>>");
	}

	public String getInputBoatType() {        
        
		Scanner sc = new Scanner(System.in);
		String Input = sc.nextLine();
		// make sure that user provides a number
		while (!userInputIsAnumber(Input)) {
			System.err.println("ERROR! Provide a number");
			Input = sc.nextLine();
		}
		
		String toReturn = "";
		int i = 0;
		for (boatTypes bt : boatTypes.values()) {
			if (Integer.valueOf(Input) == i) {
				toReturn = String.valueOf(bt);
			}
			i++;
		}
		return toReturn;
	}
	
}
