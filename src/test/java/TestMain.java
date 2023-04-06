import com.uflowertv.util.CipherUtil;
import org.junit.Test;

/**
 * @author liglo
 * @date 2023/4/6 16:55
 */
public class TestMain {

    @Test
    public void test(){
        String generator = CipherUtil.generator("youcaihua");
        System.out.println(generator);
    }
}
