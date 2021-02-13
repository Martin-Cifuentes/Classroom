package model;

public class UserAccount {
	private String name;
	private String password;
	private String profilePicture;
	private Gender gender;
	private String careers;
	private String birthday;
	private Browser favBrowser;
	
	
	public UserAccount(String n, String p, String pp, Gender g, String c, String bd, Browser fb) {
		this.setName(n);
		this.setPassword(p);
		this.setProfilePicture(pp);
		this.setGender(g);
		this.setCareers(c);
		this.setBirthday(bd);
		this.setFavBrowser(fb);
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Browser getFavBrowser() {
		return favBrowser;
	}

	public void setFavBrowser(Browser favBrowser) {
		this.favBrowser = favBrowser;
	}

	public String getCareers() {
		return careers;
	}

	public void setCareers(String careers) {
		this.careers = careers;
	}
}
