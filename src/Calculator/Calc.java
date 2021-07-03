package Calculator;

public class Calc {
    private String operator; // числа в выражении
    private int num1, num2; // оператор в выражении

    // выполнение выражения
    private int calcExp(int n1, String op, int n2){
        return switch (op) {
            case "+" -> n1 + n2;
            case "-" -> n1 - n2;
            case "/" -> n1 / n2;
            case "*" -> n1 * n2;
            default -> throw new AssertionError();
        };
    }

    // метод с проверками и выводом
    public String result(String exp) throws CalcException{
        boolean isRomanExp = false;
        Validation validation = new Validation();
        String[] expItems = exp.split(" ");
        //Проверка что создался массив из трех значений. Удачная проверка указывает на то что пользователь ввел числа и оператор разделив их пробелами. Иначе исключение.
        if(expItems.length != 3) {
            throw new CalcException("""
                    Выражение должно быть формата: "Число1 - оператор - Число2".
                    Числа и оператор должны быть разделенны пробелом
                    Римские числа должны быть в верхнем регистре""");
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
        // Обработка нулей и чисел больше 10
        if (!(num1 > 0 && num1 <= 62) || !(num2 > 0 && num2 <= 62)) {
            throw new CalcException("Числа должны быть от 1 до 62 включительно.Так как римское представление числа выше 3999 не поддерживается");
        }
        // Получаем результат
        int result = calcExp(num1, operator, num2);
        // Если числа на входе были римские, вернуть результат в римском представлении.
        if (isRomanExp) {
            String sign = result < 0 ? "-" : "";
            return sign + validation.arabicToRoman(Math.abs(result));
        }
        return String.valueOf(result);
    }
}

