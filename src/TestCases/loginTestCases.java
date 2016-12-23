package TestCases;
import Utility.propertyDataClass;
import Utility.sendAttachmentInEmail;
import Utility.testPdfReport;
import corConnectV1.headerSection;
import corConnectV1.loginPage;
import corConnectV1.menuPage;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

    public class loginTestCases {
        public headerSection hs;
        private WebDriver driver;
        private loginPage lp;
        private menuPage mp;
        private WebDriverWait wait;
        private sendAttachmentInEmail saie;

        private testPdfReport tpf;
        private propertyDataClass pdc;

        List<String> resultList=new ArrayList<String>();
        //create an instance of PdfUtilityClass
        testPdfReport pdfUtility=new testPdfReport();

        @Parameters("browser")

        @BeforeClass
        public void beforeClass(String browser)

        {
            if (browser.equalsIgnoreCase("firefox"))
            {
                driver = new FirefoxDriver();
                lp=new loginPage(driver);
                resultList.add("Step1 \n Open Chrome Browser \n Browser should open \n Browser Opened \n Result:Pass");
                wait = new WebDriverWait(driver, 70);
                driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
                lp.get();
                driver.manage().window().maximize();
            }
            else if (browser.equalsIgnoreCase("chrome"))
            {
                pdc = new propertyDataClass();
                System.setProperty("webdriver.chrome.driver",pdc.CHROMEDRIVERPATH);
                driver = new ChromeDriver();
                lp=new loginPage(driver);
                resultList.add("Step1 \n Open Chrome Browser \n Browser should open \n Browser Opened \n Result:Pass");
                wait = new WebDriverWait(driver, 70);
                driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
                lp.get();
                driver.manage().window().maximize();
            }
            else if (browser.equalsIgnoreCase("ie"))
            {
                pdc = new propertyDataClass();
                System.setProperty("webdriver.ie.driver", pdc.IEDRIVERPATH);
                driver = new InternetExplorerDriver();
                lp=new loginPage(driver);
                resultList.add("Step1 \n Open Chrome Browser \n Browser should open \n Browser Opened \n Result:Pass");
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
            //driver.get("https://corconnect-qa.corcentric.com/");
            propertyDataClass pdc = new propertyDataClass();
            System.out.println(pdc.URL);
            System.out.println(pdc.USERNAME);
            System.out.println(pdc.PASSWORD);
        }

        @Test(priority=0)
        public void blankPwd() throws Exception
        {
            System.out.println("1st step .....");
            redirectUrl();
            wait.until(ExpectedConditions.visibilityOf(lp.loginButton));
            System.out.println(pdc.USERNAME);
            lp.loginActivity(pdc.USERNAME, "");
            wait.until(ExpectedConditions.visibilityOf(lp.passwordValidateMsg));
            Assert.assertEquals(lp.getPwdValMsg(), loginPage.pwdValMsgText);
            resultList.add("Blank Password");
            resultList.add("Result: Pass");
        }

        @Test(priority=1)
        public void blankUser()
        {
            redirectUrl();
            wait.until(ExpectedConditions.visibilityOf(lp.loginButton));
            lp.loginActivity("", pdc.PASSWORD);
            wait.until(ExpectedConditions.visibilityOf(lp.userIdValidateMsg));
            Assert.assertEquals(lp.getUserIdValMsg(), loginPage.userIdValMsgText);
            resultList.add("Blank User Id");
            resultList.add("Result: Pass");
        }

        @Test(priority=2)
        public void blankUserPwd() {
            redirectUrl();
            wait.until(ExpectedConditions.visibilityOf(lp.loginButton));
            lp.loginActivity("", "");
            wait.until(ExpectedConditions.visibilityOf(lp.userIdValidateMsg));
            wait.until(ExpectedConditions.visibilityOf(lp.passwordValidateMsg));
            Assert.assertEquals(lp.getUserIdValMsg(), loginPage.userIdValMsgText);
            Assert.assertEquals(lp.getPwdValMsg(), loginPage.pwdValMsgText);
            resultList.add("Blank User Id and Password");
            resultList.add("Result: Pass");
        }

        @Test(priority=3)
        public void login() throws Exception {
            redirectUrl();
            wait.until(ExpectedConditions.visibilityOf(lp.loginButton));
            lp.loginActivity(pdc.USERNAME, pdc.PASSWORD);
            hs = new headerSection(driver);
            wait.until(ExpectedConditions.visibilityOf(hs.linkLogOut));
            mp = new menuPage(driver);
            wait.until(ExpectedConditions.visibilityOf(mp.homePageLabel));
            hs.clickLinkLogout();
            wait.until(ExpectedConditions.visibilityOf(lp.logoutValidateMsg));
            Assert.assertEquals(lp.getlogoutValMsgText(), loginPage.logoutValMsgText);
            resultList.add("Login Cases");
            resultList.add("Expected: User should be able to login");
            resultList.add("Actual: User logged in and Logout");
            resultList.add("Result: Pass");
        }

        @AfterMethod
        public void TearDown() throws COSVisitorException, IOException {
            String timeStamp = new SimpleDateFormat("dd-M-yyyy hh:mm:ss").format(Calendar.getInstance().getTime());
            //add time stamp to the resultList
            resultList.add("Test Ends: " + timeStamp);
            //write the test result pdf file with file name TestResult
            pdfUtility.WriteTestResultToPdfFile("TestResult.pdf", resultList);
        }

        @AfterClass
        public void afterClass()
        {
            try
            {
                driver.quit();
                Thread.sleep(3000);
                saie= new sendAttachmentInEmail(driver);
                saie.sentemail();

            }
            catch (Exception e)
            {
                driver = null;
                System.out.println(e.toString());

            }
        }

    }

