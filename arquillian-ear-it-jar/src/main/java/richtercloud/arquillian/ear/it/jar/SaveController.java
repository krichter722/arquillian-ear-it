package richtercloud.arquillian.ear.it.jar;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author richter
 */
@Local
public interface SaveController {

    void save(MyEntity myEntity);

    List<MyEntity> getAllMyEntities();
}
