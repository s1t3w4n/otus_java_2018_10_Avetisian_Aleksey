import java.util.*;

public class MyArrayList<T> implements List<T> {

    private Object[] myElements;
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private int size = 0;

    public MyArrayList() {
        this.myElements = EMPTY_ELEMENTDATA;
    }

    public MyArrayList(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Capacity have to be positive value");
        }
        this.myElements = new Object[size];
        this.size = size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator(myElements);
    }

    @Override
    public Object[] toArray() {
        Object[] toArray = Arrays.copyOf(myElements, size);
        return toArray;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public boolean add(T t) {
        if (myElements.length <= size) {
            Object[] newMyElements = Arrays.copyOf(myElements,myElements.length + DEFAULT_CAPACITY);
            myElements = newMyElements;
        }
        size++;
        myElements[size - 1] = t;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size ) {
            throw new IllegalArgumentException("");
        }
        return (T) myElements[index];
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size ) {
            throw new IllegalArgumentException("");
        }
            myElements[index] = element;
            return (T) myElements[index];
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public ListIterator<T> listIterator() {
        return new MyIterator(myElements);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    public class MyIterator implements ListIterator {
        private int index = -1;
        private Object[] objects;

        public MyIterator(Object[] objects) {
            this.objects = objects;
        }

        @Override
        public boolean hasNext() {
            return (index != size - 1);
        }

        @Override
        public Object next() {
            if (hasNext()) {
                index++;
                return objects[index];
            } else {
                return null;
            }
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public Object previous() {
            if (hasPrevious()) {
                index--;
                return objects[index];
            } else {
                return null;
            }
        }

        @Override
        public int nextIndex() {
            if (hasNext()) {
                return index++;
            } else {
                return index;
            }
        }

        @Override
        public int previousIndex() {
            if (hasPrevious()) {
                return index--;
            } else {
                return index;
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Unsupported method");
        }

        @Override
        public void set(Object o) {
            if (index != -1 ) {
                myElements[index] = o;
            }
        }

        @Override
        public void add(Object o) {
            throw new UnsupportedOperationException("Unsupported method");
        }
    }
}
