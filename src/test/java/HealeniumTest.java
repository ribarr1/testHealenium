import com.epam.healenium.SelfHealingDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealeniumTest {

    private static WebDriver driver;
    private static SelfHealingDriver healingDriver;

    @BeforeAll
    public static void setUp() {
        // Configurar ChromeDriver

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        healingDriver = SelfHealingDriver.create(driver);

        // Inicializar SelfHealingDriver




    }

    @Test
    public void testHealenium() throws InterruptedException {

        healingDriver.get("https://rubenibarra.com/index.php/locators");
        System.out.println("Page title is: " + healingDriver.getTitle()); // Depuración

        // Localizar el elemento usando un localizador que cambiará
      // healingDriver.findElement(By.id("firstName")).click();

        healingDriver.findElement(By.id("firstName")).sendKeys("escribe algo");
        healingDriver.findElement(By.id("lastName")).sendKeys("escribe algo mas");


        Thread.sleep(10000);

        // Verificar el título de la página
        assertEquals("locators - rubenibarra.com", healingDriver.getTitle());
    }

    @AfterAll
    public static void tearDown() {
        // Cerrar el navegador
        if (healingDriver != null) {
            healingDriver.quit();
        }
    }
}
