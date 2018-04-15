import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * This is the test class that will test the GraphProcessor.
 * 
 * @author Chentao Wang (cwang556@wisc.edu)
 */
public class TestGraphProcessor {

    GraphProcessor test;
    List<String> expectedList; //expected List
    List<String> actualList; //actual List
    int expectedNum;
    int actualNum;
    Integer expectedDis;
    Integer actualDis;

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
        expectedList = new ArrayList<>();
        actualNum = test.populateGraph("word_list.txt");
        actualList = test.getShortestPath("RAPINE", "RAPINE");
        if(! expectedList.equals(actualList))
            fail("expected: " + expectedList + " actual: " + actualList);
    }
    

    /**
     * Test if getShortestPath returns the correct List when the inputs vary and are different.
     */
    @Test
    public void test02_getShortestPath_on_varied_input() {
        expectedList = new ArrayList<>();
        expectedList.add("DEFINE");
        expectedList.add("DEFILE");
        expectedList.add("DECILE");
        expectedList.add("DECKLE");
        expectedList.add("HECKLE");
        expectedList.add("HACKLE");
        expectedList.add("HACKEE");
        expectedList.add("HACKER");
        expectedList.add("HANKER");
        expectedList.add("RANKER");
        expectedList.add("RANTER");
        expectedList.add("RENTER");
        expectedList.add("RENDER");
        expectedList.add("READER");
        expectedList.add("HEADER");
        expectedList.add("HEALER");
        expectedList.add("SEALER");
        expectedList.add("SCALER");
        expectedList.add("SCARER");
        expectedList.add("SHARER");
        expectedList.add("SHAVER");
        expectedList.add("SHIVER");
        expectedList.add("SHINER");
        expectedList.add("WHINER");
        expectedList.add("WHINEY");
        expectedList.add("WHINNY");
        expectedList.add("SHINNY");
        actualNum = test.populateGraph("word_list.txt");
        actualList = test.getShortestPath("DEFINE", "SHINNY");
        if(! expectedList.equals(actualList))
            fail("expected: " + expectedList + " actual: " + actualList);
    }
    

    /**
     * Test if getShortestPath returns an empty List when the inputs are unreacheable.
     */
    @Test
    public void test03_getShortestPath_on_unreacheable_input() {
        expectedList = new ArrayList<>();
        actualNum = test.populateGraph("word_list.txt");
        actualList = test.getShortestPath("RAPINE", "ALIKE");
        if(! expectedList.equals(actualList))
            fail("expected: " + expectedList + " actual: " + actualList);
    }
    

    /**
     * Test if getShortestDistance returns -1 if the inputs are the same.
     */
    @Test
    public void test04_getShortestDistance_on_same_input() {
        expectedDis = -1;
        actualNum = test.populateGraph("word_list.txt");
        actualDis = test.getShortestDistance("RAPINE", "RAPINE");
        if(! expectedDis.equals(actualDis))
            fail("expected: " + expectedDis + " actual: " + actualDis);
    }
    
    /**
     * Test if getShortestDistance returns the correct result if the inputs vary and are reacheable
     */
    @Test
    public void test05_getShortestDistance_on_varied_input() {
        expectedDis = 26;
        actualNum = test.populateGraph("word_list.txt");
        actualDis = test.getShortestDistance("DEFINE", "SHINNY");
        if(! expectedDis.equals(actualDis))
            fail("expected: " + expectedDis + " actual: " + actualDis);
    }
    
    /**
     * Test if getShortestDistance returns -1 if the inputs are unreacheable.
     */
    @Test
    public void test06_getShortestDistance_on_unreacheable_input() {
        expectedDis = -1;
        actualNum = test.populateGraph("word_list.txt");
        actualDis = test.getShortestDistance("RAPINE", "ALIKE");
        if(! expectedDis.equals(actualDis))
            fail("expected: " + expectedDis + " actual: " + actualDis);
    }

    /**
     * Test if getShortestPath can accept both upper case and lower case words
     */
    @Test
    public void test07_getShortestPath_on_upper_and_lower_case_words() {
        expectedList = new ArrayList<>();
        expectedList.add("BELLIES");
        expectedList.add("JELLIES");
        expectedList.add("JOLLIES");
        actualNum = test.populateGraph("word_list.txt");
        actualList = test.getShortestPath("beLliEs", "JolLiEs");
        //System.out.println(test.getShortestPath("beLliEs", "JolLiEs"));
        if(! expectedList.equals(actualList))
            fail("expected: " + expectedList + " actual: " + actualList);
    }
    
    /**
     * Test if getShortestDistance can accept both upper case and lower case words
     */
    @Test
    public void test08_getShortestDistance_on_upper_and_lower_case_words() {
        expectedDis = 2;
        actualNum = test.populateGraph("word_list.txt");
        actualDis = test.getShortestDistance("beLliEs", "JolLiEs");
        if(! expectedDis.equals(actualDis))
            fail("expected: " + expectedDis + " actual: " + actualDis);
    }
}