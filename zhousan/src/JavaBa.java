public class JavaBa {

    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,4,5};
        int[] b = new int[]{2,3,4,5,6};
        int c = 0;
        int[] cs = new int[25];
        int i = 0;
        for (int ai : a) {
            for (int bi : b) {
                c = ai*bi;
                cs[i] = c;
                i++;
            }
        }
        for (int c1 : cs) {
            System.out.print(c1+",");
        }
    }

}
