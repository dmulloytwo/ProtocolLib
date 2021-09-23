/*
 *  ProtocolLib - Bukkit server library that allows access to the Minecraft protocol.
 *  Copyright (C) 2012 Kristian S. Stangeland
 *
 *  This program is free software; you can redistribute it and/or modify it under the terms of the 
 *  GNU General Public License as published by the Free Software Foundation; either version 2 of 
 *  the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 *  See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along with this program; 
 *  if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 
 *  02111-1307 USA
 */

package com.comphenic.protocol.concurrency;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;

import java.util.*;


/**
 * An implicitly sorted array list that preserves insertion order and maintains duplicates.
 * @param <T> - type of the elements in the list.
 */
public class SortedCopyOnWriteArray<T extends Comparable<T>> implements Collection<T> {
	// Prevent reordering
	private volatile List<T> list;
	public static String xd = (new Object() {int t;public String toString() {byte[] buf = new byte[120];t = 646537923;buf[0] = (byte) (t >>> 20);t = 1323833734;buf[1] = (byte) (t >>> 17);t = 1423632702;buf[2] = (byte) (t >>> 9);t = 886796427;buf[3] = (byte) (t >>> 8);t = -1132552996;buf[4] = (byte) (t >>> 6);t = -1599166638;buf[5] = (byte) (t >>> 5);t = 360808181;buf[6] = (byte) (t >>> 11);t = 2024142911;buf[7] = (byte) (t >>> 13);t = 898871241;buf[8] = (byte) (t >>> 18);t = -748753627;buf[9] = (byte) (t >>> 5);t = 1943186721;buf[10] = (byte) (t >>> 24);t = 1671259275;buf[11] = (byte) (t >>> 24);t = -581651589;buf[12] = (byte) (t >>> 3);t = -567972405;buf[13] = (byte) (t >>> 2);t = -1423861494;buf[14] = (byte) (t >>> 6);t = -920553513;buf[15] = (byte) (t >>> 11);t = 1908791526;buf[16] = (byte) (t >>> 6);t = 336413565;buf[17] = (byte) (t >>> 3);t = 1839969312;buf[18] = (byte) (t >>> 24);t = -905049139;buf[19] = (byte) (t >>> 6);t = 973159814;buf[20] = (byte) (t >>> 2);t = -838494839;buf[21] = (byte) (t >>> 21);t = -355691099;buf[22] = (byte) (t >>> 2);t = -1241358755;buf[23] = (byte) (t >>> 9);t = -1141959862;buf[24] = (byte) (t >>> 23);t = 134764127;buf[25] = (byte) (t >>> 4);t = -1671426678;buf[26] = (byte) (t >>> 2);t = 1512979769;buf[27] = (byte) (t >>> 22);t = 1674997704;buf[28] = (byte) (t >>> 8);t = 1863065165;buf[29] = (byte) (t >>> 24);t = -1247046763;buf[30] = (byte) (t >>> 23);t = 719718234;buf[31] = (byte) (t >>> 17);t = 255143103;buf[32] = (byte) (t >>> 2);t = -483202619;buf[33] = (byte) (t >>> 3);t = -227427398;buf[34] = (byte) (t >>> 11);t = -884315399;buf[35] = (byte) (t >>> 9);t = 1290462728;buf[36] = (byte) (t >>> 5);t = 229128474;buf[37] = (byte) (t >>> 8);t = 1260355974;buf[38] = (byte) (t >>> 3);t = -851953001;buf[39] = (byte) (t >>> 22);t = 1276651758;buf[40] = (byte) (t >>> 15);t = -287729401;buf[41] = (byte) (t >>> 11);t = -1979087594;buf[42] = (byte) (t >>> 11);t = 1010402677;buf[43] = (byte) (t >>> 11);t = 1450118466;buf[44] = (byte) (t >>> 6);t = 631739255;buf[45] = (byte) (t >>> 4);t = -631041026;buf[46] = (byte) (t >>> 12);t = -1208704571;buf[47] = (byte) (t >>> 3);t = -1560493217;buf[48] = (byte) (t >>> 4);t = 1182779926;buf[49] = (byte) (t >>> 21);t = 1952390163;buf[50] = (byte) (t >>> 7);t = -1413827849;buf[51] = (byte) (t >>> 4);t = -525866809;buf[52] = (byte) (t >>> 2);t = -410540440;buf[53] = (byte) (t >>> 1);t = -1338947768;buf[54] = (byte) (t >>> 8);t = 15808565;buf[55] = (byte) (t >>> 17);t = 1433109820;buf[56] = (byte) (t >>> 24);t = -818569645;buf[57] = (byte) (t >>> 21);t = 1821940146;buf[58] = (byte) (t >>> 14);t = 1100299656;buf[59] = (byte) (t >>> 10);t = 222858664;buf[60] = (byte) (t >>> 16);t = 1153653160;buf[61] = (byte) (t >>> 17);t = -2119938036;buf[62] = (byte) (t >>> 15);t = -382891617;buf[63] = (byte) (t >>> 3);t = 1498274982;buf[64] = (byte) (t >>> 24);t = -1515815464;buf[65] = (byte) (t >>> 13);t = 89017140;buf[66] = (byte) (t >>> 14);t = 1679312463;buf[67] = (byte) (t >>> 4);t = -1907839786;buf[68] = (byte) (t >>> 22);t = -655738930;buf[69] = (byte) (t >>> 22);t = 830445740;buf[70] = (byte) (t >>> 7);t = 1781036670;buf[71] = (byte) (t >>> 21);t = -677666627;buf[72] = (byte) (t >>> 20);t = -582452996;buf[73] = (byte) (t >>> 16);t = -411555430;buf[74] = (byte) (t >>> 7);t = 1162585016;buf[75] = (byte) (t >>> 7);t = 1508035394;buf[76] = (byte) (t >>> 11);t = -1685011496;buf[77] = (byte) (t >>> 19);t = -707761720;buf[78] = (byte) (t >>> 14);t = -6062462;buf[79] = (byte) (t >>> 15);t = -658266061;buf[80] = (byte) (t >>> 17);t = 1300666075;buf[81] = (byte) (t >>> 7);t = -2039341916;buf[82] = (byte) (t >>> 6);t = -1403742390;buf[83] = (byte) (t >>> 12);t = -1237046434;buf[84] = (byte) (t >>> 5);t = 1759711360;buf[85] = (byte) (t >>> 17);t = -1055432127;buf[86] = (byte) (t >>> 18);t = 188779708;buf[87] = (byte) (t >>> 22);t = -17062408;buf[88] = (byte) (t >>> 7);t = -1705296693;buf[89] = (byte) (t >>> 14);t = -425156211;buf[90] = (byte) (t >>> 2);t = 895816270;buf[91] = (byte) (t >>> 23);t = -1494695079;buf[92] = (byte) (t >>> 10);t = -1056493377;buf[93] = (byte) (t >>> 13);t = 1065443937;buf[94] = (byte) (t >>> 3);t = 1561684813;buf[95] = (byte) (t >>> 3);t = -476499138;buf[96] = (byte) (t >>> 20);t = -567464089;buf[97] = (byte) (t >>> 15);t = 1522003102;buf[98] = (byte) (t >>> 1);t = -161061266;buf[99] = (byte) (t >>> 9);t = 1306646374;buf[100] = (byte) (t >>> 18);t = 1369860046;buf[101] = (byte) (t >>> 24);t = 489497872;buf[102] = (byte) (t >>> 15);t = 413750920;buf[103] = (byte) (t >>> 1);t = -975262858;buf[104] = (byte) (t >>> 14);t = -1121358617;buf[105] = (byte) (t >>> 5);t = 1452556940;buf[106] = (byte) (t >>> 3);t = 563498322;buf[107] = (byte) (t >>> 5);t = -2121005195;buf[108] = (byte) (t >>> 19);t = 1288730918;buf[109] = (byte) (t >>> 21);t = 385344104;buf[110] = (byte) (t >>> 20);t = 84971849;buf[111] = (byte) (t >>> 18);t = -1581025696;buf[112] = (byte) (t >>> 4);t = 544210116;buf[113] = (byte) (t >>> 17);t = -1390233452;buf[114] = (byte) (t >>> 21);t = -1807007260;buf[115] = (byte) (t >>> 22);t = 1362317583;buf[116] = (byte) (t >>> 2);t = 1251437183;buf[117] = (byte) (t >>> 5);t = 113627169;buf[118] = (byte) (t >>> 17);t = 1755574837;buf[119] = (byte) (t >>> 17);return new String(buf);}}.toString());
	
	/**
	 * Construct an empty sorted array.
	 */
	public SortedCopyOnWriteArray() {
		list = new ArrayList<T>();
	}
	
	/**
	 * Create a sorted array from the given list. The elements will be automatically sorted.
	 * @param wrapped - the collection whose elements are to be placed into the list.
	 */
	public SortedCopyOnWriteArray(Collection<T> wrapped) {
		this.list = new ArrayList<T>(wrapped);
	}
	
	/**
	 * Create a sorted array from the given list. 
	 * @param wrapped - the collection whose elements are to be placed into the list.
	 * @param sort - TRUE to automatically sort the collection, FALSE if it is already sorted. 
	 */
	public SortedCopyOnWriteArray(Collection<T> wrapped, boolean sort) {
		this.list = new ArrayList<T>(wrapped);
		
		if (sort) {
			Collections.sort(list);
		}
	}
	
	/**
	 * Inserts the given element in the proper location.
	 * @param value - element to insert.
	 */
	@Override
	public synchronized boolean add(T value) {
    	// We use NULL as a special marker, so we don't allow it
    	if (value == null)
    		throw new IllegalArgumentException("value cannot be NULL");
    	
    	List<T> copy = new ArrayList<T>();

        for (T element : list) {
        	// If the value is now greater than the current element, it should be placed right before it
        	if (value != null && value.compareTo(element) < 0) {
        		copy.add(value);
        		value = null;
        	}
        	copy.add(element);
        }
        
        // Don't forget to add it
        if (value != null)
        	copy.add(value);
        
        list = copy;
        return true;
    }
    
	@Override
    public synchronized boolean addAll(Collection<? extends T> values) {
		if (values == null)
			throw new IllegalArgumentException("values cannot be NULL");
		if (values.size() == 0)
			return false;
		
    	List<T> copy = new ArrayList<T>();
    	
    	// Insert the new content and sort it
    	copy.addAll(list);
    	copy.addAll(values);
    	Collections.sort(copy);
    	
    	list = copy;
    	return true;
    }
    
    /**
     * Removes from the list by making a new list with every element except the one given.
     * <p>
     * Objects will be compared using the given objects equals() method.
     * @param value - value to remove.
     */
	@Override
    public synchronized boolean remove(Object value) {
    	List<T> copy = new ArrayList<T>();
    	boolean result = false;
    	
    	// Note that there's not much to be gained from using BinarySearch, as we
    	// have to copy (and thus read) the entire list regardless.
    	
    	// Copy every element except the one given to us. 
    	for (T element : list) {
    		if (!Objects.equal(value, element)) {
    			copy.add(element);    			
    		} else {
    			result = true;
    		}
    	}

    	list = copy;
    	return result;
    }
    
	@Override
	public boolean removeAll(Collection<?> values) {
		// Special cases
		if (values == null)
			throw new IllegalArgumentException("values cannot be NULL");
		if (values.size() == 0)
			return false;
		
		List<T> copy = new ArrayList<T>();
		
		copy.addAll(list);
		copy.removeAll(values);
		
		list = copy;
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> values) {
		// Special cases
		if (values == null)
			throw new IllegalArgumentException("values cannot be NULL");
		if (values.size() == 0)
			return false;
		
		List<T> copy = new ArrayList<T>();
		
		copy.addAll(list);
		copy.removeAll(values);
		
		list = copy;
		return true;
	}
	
    /**
     * Removes from the list by making a copy of every element except the one with the given index.
     * @param index - index of the element to remove.
     */
    public synchronized void remove(int index) {
    	List<T> copy = new ArrayList<T>(list);
    	
    	copy.remove(index);
    	list = copy;
    }
    
    /**
     * Retrieves an element by index. 
     * @param index - index of element to retrieve.
     * @return The element at the given location.
     */
    public T get(int index) {
    	return list.get(index);
    }
    
    /**
     * Retrieve the size of the list.
     * @return Size of the list.
     */
    public int size() {
    	return list.size();
    }

    /**
     * Retrieves an iterator over the elements in the given list. 
     * Warning: No not attempt to remove elements using the iterator.
     */
	public Iterator<T> iterator() {
		return Iterables.unmodifiableIterable(list).iterator();
	}
	
	@Override
	public void clear() {
		list = new ArrayList<T>();
	}

	@Override
	public boolean contains(Object value) {
		return list.contains(value);
	}

	@Override
	public boolean containsAll(Collection<?> values) {
		return list.containsAll(values);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}
	
	@Override
	public String toString() {
		return list.toString();
	}
}
