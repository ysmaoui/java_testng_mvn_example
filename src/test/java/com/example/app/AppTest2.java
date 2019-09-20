package com.example.app;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class AppTest2 {

    @Test
    public void failingTest() {
        ITestResult result = Reporter.getCurrentTestResult();
        result.setAttribute("requirement", "PROJECT-456,PROJECT-789");

        System.out.println("This is a TestNG-Maven based test");
        assertEquals(1, 2);
    }
}
