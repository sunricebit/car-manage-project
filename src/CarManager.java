
import java.util.*;
import java.io.*;
import java.lang.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CarManager {

    public static void main(String[] args) {
        BrandList brandList = new BrandList();
        Menu menu = new Menu();
        System.out.println("");

        int choice = 0;
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String f = s + "\\brands.txt";
        brandList.loadFromFile(f);

        CarList carList = new CarList(brandList);
        currentRelativePath = Paths.get("");
        s = currentRelativePath.toAbsolutePath().toString();
        f = s + "\\cars.txt";
        carList.loadFromFile(f);
        do {
            System.out.println("-----------------------------------------------------------");
            System.out.println("[1]. List all brands");
            System.out.println("[2]. Add a new brand");
            System.out.println("[3]. Search a brand based on it’s ID");
            System.out.println("[4]. Update a brand");
            System.out.println("[5]. Save brands to the file, named brands.txt");
            System.out.println("[6]. List all cars in ascending order of brand names");
            System.out.println("[7]. List cars based on a part of an input brand name");
            System.out.println("[8]. Add a car ");
            System.out.println("[9]. Remove a car based on its ID");
            System.out.println("[10]. Update a car based on its ID");
            System.out.println("[11]. Save cars to file, named cars.txt");
            System.out.println("[12]. Exit");
            System.out.println("-----------------------------------------------------------");
            System.out.println("input your choice:");
            Scanner sc = new Scanner(System.in);
            try {
                choice = Integer.parseInt(sc.next());
            } catch (Exception e) {
                e.getStackTrace();
            }
            switch (choice) {
                case 1:
                    brandList.listBrand();
                    break;
                case 2:
                    brandList.addBrand();
                    break;
                case 3:
                    System.out.println("Enter BrandID you want to search: ");
                    sc = new Scanner(System.in);
                    String ID = sc.next();
                    if (brandList.searchID(ID) < 0) {
                        System.out.println("Not found!");

                    } else {
                        System.out.println(brandList.list.get(brandList.searchID(ID)).toString());
                    }
                    ;
                    break;
                case 4:
                    brandList.updateBrand();
                    break;
                case 5:
                    currentRelativePath = Paths.get("");
                    s = currentRelativePath.toAbsolutePath().toString();
                    f = s + "\\brands.txt";
                    brandList.saveToFile(f);
                    break;
                case 6:
                    carList.listCars();
                    break;
                case 7:
                    carList.printBasedBrandName();
                    break;
                case 8:
                    carList.addCar();
                    break;
                case 9:
                    if (carList.removeCar()) {
                        System.out.println("Remove successfully.");
                    }
                    ;
                    break;
                case 10:
                    carList.updateCar();
                    break;
                case 11:
                    currentRelativePath = Paths.get("");
                    s = currentRelativePath.toAbsolutePath().toString();
                    f = s + "\\cars.txt";
                    carList.saveToFile(f);
                    break;
                default:
            }
        } while (choice > 0 && choice <= 11);
    }

}
