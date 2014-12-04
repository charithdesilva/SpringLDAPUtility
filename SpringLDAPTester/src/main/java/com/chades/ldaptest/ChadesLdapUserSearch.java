package com.chades.ldaptest;

import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.search.LdapUserSearch;

public class ChadesLdapUserSearch implements LdapUserSearch {

	private FilterBasedLdapUserSearch worker;

	public void init(final ContextSource contextSource, String userSearchFilter) {
		this.worker = new FilterBasedLdapUserSearch("", userSearchFilter,
				(BaseLdapPathContextSource) contextSource);
	}

	@Override
	public DirContextOperations searchForUser(String username)
			throws UsernameNotFoundException {
		return this.worker.searchForUser(username);
	}

}