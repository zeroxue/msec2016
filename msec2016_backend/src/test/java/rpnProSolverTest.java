import com.msec2016.expression.Problem;
import com.msec2016.expression.rpnProSolver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by miao on 2016/9/25.
 */
public class rpnProSolverTest {
    @Test
    public void solveAll() {

        rpnProSolver rpnProSolver = new rpnProSolver();

        Problem problem1 = new Problem("1+2+3");

        List<Problem> problemList = new ArrayList<>();

        problemList.add(problem1);

        assertThat(rpnProSolver.solveAll(problemList).get(0).getAnswer())
                .isEqualTo("6");

        Problem problem2 = new Problem("2#2/3");
        problemList.add(problem2);

        assertThat(rpnProSolver.solveAll(problemList).get(1).getAnswer())
                .isEqualTo("3");


    }
}
