package by.company.library.command.impl;

import by.company.library.bean.Book;
import by.company.library.bean.KindBook;
import by.company.library.command.Command;
import by.company.library.command.exception.CommandException;
import by.company.library.service.ServiceFactory;
import by.company.library.service.exception.ServiceException;
import by.company.library.service.LibraryService;

public class AddNewBook implements Command {

	private final LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();

	@Override
	public String execute(String request) throws CommandException {
		String[] bookInformation=request.split("&");
		if (bookInformation.length < 3)
			throw new CommandException("Request too small");
		String title = bookInformation[0];
		String author = bookInformation[1];
		String kind = bookInformation[2];
		try {
			libraryService.addNewBook(new Book(title, author, getKind(kind)));
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return "complete";
	}

	private static KindBook getKind(String value) throws CommandException {
		KindBook kind = KindBook.getKind(value);
		if (kind == KindBook.UNSUPPORTED) {
			throw new CommandException("Unsupported kind type");
		}
		return kind;
	}
}
