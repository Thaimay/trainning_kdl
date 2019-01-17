package mybatis.mapper.entity;

public class Student {
	private int id;
	private String name;
	private String branch;
	private int percentage;
	private int phone;
	private String email;
	public Student() {
		
	}
	public Student(int id, String name, String branch, int percentage, int phone, String email) {
		this.id = id;
		this.name = name;
		this.branch = branch;
		this.percentage = percentage;
		this.phone = phone;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "[" + "id = " + id + ", name = " + name + ", branch = " + branch 
                + ", percentage = " + percentage  + ", phone = " + phone  + ", email = " + email + "]";
	}
}
