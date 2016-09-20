## Changes of the [original library](https://github.com/MightyPork/rcalc)

### Changes

* For DIVISION,we use `#` instead of `/`,and `/` stands for FRACTION now.

`rcalc\operations\OperationDivideFraction.java` and `rcalc\tokens\TokenOperatorDivideFraction.java` is almost copied from `*OperationDivide*`.

* And `'` to present mixed number.

change `1'1/2` => `(1+1/2)` when format the input.

`input = input.replaceAll("([0-9.]+[!]?)'(([^+\\-*#]*)?)", "($1+$2)");`

* Provide the funtion of 'UNIQUE' problem.

``` vi
1. 1+2+3  <=> (1+2)+3 <=> 3+(1+2)
                        <=> (2+1)+3 <=> 3+(2+1)

2.1+3+2 <=> (1+3)+2 <=> 2+(1+3) <=> 2+(3+1)
```

However we will not make 1 and 2 the same,they are UNIQUE.

So this is just for `ADD` and `MUL`.

To make it happen,with an easy way,we can just arrage the `ADD` and `MUL` string => make substring in order.

`ADD{3,ADD{1,2}}` =='3'>'A'==> `ADD{ADD{1,2},3}` and so on.

So the former 1 and 2 can be `ADD{ADD{1,2},3}` and `ADD{ADD{1,3},2}`.

In code:
``` java
    @Override
    public String toString() {
        String ls = left.toString();
        String rs = right.toString();
        if(ls.compareTo(rs) < 0){ //ls is small in lexicographical order
            return "ADD{" + left + "," + right + "}";
        }
        else {
            return "ADD{" + right + "," + left + "}";
        }
    }
```

In fact,there are great efficiency issues here.


### Repairs


1.`1/-2` => `Divide{1,-2}` => `Fraction(1,-2)` => when `toString` and call `isFractional`(in `rcalc\numbers\Fraction.java`):

``` java
    /**
     * Check if the number is fractional
     * 
     * @return is fractional
     */
    public boolean isFractional() {

        return !(numerator.mod(denominator)).equals(BigInteger.ZERO);
    }

```

the `.mod` method will not deal with NEGATIVE values at all.

So change `simplify` and `simplify_ip` to deal with the `denominator` when it's negative(put the negative to `numerator`),"1/-2" => "-1/2".



* When deal with `*` and `/` operation,original lib just did WRONG.

Here is code from `rcalc/TokenList.java`:

``` java
    extractOperator(TokenOperatorMultiply.class);
    extractOperator(TokenOperatorDivide.class);
```

The result is `3/4*5`("3#4*5" in our new representation) => `3/20`,it should be `15/4`.

So add `private void extractOperator(List<Class<? extends IOperatorToken>> operatorClassList)` method to deal with `MUL` and `DIVIDE` the same time.