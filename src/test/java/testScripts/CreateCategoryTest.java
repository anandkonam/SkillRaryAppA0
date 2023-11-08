package testScripts;

import org.testng.annotations.Test;

public class CreateCategoryTest extends BaseClass{
	
	@Test
	public void createCategoryTest() {
		home.clickCoursesTab();
		home.clickCategoryLink();
		soft.assertTrue(category.getPageHeader().contains("Category"));
		
	}

}
