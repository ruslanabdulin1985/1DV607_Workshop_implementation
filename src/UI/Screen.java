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
	
	public int addItemLine(String line) {
		itemcounter ++;
		text =text + String.valueOf(itemcounter) + ". " + line + "\n";
		return itemcounter;
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
