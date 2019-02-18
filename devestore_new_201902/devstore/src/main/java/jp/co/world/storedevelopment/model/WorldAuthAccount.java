package jp.co.world.storedevelopment.model;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.WorldAuthAccountModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.sp.security.AuthJson;
import jp.co.world.storedevelopment.utils.PasswordUitls;

public class WorldAuthAccount extends com.tierline.mybatis.activemodel.ActiveModel<WorldAuthAccount>
		implements UserDetails {

	private static final long serialVersionUID = 2878624527718074700L;

	private Long uid = 0L;

	private String email = "";

	private String pass = "";

	private String syaincd = "";

	private String shimei = "";

	private int approvalFlg = 0;

	private Optional<Role> role = null;

	public WorldAuthAccount() {
	}

	public WorldAuthAccount(String email, String password) {
		this.email = email;
		setRawPassword(password);
	}

	public WorldAuthAccount(String email, String password, String name) {
		this(email, password);
		shimei = name;
	}

	@Override
	public String getTableName() {
		return "employee";
	}

	@Override
	public String primaryKeyName() {
		return "uid";
	}

	private static String[] ignoreFields = new String[] { "username", "authorities", "password", "systemAccount",
			"accountNonExpired", "accountNonLocked", "credentialsNonExpired", "enabled", "permissionList", "role" };

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	private void setRawPassword(String raw) {
		setPass(PasswordUitls.encrypt(raw));
	}

	@Override
	protected ModelMapper<WorldAuthAccount> modelMapper(SqlSession session) {
		return session.getMapper(WorldAuthAccountModelMapper.class);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return getPass();
	}

	public String getPass() {
		return pass;
	}

	@Override
	public String getUsername() {
		return getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getEmail() {
		return email;
	}

	public Optional<Account> getSystemAccount() {
		return new AccountRepository().findByCode(getSyaincd());
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uID) {
		uid = uID;
	}

	@Override
	protected String getPrimaryKey() {
		return getUid().toString();
	}

	@Override
	protected void setPrimaryKey(Object id) {
		setUid(Long.valueOf(id.toString()));
	}

	public String getShimei() {
		return shimei;
	}

	public void setShimei(String shimei) {
		this.shimei = shimei;
	}

	public List<String> permissionList() {
		Account account = getSystemAccount().orElseThrow(() -> new IllegalStateException("アカウントが存在しません"));
		return account.getRole().getPermissions();
	}

	public Object createAuthJson() {
		Account account = getSystemAccount().orElseGet(() -> {
			throw new IllegalArgumentException("アカウントが存在しません");
		});
		return new AuthJson(getShimei(), account.getId(), account.getEmployeCode(), permissionList(), account.fetchDataUser());
	}

	public int getApprovalFlg() {
		return approvalFlg;
	}

	public void setApprovalFlg(int approvalFlg) {
		this.approvalFlg = approvalFlg;
	}

	public String getSyaincd() {
		return syaincd;
	}

	public void setSyaincd(String syaincd) {
		this.syaincd = syaincd;
	}

}
