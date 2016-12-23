package corConnectV1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class dealerInvTransLookup {

    protected WebDriver driver;

    @FindBy (xpath =".//*[@id='ASPxSplitter1_RightPaneBodyPanel_CallbackPanel_MainPgCntrl_uc_gridview_vertical2_lblTitleGridView_101']") // add s in span
    public WebElement ditlLabelText;

    public static  String dtilLabelText="Dealer Invoice Transaction Lookup";

    public String getDtilLabelText()

    {
        return ditlLabelText.getText();
    }

    @FindBy (xpath="//table[@id='ASPxSplitter1_RightPaneBodyPanel_CallbackPanel_MainPgCntrl_uc_gridview_vertical2_ASPxSplitter1_ASPxRoundPanel1_roundPnl_DynamicSearch_tblDynamicSearch_uc_gridview_vertical2']/tbody/tr[6]/td[2]")
    //@FindBy (xpath =".//*[@id='ASPxSplitter1_RightPaneBodyPanel_CallbackPanel_MainPgCntrl_uc_gridview_vertical3_ASPxSplitter1_ASPxRoundPanel1_roundPnl_DynamicSearch_txtFilter_dealerInvNum_70_I']")
    public WebElement dealerInvNoField;

    @FindBy (xpath=".//*[@id='ASPxSplitter1_RightPaneBodyPanel_CallbackPanel_MainPgCntrl_uc_gridview_vertical3_ASPxSplitter1_ASPxRoundPanel1_roundPnl_DynamicSearch_btnSearchDynamic_CD']/span")
    public WebElement searchButton;

    @FindBy (xpath = ".//*[@id='ASPxSplitter1_RightPaneBodyPanel_CallbackPanel_MainPgCntrl_uc_gridview_vertical3_ASPxSplitter1_ASPxRoundPanel2_callbkPnl_GridView_UC_GridView_Data1_btnReset_CD']/span")
    public WebElement resetButton;

    public void searchActivity(String invNo)
    {
        dealerInvNoField.clear();
        dealerInvNoField.sendKeys(invNo);
        searchButton.click();
    }

    public dealerInvTransLookup(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
}
