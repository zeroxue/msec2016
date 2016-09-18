package net.mightypork.rcalc.operations;


import net.mightypork.rcalc.IEvaluable;
import net.mightypork.rcalc.numbers.Fraction;


/**
 * Division
 * 
 * @author Ondřej Hruška (MightyPork)
 */
public class OperationDivideFraction extends BinaryOperation {

	/**
	 * Create division
	 *
	 * @param left left operand
	 * @param right right operand
	 */
	public OperationDivideFraction(IEvaluable left, IEvaluable right) {

		super(left, right);
	}


	@Override
	public Fraction evaluate() {

		return left.evaluate().divide(right.evaluate());
	}


	@Override
	public String toString() {

		return "FRACTION{" + left + "," + right + "}";
	}

}
