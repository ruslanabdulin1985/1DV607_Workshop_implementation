package View;

import Database.Operations;
import Model.Application;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Operations db = new Database.Operations(); // Initializing a database
		Application app = new Application(); // Initializing a application
		app.connect(db); // connect database to application
		EngConsole con = new EngConsole(); // Start console
		con.start(app); // start the application in console
	}

}
