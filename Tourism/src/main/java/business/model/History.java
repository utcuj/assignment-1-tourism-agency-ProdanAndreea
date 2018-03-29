package business.model;

import java.sql.Timestamp;

/*
 * id_history int not null unique auto_increment primary key,
id_user int not null,
id_product int not null,
quantity int not null,
foreign key (id_user) references `user`(id_user)
 */

// DATETIME values in 'YYYY-MM-DD HH:MM:SS
public class History {

	private int id_history;
	private int id_user;
	private String change;
	private Timestamp date;

	
	public History(int id_history, int id_user, String change, Timestamp date) {
		this.id_history = id_history;
		this.id_user = id_user;
		this.change = change;
		this.date = date;
	}
	
	public History(int id_user, String change) {
		this.id_user = id_user;
		this.change = change;
	}
	
	public History(String change, Timestamp date) {
		this.change = change;
		this.date = date;
	}
	
	public int getIdHistory() {
		return id_history;
	}
	
	public int getIdUser() {
		return id_user;
	}
	
	public String getChange() {
		return change;
	}
	
	public Timestamp getDate() {
		return date;
	}
	
	
	
	
	
	
}
