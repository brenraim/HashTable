/**
	This version of HashTable will do a simple put using an object's hashcode. 
	It does not use separate key and value objects. It rehashes when the load factor exceeds the predetermined limit.
	Create the appropriate private fields and set a default load factor.
	@version		09/29/2015
	@author 		Brendan Raimann
*/
public class HashTable<K,V>
{
/**
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{
		@SuppressWarnings("rawtypes")
		HashTable table = new HashTable(5);
		String str1 = "str1";
		String str2 = "str2";
		String str3 = "str3";
		String str4 = "str4";
		String str5 = "str5";
		String str6 = "str6";
		String str7 = "str7";
		String str8 = "str8";
		String str9 = "str9";
		String str10 = "str10";
		table.put(str1, str2);
		table.put(str3, str4);
		table.put(str5, str6);
		table.put(str7, str8);
		table.put(str9, str10);
		System.out.println(table.containsValue(str2));
		System.out.println(table);
		System.out.println(table.get(str1));
	}
*/
	/** the maximum percentage of objects in the array before it rehashess */
	private final double LoadFactor = .6;
	/** the array that holds the objects */
	private Entry<K, V>[] table;
	/** the size of the array */
	private int size; 
	
	@SuppressWarnings("unchecked")
	public HashTable()
	{
		table = new Entry[100];
		size = 100;
	}
	
	@SuppressWarnings("unchecked")
	public HashTable(int n)
	{
		table = new Entry[n];
		size = n;
	}
	/** 
		if the object is null, it does nothing. this makes the method compatible with rehash()
		if load factor is low enough (calcLoadFactor() < LoadFactor), places obj using its hashcode % size of array.
		if spot is taken, the obj traverses array until a spot is open (1 spot at a time)
		@param obj		Object that will be placed into the table
		@param place	Integer for placement of the object
		@return 		void
	*/
	@SuppressWarnings("unchecked")
	public void put(K key, V value)
	{
		if (value != null)
		{
			if (calcLoadFactor() < LoadFactor)
			{
				int place = Math.abs(key.hashCode() % size);
				while (table[place] != null)
				{
					place++;
					if (place >= size)
						place = 0;
				}
				table[place] = new Entry<K, V> (key, value);
				}
			else
			{
				rehash();
				put(key, value);
			}
		}

	}
	
	public V remove(K key) 
	{
		int place = Math.abs(key.hashCode() % size);

		while ((table[place].getKey().equals(key)) == false) 
		{
			place++;
			if (place >= size) 
				place = 0;
		}
		V value = table[place].getValue();
		table[place] = null;
		return value;
	}
	
	public V get(K key)
	{
		int place = Math.abs(key.hashCode() % size);

		while ((table[place].getKey().equals(key)) == false) 
		{
			place++;
			if (place >= size) 
				place = 0;
		}
		V value = table[place].getValue();
		return value;
	}
	
	public boolean containsKey(K key)
	{
		for (int i = 0; i < size; i++)
		{
			if (table[i].getKey().equals(key))
				return true;
		}
		return false;
	}
	
	public boolean containsValue(V value)
	{
		for (int i = 0; i < size; i++)
		{
			if (table[i].getValue.equals(value))
				return true;
		}
		return false;
	}
	
	/**
		prints out each spot of array with 'null' or the memory address
		@return 	String with every data entry of the table
	*/
	public String toString()
	{
		String output = "";
		for (int i = 0; i < size; i++)
		{
			if (table[i] != null)
				output = output + table[i].hashCode() + ", ";
			else
				output = output + "null, ";
		}
		return output;
	}
	/**
		creates temporary array of same size, then copies everything over
		redefines original table as a new array with  the size doubled
		rehashes data from temp to the newer, bigger array
		@param temp		An object array used for copying the data to be rahashed
		@return 		void
	*/
	private void rehash()
	{
		Entry<K,V>[] temp = new Entry[size];
		for (int i = 0; i < size; i++)
		{
			temp[i] = table[i];
		}
		size = size * 2;
		table = new Entry[size];
		for (int i = 0; i < size/2; i++)
		{
			put(temp[i].getKey(), temp[i].getValue());
		}
	}
	/**
		divides amount of entries by total size of array
		@param x		used to count entries into the array
		@return double 	returns the load factor 
	*/
	private double calcLoadFactor()
	{
		double x = 0; //count
		for (int i = 0; i < size; i++)
		{
			if (table[i] != null)
				x++;
		}
		return x/(double)size;
	}
	
	/**
		for testing only, prints load factor
		@ return String	returns the load factor as String
	*/
	//public String printLF()
	//{
	//	return calcLoadFactor() + "";
	//}
	private class Entry<K,V>
	{
		private K key;
		private V value;
		
		public Entry (K input1, V input2)
		{
			key = input1;
			value = input2;
		}
		
		public K getKey()
		{
			return key;
		}
		
		public V getValue()
		{
			return value;
		}
		
		
	}
}