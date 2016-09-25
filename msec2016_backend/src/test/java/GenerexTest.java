import com.mifmif.common.regex.Generex;

/**
 * Created by miao on 2016/9/25.
 */
public class GenerexTest {

    public static void main(String[] args) {

        String toGen = "[1-9](\\*[1-9])";

        Generex generex = new Generex(toGen);

        System.out.println(generex.random(10));

    }

}
