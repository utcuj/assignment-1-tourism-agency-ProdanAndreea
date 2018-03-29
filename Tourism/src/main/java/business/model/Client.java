package business.model;

// name, identity card number, personal numerical code, address
public class Client {

	private int id_client;
	private String name;
	private String card_no;
	private String cnp;
	private String address;
	private String phone;
	private String email;
	
	
	public Client(int id_client, String name, String card_no, String cnp, String address, String phone, String email) {
		this.id_client = id_client;
		this.name = name;
		this.card_no = card_no;
		this.cnp =cnp;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
	
	public Client(String name, String card_no, String cnp, String address, String phone, String email) {
		this.name = name;
		this.card_no = card_no;
		this.cnp =cnp;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
	
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id_client;
	}
	
	public String getCardNo() {
		return card_no;
	}
	
	public String GetCnp() {
		return cnp;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getEmail() {
		return email;
	}
		
}
