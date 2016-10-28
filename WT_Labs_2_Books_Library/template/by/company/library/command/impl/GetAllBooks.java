package by.company.library.command.impl;

import java.util.Arrays;

import by.company.library.command.Command;
import by.company.library.command.exception.CommandException;
import by.company.library.service.LibraryService;
import by.company.library.service.ServiceFactory;
import by.company.library.service.exception.ServiceException;

public class GetAllBooks implements Command{
	private final LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
	
	@Override
	public String execute(String request) throws CommandException {
		try {
			return Arrays.toString(libraryService.getAllBooks());
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
	}
}
