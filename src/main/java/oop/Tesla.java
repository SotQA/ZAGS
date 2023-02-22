package oop;

public class Tesla extends Vehicle {
    public Tesla(int numberOfWheels, int numberOfWindows, int numberOfEngines, int numberOfSeats, boolean drivenByHuman, int tankCapacity, double maxSpeed, double timeToMaxSpeed) {
        super(numberOfWheels, numberOfWindows, numberOfEngines, numberOfSeats, drivenByHuman
                , tankCapacity, maxSpeed, timeToMaxSpeed);
    }

    @Override
    public Tesla fillTheTank() {
        for (int i = 0; i < getTankCapacity(); i++) {
            getTank().add(i, "220 volts");
        }
        System.out.println("The tank is full now, you may go.");
        return this;
    }

    @Override
    public Tesla start() {
        if (isEngineStarted(true) && getTank().size() != 0) {
            drive();
        } else {
            System.out.println("You should fill your tank first!");
        }
        return this;
    }

    @Override
    public Tesla drive() {
        if (isEngineStarted(true))
            getDistance();
        return this;
    }

    @Override
    public Tesla stop() {
        System.out.println(getClassName() + " has driven " + distance + " meters and reached the speed of " + this.getMaxSpeed() + " m/s in " + this.getTimeToMaxSpeed() + " seconds .");
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
                (getClassName() + " is a electro car with " + this.getNumberOfWheels()
                        + " wheels ," + this.getNumberOfEngines() + " engines, " + this.getNumberOfSeats() + " seats"
                        + " and " + this.getNumberOfWindows() + " windows, " + this.getTankCapacity() + " tank capaticty.");
    }

    @Override
    public Vehicle fly() {
        return null;
    }
}
