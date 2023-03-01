package oop;

public class Main {
    public static void main(String[] args) {
        Tesla teslaModelY = new Tesla(4, 5, 2, 4, false,
                10, 84, 3.5);
        teslaModelY
                .fillTheTank()
                .start()
                .drive()
                .stop()
                .vehicleInfo();

        System.out.println("____________________________________________________");

        Audi audiRX8 = new Audi(4, 4, 1, 5, true,
                20, 90, 3.7);
        audiRX8
                .fillTheTank()
                .start()
                .drive()
                .stop()
                .vehicleInfo();

        System.out.println("____________________________________________________");

        Bike mountainBike = new Bike(2, 0, 0, 1, true,
                0, 14, 35);
        mountainBike
                .start()
                .drive()
                .stop()
                .vehicleInfo();

        System.out.println("____________________________________________________");

        Boeing boeing = new Boeing(10, 120, 8, 300, true,
                1000, 240, 500);
        boeing
                .fillTheTank()
                .start()
                .fly()
                .stop()
                .vehicleInfo();
    }
}
