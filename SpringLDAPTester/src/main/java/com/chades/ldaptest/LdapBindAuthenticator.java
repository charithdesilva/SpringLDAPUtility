package com.chades.ldaptest;

import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;

public class LdapBindAuthenticator extends BindAuthenticator
{
    public LdapBindAuthenticator(final BaseLdapPathContextSource contextSource)
    {
        super(contextSource);
    }
    
}
