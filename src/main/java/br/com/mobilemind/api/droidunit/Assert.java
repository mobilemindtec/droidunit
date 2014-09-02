package br.com.mobilemind.api.droidunit;

import java.util.Comparator;

/**
 * Asserts for Comparations
 *
 * @author Ricardo Bocchi
 */
public class Assert {

    private static final String DEFAULT_FAIL_MESSAGE = "test fail";

    /**
     * throw fail exception
     *
     */
    public static void fail() {
        fail(DEFAULT_FAIL_MESSAGE);
    }

    /**
     * throw fails exception
     *
     * @param message error message
     */
    public static void fail(String message) {
        throw new TestException(message);
    }

    /**
     * Compares two objects using the equals method. Equals must be true.
     *
     * @param obj1
     * @param obj2
     */
    public static void areEqual(Object obj1, Object obj2) {
        areEqual(obj1, obj2, DEFAULT_FAIL_MESSAGE);
    }

    /**
     * Compares two objects using the equals method. Equals must be true.
     *
     * @param obj1
     * @param obj2
     * @param message error message
     */
    public static void areEqual(Object obj1, Object obj2, String message) {
        if ((obj1 == null) || (obj2 == null) || !obj1.equals(obj2)) {
            String text = "expected '%s' but '%s' found: ";
            String text1 = obj1 == null ? "null" : obj1.toString();
            String text2 = obj2 == null ? "null" : obj2.toString();
            fail(String.format(text, text1, text2) + message);
        }
    }

    /**
     * Compares two objects using the equals method. Equals must be false.
     *
     * @param obj1
     * @param obj2
     */
    public static void areNoEqual(Object obj1, Object obj2) {
        areNoEqual(obj1, obj2, DEFAULT_FAIL_MESSAGE);
    }

    /**
     * Compares two objects using the equals method. Equals must be false.
     *
     * @param obj1
     * @param obj2
     * @param message error message
     */
    public static void areNoEqual(Object obj1, Object obj2, String message) {
        if ((obj1 == null) || (obj2 == null) || obj1.equals(obj2)) {
            String text = "expected '%s' but '%d' found: ";
            String text1 = obj1 == null ? "null" : obj1.toString();
            String text2 = obj2 == null ? "null" : obj2.toString();
            fail(String.format(text, text1, text2) + message);
        }
    }

    /**
     * Must be false
     *
     * @param state
     */
    public static void isFalse(Object state) {
        isFalse(state, DEFAULT_FAIL_MESSAGE);
    }

    /**
     * Must be false
     *
     * @param state
     * @param message
     */
    public static void isFalse(Object state, String message) {

        boolean b = (state != null && (state instanceof Boolean
                || state.getClass() == Boolean.TYPE)) ? (Boolean) state : false;

        if (b) {
            fail("false expected, but true found: " + message);
        }
    }

    /**
     * Must be true
     *
     * @param state
     */
    public static void isTrue(boolean state) {
        isTrue(state, DEFAULT_FAIL_MESSAGE);
    }

    /**
     * Must be true
     *
     * @param state
     * @param message
     */
    public static void isTrue(Object state, String message) {

        boolean b = (state != null && (state instanceof Boolean
                || state.getClass() == Boolean.TYPE)) ? (Boolean) state : false;

        if (!b) {
            fail("true expected, but false found: " + message);
        }
    }

    /**
     * Must be null
     *
     * @param obj
     */
    public static void isNull(Object obj) {
        isNull(obj, DEFAULT_FAIL_MESSAGE);
    }

    /**
     * Must be null
     *
     * @param obj
     * @param message
     */
    public static void isNull(Object obj, String message) {
        if (obj != null) {
            String text = "null expected, but '%s' found:";
            fail(String.format(text, obj.toString()) + message);
        }
    }

    /**
     * Can't be null
     *
     * @param obj
     */
    public static void isNotNull(Object obj) {
        isNotNull(obj, DEFAULT_FAIL_MESSAGE);
    }

    /**
     * Can't be null
     *
     * @param obj
     * @param message
     */
    public static void isNotNull(Object obj, String message) {
        if (obj == null) {
            fail("not null expected, but null value found: " + message);
        }
    }

    /**
     * Must be Greater
     *
     * @param obj1
     * @param obj2
     */
    public static void isGreater(Object obj1, Object obj2) {
        isGreater(obj1, obj2, DEFAULT_FAIL_MESSAGE);
    }

    /**
     * Must be Greater
     *
     * @param obj1
     * @param obj2
     * @param message
     */
    public static void isGreater(Object obj1, Object obj2, String message) {
        if (compare(obj1, obj2) != 1) {
            String text = "expected greater than '%s', but '%s' found: ";
            String text1 = obj1 == null ? "null" : obj1.toString();
            String text2 = obj2 == null ? "null" : obj2.toString();
            fail(String.format(text, text1, text2) + message);
        }
    }

    /**
     * Must be Greater Or Equals
     *
     * @param obj1
     * @param obj2
     */
    public static void isGreaterOrEqual(Object obj1, Object obj2) {
        isGreaterOrEqual(obj1, obj2, DEFAULT_FAIL_MESSAGE);
    }

    /**
     * Must be Greater Or Equals
     *
     * @param obj1
     * @param obj2
     * @param message
     */
    public static void isGreaterOrEqual(Object obj1, Object obj2, String message) {
        int value = compare(obj1, obj2);
        if (value != 0 && value != 1) {
            String text = "expected greater or equals than '%s', but '%s' found: ";
            String text1 = obj1 == null ? "null" : obj1.toString();
            String text2 = obj2 == null ? "null" : obj2.toString();
            fail(String.format(text, text1, text2) + message);
        }
    }

    /**
     * Must be Less
     *
     * @param obj1
     * @param obj2
     */
    public static void isLess(Object obj1, Object obj2) {
        isLess(obj1, obj2, DEFAULT_FAIL_MESSAGE);
    }

    /**
     * Must be Less
     *
     * @param obj1
     * @param obj2
     * @param message
     */
    public static void isLess(Object obj1, Object obj2, String message) {
        if (compare(obj1, obj2) != -1) {
            String text = "expected less than '%s', but '%s' found: ";
            String text1 = obj1 == null ? "null" : obj1.toString();
            String text2 = obj2 == null ? "null" : obj2.toString();
            fail(String.format(text, text1, text2) + message);
        }
    }

    /**
     * Must be Less Or Equal
     *
     * @param obj1
     * @param obj2
     */
    public static void isLessOrEqual(Object obj1, Object obj2) {
        isLessOrEqual(obj1, obj2, DEFAULT_FAIL_MESSAGE);
    }

    /**
     * Must be Less Or Equal
     *
     * @param obj1
     * @param obj2
     * @param message
     */
    public static void isLessOrEqual(Object obj1, Object obj2, String message) {
        int value = compare(obj1, obj2);
        if (value != -1 && value != 0) {
            String text = "expected less or equals than '%s', but '%s' found: ";
            String text1 = obj1 == null ? "null" : obj1.toString();
            String text2 = obj2 == null ? "null" : obj2.toString();
            fail(String.format(text, text1, text2) + message);
        }
    }

    /**
     * Operation should throw a exception
     * 
     * @param expectedException Expected Exception
     * @param operation Operation Executed
     */
    public static void shouldThrow(Class<? extends Throwable> expectedException, ExceptionDelegate operation) {
        shouldThrow(expectedException, null, operation);
    }

   /**
    * Operation should throw a exception
    * 
    * @param expectedException Expected Exception
    * @param message Fail Message
    * @param operation Operation Executed
    */
    public static void shouldThrow(Class<? extends Throwable> expectedException, String message, ExceptionDelegate operation) {
        try {
            operation.execute();
        } catch (Throwable e) {
            if (e.getClass() == expectedException) {
                return;
            }

            if (message == null) {
                message = "expected exception %s but found exception %s";
                Assert.fail(String.format(message, expectedException.getName(), e.getClass().getName()));
            } else {
                Assert.fail(message);
            }
        }

        if (message == null) {
            Assert.fail(String.format("expected exception %s", expectedException.getName()));
        } else {
            Assert.fail(message);
        }
    }

    private static int compare(Object obj1, Object obj2) {
        if (obj1 instanceof Comparator && obj2 instanceof Comparator) {
            return ((Comparator) obj1).compare(obj1, obj2);
        }

        if (obj1 instanceof Comparable && obj2 instanceof Comparable) {
            return ((Comparable) obj1).compareTo(obj2);
        }

        throw new TestException("values can not be compared");
    }
}
