package model;

import java.util.ArrayList;
import java.util.List;

public class Classroom {
	public String name;
	public List<UserAccount> users;
	
	public Classroom(){
		this.name = "Classroom";
		users = new ArrayList<UserAccount>();
	}
	public List<UserAccount> getUsers(){
		return users; 
	}
	public void createAccount(String n, String p, String pp, Gender g, String c, String bd, Browser fb ) {
		
		UserAccount newUser = new UserAccount(n, p, pp, g, c, bd, fb) ;
		users.add(newUser);
	}
	public boolean confirmLogIn(String name, String password) {
		if(findUser(name) > -1 && users.get(findUser(name)).getPassword().equalsIgnoreCase(password) ) {
			return true;
		}
		return false;
	}
	public int findUser(String n) {
		int a = 0;
		for(int i=0; i < users.size(); i++ ) {
			if(users.get(i).getName().equalsIgnoreCase(n)) {
				 return i;
			}
		}	
		return -1;
	}
}
