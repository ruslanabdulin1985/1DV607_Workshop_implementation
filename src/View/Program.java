package View;

import Database.Operations;
import Model.Application;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Operations db = new Database.Operations(); // Conection to database
		Application app = new Application(db);
		EngConsole con = new EngConsole();
		con.start(app);
	}

}
