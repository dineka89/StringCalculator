import java.io.IOException;
import java.util.Scanner;

public class Starter {
    private static boolean isDigit;

    public static void main(String[] args) throws IOException {
        worker();
    }

    public static void worker() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String str = scanner.nextLine();

        String[] a = personalSplitter(str);
        a[1] = a[1].replace(" ", "");
        String resString = "";
        int num = 0;
        if (isDigit) {
            num = Integer.parseInt(a[2]);
        }
        if (a[1].equalsIgnoreCase("+")) {
            resString = a[0] + a[2];
        } else if (a[1].equalsIgnoreCase("-")) {
            resString = a[0].replace(a[2], "");
        } else if (a[1].equalsIgnoreCase("*")) {
            for (int i = 0; i < num; ++i) {
                resString += a[0];
            }
        } else if (a[1].equalsIgnoreCase("/")) {
            if (isDigit == false) {
                throw new IOException("INCORRECT DATA");
            }
            resString = a[0].substring(0, num);
        } else {
            System.out.println("INCORRECT OPERATION TYPE");
            throw new IOException("INCORRECT DATA");
        }
        if (resString.length() >= 40) {
            resString = resString.substring(0, 40) + "...";
        }
        System.out.println(resString);
    }

    public static boolean filter(String[] a, String[] b, String entered) {
        if (a[0].substring(0, 1).equalsIgnoreCase("\"")
                && a[0].substring(a[0].length() - 1).equalsIgnoreCase("\"")) {
            System.out.println("TEST 1 OK 1 WORD");
        } else {
            System.out.println("TEST 1 ERROR");
            return false;
        }
        try {
            int num = Integer.parseInt(a[2]);
            isDigit = true;
            if (num < 1 || num > 10) {
                System.out.println("TEST 2 ERROR");
                return false;
            }
        } catch (NumberFormatException nfe) {
            if (a[2].substring(0, 1).equalsIgnoreCase("\"")
                    && a[2].substring(a[2].length() - 1).equalsIgnoreCase("\"")) {
                System.out.println("TEST 1 OK 2 WORD");
            } else {
                System.out.println("TEST 1 ERROR");
                return false;
            }
        }
        if (a[0].replace("\"" + "", "").length() <= 10 && a[2].length() <= 10) {
            System.out.println("TEST 3 OK");
        } else {
            System.out.println("TEST 3 ERROR");
        }
        return true;
    }

    private static String[] personalSplitter(String incString) throws IOException {
        char[] charArray = incString.toCharArray();
        boolean start = true;
        String[] result = new String[3];
        String[] result2 = new String[3];
        int counter = 0;
        String tmpResult = "";

        int kavCount = 0;
        for (int i = 0; i < charArray.length; ++i) {
            if (charArray[i] == "\"".toCharArray()[0]) {
                kavCount++;
            }
        }
        if (kavCount == 4) {
            for (int i = 0; i < charArray.length; ++i) {
                if (charArray[i] == "\"".toCharArray()[0] && start) {
                    tmpResult = tmpResult + charArray[i];
                    start = !start;
                } else if (charArray[i] == "\"".toCharArray()[0] && !start) {
                    result[counter] = tmpResult.replace("\"", "");
                    result2[counter] = tmpResult + "\"";
                    tmpResult = "";
                    ++counter;
                    start = !start;
                    --i;
                } else {
                    tmpResult = tmpResult + charArray[i];
                }
            }
        } else if (kavCount == 2) {
            start = true;
            result = new String[3];
            int localCounter = 0;
            for (int i = 0; i < charArray.length; ++i) {
                if (charArray[i] == "\"".toCharArray()[0] && start) {
                    tmpResult = tmpResult + charArray[i];
                    start = !start;
                } else if (charArray[i] == "\"".toCharArray()[0] && !start) {
                    result[counter] = tmpResult.replace("\"", "");
                    result2[counter] = tmpResult + "\"";
                    ++counter;
                    localCounter = i;
                    break;
                } else {
                    tmpResult = tmpResult + charArray[i];
                }
            }
            try {
                String[] incSub = incString.substring(localCounter + 2, charArray.length).split(" ");
                result[1] = incSub[0];
                result[2] = incSub[1];
                result2[1] = incSub[0];
                result2[2] = incSub[1];
            } catch (StringIndexOutOfBoundsException ape) {
                throw new IOException("INCORRECT DATA");
            }
        }
        try {
            boolean a = filter(result2, result, incString);
            if (a == false) {
                throw new IOException("INCORRECT DATA");
            }
        } catch (NullPointerException npe) {
            throw new IOException("INCORRECT DATA");
        }

        return result;
    }
}
