package org.example.arraylist;


import java.util.Arrays;

/**
 * ArrayList is the data structure that is based on an array. This array stores in itself elements of type T.
 * ArrayList methods add, remove, set new element, clean and checks that the array is empty.
 * @param <T> the object that should implement interface Comparable
 */
public class ArrayList<T extends Comparable<T>> {

    private Object[] array = new Object[10];

    private int size = 0;

    public final int size() {
        return this.size;
    }

    /**
     * @param element the object to be added to the end of the array
     * @return return true after successful added
     */
    public final boolean add(final T element) {

        if (array.length == size) {
            final Object[] oldArray = array;
            array = new Object[this.size() * 2];
            System.arraycopy(oldArray, 0, array, 0, oldArray.length);
        }

        array[size++] = element;
        return true;
    }

    /**
     * @param index index of the element in the array to be returned
     * @return the object from array
     * @throws if index size more than size array or index negative
     * that the exception is thrown IndexOutOfBoundsException
     */
    public final T get(final int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        return (T) array[index];
    }

    /**
     * @param index is position where will be written object in array
     * @param element is object which will be written in array instead of the old one
     * @return new added object
     * @throws if index size more than size array or index negative
     * that the exception is thrown IndexOutOfBoundsException
     */
    public final T set(final int index, final T element) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        array[index] = element;
        return (T) element;
    }


    /**
     * @param index is position object which will be removed from array
     * @return object which was removed from array
     * @throws if index size more than size array or index negative
     * that the exception is thrown IndexOutOfBoundsException
     */
    public final T remove(final int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        final Object element = array[index];
        if (index != this.size() - 1) {
            System.arraycopy(array, index + 1, array, index, this.size() - index - 1);
        }

        size--;
        return (T) element;
    }

    /**
     * the method cleans the whole array and makes its size equal to 0
     */
    public final void clear() {
        array = new Object[1];
        size = 0;
    }

    /**
     * @return true if array have size equal to 0 or false if array not empty
     */
    public final boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * method realize sorting by selection
     */
    public final void sort() {
        for (int i = 0; i < this.size(); i++) {
            int pos = i;
            T min = this.get(i);

            for (int j = i + 1; j < this.size; j++) {
                if (this.get(j).compareTo(min) < 0) {
                    pos = j;
                    min = this.get(j);
                }
            }
            this.set(pos, this.get(i));
            this.set(i, min);
        }
    }


    /**
     * @param array the array to be sorted by the quick sort method
     * @param from is the index of the beginning of the array to be sorted
     * @param to is the index of the last element of the array
     * @param <T> the object that should implement interface Comparable
     */
    public static <T extends Comparable<T>> void quickSort(ArrayList<T> array, int from, int to) {

        if (from < to) {
            int divideIndex = partition(array, from, to);

            quickSort(array, from, divideIndex - 1);

            quickSort(array, divideIndex, to);
        }

    }

    private static <T extends Comparable<T>>  int partition(ArrayList<T> array, int from, int to) {

        int rightIndex = to;
        int leftIndex = from;

        T pivot = array.get(from);
        while (leftIndex <= rightIndex) {

            while (array.get(leftIndex).compareTo(pivot) < 0) {
                leftIndex++;
            }

            while (array.get(rightIndex).compareTo(pivot) > 0) {
                rightIndex--;
            }

            if (leftIndex <= rightIndex) {
                T temp = array.get(leftIndex);
                array.set(leftIndex, array.get(rightIndex));
                array.set(rightIndex, temp);
                leftIndex++;
                rightIndex--;
            }
        }
        return leftIndex;
    }

}
