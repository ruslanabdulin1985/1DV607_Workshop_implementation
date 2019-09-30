package UI;
import java.util.Scanner;


public class Console {
	
//	private static int iface = 0;
	
	
	
	public static void main (String[] args) {
		String userInput;
		Scanner sc = new Scanner(System.in);
		mainMenu();
		userInput = sc.nextLine();
		if (userInput.equals("1")) {
			
			compactList();
		}
	}
	
	private static void compactList() {
		Screen compactList = new Screen("compactList");
		compactList.addTextLine("Compact List of Members:");
		compactList.addTextLine("");
		for(int i = 0; i<=2; i++) {
		compactList.addItemLine("MemberName");
		}
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
