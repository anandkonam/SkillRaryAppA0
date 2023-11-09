package testScripts;

import org.testng.annotations.Test;

public class CreateCategoryTest extends BaseClass{
	//This Case is passed
	@Test
	public void createCategoryTest() {
		home.clickCoursesTab();
		home.clickCategoryLink();
		soft.assertTrue(category.getPageHeader().contains("Category"));
		
	}

}
