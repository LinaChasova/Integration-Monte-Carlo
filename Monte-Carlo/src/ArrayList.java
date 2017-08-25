import java.util.Arrays;

/**
 * Created by AlinaCh on 04.02.2017.
 */
public class ArrayList<Item> {

    private int size;
    private final int CAPACITY = 10;
    private Item[] myArray;

    /**
     * default creation of ArrayList
     */
    public ArrayList() {
        myArray = (Item[]) new Object[CAPACITY];
        size = 0;
    }

    /**
     * creation of ArrayList with given capacity
     * @param capacity
     */
    public ArrayList(int capacity) {
        myArray = (Item[]) new Object[capacity];
        size = 0;
    }

    /**
     *
     * @return whether ArrayList is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *
     * @return the size of ArrayList
     */
    public int length() {
        return size;
    }

    /**
     * adds element to the ith index by shifting everything to the right
     * if there's not enough capacity, does size extension
     * @param i index
     * @param element
     */
    public void add(int i, Item element) {
        while (i >= myArray.length) {
            sizeExtension();
        }
        for (int j = myArray.length - 1; j >= i; j--) {
            myArray[j + 1] = myArray[j];
        }
        myArray[i] = element;
        size++;
    }

    /**
     * adds element to the end
     * if there's not enough capacity, does size extension
     * @param element
     */
    public void add(Item element) {
        if ((size + 1) >= myArray.length) {
            sizeExtension();
        }
        myArray[size + 1] = element;
        size++;
    }

    /**
     * deletes ith element
     * @param i
     */
    public void remove(int i) {
        if (i >= size) throw new ArrayIndexOutOfBoundsException("Array overflow");
        for (int j = i; j < myArray.length; j++) {
            myArray[i] = myArray[i + 1];
        }
        size--;
    }

    /**
     * replaces ith element with new one
     * @param i
     * @param element
     */
    public void replace(int i, Item element) {
        if (i >= size) throw new ArrayIndexOutOfBoundsException("Array overflow");
        myArray[i] = element;
    }

    /**
     *
     * @param i
     * @return ith element
     */
    public Item get(int i) {
        if (i >= myArray.length) throw new ArrayIndexOutOfBoundsException("Array overflow");
        return myArray[i];
    }

    /**
     * size extension of ArrayList, makes it twice longer
     */
    private void sizeExtension() {
        myArray = Arrays.copyOf(myArray, size * 2);
    }

}
