package pages;

import baseEntities.BasePage;
import core.BrowsersService;
import endpoints.TestRailPagesEndpoints;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage {

    //Селекторы
    @FindBy(id = "name")
    public WebElement username;

    @FindBy(id = "password")
    public WebElement password;

    @FindBy(id = "button_primary")
    public WebElement loginBtn;

    @FindBy(css = ".loginpage-message-image")
    public WebElement nullTitle;

    @FindBy(css = ".error-on-top")
    public WebElement errorTitle;

    //Конструкторы
    public LoginPage(BrowsersService browsersService, boolean openPageByUrl) {
        super(browsersService, openPageByUrl);
    }

    @Override
    protected void openPage() {
        driver.get(properties.getURL()+ TestRailPagesEndpoints.LOGIN_PAGE);
    }

    @Override
    protected boolean isPageOpened() {
      try { return loginBtn.isDisplayed(); }
      catch (NoSuchElementException ex){
          return false;
      }
        }

        //Setters
        public void setUsername(String text) {username.sendKeys(text);}
        public void setPassword(String text) {password.sendKeys(text);}
        public void clickLoginButton() {loginBtn.click();}
    }

