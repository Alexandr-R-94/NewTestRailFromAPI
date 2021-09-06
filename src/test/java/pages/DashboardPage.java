package pages;


import baseEntities.BasePage;
import core.BrowsersService;
import endpoints.TestRailPagesEndpoints;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {


    @FindBy(css = ".top-section.text-ppp")
    public WebElement titleLabel;

    @FindBy(id = "sidebar-projects-add")
    public WebElement addProjectBtn;

    @FindBy(id = "navigation-admin")
    public WebElement adminBtn;

    private static final String projectNameLabel = "//a[. = 'replace']";


    public DashboardPage(BrowsersService browsersService, boolean openPageByUrl) {
        super(browsersService, openPageByUrl);
    }

    @Override
    protected void openPage() {
        driver.get(properties.getURL()+ TestRailPagesEndpoints.DASHBOARD_PAGE);

    }

    @Override
    protected boolean isPageOpened() {
            try {
                return titleLabel.isDisplayed();
            } catch (NoSuchElementException ex) {
                return false;
            }
    }

    //Getters
   public WebElement getProjName(String projectName) {return driver.findElement(By.xpath(projectNameLabel.replace("replace", projectName)));}

    //Setters
    public void clickAddProjectButton() {addProjectBtn.click(); }
    public void clickAdministrationButton() {adminBtn.click(); }
    public void clickProjNameButton(String projectName) {getProjName(projectName).click(); }
}
