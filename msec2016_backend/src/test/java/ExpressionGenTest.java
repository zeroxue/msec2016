import com.mifmif.common.regex.Generex;
import com.msec2016.expression.ExpressionGen;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by miao on 2016/9/25.
 */
public class ExpressionGenTest {

    private ExpressionGen expressionGen = new ExpressionGen();


    @Test
    public void addNewType() {
        assertThat(expressionGen.addNewType("\\+", "\\-"))
                .isEqualTo("(\\+|\\-)");

        assertThat(expressionGen.addNewType("(\\+|\\-)", "\\*"))
                .isEqualTo("((\\+|\\-)|\\*)");

    }

    @Test
    public void getSomeExpression() {
        expressionGen.setDataType(true, false, false);
        expressionGen.setOperatorType(false, false, false, true);

        Generex generex = expressionGen.getGenerex();


        List<String> ls = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ls.add(generex.random(10, 10));
        }

        for (String s : ls) {
            System.out.println(s + ":" + s.length());
            //assertThat(s.length()).isEqualTo(10);
        }
    }

}
