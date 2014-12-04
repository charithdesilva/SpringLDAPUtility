package com.chades.ldaptest;

import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

public class ChadesDefaultSpringSecurityContextSource extends
		DefaultSpringSecurityContextSource {
	
	private String userName;
	private String userSearchFilter;
	private String groupSearchFilter;
	private String groupRoleAttribute;
	private String allGroupsFilter;

	public ChadesDefaultSpringSecurityContextSource(final String providerUrl,
			String userDn) {
		super(providerUrl);
		this.setUserDn(userDn.replace("%26", "&"));
		this.setPooled(true);
	}

	public String getLdapPassword() {
		return super.password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSearchFilter() {
		return userSearchFilter;
	}

	public void setUserSearchFilter(String userSearchFilter) {
		this.userSearchFilter = userSearchFilter;
	}

	public String getGroupSearchFilter() {
		return groupSearchFilter;
	}

	public void setGroupSearchFilter(String groupSearchFilter) {
		this.groupSearchFilter = groupSearchFilter;
	}

	public String getGroupRoleAttribute() {
		return groupRoleAttribute;
	}

	public void setGroupRoleAttribute(String groupRoleAttribute) {
		this.groupRoleAttribute = groupRoleAttribute;
	}

	public String getAllGroupsFilter() {
		return allGroupsFilter;
	}

	public void setAllGroupsFilter(String allGroupsFilter) {
		this.allGroupsFilter = allGroupsFilter.replace("%26", "&");
	}
	
}
