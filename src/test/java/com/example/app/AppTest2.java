package com.example.app;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class AppTest2 {

    @Test
    public void failingTest() {
        System.out.println("This is a TestNG-Maven based test");
        assertEquals(1, 2);

        ITestResult result = Reporter.getCurrentTestResult();
        result.setAttribute("requirement", "CUMULUS-456,CUMULUS-789");
    }
}