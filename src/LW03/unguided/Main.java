package LW03.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Problem 1
        Scanner registrations = new Scanner(Main.class.getResourceAsStream("registrations.txt"));

        Set<String> registeredStudents = new LinkedHashSet<>();

        while (registrations.hasNextLine()) {
            String studentId = registrations.nextLine();

            if (!registeredStudents.contains(studentId)) {
                registeredStudents.add(studentId);
            }
        }

        registrations.close();

        // Problem 2
        Scanner checkins = new Scanner(Main.class.getResourceAsStream("checkins.txt"));

        Set<String> checkedInStudents = new LinkedHashSet<>();

        int rejectedAttempts = 0;

        System.out.println("===== Event Check-In Results =====");

        while (checkins.hasNextLine()) {

            String studentId = checkins.nextLine();

            if (!registeredStudents.contains(studentId)) {

                System.out.println(studentId + ": Rejected (not registered)");

                rejectedAttempts++;

            } else if (checkedInStudents.contains(studentId)) {

                System.out.println(studentId + ": Rejected (already checked in)");

                rejectedAttempts++;

            } else {

                checkedInStudents.add(studentId);

                System.out.println(studentId + ": Checked in");
            }
        }

        checkins.close();

        int absentStudents = registeredStudents.size() - checkedInStudents.size();

        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + registeredStudents.size());
        System.out.println("Successful check-ins: " + checkedInStudents.size());
        System.out.println("Absent students: " + absentStudents);
        System.out.println("Rejected attempts: " + rejectedAttempts);
    }

}