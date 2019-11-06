package View;

import java.util.Scanner;

import Controller.User;

public class EngConsole2 {
	
	private ConsoleStatuses status;
	
	public EngConsole2() {
		status = ConsoleStatuses.start;
		
	}
	
	public void start(User cntr) {
		String userInput;
		Scanner sc = new Scanner(System.in);
		
		if (status == ConsoleStatuses.start) {
			showGreetingMessage();
			status = ConsoleStatuses.main;
		}
		
		if (status == ConsoleStatuses.main) {
			showMainScreen();
			userInput = sc.nextLine();
				if (userInput.equals("1"))
					cntr.memberList();
				if (userInput.equals("2"))
					cntr.boatList();
		}
	}
	
	
	
	private void showMainScreen() {
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

	private boolean showGreetingMessage() {
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
	
}
