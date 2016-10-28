package by.company.library.controller;

import by.company.library.command.Command;
import by.company.library.command.exception.CommandException;

public class Controller {
	private final CommandProvider provider = new CommandProvider();

	public String doAction(String request) {
		String[] requestData = request.split("\\?");
		if ((requestData.length != 2) && requestData[0].length() != request.length() - 1) {
			return "error? Don't use '\\?' in data";
		}
		String commandName = requestData[0];
		String commandData = requestData.length != 1 ? requestData[1] : "";

		String response;
		try {
			Command command = provider.getCommand(commandName);
			response = command.execute(commandData);
		} catch (CommandException e) {
			response = "error?" + e.getMessage();
		}

		return response;

	}
}
