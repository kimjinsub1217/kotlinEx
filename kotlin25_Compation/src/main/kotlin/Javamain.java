public class Javamain {
    public static int JavaA1 = 100;

    public static void javaMethod1() {
        System.out.println("javaMethod1");
    }

    public static void main(String[] args) {
        int a1 = TestClass1.Companion.getKotlinA3();
        System.out.printf("a1 :%d\n", a1);
        TestClass1.Companion.kotlinMethod3();

    }
}
