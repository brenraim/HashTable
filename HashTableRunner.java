public class HashTableRunner
{
	public static void main (String[] args)
	{
		HashTable<K,V> doop = new HashTable<K,V>(55);
		String str = "test";
		for (int i = 0; i < 98; i++)
		{
			doop.put(str);
		}
		System.out.println(doop.toString());
		//System.out.println(doop.printLF());
	}
}