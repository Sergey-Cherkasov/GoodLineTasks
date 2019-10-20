 

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
//import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RaspYandex
{

	private WebDriver driver, driverOpera, driverGecko;
	private WebElement element;
	private static final String URL = "https://rasp.yandex.ru";
	
	@Before
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "WebDrivers/chromedriver.exe");
//		System.setProperty("webdriver.gecko.driver", "WebDrivers/geckodriver.exe");
//		System.setProperty("webdriver.opera.driver", "WebDrivers/operadriver.exe");
		driver = new ChromeDriver();
//		driverGecko = new FirefoxDriver();
//		driverOpera = new OperaDriver();
//		driver.manage().deleteAllCookies();
//		driver.manage().window().maximize();
	}
	
/*	
 *	Поиск транспорта по направлению.
 */
	//	@Ignore
	@Test
	public void test1()
	{
//		1. Пользователь открывает сайт https://rasp.yandex.ru 
		driver.get(URL);
//		driverGecko.get(URL);
//		driverOpera.get(URL);

//		2. Вводит пункт отправления “Кемерово”
		element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("from")));
//		element = (new WebDriverWait(driverGecko, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("from")));
//		element = (new WebDriverWait(driverOpera, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("from")));
		element.click();
		element.sendKeys("Кемерово");

//		3. Вводит пункт назначения “Москва”
		element = driver.findElement(By.id("to"));
//		element = driverGecko.findElement(By.id("to"));
//		element = driverOpera.findElement(By.id("to"));
		element.click();
		element.sendKeys("Москва");

//		4. Вводит дату 7 сентября 
		element = driver.findElement(By.id("when"));
//		element = driverGecko.findElement(By.id("when"));
//		element = driverOpera.findElement(By.id("when"));
		element.clear();
		element.sendKeys("7 сентября");
//		element.click();
//		element = driver.findElement(By.cssSelector(".Month:nth-child(2) .CalendarDay:nth-child(23)"));
//		element.click();

//		5. Нажимает Найти
		driver.findElement(By.cssSelector(".SearchForm__submit .Button__title")).click();
//		driverGecko.findElement(By.cssSelector(".SearchForm__submit .Button__title")).click();
//		driverOpera.findElement(By.cssSelector(".SearchForm__submit .Button__title")).click();
//		element.submit();

/*
 *		6. Проверяет, что отображается название рейса.
 *		7. Проверяет, что у направления есть время в пути.
 *		8. Проверяет, что у всех рейсов есть иконка транспорта.
 *		9. Проверяет, что рейсов отобразилось 5. 
 */
		List<WebElement> elements =	(new WebDriverWait(driver, 10)).until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.cssSelector(".SearchSegment")));
//		List<WebElement> elements =	(new WebDriverWait(driverGecko, 10)).until(ExpectedConditions
//				.presenceOfAllElementsLocatedBy(By.cssSelector(".SearchSegment")));
//		List<WebElement> elements =	(new WebDriverWait(driverOpera, 10)).until(ExpectedConditions
//				.presenceOfAllElementsLocatedBy(By.cssSelector(".SearchSegment")));

		int i =0 ;
		for (WebElement webElement : elements)
		{
			++i;
			System.out.println(elements.get(elements.indexOf(webElement))
					.findElement(By.cssSelector(".SegmentTitle__number")).getText());
			assertTrue(Integer.toString(i) + " element is displayed", elements.get(elements.indexOf(webElement))
					.findElement(By.cssSelector(".SegmentTitle__number")).isDisplayed());

			System.out.println(elements.get(elements.indexOf(webElement))
					.findElement(By.cssSelector(".SearchSegment__duration")).getText());
			assertTrue(Integer.toString(i) + " element is displayed", elements.get(elements.indexOf(webElement))
					.findElement(By.cssSelector(".SearchSegment__duration")).isDisplayed());

			System.out.println(elements.get(elements.indexOf(webElement))
					.findElement(By.cssSelector(".TransportIcon__icon")).isDisplayed());
			assertTrue(Integer.toString(i) + " element is displayed", elements.get(elements.indexOf(webElement))
					.findElement(By.cssSelector(".TransportIcon__icon")).isDisplayed());
		}

		if (5 != elements.size())
		{
			System.out.println("Отображается " + elements.size() + " рейса(ов) вместо 5.");
		}
		assertEquals("Должно отобразиться 5 рейсов", 5, elements.size());
	}

/*	
 *	Поиск транспорта по нехорошему направлению.
 */
	@Test
	public void test2()
	{
		String str = "Пункт прибытия не найден. Проверьте правильность написания или выберите другой город.";
		String strElement;

//		1. Пользователь открывает сайт https://rasp.yandex.ru
		driver.get(URL);
//		driverGecko.get(URL);
//		driverOpera.get(URL);

//		2. Вводит пункт отправления “Кемерово проспект Ленина”
		element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("from")));
//		element = (new WebDriverWait(driverGecko, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("from")));
//		element = (new WebDriverWait(driverOpera, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("from")));
		element.click();
		element.sendKeys("Кемерово проспект Шахтеров");

//		3. Вводит пункт назначения “Кемерово Бакинский переулок”
		element = driver.findElement(By.id("to"));
//		element = driverGecko.findElement(By.id("to"));
//		element = driverOpera.findElement(By.id("to"));
		element.click();
		element.sendKeys("Кемерово Бакинский переулок");

//		4. Вводит дату на ближайшую среду
		element = driver.findElement(By.id("when"));
//		element = driverGecko.findElement(By.id("when"));
//		element = driverOpera.findElement(By.id("when"));
		element.clear();
		element.sendKeys("среда");
//		element.click();
//		element = driver.findElement(By.cssSelector(".Month:nth-child(2) .CalendarDay:nth-child(23)"));
//		element.click();

//		5. Нажимает на «Автобус»
		element = driver.findElement(By.cssSelector(".RadioButton:nth-child(5) > .RadioButton__buttonLable"));
//		element = driverGecko.findElement(By.cssSelector(".RadioButton:nth-child(5) > .RadioButton__buttonLable"));
//		element = driverOpera.findElement(By.cssSelector(".RadioButton:nth-child(5) > .RadioButton__buttonLable"));
		element.click();
//		5. Нажимает Найти
		driver.findElement(By.cssSelector(".SearchForm__submit .Button__title")).click();
//		driverGecko.findElement(By.cssSelector(".SearchForm__submit .Button__title")).click();
//		driverOpera.findElement(By.cssSelector(".SearchForm__submit .Button__title")).click();

/*
 *		6. Проверяет, что отображается ошибка с текстом «Пункт прибытия не найден.
 *		Проверьте правильность написания или выберите другой город.»
 */
		element = (new WebDriverWait(driver, 10)).until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector(".ErrorPageSearchForm__title:nth-child(2)")));
//		element = (new WebDriverWait(driverGecko, 10)).until(ExpectedConditions
//				.presenceOfElementLocated(By.cssSelector(".ErrorPageSearchForm__title:nth-child(2)")));
//		element = (new WebDriverWait(driverOpera, 10)).until(ExpectedConditions
//				.presenceOfElementLocated(By.cssSelector(".ErrorPageSearchForm__title:nth-child(2)")));
		strElement = element.getText();
		if (strElement.equals(str))
		{
			System.out.println("Отображается ошибка: \"" + strElement + "\"");
		}
		assertEquals(strElement, str);
	}
	
	@After
	public void tearDown()
	{
		driver.quit();
//		driverGecko.quit();
//		driverOpera.quit();
	}

}
