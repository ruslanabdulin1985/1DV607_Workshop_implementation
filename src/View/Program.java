package View;

import Database.Operations;
import Model.Application;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Operations db = new Database.Operations(); // Connection to database
		Application app = new Application(); // Start application
		app.connect(db); // connect database
		EngConsole con = new EngConsole(); // Start console
		con.start(app); // start the application in console
	}

}
