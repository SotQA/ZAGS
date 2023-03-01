package oop;

public class Bike extends Vehicle {

    public Bike(int numberOfWheels, int numberOfWindows, int numberOfEngines, int numberOfSeats,
                boolean drivenByHuman, int tankCapacity, double maxSpeed, double timeToMaxSpeed) {
        super(numberOfWheels, numberOfWindows, numberOfEngines, numberOfSeats,
                drivenByHuman, tankCapacity, maxSpeed, timeToMaxSpeed);
    }

    private boolean pedals;
    private boolean tiresFlat;

    @Override
    public Vehicle fillTheTank() {
        if (getTank().size() == 0) {
            System.out.println("This is a bike you dumbass, there is no tank here!!");
        } else {
            System.out.println("Goddamn it, still no tank brah.");
        }
        return this;
    }

    @Override
    public Vehicle start() {
        if (!tiresFlat) {
            pedals = true;
            System.out.println("Started cycling!");
        } else {
            System.out.println("You can't go with flat tires man!");
        }
        return this;
    }

    @Override
    public Vehicle drive() {
        getDistance();
        return this;
    }

    @Override
    public Vehicle stop() {
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
                (getClassName() + " is a non-mechanical vehicle with " + this.getNumberOfWheels()
                        + " wheels ," + this.getNumberOfEngines() + " engines, " + this.getNumberOfSeats() + " seats"
                        + " and " + this.getNumberOfWindows() + " windows, " + this.getTankCapacity() + " tank capaticty.");
    }

    @Override
    public Vehicle fly() {
        return null;
    }
}
