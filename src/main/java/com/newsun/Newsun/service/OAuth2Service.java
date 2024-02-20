package com.newsun.Newsun.service;

import com.newsun.Newsun.type.ELoginProvider;
import com.newsun.Newsun.util.OAuth2Util;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class OAuth2Service {

    public final OAuth2Util oAuth2Util;

    public String getRedirectUrl(ELoginProvider provider) {
        if (provider == ELoginProvider.GOOGLE) {
            return oAuth2Util.getGoogleRedirectUrl();
        }
        return null;
    }
}
