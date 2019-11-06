

import Controller.User;
import Model.Application;
import View.EngConsole2;

public class Program {

	public static void main(String[] args) {
		Application app = new Application(); // Initializing a application
		EngConsole2 con = new EngConsole2(); // Start console
		User cntr = new User();
		while(true) {
			cntr.run(app, con); // start the application in console
		}
	}

}
