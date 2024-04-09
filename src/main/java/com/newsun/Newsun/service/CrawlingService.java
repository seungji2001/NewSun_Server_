package com.newsun.Newsun.service;

import com.newsun.Newsun.dto.DanggnDto.DanggnRespoonseDto;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class CrawlingService {
    private WebDriver driver;
    private WebElement element;
    private String url;

    // 1. 드라이버 설치 경로
    public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static String WEB_DRIVER_PATH = "/Users/seungjibaek/IdeaProjects/NewSun_Server/chromedriver";

    public CrawlingService() {
        // WebDriver 경로 설정
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        // 2. WebDriver 옵션 설정 -> 부하 줄일 수 있는 옵션들 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");
        options.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
//        driver = driver.Chrome("/Applications/Google Chrome Dev.app/Contents/MacOS/chromedriver", chrome_options=options);
        driver = new ChromeDriver(options);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("Object.defineProperty(navigator, 'webdriver', { get: () => undefined })");

    }

    public List<DanggnRespoonseDto.SearchResultDto> getAll(String question) throws InterruptedException {

        url = "https://www.daangn.com/fleamarket/";

        try {
            driver.get(url);
            //읽는데 보통 2초 정도 걸린다
            Thread.sleep(2000); // 3. 페이지 로딩 대기 시간

            WebElement searchElement = null;
            boolean present;
            try {
                searchElement = driver.findElement(By.xpath("//*[@id=\"gnb-root\"]/div/div/div/div/span[1]/form/input"));
                present = true;
            } catch (NoSuchElementException e) {
                present = false;
            }

            //검색창에 검색어 입력
            assert searchElement != null;
            searchElement.sendKeys(question);
            searchElement.sendKeys(Keys.ENTER);

            Thread.sleep(3000); // 3. 페이지 로딩 대기 시간

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement nextPage = driver.findElement(By.className("more-btn"));
            for (int click_cnt = 0; click_cnt < 4; click_cnt++) {
                nextPage.click();
            }
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"flea-market-wrap\"]/article[" + 30 + "]")));

            Thread.sleep(5000); // 3. 페이지 로딩 대기 시간
            //검색 후 검색 결과 리스트로 반환 받기
            List<WebElement> resultElements = null;
            try {
                resultElements = driver.findElements(By.className("flea-market-article-link"));
                present = true;
            } catch (NoSuchElementException e) {
                present = false;
            }
            System.out.println(resultElements);


            List<DanggnRespoonseDto.SearchResultDto> list = new ArrayList<>();
            assert resultElements != null;
            for (int i = 0; i < resultElements.size(); i++) {
                list.add(
                        DanggnRespoonseDto.SearchResultDto.builder()
                                .link(resultElements.get(i).getAttribute("href"))
                                .title(resultElements.get(i).findElement(By.className("article-title")).getAttribute("innerText"))
                                .content(resultElements.get(i).findElement(By.className("article-content")).getAttribute("innerText"))
                                .region(resultElements.get(i).findElement(By.className("article-region-name")).getAttribute("innerText"))
                                .price(resultElements.get(i).findElement(By.className("article-price")).getAttribute("innerText"))
                                .build()
                );
            }

            return list;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.close(); // 5. 브라우저 종료
        }
        return null;
    }
}
