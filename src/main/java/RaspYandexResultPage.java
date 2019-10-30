
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RaspYandexResultPage {

	private WebDriver driver;

/*
 * Описание локаторов WebElements класса RaspYandexResultPage.
 */
	
	private By searchSegmentLocator = By.cssSelector(".SearchSegment");
	private By fligthNameLocator = By.cssSelector(".SegmentTitle__number");
	private By travelTimeLocator = By.cssSelector(".SearchSegment__duration");
	private By vehicleIconLocator = By.cssSelector(".TransportIcon__icon");
	private By errorTitlesLocator = By.cssSelector(".ErrorPageSearchForm__title");
	
// Переопределение конструктора объекта класса

	public RaspYandexResultPage(WebDriver driver) {
		this.driver = driver;
	}
/* 
 * Методы класса:
 * 1. Возвращает список элементов с ошибками со страницы результатов
 *    в случае неудачного поиска.
 * 2. Возвращает элемент с названием рейса.
 * 3. Возвращает количество найденных маршрутов.
 * 4. Возвращает элемент с изображением транспорта.
 * 5. Возвращает элемент с данными о времени в пути.
 * 6. Возвращает список элементов с данными о найденных маршрутах.
 */
	public List<WebElement> getErrorTitles() {
		return driver.findElements(errorTitlesLocator);
	}

	public WebElement getFligthName(int indexWebElement) {
		return getSearchSegment().get(indexWebElement).findElement(fligthNameLocator);
	}

	public Integer getNumberOfFligths() {
		return getSearchSegment().size();
	}

	public WebElement getTransportIcon(int indexWebElement) {
		return getSearchSegment().get(indexWebElement).findElement(vehicleIconLocator);
	}
	
	public WebElement getTravelTime(int indexWebElement) {
		return getSearchSegment().get(indexWebElement).findElement(travelTimeLocator);
	}

	public List <WebElement> getSearchSegment() {		
		return driver.findElements(searchSegmentLocator);
	}

}
