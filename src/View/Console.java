package View;

import Model.Boat;
import Model.BoatList;
import Model.Member;
import Model.MemberList;

public interface Console {
	public String getInput();
	
	public boolean wantsCompactList(String input);
	
	public boolean wantsToQuit(String userInput);
	
	public boolean showCompactList(MemberList mbrList);
	
	public boolean showChooseMemberList(MemberList mbrList);
	
	public boolean showGreetingMessage();
	
	public void showMainScreen();
	
	public boolean wantsToAdd(String userInput);
	
	public boolean wantsToEdit(String userInput);
	
	public boolean userInputIsAnumber(String input);
	
	public void showGoodbyeMessage();
	
	public boolean wantsBoatList(String userInput);
	
	public void showMemDetail(Member mbr, BoatList btls) ;
	
	public void showMemberDoesNoetExistError(String UID);
	
	public void showBoatDetail(Boat bt);
	
	public boolean wantsGoBack(String input);
	
	public boolean doesUserAprrove(String input) ;
	
	public void askforAprrove();
	
	public void askForMemberPersonNum() ;
	
	public void askForMemberName();
	
	public boolean wantsToDelete(String userInput);
	
	public String getInputBoatType();
	
	public void showWrongLengthError() ;
	
	public void askForBoatOwnerID();
	
	public void askForBoatLength();
	
	public void askForBoatType();
	
	public void showBoatList(BoatList boaList);
	
	public void showBoatDoesNoetExistError(String userInput);
	
	
}
