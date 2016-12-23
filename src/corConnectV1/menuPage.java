package corConnectV1;

/**
 * Created by shahbaz on 12/13/2016.
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class menuPage {
    protected WebDriver driver;
    @FindBy(xpath = ".//*[@id='ASPxSplitter1_RightPaneBodyPanel_CallbackPanel_MainPgCntrl_ctl32_lblWelcomeText']")
    public WebElement homePageLabel;

    public static  String homePageLabelText="Welcome to Daimler System Management";

    public String getWelcomeText()

    {
        return homePageLabel.getText();
    }

    @FindBy (xpath = ".//*[@id='ASPxSplitter1_RightPaneHeadPanel_ViewMenu_DXI0_T']/span")
    public WebElement billingMenu;


    /*   WebElement element = driver.findElement(By.linkText("Product Category"));

       Actions action = new Actions(driver);

       action.moveToElement(element).build().perform();

       driver.findElement(By.linkText("iPads")).click();
       */
    //Dealer Invoice Transaction Lookup
    @FindBy (xpath=".//*[@id='ASPxSplitter1_RightPaneHeadPanel_ViewMenu_DXI0i0_T']/span")
    //(css="#ASPxSplitter1_RightPaneHeadPanel_ViewMenu_DXI0i0_T > span.dx-vam")
    public WebElement menuDitl;

    public void clickMenuDitl()
    {
        menuDitl.click();
    }

    public menuPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

}
