public interface ListADT {
    public void List();

    public void insert(Object e) throws Exception;

    public Object retrieve() throws Exception;

    public void delete() throws Exception;

    public void update(Object e) throws Exception;

    public void findFirstExpenses() throws Exception;

    public void findNextExpenses() throws Exception;

    public boolean findKey(Object tKey);

    public boolean isEmpty();

    public boolean isFull();

}
