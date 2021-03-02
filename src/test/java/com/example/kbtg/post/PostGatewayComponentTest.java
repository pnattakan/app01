package com.example.kbtg.post;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class PostGatewayComponentTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(8089).httpsPort(8443));

    @Autowired
    private PostGateway postGateway;

    @Test
    public void getPostById() throws IOException {

        stubFor(get(urlPathEqualTo("/posts/1"))
                .willReturn(aResponse()
                        .withBody(read("classpath:postApiResponse.json"))
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)));

        Optional<PostResponse> postResponse = postGateway.getPostById(1);
        assertEquals(11, postResponse.get().getId());
        assertEquals(11, postResponse.get().getUserId());
        assertEquals("Test Title", postResponse.get().getTitle());
        assertEquals("Test Body", postResponse.get().getBody());
    }

    public static String read(String filePath) throws IOException {
        File file = ResourceUtils.getFile(filePath);
        return new String(Files.readAllBytes(file.toPath()));
    }
}
