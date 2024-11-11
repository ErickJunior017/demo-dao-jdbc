package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();
        Scanner input = new Scanner(System.in);

        System.out.println("==== TEST 1: seller findById ====");
        Seller seller = sellerDao.findById(1);
        System.out.println(seller);

        System.out.println("\n==== TEST 2: seller findByDepartment ====");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);
        list.forEach(System.out::println);

        System.out.println("\n==== TEST 3: seller findAll ====");
        list = sellerDao.findAll();
        list.forEach(System.out::println);

        System.out.println("\n==== TEST 4: seller INSERT ====");
        Seller sellerInsert = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
        sellerDao.insert(sellerInsert);
        System.out.println("Inserted! New id = " + sellerInsert.getId());

        System.out.println("\n==== TEST 5: seller UPDATE ====");
        seller = sellerDao.findById(1);
        seller.setName("Martha Waine");
        sellerDao.update(seller);
        System.out.println("UPDATE complete!");

        System.out.println("\n==== TEST 6: seller DELETE ====");
        System.out.print("Enter id for delete test: ");
        int id = input.nextInt();
        sellerDao.deleteByID(id);

        input.close();
    }
}
