package richtercloud.arquillian.ear.it.web;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import richtercloud.arquillian.ear.it.jar.MyEntity;
import richtercloud.arquillian.ear.it.jar.SaveController;

/**
 *
 * @author richter
 */
@Named
@SessionScoped
public class MyManagedBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @EJB
    private SaveController saveController;
    private final Random random = new Random();

    public String save() {
        MyEntity myEntity = new MyEntity(String.valueOf(random.nextInt()));
        saveController.save(myEntity);
        return "";
    }

    public List<MyEntity> getAllMyEntities() {
        List<MyEntity> retValue = saveController.getAllMyEntities();
        return retValue;
    }
}
