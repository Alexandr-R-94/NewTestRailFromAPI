package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.Waits;

public class BrowsersService {
    private WebDriver driver = null;
    private Waits waiters;
    protected final Logger logger = LogManager.getLogger(this);

    public BrowsersService() {
        switch (ReadProperties.getInstance().getBrowserName().toLowerCase()) {
            case "chrome":
                logger.warn("Произведён запуск браузера Chrome");
                WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setHeadless(ReadProperties.getInstance().isHeadless());
                chromeOptions.addArguments("--disable-gpu");
                logger.info("Отключен режим аппаратного ускорения графического процессора");
                chromeOptions.addArguments("--start-maximized");
                logger.info("Браузер запущен в режиме максимального окна");
                driver = new ChromeDriver(chromeOptions);
                break;
// Почему-то запускается вместе с Хромом, хотя в пропертях не прописан
            case "firefox":
                logger.warn("Для запуска необходимо установить браузер Firefox");
                WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
                logger.info("Данный браузер плохо работает с Selenium. Возможны баги и не открытие некоторых страниц");

           driver = new FirefoxDriver();
            break;

            default:
                logger.fatal("Browser " + ReadProperties.getInstance().getBrowserName() + " is not supported.");
        }

        waiters = new Waits(driver, ReadProperties.getInstance().getTimeOut());
    }

        public WebDriver getDriver() {
            return driver;
        }

        public Waits getWaiters () {
            return waiters;
        }

        public void sleep ( long millis){
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                logger.warn(e);
            }
        }
    }

