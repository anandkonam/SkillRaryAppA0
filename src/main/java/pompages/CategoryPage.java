package pompages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CategoryPage {
    //Declaration
	@FindBy(xpath="//h1[normalize-space(text())=\"Category\"]")
	private WebElement pageHeader;
	
	@FindBy(xpath="//a[.=\"New\"]")
}
