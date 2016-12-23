package corConnectV1;

/**
 * Created by shahbaz on 12/8/2016.
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class headerSection {

    protected WebDriver driver;

    @FindBy (xpath =".//*[@id='ASPxSplitter1_RightPaneHeadPanel_btnLogOut_CD']/span")
    public WebElement linkLogOut;


    public headerSection(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public loginPage clickLinkLogout() {
        linkLogOut.click();
        return new loginPage(driver);
    }

}



// $x("//text()[contains(.,'Logout')]")
// @FindBy(css="span.dx-vam")
// @FindBy(id="ASPxSplitter1_RightPaneHeadPanel_btnLogOut_CD")
