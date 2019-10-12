package UI;

public class Screen {
	
	private String name = "empty";
	private String text = "\n";
	int itemcounter = 0;
	
	public Screen(String nameToSet) {
		name = nameToSet;
		text = "\n";
	}
	
	public void addTextLine(String line) {
		text = text + line + "\n";
	}
	
	public void addItemLine(String ID, String item) {
		
		text = text + ID + " - " + item + "\n";
	}
	
	public void addItemLine(String ID, String item, String att1, String att2) {
		
		text = text + ID + " - " + item + " - " + att1 + " - " + att2+ "\n";
	}
	
	public void addCommandLine() {
		text = text + ">>>";
	}
	
	public String getText() {
		return text;
	}
	
	public String getName () {
		return name;
	}
	
	public void setName (String nameToSet) {
		name = nameToSet;
	}
}
