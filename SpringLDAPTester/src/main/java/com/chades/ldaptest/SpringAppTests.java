package com.chades.ldaptest;



import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ldap.AuthenticationException;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;

/**
 * 
 * @author cdesilva
 *
 */

public class SpringAppTests {
	
    @Autowired
    private ChadesDefaultSpringSecurityContextSource ldapTestServer;
    
    
    public void testAuthentication() {
    	
    	ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
    	this.ldapTestServer = context.getBean("ldapTestServer", ChadesDefaultSpringSecurityContextSource.class);
    	UsernamePasswordAuthenticationToken userNamePasswordAuth = new UsernamePasswordAuthenticationToken(ldapTestServer.getUserName(), ldapTestServer.getLdapPassword());
    	BindAuthenticator ldapBindAuthenticator = new BindAuthenticator(ldapTestServer);
    	DirContextOperations authenticated = null; 
    	
        ChadesLdapUserSearch myLdapUserSearch = new ChadesLdapUserSearch();
        myLdapUserSearch.init(ldapTestServer, ldapTestServer.getUserSearchFilter());
        
        ldapBindAuthenticator.setUserSearch(myLdapUserSearch);
        try {
			
        	authenticated  = ldapBindAuthenticator.authenticate(userNamePasswordAuth);
		} catch (AuthenticationException e) {
			System.out.println("Authentication unsuccessful for user :"+ldapTestServer.getUserName());
			System.err.println(e.getExplanation()); 
		}
        
        if(authenticated != null) {
        	
        	System.out.println("Authentication successful for user :"+ldapTestServer.getUserName());
        	
        	DefaultLdapAuthoritiesPopulator ldapAuthorizer = new DefaultLdapAuthoritiesPopulator(ldapTestServer, "");
        	ldapAuthorizer.setGroupRoleAttribute(ldapTestServer.getGroupRoleAttribute());
        	ldapAuthorizer.setGroupSearchFilter(ldapTestServer.getGroupSearchFilter());
        	ldapAuthorizer.setSearchSubtree(true);
        	ldapAuthorizer.setIgnorePartialResultException(true);
        	
        	Collection<GrantedAuthority> grantedAuthorities = ldapAuthorizer.getGrantedAuthorities(authenticated, ldapTestServer.getUserName());
        	
        	
        	if(null != grantedAuthorities) {
        		System.out.println("Granted authorities for user  "+ldapTestServer.getUserName()+" >> size "+grantedAuthorities.size());
        		System.out.println("=====================================================");
        		for(GrantedAuthority authority : grantedAuthorities){
        			System.out.println(authority.getAuthority());
        		}
        		System.out.println("=====================================================");
        	}
        	
        	System.out.println("");
        	
        	LdapAuthoritySearch allAuthoritiesSearch = new LdapAuthoritySearch();
        	allAuthoritiesSearch.init(ldapTestServer);
        	allAuthoritiesSearch.setAllGroupsFilter(ldapTestServer.getAllGroupsFilter());
        	allAuthoritiesSearch.setGroupRoleAttribute(ldapTestServer.getGroupRoleAttribute());
        	
        	List<String> authorities = allAuthoritiesSearch.findAllAuthorities();
        	
        	if(null != authorities) {
        		System.out.println("=====================================================");
        		System.out.println("All groups size "+authorities.size());
        		System.out.println("=====================================================");
        		System.out.println("Start retriving all groups");
        		System.out.println("=====================================================");
        		
        		for (String authority : authorities) {
        			System.out.println(authority);
        		}
        		
        	}
        }

        
    }

}
