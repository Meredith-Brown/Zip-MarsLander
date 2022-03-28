import junit.framework.TestCase;
import org.junit.Assert;

public class VehicleTest extends TestCase {

    public void testCheckFinalStatus1() {
        // given
        Vehicle vehicle = new Vehicle(1000);
        vehicle.Altitude = 0;
        vehicle.Velocity = 15;
        String expected = "\nCRASH!!\n\tThere were no survivors.\n\n";
        // when
        String actual = vehicle.checkFinalStatus();
        // then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(-3, vehicle.Flying);
    }

    public void testCheckFinalStatus2() {
        // given
        Vehicle vehicle = new Vehicle(1000);
        vehicle.Altitude = 0;
        vehicle.Velocity = 5;
        String expected = "\nThe Starship crashed. Good luck getting back home. Elon is pissed.\n\n";
        // when
        String actual = vehicle.checkFinalStatus();
        // then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(-2, vehicle.Flying);
    }

    public void testCheckFinalStatus3() {
        // given
        Vehicle vehicle = new Vehicle(1000);
        vehicle.Altitude = 0;
        vehicle.Velocity = 1;
        String expected = "\nYou made it! Good job!\n\n";
        // when
        String actual = vehicle.checkFinalStatus();
        // then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(0, vehicle.Flying);
    }

    public void testCheckFinalStatus4() {
        // given
        Vehicle vehicle = new Vehicle(1000);
        vehicle.Altitude = 100;
        String expected = "\nThere is no fuel left. You're floating around like Major Tom.\n\n";
        // when
        String actual = vehicle.checkFinalStatus();
        // then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(-1, vehicle.Flying);
    }

    public void testComputeDeltaV() {
        // given
        Vehicle vehicle = new Vehicle(1000);
        vehicle.Velocity = 175;
        vehicle.Gravity = 100;
        vehicle.Burn = 105;
        int deltaVExpected = 170;
        // when
        int deltaVActual = vehicle.computeDeltaV();
        // then
        Assert.assertEquals(deltaVExpected, deltaVActual);
    }

    public void testAdjustForBurn() {
        // given
        Vehicle vehicle = new Vehicle(1000);
        vehicle.Velocity = 175;
        vehicle.Gravity = 100;
        vehicle.Fuel = 1000;
        int expectedBurn = 105;
        int expectedPrevAltitude = 1000;
        int expectedVelocity = 170;
        int expectedAltitude = 830;
        int expectedFuel = 895;
        // when
        vehicle.adjustForBurn(105);
        int actualBurn = vehicle.Burn;
        int actualPrevAltitude = vehicle.PrevAltitude;
        int actualVelocity = vehicle.Velocity;
        int actualAltitude = vehicle.Altitude;
        int actualFuel = vehicle.Fuel;
        // then
        Assert.assertEquals(expectedBurn, actualBurn);
        Assert.assertEquals(expectedPrevAltitude, actualPrevAltitude);
        Assert.assertEquals(expectedVelocity, actualVelocity);
        Assert.assertEquals(expectedAltitude, actualAltitude);
        Assert.assertEquals(expectedFuel, actualFuel);
    }

    public void testStillFlying1() {
        Vehicle vehicle = new Vehicle(1000);
        Assert.assertTrue(vehicle.stillFlying());
    }

    public void testStillFlying2() {
        Vehicle vehicle = new Vehicle(1000);
        vehicle.Altitude = 0;
        Assert.assertFalse(vehicle.stillFlying());
    }

    public void testOutOfFuel1() {
        Vehicle vehicle = new Vehicle(10000);
        Assert.assertFalse(vehicle.outOfFuel());
    }

    public void testOutOfFuel2() {
        Vehicle vehicle = new Vehicle(10000);
        vehicle.Fuel = 0;
        Assert.assertTrue(vehicle.outOfFuel());
    }

    public void testGetStatus() {
        // given
        Vehicle vehicle = new Vehicle(50000);
        int tickExpected = 10*10;
        int velocityExpected = 1000;
        int fuelExpected = 12000;
        int altitudeExpected = 50000;
        int flyingExpected = 1;
        // when
        DescentEvent actualEvent = vehicle.getStatus(10);
        int tickActual = actualEvent.Seconds;
        int velocityActual = actualEvent.Velocity;
        int fuelActual = actualEvent.Fuel;
        int altitudeActual = actualEvent.Altitude;
        int flyingActual = actualEvent.getStatus();
        // then
        Assert.assertEquals(tickExpected, tickActual);
        Assert.assertEquals(velocityExpected, velocityActual);
        Assert.assertEquals(fuelExpected, fuelActual);
        Assert.assertEquals(altitudeExpected, altitudeActual);
        Assert.assertEquals(flyingExpected, flyingActual);
    }
}