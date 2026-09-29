package lw02.unguided;

import java.util.*;

public class Main{
    public static void main(String[] args) {

        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> foods = new LinkedList<>();
        LinkedList<String[]> drinks = new LinkedList<>();
        LinkedList<String[]> success = new LinkedList<>();

        Queue<String[]> q = new LinkedList<>();
        Stack<String[]> fails = new Stack<>();

        Scanner scanner = new Scanner(
                Main.class.getResourceAsStream("orders.txt")
        );

        while (scanner.hasNext()) {
            String[] order = new String[4];

            order[0] = scanner.next(); // name
            order[1] = scanner.next(); // food
            order[2] = scanner.next(); // drink
            order[3] = scanner.next(); // table

            orders.add(order);
        }
        scanner.close();
        foods.add(new String[]{"Bakso", "2"});
        foods.add(new String[]{"Sate", "1"});
        foods.add(new String[]{"Soto", "2"});

        drinks.add(new String[]{"EsTeh", "4"});
        drinks.add(new String[]{"EsJeruk", "2"});

        q.addAll(orders);

        while (!q.isEmpty()) {
            String[] order = q.poll();

            String food = order[1];
            String drink = order[2];

            boolean foodAvailable = true;
            boolean drinkAvailable = true;

            String[] foodData = null;
            String[] drinkData = null;

            if (!food.equals("-")) {
                for (String[] data : foods) {
                    if (data[0].equals(food)) {
                        foodData = data;

                        int stock = Integer.parseInt(data[1]);

                        if (stock <= 0) {
                            foodAvailable = false;
                        }

                        break;
                    }
                }
            }

            if (!drink.equals("-")) {
                for (String[] data : drinks) {
                    if (data[0].equals(drink)) {
                        drinkData = data;

                        int stock = Integer.parseInt(data[1]);

                        if (stock <= 0) {
                            drinkAvailable = false;
                        }

                        break;
                    }
                }
            }

            if (foodAvailable && drinkAvailable) {

                if (!food.equals("-")) {
                    int stock = Integer.parseInt(foodData[1]);
                    stock--;
                    foodData[1] = String.valueOf(stock);
                }

                if (!drink.equals("-")) {
                    int stock = Integer.parseInt(drinkData[1]);
                    stock--;
                    drinkData[1] = String.valueOf(stock);
                }

                success.add(order);

                } else {
                fails.push(order);
            }
        }

        System.out.println("=== Successfully Processed Orders ===");

        for (String[] order : success) {
            System.out.println(
                    order[0] + " " +
                    order[1] + " " +
                    order[2] + " " +
                    order[3]
            );
        }

        System.out.println("=== Remaining Food Stock ===");

        for (String[] food : foods) {
            System.out.println(food[0] + " : " + food[1]);
        }

        System.out.println("=== Remaining Drink Stock ===");

        for (String[] drink : drinks) {
            System.out.println(drink[0] + " : " + drink[1]);
        }

        System.out.println("=== Failed Orders ===");

        while (!fails.isEmpty()) {
            String[] order = fails.pop();

            System.out.println(
                    order[0] + " " +
                    order[1] + " " +
                    order[2] + " " +
                    order[3]
            );
        }
    }
}
