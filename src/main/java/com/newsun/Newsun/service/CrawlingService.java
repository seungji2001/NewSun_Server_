package com.newsun.Newsun.service;

import com.newsun.Newsun.domain.Post;
import com.newsun.Newsun.repository.PostRepository;
import jakarta.transaction.Transactional;
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
    private String url;

    // 1. 드라이버 설치 경로
    public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static String WEB_DRIVER_PATH = "/Users/seungjibaek/IdeaProjects/NewSun_Server/chromedriver";

    private final PostRepository postRepository;

    public CrawlingService(PostRepository postRepository) {
        this.postRepository = postRepository;
        // WebDriver 경로 설정
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        // 2. WebDriver 옵션 설정 -> 부하 줄일 수 있는 옵션들 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");
        options.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
        driver = new ChromeDriver(options);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("Object.defineProperty(navigator, 'webdriver', { get: () => undefined })");

    }

    @Transactional
    public void getItNews() throws InterruptedException {

        url = "https://www.itworld.co.kr/news/";

        try {
            driver.get(url);
            //읽는데 보통 2초 정도 걸린다
            Thread.sleep(2000); // 3. 페이지 로딩 대기 시간
            List<WebElement> elements = driver.findElements(By.xpath("/html/body/div[6]/div/div[2]/div/div[1]/div[2]//div/div[2]/div/h5/a"));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[6]/div/div[2]/div/div[1]/div[2]//div/div[2]/div/h5/a")));

            List<String> linkList = new ArrayList<>();
            // Extract URLs and store them in a list
            for (WebElement element : elements) {
                String href = element.getAttribute("href");
                if(href!=null)
                    linkList.add(href);
            }

            wait = new WebDriverWait(driver, Duration.ofSeconds(50));
            //링크 하나씩 들어가면서 제목 및 내용 크롤링
            for (String link : linkList) {
                try {
                    driver.get(link);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.className("node-title")));

                    WebElement titleElement = driver.findElement(By.className("node-title"));
                    String title = titleElement.getText();
                    WebElement contentElement = driver.findElement(By.className("node-body"));
                    String content = contentElement.getText().replace("\n", " ");

                    Post post = Post.builder()
                            .title(title)
                            .contents(content)
                            .categoryTypeName("IT")
                            .build();
                    postRepository.save(post);

                } catch (StaleElementReferenceException e) {
                    System.out.println("Stale element encountered. Retrying...");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.close(); // 5. 브라우저 종료
        }
    }
}
