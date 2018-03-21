

public class Test
{
	
	
	public Test()
	{
		System.out.println(replace("+|+++||---++-||-"));
	}
	
	private static String replace(final String s)
	{
		return s.replaceAll("[-+|]", "X");
	}
	
	public static void main(String[] args)
	{
		Test test = new Test();
	}
	
}
