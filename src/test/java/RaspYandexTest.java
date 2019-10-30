import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

@TestInstance(Lifecycle.PER_CLASS)
class RaspYandexTest {

	private static WebDriver driver;

	@BeforeAll
	static void initAll() {
		driver = new ChromeDriver();
	}
	
	@BeforeEach
	void Init() {
		driver.get("https://rasp.yandex.ru");
	}

	@Test
	void TestRouteSearchKemerovoMoscow() {
		RaspYandexSearchPage searchPage = new RaspYandexSearchPage(driver);
		RaspYandexResultPage resultPage = searchPage.findRoute("Кемерово", "Москва", "7 сентября");
		for (WebElement webElement : resultPage.getSearchSegment()) {
			int index = resultPage.getSearchSegment().indexOf(webElement);
			assertTrue(resultPage.getFligthName(index).isDisplayed());
			assertTrue(resultPage.getTravelTime(index).isDisplayed());
			assertTrue(resultPage.getTransportIcon(index).isDisplayed());
		}
		assertEquals(5, resultPage.getNumberOfFligths(),"Количество рейсов несоответствует.");
	}

	@Test
	void TestRouteSearchLeninaBakinskiy() {
		String str = "Пункт прибытия не найден. Проверьте правильность написания или выберите другой город.";
		RaspYandexSearchPage searchPage = new RaspYandexSearchPage(driver);
		RaspYandexResultPage resultPage = searchPage.findFaildRoute(
				"Кемерово проспект Ленина",
				"Кемерово Бакинский переулок",
				"среда");
		for (WebElement element : resultPage.getErrorTitles()) {
			if (element.getText().equals(str))
				System.out.println("Отображается ошибка: \"" + str + "\"");
		}
	}
	
	@AfterAll
	static void TearDownAll() {
		driver.quit();
	}

}
