package net.mightypork.rcalc.tokens;


import net.mightypork.rcalc.TokenList;
import net.mightypork.rcalc.operations.Operation;
import net.mightypork.rcalc.operations.OperationDivide;
import net.mightypork.rcalc.operations.OperationDivideFraction;


/**
 * Division token
 * 
 * @author Ondřej Hruška (MightyPork)
 */
public class TokenOperatorDivideFraction extends TokenBinaryOperator {

	@Override
	public String toString() {

		return "/";
	}


	@Override
	public Operation toOperation(TokenList left, TokenList right) {

		return new OperationDivideFraction(left.parse(), right.parse());
	}

}
