package com.selenide.qa.selenide.files;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class SelenideFileTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "firefox";
    }

    @Test
    void selenideDownloadFile() throws IOException {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloadFile = $("[data-testid = raw-button]").download();

        try (InputStream input = new FileInputStream(downloadFile)) {
            byte[] bytes = input.readAllBytes();
            String text = new String(bytes, StandardCharsets.UTF_8);
            assertThat(text).contains("welcomed");
        }
    }

    @Test
    void selenideUploadFile() {

    }
}
