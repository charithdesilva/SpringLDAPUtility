package com.chades.ldaptest;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;

import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.ContextSource;
import org.springframework.security.ldap.SpringSecurityLdapTemplate;

public class LdapAuthoritySearch {

	private ContextSource contextSource;

	public void init(final ContextSource contextSource) {
		this.contextSource = contextSource;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<String> findAllAuthorities() {
		
		
		SpringSecurityLdapTemplate template = new SpringSecurityLdapTemplate(
				contextSource);
		SearchControls searchControls = new SearchControls();
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		template.setSearchControls(searchControls);
		template.setIgnorePartialResultException(true);

		List<String> result = template.search("", allGroupsFilter,searchControls,
         new AttributesMapper() {
            public String mapFromAttributes(Attributes attrs)
               throws NamingException {
				String val = null;
				if (attrs.get(groupRoleAttribute) != null) {
					val = attrs.get(groupRoleAttribute).get().toString();
				}
				return val;
            }
         }
		);
		
		if (result == null || result.size() == 0) {
			System.out.println("ldap filtered results size empty or null");
		}

		return result;
	}

	private String groupRoleAttribute;

	private static final int PAGE_SIZE = 20000;

	private String allGroupsFilter;

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
		this.allGroupsFilter = allGroupsFilter;
	}

}