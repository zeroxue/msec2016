import com.msec2016.expression.ProGenAndSolver;
import com.msec2016.expression.Problem;
import net.mightypork.rcalc.RCalc;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by miao on 2016/9/25.
 */
public class ProGenAndSolverTest {

    private ProGenAndSolver proGenAndSolver = new ProGenAndSolver();
    private RCalc rCalc = new RCalc();  //use it as a judgement


    @Test
    public void get_Int_AddOp_ProblemsTest() {
        List<Problem> problemList = proGenAndSolver.get_Int_AddOp_Problems(0);

        problemList = proGenAndSolver.solveAll();


        // rCalc.setDebug(true);

        for (Problem problem : problemList) {
            System.out.println(problem);
            System.out.println("RCalc got:" + rCalc.evaluate(problem.getDefinition()));
            assertThat(rCalc.evaluate(problem.getDefinition()).toString())
                    .isEqualTo(problem.getAnswer());
        }
    }


    /**
     * public List<Problem> get_whatever_you_nee_Problems(int num, int minlen, int maxlen,
     * boolean intType, boolean fractionType, boolean mixedFrationType,
     * boolean addOp, boolean subOp, boolean mulOp, boolean divOp) {
     */
    @Test
    public void get_whatever_you_nee_ProblemsTest() {

        List<Problem> problemList = proGenAndSolver.get_whatever_you_nee_Problems(100, 1000, 1520,
                true, true, true,
                true, true, true, true
        );

        problemList = proGenAndSolver.solveAll();


        // rCalc.setDebug(true);

        for (Problem problem : problemList) {
            System.out.println(problem);
            System.out.println("RCalc got:" + rCalc.evaluate(problem.getDefinition()));
            assertThat(rCalc.evaluate(problem.getDefinition()).toString())
                    .isEqualTo(problem.getAnswer());
        }

    }


}
