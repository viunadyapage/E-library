/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.perpustakaan;

/**
 *
 * @author User
 */
import java.util.List;
import java.util.Scanner;

public class Perpustakaan {
    public static void main(String[] args) {
        LibraryDAO dao = new LibraryDAO();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Library");
            System.out.println("2. View All Libraries");
            System.out.println("3. Update Library");
            System.out.println("4. Delete Library");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Location: ");
                    String location = scanner.nextLine();
                    System.out.print("Phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Operational Hours: ");
                    String hours = scanner.nextLine();

                    dao.addLibrary(new Library(id, name, location, phone, hours));
                    break;

                case 2:
                    List<Library> libraries = dao.getAllLibraries();
                    for (Library lib : libraries) {
                        System.out.println("ID: " + lib.getId());
                        System.out.println("Name: " + lib.getName());
                        System.out.println("Location: " + lib.getLocation());
                        System.out.println("Phone: " + lib.getPhoneNumber());
                        System.out.println("Operational Hours: " + lib.getOperationalHour());
                        System.out.println("---------------------");
                    }
                    break;

                case 3:
                    System.out.print("Enter ID to update: ");
                    String updateId = scanner.nextLine();
                    System.out.print("New Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("New Location: ");
                    String newLocation = scanner.nextLine();
                    System.out.print("New Phone: ");
                    String newPhone = scanner.nextLine();
                    System.out.print("New Hours: ");
                    String newHours = scanner.nextLine();
                    dao.updateLibrary(new Library(updateId, newName, newLocation, newPhone, newHours));
                    break;

                case 4:
                    System.out.print("Enter ID to delete: ");
                    String deleteId = scanner.nextLine();
                    dao.deleteLibrary(deleteId);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);

        scanner.close();
    }
}
