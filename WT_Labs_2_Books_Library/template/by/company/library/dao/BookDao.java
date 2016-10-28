package by.company.library.dao;

import by.company.library.bean.Book;
import by.company.library.dao.exception.DAOException;

public interface BookDao {	
	void addBook(Book book) throws DAOException;
	void deleteBook(Book book) throws DAOException;
	boolean findBook(Book book) throws DAOException;
	String[] getAllBooks() throws DAOException;
}
