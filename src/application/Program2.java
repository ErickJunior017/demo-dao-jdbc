package application;

import model.dao.DaoFactory;
import model.dao.DepatmentDao;
import model.dao.DepatmentDao;
import model.entities.Department;
import model.entities.Department;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program2 {

    public static void main(String[] args) {

        DepatmentDao dd = DaoFactory.createDepatmentDao();
        Scanner input = new Scanner(System.in);

        System.out.println("==== TEST 1: Department findById ====");
        Department department = dd.findById(1);
        System.out.println(department);



        System.out.println("\n==== TEST 3: Department findAll ====");
        List<Department> list = dd.findAll();
        list.forEach(System.out::println);

        System.out.println("\n==== TEST 4: Department INSERT ====");
        Department DepartmentInsert = new Department(null, "MERCENARIO");
        dd.insert(DepartmentInsert);
        System.out.println("Inserted! New id = " + DepartmentInsert.getId());

        System.out.println("\n==== TEST 5: Department UPDATE ====");
        department = dd.findById(1);
        department.setName("POWER");
        dd.update(department);
        System.out.println("UPDATE complete!");

        System.out.println("\n==== TEST 6: Department DELETE ====");
        System.out.print("Enter id for delete test: ");
        int id = input.nextInt();
        dd.deleteByID(id);

        input.close();
    }
}
