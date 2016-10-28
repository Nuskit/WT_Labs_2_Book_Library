package by.company.library.service;

import by.company.library.bean.Book;
import by.company.library.service.exception.ServiceException;

public interface LibraryService {
	void addNewBook(Book book) throws ServiceException;
	void deleteBook(Book book) throws ServiceException;
	boolean findBook(Book book) throws ServiceException;
	String[] getAllBooks()throws ServiceException;
}
