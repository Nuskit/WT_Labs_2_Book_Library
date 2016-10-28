package by.company.library.dao.impl.DataBase;

public enum DataBaseFileName {
    USERS("users.txt"),
    BOOKS("books.txt");

    private final String text;
    
    private DataBaseFileName(final String text) {
        this.text = text;
    }
    
    @Override
    public String toString() {
        return text;
    }
}
