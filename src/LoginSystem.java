/*
 * This is an assignment for CS242 Network Security course
 * Contact: https://vford.me/contact
 *  
 * @author: Vitaly Ford
 * 
 */
import java.util.*;

public class LoginSystem {
	
	// hm stores 'username, password' pairs
	HashMap<String, String> hm;
	Scanner sc;
	
	public LoginSystem() {
		sc = new Scanner(System.in);
		hm = new HashMap<String, String>();
		
		// create a secure admin!
		// we will generate a long random password
		// so that nobody will be able to log in as admin!
		// how smart is that, huh?
		String password = generateSecurePassword();
		hm.put("admin", password);
	}
	
	public void register() {
		String name = "", password = "";
		// retrieve all usernames from hm
		// and save it a Set (think about it as an array with unique items)
		Set<String> usernames = hm.keySet();
		
		// get the username from the user and save it in 'name'
		p("Enter a unique username for registration: ");
		while (true) {
			name = sc.nextLine();
			
			// truncate all whitespaces from left and right
			// when checking if the username exists
			if (usernames.contains(name.trim())) {
				p("This name already exists, enter another one: ");
			}
			else {
				break;
			}
		}
		// we decided not to store usernames longer than 32 characters
		// so we will just cut the name until 32 
		if (name.length() > 32) {
			name = name.substring(0, 32);
		}
		
		// truncate all whitespaces from left and right before saving
		name = name.trim();
		
		// get the password from the user
		p("Enter the password at least 6 characters long: ");
		while (true) {
			password = sc.nextLine();
			if (password.length() < 6) {
				p("Password should be at least 6 characters long!\nEnter again: ");
			}
			else {
				break;
			}
		}
		
		// save the username and password to our hm database
		hm.put(name, password);
		pl("Username <" + name + "> has been successfully created!\nThe password is <" + password + ">.\nRemember not to give the password to anyone!");
	}
	
	public void login() {
		String name = "", password = "";
		p("Enter your username: ");
		name = sc.nextLine();
		p("Enter your password: ");
		password = sc.nextLine();
		
		// ladies and gents, this is just for fun
		if (name.equals("admin") && password.equals("magicallysecure")) {
			pl("\nYeah, it was worth trying :-P\nBut nope, wrong approach.\nTry breaking into this app from a different angle.\nBesides, admin's password is not magically secure\n but it is magically strong.");
			return;
		}
		else if (name.equals("admin") && (password.equals("magicallystrong") || password.equals("magically strong"))) {
			pl("Good try!\nBut still, wrong approach.\nTry another angle.");
			return;
		}
		
		// check if the name and the corresponding password are in our hm database
		if (hm.containsKey(name) && hm.get(name).equals(password)) {
			pl("Login successfull!");
			if (name.equals("admin")) {
				pl("");
				pl("CONGRATULATIONS! You completed the mission and\nfigured out how to break into the system as an admin!");
			}
			else {
				pl("But your goal is to login as admin, not as " + name + " :-P. Try again.");
			}
		}
		else {
			pl("Failed! Good luck next time.");
		}
	}
	
	public void list() {
		// print on the screen every username
		pl("Here is the full list of all users");
		for(String name : hm.keySet()) {
			pl("\t" + name);
		}
	}
	
	public void p(Object s) {
		System.out.print(s);
	}
	
	public void pl(Object s) {
		System.out.println(s);
	}
	
	public void intro() {
		pl("Welcome to the PseudoPassWorld!\nChoose your options wisely.\nAdmin has some sweet stuff, just saying.\n");
		pl("1. Register");
		pl("2. Login");
		pl("3. List all users");
		pl("4. Get outta here");
	}

	public void close () {
		sc.close();
	}
	
	private String generateSecurePassword() {
		String password = "magicallysecure";
		String caps     = "QWERTYUIOPASDFGHJKLZXCVBNM";
		String smalls   = "qwertyuiopasdfghjklzxcvbnm";
		String nums     = "1234567890";
		String syms     = "!@#$%^&*()_+-=<>?,./;:";
		
		String all = caps + smalls + nums + syms;
		
		Random rnd = new Random();
 
		// create a 64-character long password!
		// there is no way anyone can brute force that
		// this is not a reverse psychology
		// DO NOT try to brute force the password
		for (int i = 0; i < 64; i++) {
			password += all.charAt(rnd.nextInt(all.length()));
		}
		return password;
	}
}
