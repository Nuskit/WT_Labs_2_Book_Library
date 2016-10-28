package by.company.library.dao;

import by.company.library.bean.User;
import by.company.library.dao.exception.DAOException;

public interface UserDao {
	User registerUser(String login,String password) throws DAOException;
	User logination(String login, String password) throws DAOException;
}
