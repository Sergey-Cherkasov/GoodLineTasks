
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RaspYandexSearchPage {

	private WebDriver driver;

	@FindBy(id = "from")
	private WebElement searchFrom;

	@FindBy(id = "to")
	private WebElement searchTo;

	@FindBy(id = "when")
	private WebElement searchWhen;
	
	@FindBy(css = "label:nth-child(5)> span[class='RadioButton__buttonLable']")
	private WebElement searchBusRadioButton;
	
	@FindBy(css = ".SearchForm__submit.Button")
	private WebElement searchButton;
	
//	Конструктор объекта класса
	public RaspYandexSearchPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

// Методы класса
	
	public RaspYandexResultPage findRoute(String from, String to, String when) {
		searchFrom.clear();
		searchFrom.sendKeys(from);
		searchTo.clear();
		searchTo.sendKeys(to);
		searchWhen.clear();
		searchWhen.sendKeys(when);
		searchButton.click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		return new RaspYandexResultPage(driver);
	}

	public RaspYandexResultPage findFaildRoute(String from, String to, String when) {
		searchFrom.clear();
		searchFrom.sendKeys(from);
		searchTo.clear();
		searchTo.sendKeys(to);
		searchWhen.clear();
		searchWhen.sendKeys(when);
		searchBusRadioButton.click();
		searchButton.click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return new RaspYandexResultPage(driver);
	}

}
