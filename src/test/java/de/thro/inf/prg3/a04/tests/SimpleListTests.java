package de.thro.inf.prg3.a04.tests;

import de.thro.inf.prg3.a04.collections.CollectionsUtility;
import de.thro.inf.prg3.a04.collections.SimpleFilter;
import de.thro.inf.prg3.a04.collections.SimpleList;
import de.thro.inf.prg3.a04.collections.SimpleListImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListTests {

	private final Logger logger = LogManager.getLogger();
	private SimpleList<Integer> testList;

	@BeforeEach
	void setup(){
		testList = new SimpleListImpl<>();

		testList.add(1);
		testList.add(2);
		testList.add(3);
		testList.add(4);
		testList.add(5);
	}

	@Test
	void testAddElements(){
		logger.info("Testing if adding and iterating elements is implemented correctly");
		int counter = 0;
		for(int o : testList){
			counter++;
		}
		assertEquals(5, counter);
	}

	@Test
	void testSize(){
		logger.info("Testing if size() method is implemented correctly");
		assertEquals(5, testList.size());
	}

	@Test
	void testFilterAnonymousClass(){
		logger.info("Testing the filter possibilities by filtering for all elements greater 2");
		SimpleList<Integer> result = testList.filter(new SimpleFilter<Integer>() {
			@Override
			public boolean include(Integer item) {
				int current = item;
				return current > 2;
			}
		});

		for(int o : result){
			int i = o;
			assertTrue(i > 2);
		}
	}

	@Test
	void testFilterLambda(){
		logger.info("Testing the filter possibilities by filtering for all elements which are dividable by 2");
		SimpleList<Integer> result = testList.filter(o -> (o) % 2 == 0);

		for(int o : result){
			int i = o;
			assertTrue(i % 2 == 0);
		}
	}

	@Test
	void testMap()
	{
		SimpleList<Double> newList = testList.map(new Function<Integer, Double>()
		{
			@Override
			public Double apply(Integer t)
			{
				return t.doubleValue();
			}
		});

		// TODO: implement a test
		for (Double e : newList)
		{
		}
	}

	@Test
	void testSorting()
	{
		SimpleList<Integer> sortList = new SimpleListImpl<>();
		sortList.add(3);
		sortList.add(2);
		sortList.add(6);
		sortList.add(5);
		sortList.add(4);
		sortList.add(1);

		SimpleList<Integer> sortedList = CollectionsUtility.sort(sortList, new Comparator<Integer>()
		{
			@Override
			public int compare(Integer o1, Integer o2)
			{
				return Integer.compare(o1, o2);
			}
		});

		Integer i = 0;

		for (Integer item : sortedList)
		{
			i++;
			assertEquals(i, item);
		}
	}
}