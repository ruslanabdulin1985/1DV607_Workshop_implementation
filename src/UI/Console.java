package UI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

import App.Application;
import Members.Member;


public class Console {
	static App.Application runningApp = new App.Application();
	
	static ArrayList <String> buffer = new ArrayList<String>(); 
	
	// main porcess - handles the flow of the console input-output
	public static void main (String[] args) {
		String userInput;
		Scanner sc = new Scanner(System.in);
		mainMenu();
		while (!runningApp.getStatus().equals("exit")) {
			
			userInput = sc.nextLine();
			
			if (userInput.equals("q"))
				Runtime.getRuntime().exit(10);
			
			
			// In main menu
			if (runningApp.getStatus().equals("mainMenu")){
				if (userInput.equals("1")) {
					runningApp.setStatus("compactList");
					compactList();
				}	
				
				else if (userInput.equals("2")) {
					runningApp.setStatus("boatList");
					boatList();
				}	
				
				else if (userInput.equals("3")) {
					Runtime.getRuntime().exit(10);
				}	
				
			}
			
			// In compact List menu
			else if (runningApp.getStatus().equals("compactList")){
				if (userInput.equals("v")) {
					System.out.println("Verbose!!!!!");
				}
				
				else if(isDigit(userInput)) {
					System.out.println("else" + runningApp.getMemberById(userInput));
					if (!runningApp.getMemberById(userInput).equals(null)) {
						runningApp.setStatus("memberDetail");
						memberDetail(userInput);
					}
				}
				
				else if (userInput.equals("a")) {
					buffer.clear();
					addMemberName();
					buffer.add(sc.nextLine());
					addMemberPersonNumber();
					buffer.add(sc.nextLine());
					String[] mem = {"null",buffer.get(0),buffer.get(1)};
					runningApp.addMember(mem);
					buffer.clear();  // 
				}
				
				else  //something else provided - ask again 
					compactList();
			}
			

			// in Member Detail
			else if (runningApp.getStatus().equals("memberDetail")){
				if (userInput.equals("b")) {
					System.out.println("back");
					runningApp.setStatus("compactList");
					compactList();
				}
			}
			
			// In Boat List
			else if (runningApp.getStatus().equals("boatList")) {
				if (userInput.equals("b")) {
					System.out.println("back");
					runningApp.setStatus("mainMenu");
					mainMenu();
				}
				
				if (userInput.equals("a")) {
					buffer.clear();
					runningApp.setStatus("addBoatType");
					addBoatType();
					String typeID = sc.nextLine();
					if (typeID.equals("1"))
						buffer.add("Kayak/Canoe");
					else if (typeID.equals("2"))
						buffer.add("Motorsailer");
					else if (typeID.equals("3"))
						buffer.add("Motorsailer");
					else if (typeID.equals("4"))
						buffer.add("Other");
					
					addBoatLength();
					buffer.add(sc.nextLine());
					addBoatReg();
					buffer.add(sc.nextLine());
					
					String[] bt = {"null", buffer.get(0),buffer.get(1),buffer.get(2)};
					runningApp.addBoat(bt);
					buffer.clear();  // 
					runningApp.setStatus("boatList");
					boatList();
				}
			}
		}
		
		userInput = sc.nextLine();
		System.out.println(userInput);
	}
	
	private static void boatList() {
		Screen boatList = new Screen("boatList");
		boatList.addTextLine(":::List of Boats:::");
		boatList.addTextLine("");
		boatList.addTextLine("ID " + " type " + " length " + " member ");
		
		ArrayList<String[]> boats = (ArrayList<String[]>) runningApp.getBoatsAsStringArrays().clone();
		
		for (String[] boat : boats) 
		{
			boatList.addItemLine(boat[0] , boat[1], boat[2], runningApp.getMemberById(boat[3])[1]) ;
		}
		boatList.addTextLine("");

		boatList.addTextLine("a - Add, Type in boat ID-number for boat detail");
		boatList.addCommandLine();
		System.out.print(boatList.getText());
	}
	
	
	private static void addMemberName() {
		Screen addName = new Screen("addName");
		addName.addTextLine(":::Add User:::");
		addName.addTextLine("");
		addName.addTextLine("Enter name :");
		addName.addCommandLine();
		System.out.print(addName.getText());
	}
	

	private static void addBoatLength() {
		Screen addLength = new Screen("addName");
		addLength.addTextLine(":::Register Boat:::");
		addLength.addTextLine("");
		addLength.addTextLine("Enter length :");
		addLength.addCommandLine();
		System.out.print(addLength.getText());
	}
	
	private static void addBoatType() {
		Screen addLength = new Screen("addType");
		addLength.addTextLine(":::Register Boat:::");
		addLength.addTextLine("");
		addLength.addItemLine("1", "Kayak/Canoe");
		addLength.addItemLine("2", "Motorsailer");
		addLength.addItemLine("3", "Sailboat");
		addLength.addItemLine("4", "Other");
		addLength.addTextLine("");
		addLength.addTextLine("Enter type number :");
		addLength.addCommandLine();
		System.out.print(addLength.getText());
	}
	
	private static void addBoatReg() {
		Screen addReg = new Screen("addReg");
		addReg.addTextLine(":::Register Boat:::");
		addReg.addTextLine("");
		addReg.addTextLine("Choose an owner :");
		addReg.addTextLine("");
		ArrayList<String[]> members = (ArrayList<String[]>) runningApp.getMembersAsStringArrays().clone();
		
		for (String[] member : members) 
		{
			addReg.addItemLine(member[0] , member[1]) ;
		}
		addReg.addTextLine("");
		addReg.addCommandLine();
		
		System.out.print(addReg.getText());
	}
	
	
	private static void addMemberPersonNumber() {
		Screen addPersonNum = new Screen("addName");
		addPersonNum.addTextLine(":::Add User:::");
		addPersonNum.addTextLine("");
		addPersonNum.addTextLine("Enter personal number :");
		addPersonNum.addCommandLine();
		System.out.print(addPersonNum.getText());
	}
	

	private static void memberDetail(String UID) {
		String [] member = runningApp.getMemberById(UID);
		Screen memDetail = new Screen("compactList");
		memDetail.addTextLine(":::Member's Detail:::");
		memDetail.addTextLine("");
		memDetail.addTextLine("User ID : " + member[0]);
		memDetail.addTextLine("Name : " + member[1]);
		memDetail.addTextLine("Personal Number : " + member[2]);
		memDetail.addTextLine("");
		memDetail.addTextLine(":Boats registred:");
		memDetail.addTextLine("\tID - type - length:");
		memDetail.addTextLine(runningApp.getBoatsByUID(UID)); 
		memDetail.addTextLine("");
		memDetail.addTextLine("e - Edit, d - Delete, b - Back");
		memDetail.addCommandLine();
		System.out.print(memDetail.getText());	
		}
	
	

	
	@SuppressWarnings("unchecked")
	private static void compactList() {
		Screen compactList = new Screen("compactList");
		compactList.addTextLine(":::Compact List of Members:::");
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

	private static boolean isDigit(String str) {
		for (int i=0; i<str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}
}
