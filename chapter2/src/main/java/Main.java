import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.function.Predicate;

/**
 * @author paul
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("hello, world!");

        /**
         *
         * 1.a) T --> Function --> R
         *
         * 1.b) Unary operations (minus)
         *
         * 1.c) x -> x + 1
         *
         */

        /**
         *
         * 2)
         *
         */

        ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd-MM-yyyy"));


        /**
         *
         * 3.a) yes
         * 3.b) yes
         * 3.c) no
         *
         */

        // 3.a)
        Runnable runnable = () -> System.out.println("hello world");

        // 3.b)
        JButton button = new JButton();
        button.addActionListener(e -> System.out.println(e.getActionCommand()));

        // 3.c)
        check((Predicate<Integer>) x -> x > 5);
        check((IntPad) x -> x > 5);
        // check(x -> x > 5); // will not comlipe

    }

    static boolean check(Predicate<Integer> predicate) {
        return predicate.test(5);
    }

    static boolean check(IntPad predicate) {
        return predicate.test(5);
    }

}

interface IntPad {
    boolean test(Integer value);
}
