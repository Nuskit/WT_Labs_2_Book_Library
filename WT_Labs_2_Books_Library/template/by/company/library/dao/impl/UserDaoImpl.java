package by.company.library.dao.impl;

import java.io.FileNotFoundException;
import java.io.IOException;

import by.company.library.bean.User;
import by.company.library.bean.UserPermission;
import by.company.library.dao.UserDao;
import by.company.library.dao.exception.DAOException;
import by.company.library.dao.impl.DataBase.*;

public class UserDaoImpl implements UserDao {

	private final DataBaseFileName fileName = DataBaseFileName.USERS;

	@Override
	public User registerUser(String login, String password) throws DAOException {
		UserPermission userPermission = UserPermission.USER;
		try (DataBaseWriterRAII writer = new DataBaseWriterRAII(fileName)) {
			writer.writeLine(String.format("%s %s", formatLoginAndPassword(login, password), userPermission));
		} catch (IOException e) {
			throw new DAOException(String.format("Error work with file %s.", fileName));
		}
		return new User(login, password, userPermission);
	}

	@Override
	public User logination(String login, String password) throws DAOException {
		User currentUser;
		try (DataBaseReaderRAII reader = new DataBaseReaderRAII(fileName)) {
			currentUser = findUser(reader, formatLoginAndPassword(login, password));
		} catch (FileNotFoundException e) {
			throw new DAOException(String.format("File not found %s.", fileName));
		} catch (IOException e) {
			throw new DAOException(String.format("Error work with file %s.", fileName));
		}

		if (currentUser == null) {
			throw new DAOException("Not found user in DataBase");
		}
		return currentUser;
	}

	private String formatLoginAndPassword(String login, String password) {
		return String.format("%s %s", login, password);
	}

	private User findUser(DataBaseReaderRAII reader, String userData) throws IOException, DAOException {
		String line;
		User currentUser = null;
		while ((currentUser == null) && ((line = reader.readLine()) != null)) {
			if (isEqualsUserInformation(line.substring(0, line.lastIndexOf(" ")), userData)) {
				currentUser = getUserFromLine(line);
			}
		}
		return currentUser;
	}

	private User getUserFromLine(String userData) throws DAOException {
		String[] words = userData.split("\\s+");
		if (words.length < 3) {
			throw new DAOException("Damaged database");
		}
		return new User(words[0], words[1], getPermission(words[2]));
	}

	private UserPermission getPermission(String value) throws DAOException {
		UserPermission userPermission = UserPermission.getPermission(value);
		if (userPermission == UserPermission.UNSUPPORTED) {
			throw new DAOException("Damaged database");
		}
		return userPermission;
	}

	private boolean isEqualsUserInformation(String dataFromFile, String userData) {
		return dataFromFile.compareTo(userData) == 0 ? true : false;
	}
}
