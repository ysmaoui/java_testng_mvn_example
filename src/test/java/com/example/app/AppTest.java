package com.example.app;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class AppTest {
    @Test
    public void testAdd() {
        System.out.println("This is TestNG-Maven Example");
        String str = "TestNG is working fine";
        assertEquals("TestNG is working fine", str);


        ITestResult result = Reporter.getCurrentTestResult();
        result.setAttribute("requirement", "CUMULUS-123");
    }
}