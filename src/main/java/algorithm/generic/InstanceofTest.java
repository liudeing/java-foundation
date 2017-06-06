package algorithm.generic;

/**
 * Created by liur on 17-5-17.
 */
public class InstanceofTest {
    public static void main(String[] args) {
        GenericMemoryCell<Integer> cell1 = new GenericMemoryCell();
        cell1.write(4);
        Object cell = cell1;
        GenericMemoryCell<String> cell2 = (GenericMemoryCell<String>) cell;
        //instanceof 类型只对原始类型进行
        String s = cell2.read();
        System.out.println(s);
    }

    static class GenericMemoryCell<T> {
        private int value;

        public int write(int i) {
            return value;
        }

        public String read() {
            return String.valueOf(value);
        }
    }
}
