import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.script.ScriptException;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class zetamac {
    // https://stackoverflow.com/questions/2605032/is-there-an-eval-function-in-java
//    static ScriptEngineManager manager = new ScriptEngineManager();
//    static ScriptEngine engine = manager.getEngineByName("js");
    public static void zetamac_selenium() throws InterruptedException {
        // System.setProperty("webdriver.chrome.driver", new File("").getAbsolutePath().concat("/chromedriver"));
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless");
        options.addArguments("window-size=1600x2400");
        //Initiating your chromedriver
        WebDriver driver = new ChromeDriver(options);
        // WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://arithmetic.zetamac.com/game?key=a7220a92");
        try {
            while (true) {
                while (driver.findElements(By.xpath("/html/body/div/div/div[1]/span")).size() == 0) {
                    try {
                        sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                String question = driver.findElement(By.xpath("/html/body/div/div/div[1]/span")).getText();
                question = question.replaceAll(" ", "");
                System.out.println(question);
                int result = 0;
                if (question.contains("+")) {
                    String[] split = question.split("\\+");
                    result = Integer.parseInt(split[0]) + Integer.parseInt(split[1]);
                } else if (question.contains("–")) {
                    String[] split = question.split("–");
                    result = Integer.parseInt(split[0]) - Integer.parseInt(split[1]);
                } else if (question.contains("×")) {
                    String[] split = question.split("×");
                    result = Integer.parseInt(split[0]) * Integer.parseInt(split[1]);
                } else {
                    String[] split = question.split("÷");
                    result = Integer.parseInt(split[0]) / Integer.parseInt(split[1]);
                }
                WebElement rollbox = driver.findElement(By.xpath("/html/body/div/div/div[1]/input"));

                rollbox.sendKeys(Integer.toString(result));
            }
        }
        catch (Exception e)
        {
            // get the result
        //    e.printStackTrace();
            sleep(4422);
            String result = driver.findElement(By.xpath("/html/body/div/div/div[2]/p[1]")).getText();
            System.out.println(result);
        }
        driver.quit();
    }
    public static void main(String[] args) throws InterruptedException {
	// write your code here
        zetamac_selenium();
    }
}
