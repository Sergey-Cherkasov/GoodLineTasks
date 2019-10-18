package com.svcdev;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RaspYandex
{

	private WebDriver driver;
	private WebElement element;
	private static final String URL = "https://rasp.yandex.ru";
	
	@Before
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "d:/eclipse/webdrivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}
	
//	@Ignore
	@Test
	public void test1()
	{
		driver.get(URL);
		element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("from")));
		element.click();
		element.sendKeys("Кемерово");
		
		element = driver.findElement(By.id("to"));
		element.click();
		element.sendKeys("Москва");
		
		element = driver.findElement(By.id("when"));
		element.click();
		element = driver.findElement(By.cssSelector(".Month:nth-child(2) .CalendarDay:nth-child(23)"));
		element.click();
		
		element.submit();

		List<WebElement> elements =	(new WebDriverWait(driver, 10)).until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.cssSelector(".SearchSegment")));

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

		assertEquals("Должно отобразиться 5 рейсов", 5, elements.size());
		
	}

	@Test
	public void test2()
	{
		String str = "Пункт прибытия не найден. Проверьте правильность написания или выберите другой город.";
		String strElement;
		driver.get(URL);
		element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("from")));
		element.click();
		element.sendKeys("Кемерово проспект Шахтеров");
		
		element = driver.findElement(By.id("to"));
		element.click();
		element.sendKeys("Кемерово Бакинский переулок");
		
		element = driver.findElement(By.id("when"));
		element.click();
		element = driver.findElement(By.cssSelector(".Month:nth-child(2) .CalendarDay:nth-child(23)"));
		element.click();
		
		element = driver.findElement(By.cssSelector(".RadioButton:nth-child(5) > .RadioButton__buttonLable"));
		element.click();
		
		driver.findElement(By.cssSelector(".SearchForm__submit .Button__title")).click();
		
		element = (new WebDriverWait(driver, 10)).until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector(".ErrorPageSearchForm__title:nth-child(2)")));
		strElement = element.getText();
		System.out.println(strElement.equals(str));
		assertEquals(strElement, str);
		
	}
	
	@After
	public void tearDown()
	{
		driver.quit();
	}

}
