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
        String[] a = personalSplitter(str);
        a[1] = a[1].replace(" ", "");
        String resString = "";
        int num = Integer.parseInt(a[2]);
        if (a[1].equalsIgnoreCase("+")) {
            resString = a[0] + a[2];
        } else if (a[1].equalsIgnoreCase("-")) {
            resString = a[0].replace(a[2], "");
        } else if (a[1].equalsIgnoreCase("*")) {
            for (int i = 0; i < num; ++i) {
                resString += a[0];
            }
        } else if (a[1].equalsIgnoreCase("/")) {
            resString = a[0].substring(0, num);
        } else {
            System.out.println("INCORRECT OPERATION TYPE");
        }
        if (resString.length() >= 40) {
            resString = resString.substring(0, 40) + "...";
        }
        System.out.println(resString);
    }


    public static boolean filter(String[] a, String[] b, String entered) {
        boolean result = true;
        //проверка на кавычки
        if (a[0].substring(0, 1).equalsIgnoreCase("\"")
                && a[0].substring(a[0].length() - 1).equalsIgnoreCase("\"")) {
            System.out.println("TEST 1 OK");
        } else {
            System.out.println("TEST 1 ERROR");
            return false;
        }
        //проверка на число >1 && <=10
        try {
            int num = Integer.parseInt(a[2]);
            if (num < 1 || num > 10) {
                System.out.println("TEST 2 ERROR");
                return false;
            }
        } catch (NumberFormatException nfe) {
            //test not necessary
        }
        return result;
    }

    private static String[] personalSplitter(String incString) {
        char[] charArray = incString.toCharArray();
        boolean start = true;
        String[] result = new String[3];
        String[] result2 = new String[3];
        int counter = 0;
        String tmpResult = "";

        //цикл для подсчета кавычек
        int kavCount = 0;
        for (int i = 0; i < charArray.length; ++i) {
            if (charArray[i] == "\"".toCharArray()[0]) {
                kavCount++;
            }
        }
        //в зависимости от количества кавычек выбираем алгоритм
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
            String[] incSub = incString.substring(localCounter + 2, charArray.length).split(" ");
            //дописываем в массив result
            result[1] = incSub[0];
            result[2] = incSub[1];
        }
        filter(result2, result, incString);
        return result;
    }
}
