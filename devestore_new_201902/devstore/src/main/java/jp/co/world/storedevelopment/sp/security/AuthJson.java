package jp.co.world.storedevelopment.sp.security;

import java.util.Arrays;
import java.util.List;

public class AuthJson {

	private Long id = Long.MIN_VALUE;

	private String name = "";

	private String code = "";

	private List<String> permissions = Arrays.asList();
	
	private String dataUser = "";

	public AuthJson(String name, Long id, String code, List<String> permissions) {
		setId(id);
		setName(name);
		setCode(code);
		setPermissions(permissions);
	}

	public AuthJson(String name, Long id, String code, List<String> permissions, String dataUser) {
		this(name, id, code, permissions);
		setDataUser(dataUser);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDataUser() {
		return dataUser;
	}

	public void setDataUser(String dataUser) {
		this.dataUser = dataUser;
	}

}
