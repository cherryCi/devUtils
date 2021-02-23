/**
 * @author cherry.cui
 * @version 1.0
 * @date 2020/10/30 12:44
 */
public class testUtils {

    public static int reverse(int x) {
        String num = String.valueOf(x);
        char[] array = num.toCharArray();
        boolean isFu = false;
        int index = 0;
        char[] result = new char[num.length()];
        if (array[0] == '-') {
            result[0] = array[0];
            index = 1;
        }
        int resultIndex = index;
        int end = num.length() - 1;
        for (int i = end; i >= index; i--) {
            if (i == end && array[end] == 0) {
                continue;
            }

            result[resultIndex] = array[i];
            resultIndex++;
        }
        String resultStr = new String(result);
        if(resultStr.compareTo("214748364")<0&&resultStr.compareTo("-214748364")>0){
            return Integer.parseInt(resultStr);
        }
        return 0;
    }

    public static void main(String[] args) {
        reverse(1534236469);
    }


}
