package by.company.library.command.impl;

import by.company.library.bean.User;
import by.company.library.command.Command;
import by.company.library.command.exception.CommandException;
import by.company.library.service.ServiceFactory;
import by.company.library.service.UserService;
import by.company.library.service.exception.ServiceException;

public class Registration implements Command {

	private final UserService userService = ServiceFactory.getInstance().getUserService();

	@Override
	public String execute(String request) throws CommandException {
		String[] registerInformation = request.split("&");
		if (registerInformation.length < 2)
			throw new CommandException("Request too small");
		String login = registerInformation[0];
		String password = registerInformation[1];
		User newUser = null;
		try {
			newUser = userService.registerUser(login, password);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return String.format("%s %s", newUser.GetLogin(),
				newUser.GetPermission().toString());
	}
}
