import java.util.function.BinaryOperator;

public class GCDExample 
{

	static BinaryOperator<Integer> gcd = (i, j) -> j == 0 ? i : GCDExample.gcd.apply(j, i%j);
	
	public static void main(String[] args) 
		{
			for(int i = 0; i < 10; i++)
			{
				for(int j = 0; j < 10; j++)
				{
					System.out.println(gcd.apply(i, j));
				}
			}
		}

}
