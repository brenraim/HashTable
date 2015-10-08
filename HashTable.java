/**
*	This version of HashTable builds on Version 0 and adds generics as well as a few additional methods.
*	@version		10/07/2015
*	@author 		Brendan Raimann
*/
public class HashTable<K,V>
{
	//the maximum percentage of objects in the array before it rehashes
	private final double LoadFactor = .6;
	//the array that holds the objects 
	private Entry<K, V>[] table;
	//the size of the array
	private int size; 
	//amount of occupied spots in the array
	private double occupied;
	
	/**
	*	Default constructor. Initializes to capacity 100
	*/
	@SuppressWarnings("unchecked")
	public HashTable()
	{
		table = new Entry[100];
		size = 100;
		occupied = 0;
	}
	
	/**
	 *	Constructor that initializes to a desired capacity
	 *	@param n for the capacity of the HashTable
	 */
	@SuppressWarnings("unchecked")
	public HashTable(int n)
	{
		table = new Entry[n];
		size = n;
		occupied = 0;
	}
	
	/** 
	*	Puts a key-value entry in the hashtable. 
	*	Deals with collisions by placing the object in the next open spot.
	*	@param key For placement of the value
	*	@param value The data stored in each each entry
	*/
	@SuppressWarnings("unchecked")
	public void put(K key, V value)
	{
		//linear runtime because calcLoadFactor does not have loops
		if (calcLoadFactor() < LoadFactor)
		{
			int place = Math.abs(key.hashCode() % size);
			while (table[place] != null && table[place].getKey().equals(key) == false)
			{
				place++;
				if (place >= size)
					place = 0;
			}
			if (table[place] == null)
				occupied++;
			table[place] = new Entry<K, V> (key, value);
			}
		else
		{
			//if occupancy is above LoadFactor
			rehash();
			put(key, value);
		}

	}
	
	/**
	*	Removes the entry with the corresponding key and returns its value. 
	*	Returns null if the key does not exist in the table.
	*	@param key for locating entry to be removed
	*	@return V
	*/
	public V remove(K key)
	{
		if (containsKey(key) == false)
			return null;
		occupied--;
		
		int place = Math.abs(key.hashCode() % size);
		while (table[place].getKey() != key)
		{
			place++;
			if (place >= size) 
				place = 0;
		}
		
		V value = table[place].getValue();
		table[place] = null;
		if (place == size - 1)
			place = 0;
		else
			place++;
		while (table[place] != null)
		{
			Entry<K,V> temp = table[place];
			table[place] = null;
			put(temp.getKey(), temp.getValue()); //ASDFASDFASDFASDFASDFFAD
			occupied--;
			place++;
			if (place >= size) 
				place = 0;
		}
		return value;
	}
	
	/**
	*	Returns the value that corresponds to key.
	*	Returns null if the key does not exist in the table.
	*	@param key for finding the value in the HashTable
	*	@return V
	*/
	public V get(K key)
	{
		if (containsKey(key) == false)
			return null;
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
	
	/**
	*	Returns whether or not key exists in the table.
	*	@param key to be compared with all keys in the table
	*	@return boolean
	*/
	public boolean containsKey(K key)
	{
		for (int i = 0; i < size; i++)
		{
			if (table[i] != null)
			{
				if (table[i].getKey().equals(key))
					return true;
			}
		}
		return false;
	}
	
	/**
	*	Returns whether or not value exists in the table.
	*	@param value to be compared with all values in the table
	*	@return boolean
	*/
	public boolean containsValue(V value)
	{
		for (int i = 0; i < size; i++)
		{
			if (table[i] != null)
			{
				if (table[i].getValue().equals(value))
					return true;
			}
		}
		return false;
	}
	
	/**
	*	prints out each spot of array with 'null' or the memory address
	*	@return String
	*/
	public String toString()
	{
		String output = "";
		for (int i = 0; i < size; i++)
		{
			if (table[i] != null)
				output = output + i + ": (" + table[i].getKey() + ", " + table[i].getValue() + ", " + table[i].hashCode() + ")\n";
			else
				output = output + i + ":null \n";
		}
		return output;
	}
	/**
	*	Doubles the size of the HashTable and rehashes each item contained within.
	*	Should be called anytime calling the put function makes the current fill of the HashTable exceed the load factor.
	*	@param temp	An object array used for copying the data to be rahashed
	*	@return void
	*/
	private void rehash()
	{
		occupied = 0;
		Entry<K,V>[] temp = new Entry[size];
		for (int i = 0; i < size; i++)
		{
			temp[i] = table[i];
		}
		size = size * 2;
		table = new Entry[size];
		for (int i = 0; i < size/2; i++)
		{
			if (temp[i] != null)
				put(temp[i].getKey(), temp[i].getValue());
		}
	}
	/**
	*	divides amount of entries by total size of array
	*	@return double
	*/
	private double calcLoadFactor()
	{
		return occupied/(double)size;
	}
	
	/**
	*	Nested class used for holding a key and value.
	*/
	private class Entry<K,V>
	{
		private K key;
		private V value;
		
		/**
		 * Initializes Entry Object with key and value
		 * @param k
		 * @param v
		 */
		public Entry (K input1, V input2)
		{
			key = input1;
			value = input2;
		}
		
		/**
		*	@return K key
		*/
		public K getKey()
		{
			return key;
		}
		
		/**
		*	@return V value
		*/
		public V getValue()
		{
			return value;
		}
		
		
	}
}