
import java.util.*;
import java.nio.file.*;
import java.io.IOException;

public class ClinicRecords {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> patients = new ArrayList<>();
        Path file = Path.of("clinic_records.txt");

        try {
            System.out.print("Enter number of patients: ");
            int n = Integer.parseInt(sc.nextLine().trim());

            if (n < 1) {
                throw new IllegalArgumentException(
                        "Enter at least one patient.");
            }

            for (int i = 0; i < n; i++) {
                System.out.print("\nEnter patient name: ");
                String name = sc.nextLine().trim();

                System.out.print("Enter disease: ");
                String disease = sc.nextLine().trim();

                if (name.isEmpty() || disease.isEmpty()) {
                    System.out.println(
                            "Name and disease cannot be empty.");
                    i--;
                    continue;
                }

                String record = String.join(",", name, disease);
                patients.add(record);
            }

            // Sort patient records by name
            Collections.sort(patients);

            System.out.println("\n--- CLINIC PATIENT RECORDS ---");

            for (String record : patients) {
                String[] data = record.split(",");

                System.out.println(
                        "Name: " + data[0]
                        + ", Disease: " + data[1]);
            }

            // Search for a patient
            System.out.print("\nEnter name to search: ");
            String search = sc.nextLine().trim();

            boolean found = false;

            for (String record : patients) {
                String[] data = record.split(",");

                if (data[0].equalsIgnoreCase(search)) {
                    System.out.println("Patient found: " + record);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Patient not found.");
            }

            // Save records to file
            Files.write(file, patients);

            System.out.println("\nRecords saved successfully.");

            // Read records from file
            System.out.println("\n--- SAVED FILE CONTENTS ---");

            List<String> savedRecords = Files.readAllLines(file);

            savedRecords.forEach(System.out::println);

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        } finally {
            sc.close();
            System.out.println("Clinic record process completed.");
        }
    }
}
