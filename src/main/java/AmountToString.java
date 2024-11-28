import java.math.BigDecimal;

public class AmountToString {
    public static final String[] HUNDREDS = {"", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};
    public static final String[] TENS = {"", "десять", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
    public static final String[] PRIME_NUMBERS = {"", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять", "десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать", "двадцать"};

    public static String numberToString(BigDecimal amount) {
        String lost;
        String whole;
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            return "Сумма == 0";
        }
        if (amount.toString().contains(".")) {
            String[] devided = amount.toString().split("\\.");
            return toStr(devided[0]).concat(" рублей ").concat(toStr(devided[1])).concat(" копеек");
        }
        return toStr(amount.toString());
    }

    private static String toStr(String str) {
        if (str.equals("0")) {
            return "ноль";
        }
        int rubInt = Integer.parseInt(str);
        StringBuilder result = new StringBuilder();
        if (rubInt >= 1000) {
            int temp = rubInt / 1000;

            if (temp >= 100) {
                result.append(HUNDREDS[temp / 100]).append(" ");
                temp %= 100;
            }
            if (temp >= 20) {
                result.append(TENS[temp / 10]).append(" ");
                temp %= 10;
            }
            if (temp > 0) {
                result.append(PRIME_NUMBERS[temp]);
            }
            result.append(" тысяч").append(" ");
            rubInt = rubInt % 1000;
        }
        if (rubInt >= 100) {
            int temp = rubInt / 100;
            result.append(HUNDREDS[temp]).append(" ");
            rubInt = rubInt % 100;
        }
        if (rubInt >= 20) {
            int temp = rubInt / 10;
            result.append(TENS[temp]).append(" ");
            rubInt = rubInt % 10;
        }
        if (rubInt > 0) {
            result.append(PRIME_NUMBERS[rubInt]).append(" ");
        }
        return result.toString().trim();
    }

    public static void main(String[] args) {
        BigDecimal value = new BigDecimal("83340.37");
        System.out.println(value+"\n"+numberToString(value));
        BigDecimal value1 = new BigDecimal("888.13");
        System.out.println(value1+"\n"+numberToString(value1));
    }
}

