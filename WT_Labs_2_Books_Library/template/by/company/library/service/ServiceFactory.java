package by.company.library.service;

import by.company.library.service.impl.LibraryServiceImpl;
import by.company.library.service.impl.UserServiceImpl;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();
	
	private final LibraryService updateLibraryService = new LibraryServiceImpl();
	private final UserService userService = new UserServiceImpl();
	
	
	public static ServiceFactory getInstance(){
		return instance;
	}
	
	public LibraryService getLibraryService(){
		return updateLibraryService;
	}
	
	public UserService getUserService(){
		return userService;
	}

}
