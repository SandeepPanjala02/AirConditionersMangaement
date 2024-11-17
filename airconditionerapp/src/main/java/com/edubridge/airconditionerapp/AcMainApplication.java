package com.edubridge.airconditionerapp;

import java.util.List;
import java.util.Scanner;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.edubridge.airconditionerapp.model.AirConditioner;
import com.edubridge.airconditionerapp.util.HibernateUtils;

public class AcMainApplication {
	 public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        int choice;

	        do {
	            System.out.println("\nAir Conditioner Management");
	            System.out.println("1. Add Air Conditioner");
	            System.out.println("2. View All Air Conditioners");
	            System.out.println("3. Update Air Conditioner");
	            System.out.println("4. Delete Air Conditioner");
	            System.out.println("5. Exit");
	            System.out.print("Enter your choice: ");
	            choice = scanner.nextInt();

	            switch (choice) {
	                case 1 -> addAirConditioner(scanner);
	                case 2 -> viewAirConditioners();
	                case 3 -> updateAirConditioner(scanner);
	                case 4 -> deleteAirConditioner(scanner);
	                case 5 -> System.out.println("Exiting...");
	                default -> System.out.println("Invalid choice!");
	            }
	        } while (choice != 5);

	        HibernateUtils.shutdown();
	        scanner.close();
	    }

	    private static void addAirConditioner(Scanner scanner) {
	        System.out.print("Enter brand: ");
	        String brand = scanner.next();
	        System.out.print("Enter model: ");
	        String model = scanner.next();
	        System.out.print("Enter price: ");
	        double price = scanner.nextDouble();
	        System.out.print("Enter power rating: ");
	        double powerRating = scanner.nextDouble();

	        AirConditioner ac = new AirConditioner(brand, model, price, powerRating);

	        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	            Transaction transaction = session.beginTransaction();
	            session.save(ac);
	            transaction.commit();
	            System.out.println("Air Conditioner added successfully.");
	        }
	    }

	    private static void viewAirConditioners() {
	        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	            List<AirConditioner> airConditioners = session.createQuery("from AirConditioner", AirConditioner.class).list();
	            airConditioners.forEach(System.out::println);
	        }
	    }

	    private static void updateAirConditioner(Scanner scanner) {
	        System.out.print("Enter Air Conditioner ID to update: ");
	        int id = scanner.nextInt();

	        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	            AirConditioner ac = session.get(AirConditioner.class, id);
	            if (ac != null) {
	                System.out.print("Enter new brand: ");
	                ac.setBrand(scanner.next());
	                System.out.print("Enter new model: ");
	                ac.setModel(scanner.next());
	                System.out.print("Enter new price: ");
	                ac.setPrice(scanner.nextDouble());
	                System.out.print("Enter new power rating: ");
	                ac.setPoweRating(scanner.nextDouble());

	                Transaction transaction = session.beginTransaction();
	                session.update(ac);
	                transaction.commit();
	                System.out.println("Air Conditioner updated successfully.");
	            } else {
	                System.out.println("Air Conditioner not found.");
	            }
	        }
	    }

	    private static void deleteAirConditioner(Scanner scanner) {
	        System.out.print("Enter Air Conditioner ID to delete: ");
	        int id = scanner.nextInt();

	        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	            AirConditioner ac = session.get(AirConditioner.class, id);
	            if (ac != null) {
	                Transaction transaction = session.beginTransaction();
	                session.delete(ac);
	                transaction.commit();
	                System.out.println("Air Conditioner deleted successfully.");
	            } else {
	                System.out.println("Air Conditioner not found.");
	            }
	        }
	    }

}
