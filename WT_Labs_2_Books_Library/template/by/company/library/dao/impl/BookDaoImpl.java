package by.company.library.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import by.company.library.bean.Book;
import by.company.library.bean.KindBook;
import by.company.library.dao.BookDao;
import by.company.library.dao.exception.DAOException;
import by.company.library.dao.impl.DataBase.DataBaseFileName;
import by.company.library.dao.impl.DataBase.DataBaseReaderRAII;
import by.company.library.dao.impl.DataBase.DataBaseWriterRAII;

public class BookDaoImpl implements BookDao {

	private final DataBaseFileName fileName = DataBaseFileName.BOOKS;
	private final String tempFile = "tempFile.txt";
	
	
	@Override
	public void addBook(Book book) throws DAOException {
		try (DataBaseWriterRAII writer = new DataBaseWriterRAII(fileName)) {
			writer.writeLine(book.toString());
		} catch (IOException e) {
			throw new DAOException(String.format("Error work with file %s.", fileName));
		}
	}

	@Override
	public void deleteBook(Book book) throws DAOException {
		try (DataBaseReaderRAII reader = new DataBaseReaderRAII(fileName)) {
			tryDeleteBook(reader, book);
		} catch (IOException e) {
			throw new DAOException(String.format("Error work with file %s.", fileName));
		}
		saveFileChanges();
	}
	
	private void saveFileChanges()throws DAOException{
		File oldFile=new File(fileName.toString());
		File newFile=new File(tempFile);
		oldFile.delete();
		if (!(newFile.renameTo(oldFile))) {
			throw new DAOException("Don't saving change at delete book");
		}
	}

	private void tryDeleteBook(DataBaseReaderRAII reader, Book book) throws IOException, DAOException {

		try (DataBaseWriterRAII writer = new DataBaseWriterRAII(tempFile, false)) {
			boolean isDeleteBook = false;
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.trim().compareTo(book.toString()) != 0) {
					writer.writeLine(line.trim());
				} else {
					isDeleteBook = true;
				}
			}
			if (!isDeleteBook) {
				throw new DAOException("Don't found delete book");
			}
		}
	}

	private void checkForUnsupportedKind(Book book) throws DAOException {
		if (book.GetKind() == KindBook.UNSUPPORTED)
			throw new DAOException("Damaged database");
	}

	@Override
	public boolean findBook(Book book) throws DAOException {
		try (DataBaseReaderRAII reader = new DataBaseReaderRAII(fileName)) {
			return tryFindAllBooks(reader, book);
		} catch (IOException e) {
			throw new DAOException(String.format("Error work with file %s.", fileName));
		}
	}

	private boolean tryFindAllBooks(DataBaseReaderRAII reader, Book book) throws IOException, DAOException {
		boolean isFoundBook = false;
		String line;
		while ((!isFoundBook)&&((line = reader.readLine()) != null)) {
			if (line.trim().compareTo(book.toString()) == 0) {
				isFoundBook=true;
			}
		}
		return isFoundBook;
	}

	@Override
	public String[] getAllBooks() throws DAOException {
		try (DataBaseReaderRAII reader = new DataBaseReaderRAII(fileName)) {
			return tryGetAllBooks(reader);
		} catch (IOException e) {
			throw new DAOException(String.format("Error work with file %s.", fileName));
		}
	}

	private String[] tryGetAllBooks(DataBaseReaderRAII reader) throws IOException {
		List<String> books = new ArrayList<>();
		String line;
		while ((line = reader.readLine()) != null) {
			books.add(line);
		}
		String[] stockArray = new String[books.size()];
		stockArray = books.toArray(stockArray);
		return stockArray;
	}
}
