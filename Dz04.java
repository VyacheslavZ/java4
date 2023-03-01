import java.util.LinkedList;
import java.util.Scanner;

public class Dz04 {
    
    public static void main(String[] args) {

        System.out.println("Реализовать консольное приложение, которое принимает от пользователя строку вида: -text~num-");
            
        Scanner in = new Scanner(System.in);
        System.out.println("""
                Введите значение и его ключ (например:text~4).
                Для выхода из программы, введите "exit".""");

        LinkedList<String> valueList = new LinkedList<>();
        while (true) {
            System.out.print("> ");
            String readLine = in.nextLine();
            if (isCorrectInput(readLine)) {
                String[] dataArray = readLine.split("~");
                if (dataArray[0].equals("print") && valueList.contains(dataArray[1])) {
                    String value = getValue(valueList, dataArray[1]);
                    System.out.println(value);
                    valueList.remove(value);
                    valueList.remove(dataArray[1]);
                } else if (dataArray[0].equals("print") && !(valueList.contains(dataArray[1]))) {
                    System.out.println("\"\"");
                } else if (!(dataArray[0].equals("print")) && valueList.contains(dataArray[1])) {
                    System.out.println("Ключь уже занят!");
                } else {
                    valueList.add(dataArray[0]);
                    valueList.add(dataArray[1]);
                }
            } else if (readLine.equalsIgnoreCase("exit")) {
                break;
            } else {
                System.out.println("Ошибка!");
            }
        }
        in.close();
    }

    private static boolean isCorrectInput(String str) {
        if (str.contains("~")) {
            String[] strArr = str.split("~");
            return isDigit(strArr[1]);
        } else {
            return false;
        }
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static String getValue(LinkedList<String> lst, String key) {
        String value = "";
        for (int i = 1; i < lst.size(); i += 2) {
            if (lst.get(i).equals(key)) {
                value = lst.get(i - 1);
                break;
            }
        }
        return value;
    }
}