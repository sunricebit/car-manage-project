
import java.util.*;
import java.io.*;
import java.lang.*;

public class Menu {

    public int int_getChoice(ArrayList<Brand> options) {
        int response;
        int N = options.size();
        for (int i = 0; i < N; i++) {
            System.out.println((i + 1) + " " + options.get(i));
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Please choose an option 1.." + N + ": ");
        response = Integer.parseInt(sc.next());
        return response;
    }

    public Brand ref_getChoice(ArrayList<Brand> options) {
        int response;
        int N = options.size();
        do {
            response = int_getChoice(options);
        } while (response < 0 || response > N);
        return options.get(response - 1);
    }
}
