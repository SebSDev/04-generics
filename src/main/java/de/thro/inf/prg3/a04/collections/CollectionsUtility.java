package de.thro.inf.prg3.a04.collections;

import java.util.Comparator;

/**
 * @author Sebastian Schäffler
 * created at 25.10.2018
 * description:
 */
public abstract class CollectionsUtility
{
    private CollectionsUtility()
    {

    }

    /**
     * sorting a SimpleList using insertion sort
     * @param list
     * @param cmp
     * @param <T>
     * @return
     */
    public static <T> SimpleList<T> sort(SimpleList<T> list, Comparator<T> cmp)
    {
        SimpleList<T> newList;
        try
        {
            newList = list.getClass().newInstance();
        }
        catch(Exception e)
        {
            newList = new SimpleListImpl<>();
        }

        T[] arr = list.toArray();
        int j;
        T key;

        for (int i = 1; i < arr.length; i++)
        {
            key = arr[i];
            j = i - 1;

            while (j >= 0 && cmp.compare(key, arr[j]) < 0)
            {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }

        // adding the array elements to the new list
        for (T e : arr)
        {
            newList.add(e);
        }

        return newList;

    }
}
