package de.thro.inf.prg3.a04.collections;

import java.util.function.Function;

public interface SimpleList<T> extends Iterable<T> {
	/**
	 * Add a given object to the back of the list.
	 */
	void add(T o);

	/**
	 * @return current size of the list
	 */
	int size();

	/**
	 * Generate a new list using the given filter instance.
	 * @return a new, filtered list
	 */
	//SimpleList<T> filter(SimpleFilter<T> filter);


	/**
	 * Get a new SimpleList instance with all items of this list which match the given filter
	 * @param filter SimpleFilter instance
	 * @return new SimpleList instance
	 */
	default SimpleList<T> filter(SimpleFilter<T> filter)
	{
		SimpleList<T> result;
		try
		{
			result = this.getClass().newInstance();
		}
		catch(Exception e)
		{
			result = new SimpleListImpl<>();

		}

		for (T o : this)
		{
			if (filter.include(o))
			{
				result.add(o);
			}
		}
		return result;
	}

	/**
	 * R: Type of the new List elements
     * T: Type of old List
	 * @param transform transformation function
	 * @param <R> Type to cast the List Items to
	 * @return a List of the new Type
	 */
	default <R> SimpleList<R> map(Function<T, R> transform)
	{
        SimpleList<R> result;
        // TODO: check if there is a better solution to this
        try
        {
            result = this.getClass().newInstance();
        }
        catch(Exception e)
        {
            result = new SimpleListImpl<>();
        }

        for (T e : this)
        {
            result.add(transform.apply(e));
        }

        return result;
	}

	T[] toArray();

	void addDefault(Class<T> clazz) throws IllegalAccessException, InstantiationException;
}
