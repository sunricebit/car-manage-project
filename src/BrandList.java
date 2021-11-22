
import java.util.*;
import java.io.*;
import java.lang.*;

public class BrandList {

    public ArrayList<Brand> list;

    public BrandList() {
        list = new ArrayList();
    }

    public boolean loadFromFile(String file) {
        File f = new File(file);
        if (!f.exists()) {
            return false;
        } else {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.length() > 0) {
                        StringTokenizer stk = new StringTokenizer(line, ",:");
                        String ID = stk.nextToken();
                        ID = ID.trim();
                        String name = stk.nextToken();
                        name = name.trim();
                        String sound = stk.nextToken();
                        sound = sound.trim();
                        double price = Double.parseDouble(stk.nextToken(": "));
                        Brand p = new Brand(ID, name, sound, price);
                        list.add(p);
                    }
                }
                System.out.println("Read data success");
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        return true;
    }

    public boolean saveToFile(String file) {
        File f = new File(file);
        boolean result = false;
        try {
            if (f.isFile() && f.canWrite()) {
                FileWriter fw = new FileWriter(f);
                PrintWriter pw = new PrintWriter(fw);
                for (int i = 0; i < list.size(); i++) {
                    
                    Brand o = list.get(i);
                    pw.println(o.toString());
                }
                result = true;
                System.out.println("Write data to " + f.getCanonicalPath() + " is completed");
                pw.close();
                fw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public int searchID(String ID) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).brandID.compareTo(ID)==0) {
                return i;
            }
        }
        return -1;
    }

    public Brand getUserChoice() {
        Menu mnu = new Menu();
        return (Brand) mnu.ref_getChoice(list);
    }

    public void addBrand() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter BrandID: ");
        String ID = sc.nextLine();
        if (searchID(ID) != -1) {
            System.out.println("BrandID already exists.");
        } else {
            System.out.println("Enter Brand Name: ");
            sc = new Scanner(System.in);
            String brName = sc.nextLine();
            while (brName.trim().equals("")) {
                System.out.println("Brand Name can not be blank.");
                System.out.println("Enter Brand Name again: ");
                sc = new Scanner(System.in);
                brName = sc.nextLine();
            }
            System.out.println("Enter Sound Brand: ");
            sc = new Scanner(System.in);
            String sdBrand = sc.nextLine();
            while (sdBrand.trim().equals("")) {
                System.out.println("Sound Brand can not be blank.");
                System.out.println("Enter Sound Brand again: ");
                sc = new Scanner(System.in);
                sdBrand = sc.nextLine();
            }
            System.out.println("Enter price: ");
            sc = new Scanner(System.in);
            double price = sc.nextDouble();
            while (price <= 0) {
                System.out.println("Price can not smaller than or equal 0.");
                System.out.println("Enter price again: ");
                sc = new Scanner(System.in);
                price = sc.nextDouble();
            }
            Brand o = new Brand(ID, brName, sdBrand, price);
            list.add(o);
        }

    }

    public void updateBrand() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter BrandID: ");
        String ID = sc.nextLine();
        int pos = searchID(ID);
        if (pos < 0) {
            System.out.println("Not found!");
        } else {
            System.out.println("Enter Brand Name: ");
            sc = new Scanner(System.in);
            String brName = sc.nextLine();
            while (brName.trim().equals("")) {
                System.out.println("Brand Name can not blank.");
                System.out.println("Enter Brand Name again: ");
                sc = new Scanner(System.in);
                brName = sc.nextLine();
            }
            System.out.println("Enter Sound Brand: ");
            sc = new Scanner(System.in);
            String sdBrand = sc.nextLine();
            while (sdBrand.trim().equals("")) {
                System.out.println("Sound Brand can not blank.");
                System.out.println("Enter Sound Brand again: ");
                sc = new Scanner(System.in);
                sdBrand = sc.nextLine();
            }
            System.out.println("Enter price: ");
            sc = new Scanner(System.in);
            double price = sc.nextDouble();
            while (price <= 0) {
                System.out.println("Price can not smaller than or equal 0.");
                System.out.println("Enter price again: ");
                sc = new Scanner(System.in);
                price = sc.nextDouble();
            }
            list.get(pos).setBrandName(brName);
            list.get(pos).setSoundBrand(sdBrand);
            list.get(pos).setPrice(price);
        }
    }

    public void listBrand() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }

}
