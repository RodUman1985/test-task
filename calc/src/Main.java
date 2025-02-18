import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Введите исходные данные. Допустимый диапазан опперандов: 1 - 10");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) {
        if (!stringValidation(input)) {
            throw new RuntimeException("неверный формат входящей строки");
        }
        if (!operandValidation(input)) {
            throw new RuntimeException("неверные операнды");
        }
        String[] crArray = transformInputString(input);
        int firstOperand = Integer.parseInt(crArray[0]);
        int secondOperand = Integer.parseInt(crArray[crArray.length - 1]);
        char operator = getOperator(input);
        switch (operator) {
            case '/':
                return division(firstOperand, secondOperand);
            case '*':
                return multiplication(firstOperand, secondOperand);
            case '+':
                return addition(firstOperand, secondOperand);
            case '-':
                return subtraction(firstOperand, secondOperand);
            default:
                throw new IllegalArgumentException("неверный оператор");
        }

    }


    public static boolean stringValidation(String input) {
        return !((!firstStringValidation(input)) | (!secondStringValidation(input)));
    }

    public static boolean firstStringValidation(String input) {
        char[] charArray = input.toCharArray();
        char[] operatorArray = {'+', '-', '/', '*'};
        char firstElement = charArray[0];
        char lastElement = charArray[charArray.length - 1];
        for (int i = 0; i < operatorArray.length; i = i + 1) {
            if ((operatorArray[i] == firstElement) || (operatorArray[i] == lastElement)) {
                return false;
            }
        }
        return true;
    }


    public static boolean secondStringValidation(String input) {
        int summ = 0;
        char[] charArray = input.toCharArray();
        char[] operatorArray = {'+', '-', '/', '*'};
        for (int i = 0; i < operatorArray.length; i++) {
            for (int j = 0; j < charArray.length; j++) {
                if (operatorArray[i] == charArray[j]) {
                    summ++;
                }
            }
        }
        return summ == 1;
    }


    public static char getOperator(String input) {
        int summ = 0;
        char operator = 'x';
        char[] charArray = input.toCharArray();
        char[] operatorArray = {'+', '-', '/', '*'};
        for (int i = 0; i < operatorArray.length; i++) {
            for (int j = 0; j < charArray.length; j++) {
                if (operatorArray[i] == charArray[j]) {
                    summ++;
                    operator = operatorArray[i];
                }
            }
        }
        if (summ != 1) {
            throw new RuntimeException("неверныей оператор");
        }
        return operator;
    }

    public static boolean operandValidation(String input) {
        int summ = 0;
        String[] stringArray = input.split("[^1-9]");
        String[] operandArray = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        for (int i = 0; i < stringArray.length; i++) {
            for (int j = 0; j < operandArray.length; j++) {
                if (stringArray[i].equals(operandArray[j])) {
                    summ++;
                }
            }
        }
        if (summ != 2) {
            return false;
        }
        return true;
    }

    public static String[] transformInputString(String input) {
        String[] crtArray = input.split("[^1-9]");
        return crtArray;
    }
//multiplication, division, addition, subtraction

    public static String division(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("делить на 0 нельзя");
        }
        return String.valueOf(a / b);
    }

    public static String multiplication(int a, int b) {
        return String.valueOf(a * b);
    }

    public static String addition(int a, int b) {
        return String.valueOf(a + b);
    }

    public static String subtraction(int a, int b) {
        return String.valueOf(a - b);
    }
}