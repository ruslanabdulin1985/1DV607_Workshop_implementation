package View;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Model.Application;


public class EngConsole {
	
	Application runningApp; 
	ArrayList <String> buffer;
	String UIDbuffer;
	String BIDbuffer;
	
	// default constructor
	public EngConsole() {
	runningApp = new Application();
	buffer = new ArrayList<String>(); 
	UIDbuffer = "";
	BIDbuffer = "";
	}
	
	// main porcess - handles the flow of the console input-output
	public void start() {
		
		String userInput;
		Scanner sc = new Scanner(System.in);
		this.mainMenu();
		
		//as long as status not exit:
		while (!this.runningApp.getStatus().equals("exit")) {
			userInput = sc.nextLine(); 
			
			if (userInput.equals("q")) {
				this.exitApp();
				runningApp.exit();  // if input q than exit
			}
			// In main menu
			if (runningApp.getStatus().equals("mainMenu")){
				if (userInput.equals("1")) {
					runningApp.setStatus("compactList");
					this.compactList();
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
					this.notImplemented();
				}
				
				else if (userInput.equals("b")){
					runningApp.setStatus("mainMenu");
					this.mainMenu();
				}
				
				else if(isDigit(userInput)) {
					if (runningApp.getMemberById(Integer.valueOf(userInput)) == null)
						this.compactList();
					
					else if (!(runningApp.getMemberById(Integer.valueOf(userInput))==null)) {
						runningApp.setStatus("memberDetail");
						UIDbuffer = userInput;
						this.memberDetail(userInput);
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
					runningApp.setStatus("compactList");
					this.compactList();
					buffer.clear();  // 
				}
				
				else  //something else provided - ask again 
					this.compactList();
			}
			
			// in Delete Member menu
			else if (runningApp.getStatus().equals("removeMem")) {
			
				
				String confirmation = sc.nextLine();
				if (confirmation.equals("y")){
					runningApp.deleteMember(Integer.valueOf(UIDbuffer));
					runningApp.setStatus("compactList");
				}
					
				else if( confirmation.equals("n")){
					runningApp.setStatus("compactList");
				}
			}
			
			

			
			// in Member Detail
			else if (runningApp.getStatus().equals("memberDetail")){
				if (userInput.equals("b")) {
					System.out.println("back");
					runningApp.setStatus("compactList");
					this.compactList();
				}
				
				else if (userInput.equals("d")){
					this.confirmAction();
					if (sc.nextLine().equals("y")) {
						
						runningApp.deleteMember(Integer.valueOf(UIDbuffer));
						this.compactList();
						runningApp.setStatus("compactList");
						}
					else {
						this.memberDetail(UIDbuffer);
						runningApp.setStatus("memberDetail");}
						
				}
				
				else if (userInput.equals("e")) {
					buffer.clear();
					addMemberName();
					buffer.add(sc.nextLine());
					addMemberPersonNumber();
					buffer.add(sc.nextLine());
					String[] mem = {"null",buffer.get(0),buffer.get(1)};
					runningApp.changeMember(UIDbuffer, mem);
					buffer.clear();  // 
					this.memberDetail(UIDbuffer);
				}
			}
			
			// In 
			
			// In Boat List
			else if (runningApp.getStatus().equals("boatList")) {
				if (userInput.equals("b")) {
					System.out.println("back");
					runningApp.setStatus("mainMenu");
					this.mainMenu();
				}
				
				else if(isDigit(userInput)) {
					if (runningApp.getBoatById(userInput) == null)
						this.boatList();
					
					else if (!(runningApp.getBoatById(userInput)==null)) {
						runningApp.setStatus("boatDetail");
						BIDbuffer = userInput;
						this.boatDetail(userInput);
					}
				}
				
				else if (userInput.equals("a")) {
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
					else 
						buffer.add("Not stated");
					
					addBoatLength();
					buffer.add(sc.nextLine());
					addBoatReg();
					buffer.add(sc.nextLine());
					
					//String[] bt = {"null", buffer.get(0),buffer.get(1),buffer.get(2)};
					runningApp.addBoat(buffer.get(0),Integer.valueOf(buffer.get(1)),Integer.valueOf(buffer.get(2)));
					buffer.clear();  // 
					runningApp.setStatus("boatList");
					boatList();
				}
			}
			
			//InBoatDetail
			else if (runningApp.getStatus().equals("boatDetail")){
				if (userInput.equals("b")) {
					System.out.println("back");
					runningApp.setStatus("boatList");
					this.boatList();
				}
				
				else if (userInput.equals("d")) {
					this.notImplemented();
				}
				
				else if (userInput.equals("e")) {
					buffer.clear();
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
					else 
						buffer.add("Not stated");
					addBoatLength();
					buffer.add(sc.nextLine());
					addBoatReg();
					buffer.add(sc.nextLine());
					String[] bt = {"null",buffer.get(0),buffer.get(1),buffer.get(2)};
					runningApp.changeBoat(BIDbuffer, bt);
					buffer.clear();  // 
					boatDetail(BIDbuffer);
				}
				
			}
	}
		
		userInput = sc.nextLine();
		System.out.println(userInput);
		
	}
	
	private void exitApp() {
		Screen exitApp = new Screen("exitApp");
		exitApp.addTextLine("Application is closed");
		System.out.print(exitApp.getText());
	}

	private void boatList() {
		Screen boatList = new Screen("boatList");
		boatList.addTextLine(":::List of Boats:::");
		boatList.addTextLine("");
		boatList.addTextLine("ID " + " type " + " length " + " owner ");
		
		ArrayList<String[]> boats = (ArrayList<String[]>) runningApp.getBoatsAsStringArrays().clone();
		
		for (String[] boat : boats) 
		{
			boatList.addItemLine(boat[0] , boat[1], boat[2], runningApp.getMemberArrById(Integer.valueOf(boat[3]))[1]);// boat[3])[1]) ;
		}
		boatList.addTextLine("");

		boatList.addTextLine("a-Add, ID#-Detail, b-Back  q-Quit");
		boatList.addCommandLine();
		System.out.print(boatList.getText());
	}
	
	
	private void addMemberName() {
		Screen addName = new Screen("addName");
		addName.addTextLine(":::Add User:::");
		addName.addTextLine("");
		addName.addTextLine("Enter name :");
		addName.addCommandLine();
		System.out.print(addName.getText());
	}
	

	private void addBoatLength() {
		Screen addLength = new Screen("addName");
		addLength.addTextLine(":::Register Boat:::");
		addLength.addTextLine("");
		addLength.addTextLine("Enter length :");
		addLength.addCommandLine();
		System.out.print(addLength.getText());
	}
	
	private void addBoatType() {
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
	
	private void addBoatReg() {
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
	
	
	private void addMemberPersonNumber() {
		Screen addPersonNum = new Screen("addName");
		addPersonNum.addTextLine(":::Add User:::");
		addPersonNum.addTextLine("");
		addPersonNum.addTextLine("Enter personal number :");
		addPersonNum.addCommandLine();
		System.out.print(addPersonNum.getText());
	}
	

	private void memberDetail(String UID) {
		String [] member = runningApp.getMemberArrById(Integer.valueOf(UID));
		Screen memDetail = new Screen("compactList");
		memDetail.addTextLine(":::Member's Detail:::");
		memDetail.addTextLine("");
		memDetail.addTextLine("User ID : " + member[0]);
		memDetail.addTextLine("Name : " + member[1]);
		memDetail.addTextLine("Personal Number : " + member[2]);
		memDetail.addTextLine("");
		memDetail.addTextLine(":Boats registred:");
		memDetail.addTextLine("\tID - type - length:");
		String[][] boatsRegistred = runningApp.getBoatsByUID(Integer.valueOf(UID));
		
		for (String[] b : boatsRegistred) {
			memDetail.addTextLine("\t" + b[0] + " | " + b[1] + " | " + b[2]);
		}
		memDetail.addTextLine(runningApp.getBoatsByUID(Integer.valueOf(UID)).toString()); 
		memDetail.addTextLine("");
		memDetail.addTextLine("e-Edit, d-Delete, b-Back, q-Quit");
		memDetail.addCommandLine();
		System.out.print(memDetail.getText());	
		}
	
	private void boatDetail(String BID) {
		String [] boat = runningApp.getBoatById(BID);
		Screen memDetail = new Screen("boatList");
		memDetail.addTextLine(":::Boats's Detail:::");
		memDetail.addTextLine("");
		memDetail.addTextLine("Boat ID : " + boat[0]);
		memDetail.addTextLine("Type : " + boat[1]);
		memDetail.addTextLine("Length : " + boat[2]);
		memDetail.addTextLine("Registred to : " + runningApp.getMemberArrById(Integer.valueOf(boat[3]))[1]);
		memDetail.addTextLine("");
		memDetail.addTextLine("e-Edit, d-Delete, b-Back, q - Quit");
		memDetail.addCommandLine();
		System.out.print(memDetail.getText());	
		}

	
	@SuppressWarnings("unchecked")
	private void compactList() {
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

		compactList.addTextLine("a-Add, v-Verbose, ID#-Detail, b-Back, q-Quit");
		compactList.addCommandLine();
		System.out.print(compactList.getText());
	}

	private void mainMenu() {
		
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
	
	private void removeMember() {
		Screen removeMem = new Screen("removeMem");
		removeMem.addTextLine("Provide ID of the user to be romoved");
		removeMem.addTextLine("");
		removeMem.addCommandLine();
		System.out.print(removeMem.getText());
	}
	
	private void confirmAction() {
		Screen confirm = new Screen ("confirm");
		confirm.addTextLine("Are you shure?");
		confirm.addTextLine("");
		confirm.addTextLine("y - Yes, n - No, q - Quit");
		confirm.addCommandLine();
		System.out.print(confirm.getText());
	}
	
	
	private void notImplemented() {
		Screen ni = new Screen("ni");
		ni.addTextLine(":::NOT YET IMPLEMENTED:::");
		ni.addCommandLine();
		System.out.print(ni.getText());
	}

	private boolean isDigit(String str) {
		for (int i=0; i<str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}
}
