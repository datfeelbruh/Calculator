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
            Romans.valueOf(str);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
    // конвертация из римского в арабское (I...X)
    public Integer romanToArabic(String str){
       if(isRoman(str)) {
           return Romans.valueOf(str).weight;
        }
       return null;
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
