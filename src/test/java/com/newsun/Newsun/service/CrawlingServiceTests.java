package com.newsun.Newsun.service;

import com.newsun.Newsun.domain.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CrawlingServiceTests {
    @Autowired
    CrawlingService crawlingService;

    @Test
    public void setCrawlingService() throws InterruptedException {
       crawlingService.getItNews();
    }
}
