/**
	This version of HashTable will do a simple put using an object's hashcode. 
	It does not use separate key and value objects. It rehashes when the load factor exceeds the predetermined limit.
	Create the appropriate private fields and set a default load factor.
	@version		09/29/2015
	@author 		Brendan Raimann
*/
public class HashTable
{
	/** the maximum percentage of objects in the array before it rehashess */
	private final double LoadFactor = .6;
	/** the array that holds the objects */
	private Object[] table;
	/** the size of the array */
	private int size; 
	
	public HashTable()
	{
		table = new Object[100];
		size = 100;
	}
	
	public HashTable(int n)
	{
		table = new Object[n];
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
	public void put(Object obj)
	{
		if (obj != null)
		{
			if (calcLoadFactor() < LoadFactor)
			{
				int place = Math.abs(obj.hashCode() % size);
				while (table[place] != null)
				{
					place++;
					if (place >= size)
						place = 0;
				}
				table[place] = obj;
				}
			else
			{
				rehash();
				put(obj);
			}
		}

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
		Object[] temp = new Object[size];
		for (int i = 0; i < size; i++)
		{
			temp[i] = table[i];
		}
		size = size * 2;
		table = new Object[size];
		for (int i = 0; i < size/2; i++)
		{
			put(temp[i]);
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
}