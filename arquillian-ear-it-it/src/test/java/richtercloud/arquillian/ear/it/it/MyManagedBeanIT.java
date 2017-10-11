package richtercloud.arquillian.ear.it.it;

import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import richtercloud.arquillian.ear.it.jar.MyEntity;
import richtercloud.arquillian.ear.it.jar.SaveController;

/**
 *
 * @author richter
 */
@RunWith(Arquillian.class)
public class MyManagedBeanIT {
    
    @Deployment
    public static Archive<?> createDeployment() {
//        EnterpriseArchive retValue = ShrinkWrap.create(EnterpriseArchive.class)
//                .addAsModule(Maven.configureResolver().workOffline().resolve("richtercloud:arquillian-ear-it-web:war:1.0-SNAPSHOT").withoutTransitivity().asSingle(WebArchive.class))
//                .addAsModule(Maven.configureResolver().workOffline().resolve("richtercloud:arquillian-ear-it-ejb:ejb:1.0-SNAPSHOT").withoutTransitivity().asSingle(JavaArchive.class));
        EnterpriseArchive retValue = Maven.configureResolver().workOffline().resolve("richtercloud:arquillian-ear-it-ear:ear:1.0-SNAPSHOT").withoutTransitivity().asSingle(EnterpriseArchive.class);
        retValue.writeTo(System.out, Formatters.VERBOSE);
        return retValue;
    }

    @EJB
    private SaveController saveController;

    @Test
    public void testSaveController() {
        assertNotNull(saveController);
        Random random = new Random();
        MyEntity myEntity = new MyEntity(String.valueOf(random.nextInt()));
        saveController.save(myEntity);
        List<MyEntity> allMyEntities = saveController.getAllMyEntities();
        assertNotNull(allMyEntities);
        assertTrue(allMyEntities.size() == 1);
    }
}
