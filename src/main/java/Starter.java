import java.util.Scanner;

//класс стартер
public class Starter {
    //переменная отвечает за то, является ли a[2] числом
    private static boolean isDigit;

    // принисает метод worker000000
    public static void main(String[] args) {
        //вызов метода воркер11111111
        worker();
        //выводит строку finish
        System.out.println("FINISH");
    }

    //метод worker
    public static void worker() {
        //создается сканер
        Scanner scanner = new Scanner(System.in);
        //строка str считанная из консоли
        String str = scanner.nextLine();
        //массив a с результатом разбиения на элементы
        String[] a = personalSplitter(str);
        //индекс a[1] поменять пробел на пустое значение
        a[1] = a[1].replace(" ", "");
        //строка resString без пробела0000 инициализация новой переменной String
        String resString = "";
        //распарсили строку в число
        int num = 0;
        if (isDigit) {
            num = Integer.parseInt(a[2]);
        }
        //если индекс a[1] равен +
        if (a[1].equalsIgnoreCase("+")) {
            // тогда нулевой индекс прибавить ко 2
            resString = a[0] + a[2];
            //если индекс 1 равен -
        } else if (a[1].equalsIgnoreCase("-")) {
            //тогда индекс 2 меняем на пустоту
            resString = a[0].replace(a[2], "");
            //если индекс 1 равен *
        } else if (a[1].equalsIgnoreCase("*")) {
            // цикл for проходит от 0 пока i не будет меньше num и прибавляет по 1
            for (int i = 0; i < num; ++i) {
                // 0 индекс строки равен i 0000000 конкатенируем значение нулевого элемента num раз
                resString += a[0];
            }
            //если индекс 1 равен /
        } else if (a[1].equalsIgnoreCase("/")) {
            // тогда строка = 0 индекс вернуть то что внутри посчитается(от 0 до указаной цифры)
            // вернуть подстроку строки a[0] в диапазоне 0-num
            resString = a[0].substring(0, num);
        } else {
            //иначе вывести incorrect operation type
            System.out.println("INCORRECT OPERATION TYPE");
        }
        // если длина строки больше либо равно 40
        if (resString.length() >= 40) {
            //тогда посчитать строку от 0 до 40 а после прибавить строку "..."
            resString = resString.substring(0, 40) + "...";
        }
        //вывести restring
        System.out.println(resString);
    }


    // булевый метод filter принимает строковый массив a, массив b, строку enterd
    public static boolean filter(String[] a, String[] b, String entered) {
        //результат = правда
        boolean result = true;
        //проверка на кавычки
        // если от 0 индекса посчитать от 0 до 1 и будет ковычка
        if (a[0].substring(0, 1).equalsIgnoreCase("\"")
                // и посчитать от 0 индекса и всю длину строки и отнять 1 равен ковычка
                && a[0].substring(a[0].length() - 1).equalsIgnoreCase("\"")) {
            // вывести тест 1 пройден
            System.out.println("TEST 1 OK 1 WORD");
        } else {
            // иначе вывести тест 1 не пройден
            System.out.println("TEST 1 ERROR");
            //вернуть false
            return false;
        }
        //проверка на число >1 && <=10
        //попытаться поймать ошибку
        try {
            //распарсить строку с индексом 2 в число
            int num = Integer.parseInt(a[2]);
            isDigit = true;
            //если число меньше 1 и число больше 10
            if (num < 1 || num > 10) {
                //тогда вывести test 2 не прошел
                System.out.println("TEST 2 ERROR");
                // вернуть false
                return false;
            }
            // поймать ошибку NumberFormatException nfe
        } catch (NumberFormatException nfe) {
            if (a[2].substring(0, 1).equalsIgnoreCase("\"")
                    // и посчитать от 0 индекса и всю длину строки и отнять 1 равен ковычка
                    && a[2].substring(a[2].length() - 1).equalsIgnoreCase("\"")) {
                // вывести тест 1 пройден
                System.out.println("TEST 1 OK 2 WORD");
            } else {
                // иначе вывести тест 1 не пройден
                System.out.println("TEST 1 ERROR");
                //вернуть false
                return false;
            }
        }
        if(a[0].replace("\"", "").length() <= 10 && a[2].length() <= 10 ) {
            System.out.println("TEST 3 OK");
        }else {
            System.out.println("TEST 3 ERROR");
        }
        // вернуть результат
        return result;
    }

    // статический метод возвращающий строковый массив personalSplitter который принимает incString
    private static String[] personalSplitter(String incString) {
        char[] charArray = incString.toCharArray();// массив символов
        boolean start = true;
        //массив result равен новому строковому массиву в котором 3 индекса 0000 инициализируем новый стринг массив на 3 элемента
        String[] result = new String[3];
        //массив result2 равен новому строковому массиву в котором 3 индекса
        String[] result2 = new String[3];
        //переменная counter с типом число равен 0
        int counter = 0;
        //tmpResult равно без пробела
        String tmpResult = "";

        //цикл для подсчета кавычек
        //переменная kavCount равен 0
        int kavCount = 0;
        //цикл for если
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
            result2[1] = incSub[0];
            result2[2] = incSub[1];

        }
        filter(result2, result, incString);
        return result;
    }
}
