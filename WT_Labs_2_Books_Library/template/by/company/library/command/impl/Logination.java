package by.company.library.command.impl;

import by.company.library.bean.User;
import by.company.library.command.Command;
import by.company.library.command.exception.CommandException;
import by.company.library.service.ServiceFactory;
import by.company.library.service.UserService;
import by.company.library.service.exception.ServiceException;

public class Logination implements Command{

	private final UserService userService = ServiceFactory.getInstance().getUserService();
	
	@Override
	public String execute(String request) throws CommandException {	
		String[] loginInformation = request.split("&");
		if (loginInformation.length < 2)
			throw new CommandException("Request too small");
		String login = loginInformation[0];
		String password = loginInformation[1];
		User loginUser=null;
		try {
			loginUser=userService.logination(login, password);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return String.format("%s", loginUser.GetPermission().toString());
	}

}
