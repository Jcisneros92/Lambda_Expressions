import java.util.function.UnaryOperator;

public class FibonacciExample 
{
	static UnaryOperator<Integer> fibonacci = (x) -> 
	{
		if(x <= 0) return 0;
		if(x == 1) return 1;
		else return FibonacciExample.fibonacci.apply(x-2)+FibonacciExample.fibonacci.apply(x-1);
	};
		
	public static void main(String[] args) 
	{
		for(int i = 0; i < 16; i++)
		{
			System.out.println(fibonacci.apply(i));
		}
	}
}