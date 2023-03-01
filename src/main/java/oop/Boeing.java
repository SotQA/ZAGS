package oop;

public class Boeing extends Vehicle {

    public Boeing(int numberOfWheels, int numberOfWindows, int numberOfEngines, int numberOfSeats, boolean drivenByHuman, int tankCapacity, double maxSpeed, double timeToMaxSpeed) {
        super(numberOfWheels, numberOfWindows, numberOfEngines, numberOfSeats, drivenByHuman, tankCapacity, maxSpeed, timeToMaxSpeed);
    }

    @Override
    public Vehicle fillTheTank() {
        for (int i = 0; i < getTankCapacity(); i++) {
            getTank().add(i, 1);
        }
        System.out.println("The tank is full now, pilots are prepared.");
        return this;
    }

    @Override
    public Vehicle start() {
        if (isEngineStarted(true) && getTank().size() != 0) {
            drive();
        } else {
            System.out.println("You should fill your tank first!");
        }
        return this;
    }

    @Override
    public Vehicle fly() {
        if (isEngineStarted(true))
            getDistance();
        return this;
    }

    @Override
    public Vehicle stop() {
        System.out.println(getClassName() + " has flown " + distance + " meters and reached the speed of " + this.getMaxSpeed() + " m/s in " + this.getTimeToMaxSpeed() + " seconds .");
        return this;
    }

    @Override
    public double getDistance() {
        distance = this.getMaxSpeed() * this.getTimeToMaxSpeed();
        return distance;
    }


    @Override
    public void vehicleInfo() {
        System.out.println
                (getClassName() + " 747 is one of the biggest plane on earth " + this.getNumberOfWheels()
                        + " wheels ," + this.getNumberOfEngines() + " engines, " + this.getNumberOfSeats() + " seats"
                        + " and " + this.getNumberOfWindows() + " windows, " + this.getTankCapacity() + " tank capaticty.");
    }

    @Override
    public Vehicle drive() {
        return null;
    }
}
