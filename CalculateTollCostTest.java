package com.testapp.test;

import com.mycompany.testapp.CalculateTollCost;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Riddhi
 */
public class CalculateTollCostTest {

    public CalculateTollCostTest() {
    }
    private static CalculateTollCost caltollcost;

    @Before
    public void setUp() throws Exception {
        caltollcost = new CalculateTollCost();
    }
    @After
public void tearDown() throws Exception {
}

    @Test
    public void TestDistanceOfQEWToHighway400() throws IOException, FileNotFoundException, ParseException {
        double result = CalculateTollCost.calculatedistance("QEW", "Highway 400");
        assertEquals(result,67.74799999999999);
    }

    @Test
    public void TestCostOfQEWToHighway400() throws IOException, FileNotFoundException, ParseException {
        double result = CalculateTollCost.CalculateCost("QEW", "Highway 400");
        assertEquals( result,16.936999999999998);
    }

    
}
