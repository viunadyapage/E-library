package com.mycompany.perpustakaan;

import java.util.List;
import java.util.Scanner;

public class Library {
    public static void main(String[] args) {
        LibraryDAO dao = new LibraryDAO();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n==== MENU PERPUSTAKAAN ====");
            System.out.println("1. Tambah Perpustakaan");
            System.out.println("2. Lihat Semua");
            System.out.println("3. Update");
            System.out.println("4. Hapus");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // untuk konsumsi newline

            switch (choice) {
                case 1:
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Nama: ");
                    String name = scanner.nextLine();
                    System.out.print("Lokasi: ");
                    String location = scanner.nextLine();
                    System.out.print("No. Telp: ");
                    String phone = scanner.nextLine();
                    System.out.print("Jam Operasional: ");
                    String hour = scanner.nextLine();
                    dao.addLibrary(new Library(id, name, location, phone, hour));
                    break;
                case 2:
                    List<Library> list = dao.getAllLibraries();
                    for (Library l : list) {
                        System.out.println(l.getId() + " | " + l.getName() + " | " + l.getLocation() + " | " + l.getPhoneNumber() + " | " + l.getOperationalHour());
                    }
                    break;
                case 3:
                    System.out.print("ID yang ingin diupdate: ");
                    String uid = scanner.nextLine();
                    System.out.print("Nama Baru: ");
                    String uname = scanner.nextLine();
                    System.out.print("Lokasi Baru: ");
                    String uloc = scanner.nextLine();
                    System.out.print("No. Telp Baru: ");
                    String uphone = scanner.nextLine();
                    System.out.print("Jam Operasional Baru: ");
                    String uhour = scanner.nextLine();
                    dao.updateLibrary(new Library(uid, uname, uloc, uphone, uhour));
                    break;
                case 4:
                    System.out.print("ID yang ingin dihapus: ");
                    String did = scanner.nextLine();
                    dao.deleteLibrary(did);
                    break;
                case 0:
                    System.out.println("Keluar...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }

        } while (choice != 0);
    }
}
