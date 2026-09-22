package LW01.unguided;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(
                Main.class.getResourceAsStream("washes.txt")
        );

        int totalRecords = sc.nextInt();

        WashService[] washes = new WashService[totalRecords];
        int[] units = new int[totalRecords];

        for (int i = 0; i < totalRecords; i++) {

            String type = sc.next();
            String id = sc.next();
            int days = sc.nextInt();
            units[i] = sc.nextInt();

            if (type.equals("MOTORCYCLE")) {

                washes[i] = new MotorcycleWash(id, days);

            } else if (type.equals("CAR")) {

                washes[i] = new CarWash(id, days);

            }
        }

        sc.close();

        for (int i = 0; i < totalRecords; i++) {

            System.out.println(
                    washes[i].getId() + " | "
                    + washes[i].label() + " | "
                    + washes[i].calculateCharge(units[i])
            );
        }
    }
}