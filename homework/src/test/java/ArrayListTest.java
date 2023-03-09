import org.example.arraylist.ArrayList;


import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.ListIterator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArrayListTest {


    ArrayList<String> array = new ArrayList<String>();

    @Test
    public void addTest() {
        array.add("1");
        array.add("2");
        array.add("3");

        assertThat(array.size()).isEqualTo(3);
        assertThat(array.isEmpty()).isFalse();
    }

    @Test
    public void addOfIndexTest() {
        array.add("1");
        array.add("2");
        array.add("3");

        array.add(1, "5");

        assertThat(array.size()).isEqualTo(4);
        assertThat(array.get(1)).isEqualTo("5");
        assertThat(array.get(3)).isEqualTo("3");
    }

    @Test
    public void getTest() {
        array.add("1");
        array.add("2");
        array.add("3");

        assertThat(array.get(0)).isEqualTo("1");
        assertThat(array.get(2)).isEqualTo("3");
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(3));
    }

    @Test
    public void removeTest() {
        array.add("1");
        array.add("2");
        array.add("3");

        assertThat(array.size()).isEqualTo(3);
        assertThat(array.remove(0)).isEqualTo("1");
        assertThat(array.size()).isEqualTo(2);
        assertThat(array.remove(array.size() - 1)).isEqualTo("3");
    }

    @Test
    public void cleanTest() {

        array.add("1");
        array.add("2");
        array.add("3");

        assertThat(array.size()).isEqualTo(3);

        array.clear();

        assertThat(array.size()).isEqualTo(0);
        assertThat(array.isEmpty()).isTrue();

    }

    @Test
    public void setTest() {

        array.add("1");
        array.add("2");
        array.add("3");

        array.set(0, "5");

        assertThat(array.get(0)).isEqualTo("5");
    }

    @Test
    public void sortTest() {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(3);
        numbers.add(1);
        numbers.add(2);
        numbers.add(6);
        numbers.add(4);
        numbers.add(5);

        numbers.sort();

        assertThat(numbers.get(0)).isEqualTo(1);
        assertThat(numbers.get(1)).isEqualTo(2);
        assertThat(numbers.get(numbers.size() - 1)).isEqualTo(6);
    }


    @Test
    public void quickSortTest() {

        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(3);
        numbers.add(1);
        numbers.add(2);
        numbers.add(6);
        numbers.add(4);
        numbers.add(5);

        ArrayList.quickSort(numbers, 0, numbers.size() - 1);

        assertThat(numbers.get(0)).isEqualTo(1);
        assertThat(numbers.get(1)).isEqualTo(2);
        assertThat(numbers.get(numbers.size() - 1)).isEqualTo(6);

    }

    @Test
    public void iteratorTest() {

        array.add("1");
        array.add("2");
        array.add("4");
        array.add("5");
        array.add("6");

        Iterator<String> iterator = array.iterator();

        assertThat(array.size()).isEqualTo(5);

        assertThat(iterator.next()).isEqualTo("1");
        assertThat(iterator.next()).isEqualTo("2");
        iterator.remove();
        assertThat(array.size()).isEqualTo(4);

    }

}
