package oop;

import java.util.ArrayList;

abstract public class Vehicle {
    private final int numberOfWheels;
    private final int numberOfWindows;
    private final int numberOfEngines;
    private final int numberOfSeats;
    private final boolean drivenByHuman;
    private final int tankCapacity;
    private final String className = this.getClass().getSimpleName();
    private final double maxSpeed;
    private final double timeToMaxSpeed;
    protected double distance;

    private final ArrayList<Object> tank = new ArrayList<>();


    public Vehicle(int numberOfWheels, int numberOfWindows, int numberOfEngines, int numberOfSeats, boolean drivenByHuman, int tankCapacity, double maxSpeed, double timeToMaxSpeed) {
        this.numberOfWheels = numberOfWheels;
        this.numberOfWindows = numberOfWindows;
        this.numberOfEngines = numberOfEngines;
        this.numberOfSeats = numberOfSeats;
        this.drivenByHuman = drivenByHuman;
        this.tankCapacity = tankCapacity;
        this.maxSpeed = maxSpeed;
        this.timeToMaxSpeed = timeToMaxSpeed;
    }

    public boolean isEngineStarted(boolean engine) {
        return engine;
    }

    public int getTankCapacity() {
        return tankCapacity;
    }

    public double getDistance() {
        return distance;
    }

    public String getClassName() {
        return className;
    }

    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    public int getNumberOfWindows() {
        return numberOfWindows;
    }

    public int getNumberOfEngines() {
        return numberOfEngines;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public boolean isDrivenByHuman() {
        return drivenByHuman;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getTimeToMaxSpeed() {
        return timeToMaxSpeed;
    }

    public ArrayList<Object> getTank() {
        return tank;
    }

    abstract public Vehicle drive();

    abstract public Vehicle start();

    abstract public Vehicle stop();

    public abstract Vehicle fly();

    abstract public Vehicle fillTheTank();

    abstract public void vehicleInfo();
}
