package Aug.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Aug.PageObjects.HomePage;
import Aug.PageObjects.LoginPage;
import Aug.Utils.retryclass;

public class LoginTest extends BaseTest {


	@Test(groups={"sanity"})
	public void Loginpage() {
		LoginPage login = new LoginPage(driver);
		HomePage Home = new HomePage(driver);

		login.Loginmethod();
		Assert.assertTrue(Home.verifylogo());

	}

	@Test(groups="regression")
	public void Loginpage1() {

		LoginPage login = new LoginPage(driver);
		HomePage Home = new HomePage(driver);

		login.Loginmethod();
		Home.verifylogo();
		Assert.assertTrue(Home.verifylogo());
	}


	@Test(enabled=false)
	public void Loginskipped() {

		LoginPage login = new LoginPage(driver);
		HomePage Home = new HomePage(driver);

		login.Loginmethod();
		Home.verifylogo();
		Assert.assertTrue(Home.verifylogo());
	}

	@Test(retryAnalyzer=retryclass.class)
	public void Loginandverify() {
		LoginPage login = new LoginPage(driver);
		HomePage Home = new HomePage(driver);

		login.Loginmethod();
		Home.verifylogo();
//		Assert.assertTrue(Home.verifylogo());
		Assert.fail();
	}
	
	@Test
	public void Loginpage3() {
		LoginPage login = new LoginPage(driver);
		HomePage Home = new HomePage(driver);

		login.Loginmethod();
		Home.verifylogo();
		
		Assert.assertTrue(Home.verifylogo());
	}
}
