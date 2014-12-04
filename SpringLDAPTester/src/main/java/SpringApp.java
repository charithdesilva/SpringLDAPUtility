import com.chades.ldaptest.SpringAppTests;

/**
 * 
 * @author cdesilva
 *
 */

public class SpringApp {
	
	public static void main(String aa[]) {
		
		System.out.println("starting ...");
		SpringAppTests test = new SpringAppTests();
		test.testAuthentication();
	}


}
