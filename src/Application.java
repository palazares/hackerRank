public class Application {
    public static void main(String[] args) {
        String s = "epsxyyflvrrrxzvnoenvpegvuonodjoxfwdmcvwctmekpsnamchznsoxaklzjgrqruyzavshfbmuhdwwmpbkwcuomqhiyvuztwvq";
        long n = 549382313570L;
        System.out.println(repeatedString(s, n));
    }

    // Complete the repeatedString function below.
    static long repeatedString(String s, long n) {
        int residual = (int) (n % s.length());
        long repeatedCount = n / s.length();
        String residualString = s.substring(0, residual);
        int mainStringAs = getAsFromString(s);
        int residualStringAs = getAsFromString(residualString);
        long totalAs = mainStringAs * repeatedCount + residualStringAs;
        return totalAs;
    }

    private static int getAsFromString(String s) {
        int totalAs = 0;
        for (char c : s.toCharArray()) {
            if(c == 'a'){
                totalAs++;
            }
        }
        return totalAs;
    }
}
