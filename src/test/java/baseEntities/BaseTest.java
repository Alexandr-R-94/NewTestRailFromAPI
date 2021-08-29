package baseEntities;

import core.BrowsersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.Listener;


@Listeners(Listener.class)
public abstract class BaseTest {
    protected final Logger logger = LogManager.getLogger(this);
    public BrowsersService browsersService;

    @BeforeMethod
    public void startBrowser() {
        browsersService = new BrowsersService();
    }

    @AfterMethod
    public void closeBrowser() {
        browsersService.getDriver().quit();
        browsersService = null;
    }
}