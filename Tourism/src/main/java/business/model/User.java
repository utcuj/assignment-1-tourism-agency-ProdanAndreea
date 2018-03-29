package business.model;

public class User {
	
	private int id_user; // use id as primary key bc primary key cannot change, but username can change
	private String username;
	private String password;
	private String name;
	private enum Type {AGENT, ADMIN};
	private Type type;
	
	public User(String username, String password, String name, String type) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.type = Type.valueOf(type);
	}
	
	public User(int id_user, String username, String password, String name) {
		this.id_user = id_user;
		this.username = username;
		this.password = password;
		this.name = name;
	}
	
	public int getId() {
		return id_user;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type.toString();
	}
	
	public void setType(String type) {
		this.type = Type.valueOf(type);
	}
	
	

}
