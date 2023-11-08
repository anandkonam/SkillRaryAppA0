package testScripts;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.IConstantPath;

public class CreateCourseTest extends BaseClass {
	@Test
	public void createCourseTest() {
		SoftAssert soft = new SoftAssert();
		home.clickCoursesTab();
		home.clickCourseListLink();
		soft.assertTrue(course.getPageHeader().contains("Course List"));
		
		course.clickNewButton();
		Thread.sleep(3000);
		soft.assertEquals(addCourse.getPageHeader(), "Ass New Course");
		Map<String, String> map = excel.readFromExcel("Sheet1", "Add Course");
		String courseName = map.get("Name")+jutil.generateRandom(100);
		addCourse.setName(courseName);
		addCourse.selectCategory(webUtil,map.get("Category"));
		addCourse.setPrice(map.get("Price"));
		addCourse.uploadPhoto(map.get("Photo"));
		
		addCourse.setDescription(webUtil, map.get("Description"));
		addCourse.clickSaveButton();
		soft.assertTrue(course.getSuccessMessage().contains("Success"));
		
		boolean isPresent = false;
		List<WebElement> courseNameList = course.getCourseList();
		for(WebElement name : courseNameList) {
			if(name.getText().equals(courseName)) {
				isPresent = true;
				break;
			}
		}
		soft.assertTrue(isPresent);
		course.clickDeleteButton(courseName, driver);
		course.clickDelete();
		soft.assertTrue(course.getSuccessMessage().contains("Success"));
		if(course.getSuccessMessage().contains("Success"))
			excel.writeToExcel("Sheet1", "Add Course", "Pass", IConstantPath.EXCEL_PATH);
			else
				excel.writeToExcel("Sheet1", "Add Course", "Fail", IConstantPath.EXCEL_PATH);
				soft.assertAll();
	}
    
}
