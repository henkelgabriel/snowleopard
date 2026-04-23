package model;

public class Lead {
private int id;
private String name;
private String phone;
private String email;
private String company;

public Lead(String name, String phone, String email, String company) {
	this.name=name;
	this.phone=phone;
	this.email=email;
	this.company=company;
}
public int getID() {
	return id;
}
public void setID(int id) {
	this.id=id;
}
public String getName(){ 
	return name; 
	}
public String getPhone(){
	return phone; 
	}
public String getEmail() {
	return email;
	}
public String getCompany() { 
	return company; 
	}
public String toString() {
    return "Lead{name=" + name + ", phone=" + phone + ", email=" + email + ", company=" + company + "}";
}
}
