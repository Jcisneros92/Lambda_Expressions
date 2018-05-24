import java.util.function.UnaryOperator;

public class FactorialExample
{

	static UnaryOperator<Long> Factorial = x -> x == 0 ? 1 : x * FactorialExample.Factorial.apply((long)(x - 1));

	public static void main(String[] args) 
	{
		for(int i = 0; i < 16; i++)
		{
			System.out.println(Factorial.apply((long)i));
		}
	}

}