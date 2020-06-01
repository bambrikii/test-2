package org.bambrikii.examples.returnval;

/**
 * Created by Alexander Arakelyan on 14/07/18 22:26.
 */
public class ReturnValMain {
    public String returnVal1(String val) {
        return val == null ? val + "" : null;
    }

    public String returnVal2(String val) {
        return val == null ? val + "" : val;
    }
}
