/*
 * This is an assignment for CS242 Network Security course
 * Contact: https://vford.me/contact
 *  
 * @author: Vitaly Ford
 * 
 */

public class Testing {

	// also, think how you could improve the code itself in both files
	// is there anything you would refactor?
	// is there anything that annoys you as a user?
	// is there any bugs that stand out as you use the system?
	// is there anything that you could make more efficient?
	// is there anything that can be improved from a design standpoint?
	// would you process exceptions differently?
	// would you process user input differently?
	// etc.
	public static void main(String[] args) {
		
		LoginSystem l = new LoginSystem();
		
		l.intro();
		
		while (true) {			
			Integer input = 0;
			
			try {
				l.p("\nMake your selection: ");
				input = Integer.parseInt(l.sc.nextLine());
			}
			catch(Exception e) {
				l.pl("C'mon, you really thought it will be that easy?\nEnter a number, no letters!\nAnd it should be within reasonable limits :-P");
				continue;
			}
			if (input < 1 || input > 4) {
				l.pl("Really? The options given are only 1-4! :-P");
				continue;
			}

			if (input == 1) {
				l.register();
			}
			else if (input == 2) {
				l.login();
			}
			else if (input == 3) {
				l.list();
			}
			else if (input == 4) {
				l.pl("Bye!");
				break;
			}
		}
		l.close();
	}

}
