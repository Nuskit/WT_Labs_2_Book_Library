package by.company.library.service.impl;

import by.company.library.bean.Book;
import by.company.library.dao.BookDao;
import by.company.library.dao.DAOFactory;
import by.company.library.dao.exception.DAOException;
import by.company.library.service.LibraryService;
import by.company.library.service.exception.ServiceException;

public class LibraryServiceImpl implements LibraryService {

	private final BookDao bookDAO = DAOFactory.getInstance().getBookDAO();

	@Override
	public void addNewBook(Book book) throws ServiceException {
		try {
			bookDAO.addBook(book);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteBook(Book book) throws ServiceException {
		try {
			bookDAO.deleteBook(book);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean findBook(Book book) throws ServiceException {
		try {
			return bookDAO.findBook(book);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public String[] getAllBooks() throws ServiceException {
		try {
			return bookDAO.getAllBooks();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
