package com.swaglabs.utils;
import org.testng.asserts.SoftAssert ;

public class customSoftAssertion extends SoftAssert {
    private customSoftAssertion(){
    // private constructor
    }
    public static customSoftAssertion softAssertion =new customSoftAssertion();
    public static void customSoftAssertAll(){
        try {
            softAssertion.assertAll("custom assertion");
        } catch (Exception e) {
            System.out.println("Custom Soft Assert Failed");

        }

    }


}
