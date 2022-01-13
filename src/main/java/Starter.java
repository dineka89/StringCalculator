import java.util.Scanner;

public class Starter {
    public Starter() {
    }

    public static void main(String[] args) {
        worker();
        System.out.println("FINISH");
    }

    public static void worker() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        filter(str);
        String[] a = personalSplitter(str);
        if (a[1].equalsIgnoreCase(" + ")) {
            System.out.println(a[0] + a[2]);
        } else if (a[1].equalsIgnoreCase(" - ")) {
            String c = a[0].replace(a[2], "");
            System.out.println(c);
        } else {
            int num;
            if (a[1].equalsIgnoreCase(" * ")) {
                num = Integer.parseInt(a[2]);

                for(int i = 0; i < num; ++i) {
                    System.out.print(a[0]);
                }
            } else if (a[1].equalsIgnoreCase(" / ")) {
                num = Integer.parseInt(a[2]);
                String abv = a[0].substring(num);
                System.out.println(abv);
            } else {
                System.out.println("INCORRECT OPERATION TYPE");
            }
        }

    }

    public static boolean filter(String incString) {
        String[] testA1 = incString.split(" ");
        if (testA1[0].substring(0, 1).equalsIgnoreCase("\"") && testA1[0].substring(testA1[0].length() - 1).equalsIgnoreCase("\"")) {
            System.out.println("TEST 1 OK");
            return true;
        } else {
            System.out.println("TEST 1 ERROR");
            return false;
        }
    }

    private static String[] personalSplitter(String incString) {
        char[] charArray = incString.toCharArray();
        boolean start = true;
        String[] result = new String[3];
        int counter = 0;
        String tmpResult = "";

        for(int i = 0; i < charArray.length; ++i) {
            if (charArray[i] == "\"".toCharArray()[0] && start) {
                tmpResult = tmpResult + charArray[i];
                start = !start;
            } else if (charArray[i] == "\"".toCharArray()[0] && !start) {
                result[counter] = tmpResult.replace("\"", "");
                tmpResult = "";
                ++counter;
                start = !start;
                --i;
            } else {
                tmpResult = tmpResult + charArray[i];
            }
        }

        return result;
    }
}
