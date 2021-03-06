package net.mightypork.rcalc.tokens;


import net.mightypork.rcalc.IToken;
import net.mightypork.rcalc.TokenList;


/**
 * Left parenthesis token
 * 
 * @author Ondřej Hruška (MightyPork)
 */
public class TokenParenthesisLeft implements IToken {

	@Override
	public String toString() {

		return "(";
	}

	@Override
	public TokenList wrapInTokenList() {
		throw new RuntimeException("Cannot wrap a parenthesis in a TokenList!");
	}

}
