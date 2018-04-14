

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * This is the test class that will test the GraphProcessor.
 */
public class TestGraphProcessor {

    GraphProcessor test = null;
    List<String> expected = null; //expected List
    List<String> actual = null; //actual List
    int expectedNum;
    int actualNum;

    /**
     * This is the JUnit setup method before class
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * This is the JUnit teardown method after class
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * This is the setup method that initializes specific fields
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        test = new GraphProcessor();
    }

    /**
     * This is the teardown method that reset the initialized fields to null
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        test = null;
    }

    /**
     * Test if getShortestPath returns an empty list when the inputs are the same.
     */
    @Test
    public void test01_getShortestPath_on_same_input() {
        expected = new ArrayList<String>();
        actual = test.populateGraph("test.txt").getShortestPath("cat", "cat");
        if(! expected.equals(actual))
            fail("expected: " + expected + " actual: " + actual);
    }
    

    /**
     * Test if getShortestPath returns the correct List when the inputs vary and are different.
     */
    @Test
    public void test02_getShortestPath_on_varied_input() {
        expected = new ArrayList<String>();
        expected.add("cat");
        expected.add("hat");
        expected.add("heat");
        expected.add("wheat");
        actual = test.populateGraph("test.txt").getShortestPath("cat", "wheat");
        if(! expected.equals(actual))
            fail("expected: " + expected + " actual: " + actual);
    }
    

    /**
     * Test if getShortestPath returns an empty List when the inputs are unreacheable.
     */
    @Test
    public void test03_getShortestPath_on_unreacheable_input() {
        expected = new ArrayList<String>();
        actual = test.populateGraph("test.txt").getShortestPath("cat", "kit");
        if(! expected.equals(actual))
            fail("expected: " + expected + " actual: " + actual);
    }
    

    /**
     * Test if getShortestDistance returns -1 if the inputs are the same.
     */
    @Test
    public void test04_getShortestDistance_on_same_input() {
        expectedNum = -1;
        actualNum = test.populateGraph("test.txt").getShortestDistance("cat", "cat");
        if(! expected.equals(actual))
            fail("expected: " + expected + " actual: " + actual);
    }
    
    /**
     * Test if getShortestDistance returns the correct result if the inputs vary and are reacheable
     */
    @Test
    public void test05_getShortestDistance_on_varied_input() {
        expectedNum = 3;
        actualNum = test.populateGraph("test.txt").getShortestDistance("cat", "wheat");
        if(! expected.equals(actual))
            fail("expected: " + expected + " actual: " + actual);
    }
    

    /**
     * Test if getShortestDistance returns -1 if the inputs are unreacheable.
     */
    @Test
    public void test06_getShortestDistance_on_unreacheable_input() {
        expectedNum = -1;
        actualNum = test.populateGraph("test.txt").getShortestPath("cat", "kit");
        if(! expected.equals(actual))
            fail("expected: " + expected + " actual: " + actual);
    }
    
}

