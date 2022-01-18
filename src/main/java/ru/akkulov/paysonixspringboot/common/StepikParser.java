package ru.akkulov.paysonixspringboot.common;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Slf4j
@Component
public class StepikParser {

  @Value(("${login}"))
  private String login;

  @Value(("${password}"))
  private String password;

  @Value(("${driver_path}"))
  private String driverPath;

  private List<String> bookNames;

  public List<String> parse() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", driverPath);
    WebDriver driver = new ChromeDriver();
    driver.get("https://stepik.org/lesson/297509/step/1?unit=279269");

    Thread.sleep(6000);
    WebElement log_in = driver.findElement(By.xpath("//*[@id=\"ember135\"]"));
    log_in.click();

    Thread.sleep(2000);
    WebElement login = driver.findElement(By.xpath("//*[@id=\"id_login_email\"]"));
    login.sendKeys(this.login);

    WebElement password = driver.findElement(By.xpath("//*[@id=\"id_login_password\"]"));
    password.sendKeys(this.password);

    log_in = driver.findElement(By.xpath("//*[@id=\"login_form\"]/button"));
    log_in.click();

    log.info("Успешная аутентификация");

    Thread.sleep(10000);
    driver.get("https://stepik.org/lesson/297509/step/1?auth=login&unit=279269");

    Thread.sleep(10000);
    log.info("Поиск данных");

    bookNames = new ArrayList<>();
    for (int i = 3; i < 8; i++) {
      WebElement bookName = driver.findElement(By.xpath(String.format("//*[@id=\"ember257\"]/span/table/tbody/tr[%s]/td[2]", i)));
      bookNames.add(bookName.getText());
    }

    log.info("Данные найдены");

    return bookNames;
  }
}
