package TestCases;

import Utility.sendAttachmentInEmail;
import corConnectV1.dealerInvTransLookup;
import corConnectV1.headerSection;
import corConnectV1.loginPage;
import corConnectV1.menuPage;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class ditlTestCases {
    public headerSection hs;
    private WebDriver driver;
    private loginPage lp;
    private menuPage mp;
    private WebDriverWait wait;
    private sendAttachmentInEmail saie;
    private dealerInvTransLookup ditl;

    @Parameters("browser")

    @BeforeClass
    public void beforeClass(String browser)
    {
        if (browser.equalsIgnoreCase("firefox"))
        {
            driver = new FirefoxDriver();
            lp=new loginPage(driver);
            wait = new WebDriverWait(driver, 70);
            driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
            lp.get();
            driver.manage().window().maximize();
        }
        else if (browser.equalsIgnoreCase("chrome"))
        {
            System.setProperty("webdriver.chrome.driver",
                    "E:\\chromedriver.exe");
            driver = new ChromeDriver();
            lp=new loginPage(driver);
            wait = new WebDriverWait(driver, 70);
            driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
            lp.get();
            driver.manage().window().maximize();
        }
        else if (browser.equalsIgnoreCase("ie"))
        {
            System.setProperty("webdriver.ie.driver", "E:\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
            lp=new loginPage(driver);
            wait = new WebDriverWait(driver, 70);
            driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
            lp.get();
            driver.manage().window().maximize();
        }
        else
        {
            throw new IllegalArgumentException("The Browser Type is Undefined");
        }
    }

    @Before
    public void redirectUrl()
    {
        driver.get("https://corconnect-qa.corcentric.com/");
    }

    @Test
    public void ditlOpen() throws Exception
    {
        //redirectUrl();
        wait.until(ExpectedConditions.visibilityOf(lp.loginButton));
        lp.loginActivity("support", "abc");
        hs=new headerSection(driver);
        wait.until(ExpectedConditions.visibilityOf(hs.linkLogOut));
        mp=new menuPage(driver);
        wait.until(ExpectedConditions.visibilityOf(mp.homePageLabel));
        Assert.assertEquals(mp.getWelcomeText(),menuPage.homePageLabelText );
        System.out.println(mp.getWelcomeText());
        wait.until(ExpectedConditions.visibilityOf(mp.billingMenu));
        Actions action = new Actions(driver);
        action.moveToElement(mp.billingMenu).build().perform();
        wait.until(ExpectedConditions.visibilityOf(mp.menuDitl));
        mp.clickMenuDitl();
        ditl=new dealerInvTransLookup(driver);
        wait.until(ExpectedConditions.visibilityOf(ditl.ditlLabelText));
        Assert.assertEquals(ditl.getDtilLabelText(), dealerInvTransLookup.dtilLabelText);
        System.out.println(ditl.getDtilLabelText());
        System.out.println("1st step .....");
      /*  WebElement element = driver.findElement(By.xpath("//table[@id='ASPxSplitter1_RightPaneBodyPanel_CallbackPanel_MainPgCntrl_uc_gridview_vertical2_ASPxSplitter1_ASPxRoundPanel1_roundPnl_DynamicSearch_tblDynamicSearch_uc_gridview_vertical2']/tbody/tr[6]/td[2]"));
        Thread.sleep(2000);
        element.sendKeys("19dec01");
        Thread.sleep(2000);*/
        //String sCellValue = driver.findElement(By.xpath(".//*[@id='ASPxSplitter1_RightPaneBodyPanel_CallbackPanel_MainPgCntrl_uc_gridview_vertical3_ASPxSplitter1_ASPxRoundPanel1_roundPnl_DynamicSearch_lblAssoc_dealerInvNum_70']")).getText();
        //System.out.println(sCellValue);
     
        /*ditl.searchActivity("19dec01"); 
        wait.until(ExpectedConditions.visibilityOf(ditl.resetButton));*/

        //19dec01
    }

    @AfterClass
    public void afterClass()
    {
        try
        {
            driver.quit();

        }
        catch (Exception e)
        {
            driver = null;
            System.out.println(e.toString());

        }
    }

}