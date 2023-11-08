package genericLibraries;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import pompages.AddNewCoursePage;
import pompages.CourseListPage;

public class BaseClass {
    
	//@BeforeSuite
	//@BeforeTest
	
	@BeforeClass
	public void ClassConfig() {
		property = new PropertiesUtility();
		excel = new ExcelUtility();
		jutil = new JavaUtility();
		webutil = new WebDriverUtility();
		
		property.propertiesInitialization(IConstantPath.PROPERTIES_PATH);
		driver = webutil.launchBrowser(property.readFromProperties("browser"));
		
		sdriver = driver;
		sjutil = jutil;
	}
	
	@BeforeMethod
	
	public void methodConfig() {
		excel.excelInitialization(IConsatntPath.EXCEL_PATH);
		welcome = new WelcomPage(driver);
		login = new LoginPage(driver);
		home = new AdminHomePage(driver);
		users = new UsersPage(driver);
		course = new CourseListPage(driver);
		category = new categorypage(driver);
		addUser = new AddNewUserPage(driver);
		addCourse = new AddNewCoursePage(driver);
		addCategory = new AddNewCategoryPage(driver);
		
		webutil.navigateToApp(property.readFromProperties("url"));
		Assert.assertEquals(welcome.getLogo(), "SkillRary-ECommerce");
		
		long time = Long.parseLong(property.readFromProperties("timeouts"));
		webutil.waitTillElementFound(time);
		
		welcome.clickLoginButton();
		Assert.assertEquals(login.getPageHeader(), "Login");;
		
		login.setEmail(property.readFromProperties("username"));
		login.setPassword(property.readFromProperties("password"));
		login.clickLogin();
		Assert.assertEquals(home.getAdminIcon(), "SkillRary Admin");
		
	}
	@AfterMethod
	public void methodTearDown() {
		excel.closeExcel();
		home.signOutofApp();
	}
	@AfterClass
	public void ClassTearDown() {
		webutil.closeAllWindow();
	}
	//@AfterTest
	//@AfterSuite
}
