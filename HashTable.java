public class HashTable
{
	private final double LoadFactor = .6;
	private Object[] table;
	private int size; //in hindsight, size is unnecessary, but it helped me comprehend the code better.
	
	public HashTable()
	{
		table = new Object[100];
		size = 100;
	}
	
	public void put(Object obj)
	//if the object is null, it does nothing. this makes the method compatible with rehash()
	//if load factor is low enough, places obj with its hashcode % size of array.
	//if spot is taken, the obj traverses array until a spot is open (1 spot at a time)
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
	
	public String toString()
	//prints out each spot of array with 'null' or the memory address
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
	
	private void rehash()
	{
		//creates temporary array of same size, copies everything over,
		//redefines original table as new one with double the size
		//rehashes data in temp to the newer, bigger array
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
	
	public String printLF()
	{
		//for testing the code
		return calcLoadFactor() + "";
	}
}