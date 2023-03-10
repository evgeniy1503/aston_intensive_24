package org.example.arraylist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArrayList is the data structure that is based on an array. This array stores in itself elements of type T.
 * ArrayList methods add, remove, set new element, clean and checks that the array is empty.
 * @param <T> the object that will be added in array
 */
public class ArrayList<T> {

    private Object[] array;

    private final int CAPACITY = 10;

    private int size = 0;

    public ArrayList() {
        array = new Object[CAPACITY];
    }

    public ArrayList(int capacity) {
        array = new Object[capacity];
    }

    public final int size() {
        return this.size;
    }

    /**
     * @param element the object to be added to the end of the array
     * @return return true after successful added
     */
    public boolean add(T element) {

        if (array.length == size) {
            final Object[] oldArray = array;
            array = new Object[this.size() * 2];
            System.arraycopy(oldArray, 0, array, 0, oldArray.length);
        }

        array[size++] = element;
        return true;
    }

    /**
     * @param index is the number of the position where the new element should be written
     * @param element is an object that will be added to the index position
     */
    public void add(int index, T element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (size == 0 || index == size) {
            add(element);
        } else if (array.length == size) {
            final Object[] tempArray = array;

            array = new Object[this.size() * 2];

            System.arraycopy(tempArray, 0, array, 0, index);
            System.arraycopy(tempArray, index, array, index + 1, size() - index);

            set(index, element);
            size++;
        } else {
            final Object[] tempArray = array;

            System.arraycopy(tempArray, 0, array, 0, index + 1);
            System.arraycopy(tempArray, index, array, index + 1, size() - index);
            set(index, element);
            size++;
        }
    }

    /**
     * @param index index of the element in the array to be returned
     * @return the object from array
     * @throws if index size more than size array or index negative
     * that the exception is thrown IndexOutOfBoundsException
     */
    public T get(final int index) {

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
    public T set(int index, T element) {

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
    public T remove(int index) {

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
    public void clear() {
        array = new Object[1];
        size = 0;
    }

    /**
     * @return true if array have size equal to 0 or false if array not empty
     */
    public boolean isEmpty() {
        return this.size == 0;
    }


    /**
     * @return instance of the Iterator type
     */
    public Iterator<T> iterator() {
        return new ElementsIterator();
    }


    /**
     * @param array the array to be sorted by the quick sort method
     * @param from is the index of the beginning of the array to be sorted
     * @param to is the index of the last element of the array
     * @param <T> the object that should implement interface Comparable
     */
    public static <T extends Comparable> void quickSort(ArrayList<T> array, int from, int to) {

        if (from < to) {
            int divideIndex = partition(array, from, to);

            quickSort(array, from, divideIndex - 1);

            quickSort(array, divideIndex, to);
        }

    }

    private static <T extends Comparable>  int partition(ArrayList<T> array, int from, int to) {

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

    private class ElementsIterator implements Iterator<T> {

        private static final int LAST_IS_NOT_SET = -1;
        private int index;
        private int lastIndex = -1;

        ElementsIterator() {
            this(0);
        }

        ElementsIterator(final int index) {
            this.index = index;
        }

        @Override
        public boolean hasNext() {
            return ArrayList.this.size() > index;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastIndex = index++;
            return ArrayList.this.get(lastIndex);
        }


        @Override
        public void remove() {
            if (lastIndex == LAST_IS_NOT_SET) {
                throw new IllegalStateException();
            }
            ArrayList.this.remove(lastIndex);
            index--;
            lastIndex = LAST_IS_NOT_SET;
        }

    }

}
