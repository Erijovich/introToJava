package homeworkOne;

/*
1. Выбросить случайное целое число в диапазоне от 0 до 2000 и сохранить в i
2. Посчитать и сохранить в n номер старшего значащего бита выпавшего числа
3. Найти все кратные n числа в диапазоне от i до Short.MAX_VALUE сохранить в массив m1
4. Найти все некратные n числа в диапазоне от Short.MIN_VALUE до i и сохранить в массив m2
Пункты реализовать в методе main
*Пункты реализовать в разных методах
int i = new Random().nextInt(k); //это кидалка случайных чисел!)
* */

// import java.util.Arrays;
import java.util.Random;

public class Main {
    final private static int BOUND = 2000;
    public static void main(String[] args) {
        int i, n;
        int[] m1, m2;

        // 1. Выбросить случайное целое число в диапазоне от 0 до 2000 и сохранить в i
        i = randomizerTwoThousand();
        print("random i = %d\n", i);

        // 2. Посчитать и сохранить в n номер старшего значащего бита выпавшего числа
        n = Integer.toBinaryString(i).length();
        print("monsenior bit = %d\n", n);

        // 3. Найти все кратные n числа в диапазоне от i до Short.MAX_VALUE сохранить в массив
        // Считаю, что диапазон в данной задаче ВКЛЮЧАЕТ граничные числа
        m1 = arrayMaker(i, Short.MAX_VALUE, n);
        // print(Arrays.toString(m1));

        // 4. Найти все некратные n числа в диапазоне от Short.MIN_VALUE до i и сохранить в массив m2
        m2 = arrayMaker(Short.MIN_VALUE, i, n, true);
        // print(Arrays.toString(m2));
    }

    private static int randomizerTwoThousand() {
        Random rnd = new Random();
        return rnd.nextInt(BOUND);
    }

    static int[] arrayMaker(int start, int end, int step) {

        int arrStart = start + Math.floorMod(-start, step); // первый валидный элемент массива (кратный шагу)
        int arrEnd = end - Math.floorMod(end, step); // последний валидный элемент (кратный шагу)
        int arrSize = (arrEnd - arrStart + step) / step; // размер массива разница мин макс делённая на шаг и плюс один элемент (+step)
        int[] array = new int[arrSize]; // да уж, не пайтон это совсем.. =)

        for (int i = arrStart; i <= end; i += step) { // заполняем массив
            array[(i-arrStart)/step] = i;}
        return array; }

    static int[] arrayMaker(int start, int end, int step, boolean flag) { // флагом отмечаем заполнение не кратных чисел
                                                                          // хотелось оставить одно название метода
        int index = 0;
        int currentNum = start;
        int[] array = new int[0];

        while (currentNum <= end) { // идём по всем элементам
            if (Math.floorMod(currentNum,step) != 0) { // Если НЕ кратен
                if (array.length <= index) array = arrayDoubleSizer(array); // проверяем , что влезает в массив
                array[index] = currentNum;
                index++;
            }
            currentNum++;
        }
        int [] finalArray = new int[index]; // подгоняем размер массива
        for (int i = 0; i < finalArray.length; i++) finalArray[i] = array[i];
        return finalArray;
    }

    static int[] arrayDoubleSizer (int[] array) { // arrayzz dynamics inc 😎
        int [] newArray =  new int[array.length * 2 + 1]; // АГА! я понял почему тут плюс один - БЕЗОПАСНОСТЬ! если нулевой длины массив будет
        for (int i = 0; i < array.length; i++) newArray[i] = array[i]; // System.arraycopy(array, 0, newArray, 0, array.length); интересно идешка предлагает, надо посмотреть
        return newArray;
    }


    static int[] arrayMaker(int size, int step) { // это упрощённый массив. Создаём от нулевого элемента
        int arrSize = size / step;
        int[] someArray = new int[arrSize];
        for (int i = step; i <= size; i += step) {
            someArray[i / step - 1] = i;} // -1 т.к. индексация с нулевого элемента
        return someArray;
    }

    public static void print(Object o) {System.out.println(o);}

    private static void print(String str, Object o) {System.out.printf(str, o);}
}
