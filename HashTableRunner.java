public class HashTableRunner
{
	public static void main (String[] args)
	{
		HashTable doop = new HashTable(55);
		String str = "test";
		for (int i = 0; i < 98; i++)
		{
			doop.put(str);
		}
		System.out.println(doop.toString());
		//System.out.println(doop.printLF());
	}
}