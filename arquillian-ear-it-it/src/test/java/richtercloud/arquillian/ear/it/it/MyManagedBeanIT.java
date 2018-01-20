package richtercloud.arquillian.ear.it.it;

import java.io.IOException;
import java.net.URL;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import richtercloud.selenium.tools.SeleniumHelper;

/**
 *
 * @author richter
 */
@RunWith(Arquillian.class)
public class MyManagedBeanIT {
    private final SeleniumHelper seleniumHelper = new SeleniumHelper();
    
    @Deployment(testable = false)
    public static Archive<?> createDeployment() {
        EnterpriseArchive retValue = Maven.configureResolver().workOffline().resolve("richtercloud:arquillian-ear-it-ear:ear:1.0-SNAPSHOT").withTransitivity().asSingle(EnterpriseArchive.class);
        retValue.writeTo(System.out, Formatters.VERBOSE);
            //web module in ear isn't listed, but present
        return retValue;
    }

    @Drone
    private WebDriver browser;
    @ArquillianResource
    private URL deploymentUrl;
    @FindBy(id = "mainForm:saveButton")
    private WebElement saveButton;

    @Test
    public void testWebFrontend() throws IOException {
        browser.get(deploymentUrl+ "/arquillian-ear-it-web/index.xhtml");
        seleniumHelper.screenshot(browser);
        Assert.assertTrue(saveButton.isDisplayed());
    }
}
