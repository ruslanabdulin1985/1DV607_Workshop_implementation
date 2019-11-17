

import Controller.User;
import Model.Application;
import View.Console;

import View.EngConsole;

public class Program {

	public static void main(String[] args) {
		Application app = new Application(); // Initializing a application
		//EngConsole con = new EngConsole(); // Start console
		Console con = new EngConsole();
		User cntr = new User();
		while(true) {
			cntr.run(app, con); // start the application in console
		}
	}

}
