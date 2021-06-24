package Calculator;

public class Calc {
    private String operator; // числа в выражении
    private int num1, num2; // оператор в выражении

    // выполнение выражения
    private int calcExp(int n1, String op, int n2){
        int result;
        switch(op){
            case "+":
                result = n1 + n2;
                break;
            case "-":
                result = n1 - n2;
                break;
            case "/":
                result = n1 / n2;
                break;
            case "*":
                result = n1 * n2;
                break;
            default:
                throw new AssertionError();
        }
        return result;
    }

    // метод с проверками и выводом
    public String result(String exp) throws CalcException{
        boolean isRomanExp = false;
        Validation validation = new Validation();
        String[] expItems = exp.split(" ");
        //Проверка что создался массив из трех значений. Удачная проверка указывает на то что пользователь ввел числа и оператор разделив их пробелами. Иначе исключение.
        if(expItems.length != 3) {
            throw new CalcException("Выражение должно быть формата: \"Число1 - оператор - Число2\".\nЧисла и оператор должны быть разделенны пробелом\n" +
                    "Римские числа должны быть в верхнем регистре");
        }
        // Проверка оператора, должен быть + - / *
        if (validation.correctOperator(expItems[1])){
            operator = expItems[1];
        } else {
            throw new CalcException("Оператор '" + expItems[1] +  "' некорректен, должен быть +, -, *, / ");
        }
        // Проверка чисел, должны быть оба арабские или оба римские
        if (validation.isArabic(expItems[0]) && validation.isArabic(expItems[2])) {
            num1 = Integer.parseInt(expItems[0]);
            num2 = Integer.parseInt(expItems[2]);
        } else if (validation.isRoman(expItems[0]) && validation.isRoman(expItems[2])) {
            num1 = validation.romanToArabic(expItems[0]);
            num2 = validation.romanToArabic(expItems[2]);
            isRomanExp = true;
        } else {
            throw new CalcException("Числа должны быть целыми.\nЧисла должны быть оба римские или оба арабские.\nРимские числа должны быть в верхнем регистре");
        }
        if (!(num1 >= 1 && num1 <= 10) && !(num2 >= 1 && num2 <= 10)) {
            throw new CalcException("Числа должны быть от 1 до 10 включительно");
        }
        // Получаем результат
        int result = calcExp(num1, operator, num2);

        if (isRomanExp) {
            String sign = result < 0 ? "-" : "";
            return sign + validation.arabicToRoman(Math.abs(result));
        }
        return String.valueOf(result);
    }
}
