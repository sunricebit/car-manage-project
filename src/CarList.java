
import java.util.*;
import java.io.*;
import java.lang.*;

public class CarList {

    BrandList brandList;
    ArrayList<Car> list = new ArrayList<>();

    public CarList(BrandList bList) {
        brandList = bList;
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
                    line = line.trim();
                    if (line.length() > 0) {
                        StringTokenizer stk = new StringTokenizer(line, ", ");
                        String ID = stk.nextToken();
                        ID = ID.trim();
                        String brandID = stk.nextToken();
                        brandID = brandID.trim();
                        String color = stk.nextToken();
                        color = color.trim();
                        String frameID = stk.nextToken();
                        frameID = frameID.trim();
                        String engineID = stk.nextToken();
                        engineID = engineID.trim();
                        int pos = brandList.searchID(brandID);
                        Brand brand = brandList.list.get(pos);
                        Car c = new Car(ID, brand, color, frameID, engineID);
                        list.add(c);
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
                    Car o = list.get(i);
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
            if (list.get(i).carID.compareTo(ID) == 0) {
                return i;
            }
        }
        return -1;
    }

    public int searchFrame(String fID) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).frameID.compareTo(fID) == 0) {
                return i;
            }
        }
        return -1;
    }

    public int searchEngine(String eID) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).engineID.compareTo(eID) == 0) {
                return i;
            }
        }
        return -1;
    }

    public void addCar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter car ID: ");
        String ID = sc.nextLine();
        if (searchID(ID) != -1) {
            System.out.println("Car ID already exist.");
        } else {
            Menu menu = new Menu();
            Brand brand = (Brand) menu.ref_getChoice(brandList.list);

            System.out.println("Enter color: ");
            sc = new Scanner(System.in);
            String color = sc.nextLine();
            while (color.trim().equals("")) {
                System.out.println("Color can not be blank.");
                System.out.println("Enter color again: ");
                sc = new Scanner(System.in);
                color = sc.nextLine();
            }

            System.out.println("Enter frame ID: ");
            sc = new Scanner(System.in);
            String frameID = sc.nextLine();
            if (searchFrame(frameID) != -1) {
                System.out.println("FrameID already exist.");
            } else {
                while (checkFrame(frameID) == false) {
                    System.out.println("Frame ID must be same form as F00000.");
                    System.out.println("Enter frame ID again: ");
                    sc = new Scanner(System.in);
                    frameID = sc.nextLine();
                }
            }

            System.out.println("Enter engine ID: ");
            sc = new Scanner(System.in);
            String engineID = sc.nextLine();
            if (searchEngine(engineID) != -1) {
                System.out.println("EngineID already exist.");
            } else {
                while (checkEngine(engineID) == false) {
                    System.out.println("Engine ID must be same form as E00000.");
                    System.out.println("Enter engine ID again: ");
                    sc = new Scanner(System.in);
                    engineID = sc.nextLine();
                }
            }

            Car o = new Car(ID, brand, color, frameID, engineID);
            list.add(o);
        }
    }

    public boolean checkFrame(String s) {
        if (s.charAt(0) != 'F') {
            return false;
        }
        if (s.length() != 6) {
            return false;
        }
        for (int i = 1; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public boolean checkEngine(String s) {
        if (s.charAt(0) != 'E') {
            return false;
        }
        if (s.length() != 6) {
            return false;
        }
        for (int i = 1; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public void printBasedBrandName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a part of brand name: ");
        String aPart = sc.next();
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            Car c = list.get(i);
            if (c.brand.getBrandName().contains(aPart)) {
                System.out.println(c.screenString());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No car is detected!");
        }
    }

    public boolean removeCar() {
        System.out.println("Enter car ID: ");
        Scanner sc = new Scanner(System.in);
        int pos = searchID(sc.next());
        if (pos < 0) {
            System.out.println("Not found!");
            return false;
        } else {
            list.remove(pos);
        }
        return true;
    }

    public boolean updateCar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Car ID: ");
        String ID = sc.nextLine();
        int pos = searchID(ID);
        if (pos < 0) {
            System.out.println("Not found!");
            return false;
        } else {
            Menu menu = new Menu();
            Brand brand = (Brand) menu.ref_getChoice(brandList.list);
            System.out.println("Enter color: ");
            sc = new Scanner(System.in);
            String color = sc.nextLine();
            while (color.trim().equals("")) {
                System.out.println("Color can not be blank.");
                System.out.println("Enter color again: ");
                sc = new Scanner(System.in);
                color = sc.nextLine();
            }

            System.out.println("Enter frame ID: ");
            sc = new Scanner(System.in);
            String frameID = sc.nextLine();
            if (searchFrame(frameID) != -1) {
                System.out.println("FrameID already exist.");
            } else {
                while (checkFrame(frameID) == false) {
                    System.out.println("Frame ID must be same form as F0000.");
                    System.out.println("Enter frame ID again: ");
                    sc = new Scanner(System.in);
                    frameID = sc.nextLine();
                }
            }

            System.out.println("Enter engine ID: ");
            sc = new Scanner(System.in);
            String engineID = sc.nextLine();
            if (searchEngine(engineID) != -1) {
                System.out.println("EngineID already exist.");
            } else {
                while (checkEngine(engineID) == false) {
                    System.out.println("Engine ID must be same form as E0000.");
                    System.out.println("Enter engine ID again: ");
                    sc = new Scanner(System.in);
                    engineID = sc.nextLine();
                }
            }
            Car o = new Car(ID, brand, color, frameID, engineID);
            list.set(pos, o);
        }
        return true;
    }

    public void listCars() {
//        list.sort(new Comparator<Car>() {
//            @Override
//            public int compare(Car o1, Car o2) {
//                if (o1.getBrand().getBrandName().compareTo(o2.getBrand().getBrandName()) > 0) {
//                    return 1;
//                } else if (o1.getBrand().getBrandName().compareTo(o2.getBrand().getBrandName()) < 0) {
//                    return -1;
//                }
//                return 0;
//            }
//        });
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            Car c = list.get(i);
            System.out.println(c.screenString());
        }

    }
}
