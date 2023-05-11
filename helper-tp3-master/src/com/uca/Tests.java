package com.uca;

import org.junit.jupiter.api.Test;

import java.beans.Transient;
import java.util.concurrent.Callable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Tests {
    
    @Test
    public void testConvertArabRoman(){
        // test arab to roman
        assertThat(RomanConverter.getRomanFromNumber(4), equalTo("IV"));
        assertThat(RomanConverter.getRomanFromNumber(3), equalTo("III"));
        assertThat(RomanConverter.getRomanFromNumber(45), equalTo("XLV"));
    }

    @Test
    public void testConvertRomanArab(){
        // test roman to arab
        assertThat(RomanConverter.getNumberFromRoman("III"), equalTo(3));
    }

    @Test
    public void testNegative(){
        // test error negative
        assertThat(exceptionOf(() -> RomanConverter.getRomanFromNumber(-2)), instanceOf(IllegalArgumentException.class));
        assertThat(exceptionOf(() -> RomanConverter.getRomanFromNumber(-0)), instanceOf(IllegalArgumentException.class));
        assertThat(exceptionOf(() -> RomanConverter.getRomanFromNumber(-999)), instanceOf(IllegalArgumentException.class));
    }

    @Test
    public void testInterval(){
        // test out of interval
        assertThat(exceptionOf(() -> RomanConverter.getRomanFromNumber(4000)), instanceOf(IllegalArgumentException.class));
        assertThat(exceptionOf(() -> RomanConverter.getRomanFromNumber(0)), instanceOf(IllegalArgumentException.class));
        assertThat(exceptionOf(() -> RomanConverter.getRomanFromNumber(9999)), instanceOf(IllegalArgumentException.class));
    }

    @Test
    public void testRepitition(){
        // test repetitions
        assertThat(exceptionOf(() -> RomanConverter.getNumberFromRoman("IIIII")), instanceOf(IllegalArgumentException.class));
        assertThat(exceptionOf(() -> RomanConverter.getNumberFromRoman("CCCC")), instanceOf(IllegalArgumentException.class));
        assertThat(exceptionOf(() -> RomanConverter.getNumberFromRoman("DDDDDD")), instanceOf(IllegalArgumentException.class));
    }

    @Test
    public void testPair(){
        // test pair repititions (VV)
        assertThat(exceptionOf(() -> RomanConverter.getNumberFromRoman("VV")), instanceOf(IllegalArgumentException.class));
        assertThat(exceptionOf(() -> RomanConverter.getNumberFromRoman("LL")), instanceOf(IllegalArgumentException.class));
        assertThat(exceptionOf(() -> RomanConverter.getNumberFromRoman("DD")), instanceOf(IllegalArgumentException.class));
    }

    @Test
    public void testPrefixes(){
        // test wrong prefixes
        assertThat(exceptionOf(() -> RomanConverter.getNumberFromRoman("IIV")), instanceOf(IllegalArgumentException.class));
        assertThat(exceptionOf(() -> RomanConverter.getNumberFromRoman("VIV")), instanceOf(IllegalArgumentException.class));
        assertThat(exceptionOf(() -> RomanConverter.getNumberFromRoman("CLDX")), instanceOf(IllegalArgumentException.class));
    }
    
    @Test
    public void testDoubleValue(){
        RomanNumber roman = new RomanNumber(9, "IX");
        double d1 = roman.doubleValue();
        double d2 = 9;
        assertThat(d1, equalTo(d2));
    }

    @Test
    public void testFloatValue(){
        RomanNumber roman = new RomanNumber(9, "IX");
        float f1 = roman.floatValue();
        float f2 = 9;
        assertThat(f1, equalTo(f2));
    }

    @Test
    public void testIntValue(){
        RomanNumber roman = new RomanNumber(9, "IX");
        int I1 = roman.intValue();
        int I2 = 9;
        assertThat(I1, equalTo(I2));
    }

    @Test
    public void testLongValue(){
        RomanNumber roman = new RomanNumber(9, "IX");
        long l1 = roman.longValue();
        long l2 = 9;
        assertThat(l1, equalTo(l2));
    }

    @Test
    public void testToString(){
        RomanNumber roman = new RomanNumber(9, "IX");
        assertThat(roman.toString(), equalTo("IX"));
    }

    @Test
    public void testTout(){
        for (int i = 1; i < 4000; i++){
            assertThat(i, equalTo(RomanConverter.getNumberFromRoman(RomanConverter.getRomanFromNumber(i))));
        }
    }

    //Help you to handle exception. :-)
    public static Throwable exceptionOf(Callable<?> callable) {
        try {
            callable.call();
            return null;
        } catch (Throwable t) {
            return t;
        }
    }
}