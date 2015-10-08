public class HashTableRunner
{


	public static void main (String[] args)
	{
		HashTable<Integer, String> h = new HashTable<Integer, String>(10);
		h.put(1, "first");
		h.put(2, "second");
		h.put(2, "s2");
		h.put(2, "s3");
		h.put(3, "third");
		h.put(4, "fourth");
		h.put(5, "fifth");
		h.put(6, "sixth");
		h.put(7, "seventh");
		h.put(8, "eighth");
		h.put(1, "f2");
		h.put(71,"seventy one");
		h.put(28, "twenty eighth");
		h.put(9, "ninth");
		
		System.out.println(h.get(1));
		System.out.println(h.containsKey(1));
		System.out.println(h.containsKey(8));
		System.out.println(h.containsValue("first"));
		System.out.println(h.containsValue("efs"));
		System.out.println(h.toString());
		
		System.out.println(h.remove(1));
		System.out.println(h.remove(28));
		System.out.println(h.toString());
	}
}