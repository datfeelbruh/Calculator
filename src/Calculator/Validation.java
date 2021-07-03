package Calculator;

public class Validation {
    private int num1, num2;
    private String operator;
    final Romans[] values = Romans.values();
   // проверка на арабское число
   public boolean isArabic(String str){
       try {
           Integer.parseInt(str);
           return true;
       } catch (NumberFormatException e){
            return false;
       }
   }
   // проверка на римское число
    public boolean isRoman(String str){
        try {
            romanToArabic(str);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
    // конвертация из римского в арабское (I...X)
    public Integer romanToArabic(String str){
       int result = 0;
       for(int i = 0; i < str.length() - 1; i++) {
           if (Romans.valueOf(String.valueOf(str.charAt(i))).weight < Romans.valueOf(String.valueOf(str.charAt(i + 1))).weight) {
               result -= Romans.valueOf(String.valueOf(str.charAt(i))).weight;
           } else {
               result += Romans.valueOf(String.valueOf(str.charAt(i))).weight;
           }
       }
       result += Romans.valueOf(String.valueOf(str.charAt(str.length() - 1))).weight;
       return result;
    }
    //Конвертация из арабского в римское (1...100)
    public String arabicToRoman(Integer num){
       StringBuilder result = new StringBuilder();
       for (int i = values.length - 1; i >= 0; i--) {
           while (num >= values[i].weight) {
               result.append(values[i]);
               num -= values[i].weight;
           }
       }
       return result.toString();
    }
    // проверка корректности оператора
    public boolean correctOperator(String op) {
       return "+".equals(op) || "-".equals(op) || "*".equals(op) || "/".equals(op);
    }


}
