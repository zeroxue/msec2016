package net.mightypork.rcalc.operations;


import net.mightypork.rcalc.IEvaluable;
import net.mightypork.rcalc.numbers.Fraction;


/**
 * Addition
 * 
 * @author Ondřej Hruška (MightyPork)
 */
public class OperationAdd extends BinaryOperation {

	/**
	 * Create addition
	 * 
	 * @param left left operand
	 * @param right right operand
	 */
	public OperationAdd(IEvaluable left, IEvaluable right) {

		super(left, right);
	}


	@Override
	public Fraction evaluate() {

		return left.evaluate().add(right.evaluate());
	}


	@Override
	public String toString() {
//		Fraction l = left.evaluate();
//		Fraction r = right.evaluate();
//		if(l.subtract(r).getDoubleValue() >= 0){ //ADD{small,big}
//			return "ADD{" + right + "," + left + "}";
//		}
//		else {
//			return "ADD{" + left + "," + right + "}";
//		}

		String ls = left.toString();
		String rs = right.toString();
		if(ls.compareTo(rs) < 0){ //ls is small in lexicographical order
			return "ADD{" + left + "," + right + "}";
		}
		else {
			return "ADD{" + right + "," + left + "}";
		}
	}
}
