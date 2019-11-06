

import Model.Application;
import View.EngConsole;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application app = new Application(); // Initializing a application
		EngConsole con = new EngConsole(); // Start console
		con.start(app); // start the application in console
	}

}
