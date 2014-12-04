/**
 * 
 */
package com.chades.ldaptest;

import org.junit.Test;

import com.chades.ldaptest.SpringAppTests;

/**
 * @author cdesilva
 *
 */
public class TestLDAP {

	@Test
	public void test() {
		
		System.out.println("starting ...");
		SpringAppTests test = new SpringAppTests();
		test.testAuthentication();
	}

}
