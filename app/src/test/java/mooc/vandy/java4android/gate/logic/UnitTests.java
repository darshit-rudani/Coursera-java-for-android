package mooc.vandy.java4android.gate.logic;

import org.junit.Test;

import java.lang.reflect.Field;

import io.magnum.autograder.junit.Rubric;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class UnitTests {
    @Rubric(
            value = "testGateConstructor",
            goal = "The goal of this evaluation is to test GateConstructor",
            points = 4.0,
            reference = "This Test fails when: The Could not create Gate object"
    )
    @Test(timeout = 100)
    public void testGateConstructor() {
        Gate testGate = new Gate();
        assertEquals(testGate.getClass().getSimpleName(), "Gate");
        assertEquals("Constructor failed to set the Gate to CLOSED.", Gate.CLOSED, testGate.getSwingDirection());
    }

    @Rubric(
            value = "testOpenIn",
            goal = "The goal of this evaluation is to test Open(Gate.IN)",
            points = 4.0,
            reference = "This Test fails when: The Gate could not be set to " +
                    "IN or the open method returns false"
    )
    @Test(timeout = 100)
    public void testOpenIn() {
        Gate testGate = new Gate();
        assertTrue(testGate.open(Gate.IN));
        assertEquals(Gate.IN, testGate.getSwingDirection());
    }

    @Rubric(
            value = "testOpenOut",
            goal = "The goal of this evaluation is to test Open(Gate.OUT)",
            points = 4.0,
            reference = "This Test fails when: The Gate could not be set to " +
                    "OUT or the open method returns false"
    )
    @Test(timeout = 100)
    public void testOpenOut() {
        Gate testGate = new Gate();
        assertTrue(testGate.open(Gate.OUT));
        assertEquals(Gate.OUT, testGate.getSwingDirection());
    }

    @Rubric(
            value = "testOpenClose",
            goal = "The goal of this evaluation is to test open(Gate.CLOSED)",
            points = 4.0,
            reference = "This Test fails when: Gate open does not return " +
                    "false when passed a value of CLOSED or if the swing " +
                    "state is changed by this call"
    )
    @Test(timeout = 100)
    public void testOpenClosed() {
        Gate testGate = new Gate();
        assertTrue(testGate.open(Gate.IN));
        int direction = testGate.getSwingDirection();
        assertFalse(testGate.open(Gate.CLOSED));
        assertEquals(direction, testGate.getSwingDirection());
    }

    @Rubric(
            value = "testCloseGate",
            goal = "The goal of this evaluation is to test CloseGate",
            points = 4.0,
            reference = "This Test fails when: The Gate did not close properly"
    )
    @Test(timeout = 100)
    public void testCloseGate() {
        Gate testGate = new Gate();
        assertTrue(testGate.open(Gate.IN));
        testGate.close();
        assertEquals(Gate.CLOSED, testGate.getSwingDirection());
    }

    @Rubric(
            value = "testSetSwingIn",
            goal = "The goal of this evaluation is to test SetSwingIn",
            points = 3.0,
            reference = "This Test fails when: The Gate could not be set to " +
                    "swing IN"
    )
    @Test(timeout = 100)
    public void testSetSwingIn() {
        Gate testGate = new Gate();
        assertTrue(testGate.setSwing(Gate.IN));
        assertEquals(Gate.IN, testGate.getSwingDirection());
    }

    @Rubric(
            value = "testSetSwingOut",
            goal = "The goal of this evaluation is to test SetSwingOut",
            points = 3.0,
            reference = "This Test fails when: The Gate could not be set to " +
                    "swing OUT"
    )
    @Test(timeout = 100)
    public void testSetSwingOut() {
        Gate testGate = new Gate();
        assertTrue(testGate.setSwing(Gate.OUT));
        assertEquals(Gate.OUT, testGate.getSwingDirection());
    }

    @Rubric(
            value = "testInvalidSwingParameter",
            goal = "The goal of this evaluation is to test InvalidSwingParameter",
            points = 4.0,
            reference = "This Test fails when: The Gate was set to invalid number"
    )
    @Test(timeout = 100)
    public void testInvalidSwingParameter() {
        Gate testGate = new Gate();
        testGate.close();
        assertFalse(testGate.setSwing(43));
        assertEquals(Gate.CLOSED, testGate.getSwingDirection());
    }

    @Rubric(
            value = "testThruIn",
            goal = "The goal of this evaluation is to test ThruIn",
            points = 4.0,
            reference = "This Test fails when: The gate allows thru() with IN"
    )
    @Test(timeout = 100)
    public void testThruIn() {
        int cur = 10;
        Gate testGate = new Gate();
        testGate.open(Gate.IN);
        cur += testGate.thru(8);
        assertEquals("thru() method returned wrong value for IN gate", 18, cur);
        assertEquals("thru() method should not change swing direction", Gate.IN, testGate.getSwingDirection());
    }

    @Rubric(
            value = "testThruOut",
            goal = "The goal of this evaluation is to test ThruOut",
            points = 4.0,
            reference = "This Test fails when: The gate allows thru() with OUT"
    )
    @Test(timeout = 100)
    public void testThruOut() {
        int cur = 10;
        Gate testGate = new Gate();
        testGate.open(Gate.OUT);
        cur += testGate.thru(3);
        assertEquals("thru() method returned wrong value for OUT gate", 7, cur);
        assertEquals("thru() method should not change swing direction", Gate.OUT, testGate.getSwingDirection());
    }


    @Rubric(
            value = "testThruWhenClosed",
            goal = "The goal of this evaluation is to test ThruWhenClosed",
            points = 4.0,
            reference = "This Test fails when: The gate Allowed thru() when " +
                    "gate was closed"
    )
    @Test(timeout = 100)
    public void testThruWhenClosed() {
        Gate testGate = new Gate();
        testGate.close();
        int cur = 10;
        cur += testGate.thru(4);
        assertEquals("thru() method returned wrong value for CLOSED gate", 10, cur);
        assertEquals("thru() method should not change swing direction", Gate.CLOSED, testGate.getSwingDirection());
    }

    @Rubric(
            value = "testGateToStringWhenOpenedIn",
            goal = "The goal of this evaluation is to test GateToString",
            points = 3.0,
            reference = "This Test fails when: The method toString() is not " +
                    "created properly"
    )
    @Test(timeout = 100)
    public void testGateToStringWhenOpenedIn() {
        String expected = "This gate is open and swings to enter the pen only";
        Gate testGate = new Gate();
        testGate.open(Gate.IN);
        assertEquals(expected, testGate.toString());
        assertEquals("toString() method should not change swing direction", Gate.IN, testGate.getSwingDirection());
    }

    @Rubric(
            value = "testGateToStringWhenOpenedOut",
            goal = "The goal of this evaluation is to test GateToString",
            points = 3.0,
            reference = "This Test fails when: The method toString() is not " +
                    "created properly"
    )
    @Test(timeout = 100)
    public void testGateToStringWhenOpenedOut() {
        String expected = "This gate is open and swings to exit the pen only";
        Gate testGate = new Gate();
        testGate.open(Gate.OUT);
        assertEquals(expected, testGate.toString());
        assertEquals("toString() method should not change swing direction", Gate.OUT, testGate.getSwingDirection());
    }

    @Rubric(
            value = "testGateToStringWhenClosed",
            goal = "The goal of this evaluation is to test GateToString",
            points = 3.0,
            reference = "This Test fails when: The method toString() is not " +
                    "created properly"
    )
    @Test(timeout = 100)
    public void testGateToStringWhenClosed() {
        String expected = "This gate is closed";
        Gate testGate = new Gate();
        testGate.close();
        assertEquals(expected, testGate.toString());
        assertEquals("toString() method should not change swing direction", Gate.CLOSED, testGate.getSwingDirection());
    }

    @Rubric(
            value = "testGateToString",
            goal = "The goal of this evaluation is to test GateToString",
            points = 3.0,
            reference = "This Test fails when: The method toString() is not " +
                    "created properly"
    )
    @Test(timeout = 100)
    public void testGateToStringWhenInvalid() {
        String expected = "This gate has an invalid swing direction";
        Gate testGate = new Gate();
        try {
            // Set the private member to an invalid swing value using
            // reflection.
            testGate.getClass().getDeclaredField("mSwing");
            Field field = testGate.getClass().getDeclaredField("mSwing");
            field.setAccessible(true);
            field.setInt(testGate, 43);
            assertEquals(expected, testGate.toString());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // Don't bother reporting this as an error because
            // we are not really testing if the mSwing field is
            // named correctly.
        }
    }


    @Rubric(
            value = "testGatesAreIndependent",
            goal = "The goal of this evaluation is to ensure all Gate objects are independent",
            points = 3.0,
            reference = "This Test fails when: Gate objects are not independent"
    )
    @Test(timeout = 100)
    public void testGatesAreIndependent() {
        Gate testGate1 = new Gate();
        assertTrue(testGate1.open(Gate.IN));
        assertEquals(Gate.IN, testGate1.getSwingDirection());
        Gate testGate2 = new Gate();
        assertTrue(testGate2.open(Gate.OUT));
        assertEquals(Gate.OUT, testGate2.getSwingDirection());
        Gate testGate3 = new Gate();
        assertTrue(testGate3.open(Gate.IN));
        int direction = testGate3.getSwingDirection();
        assertFalse(testGate3.open(Gate.CLOSED));
        assertEquals(direction, testGate3.getSwingDirection());
        Gate testGate4 = new Gate();
        assertTrue(testGate4.open(Gate.IN));
        testGate4.close();
        assertEquals(Gate.CLOSED, testGate4.getSwingDirection());

        // now re-verify the status of all Gates to make sure the later Gates
        // did not affect the earlier Gates
        assertEquals("Changing one Gate should not affect other Gates", Gate.IN, testGate1.getSwingDirection());
        assertEquals("Changing one Gate should not affect other Gates", Gate.OUT, testGate2.getSwingDirection());
        assertEquals("Changing one Gate should not affect other Gates", direction, testGate3.getSwingDirection());
        assertEquals("Changing one Gate should not affect other Gates", Gate.CLOSED, testGate4.getSwingDirection());

    }

}
