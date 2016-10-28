package by.company.library.service.impl;

import by.company.library.bean.User;
import by.company.library.dao.DAOFactory;
import by.company.library.dao.UserDao;
import by.company.library.dao.exception.DAOException;
import by.company.library.security.MD5Util;
import by.company.library.security.exception.CustomSecurityException;
import by.company.library.service.UserService;
import by.company.library.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

	private final UserDao userDAO = DAOFactory.getInstance().getUserDAO();

	@Override
	public User registerUser(String login, String password) throws ServiceException {
		try{
			return userDAO.registerUser(login, MD5Util.md5Custom(password));
		}catch(DAOException | CustomSecurityException e){
			throw new ServiceException(e);
		}
	}

	@Override
	public User logination(String login, String password) throws ServiceException {
		try{
			return userDAO.logination(login, MD5Util.md5Custom(password));
		}catch(DAOException | CustomSecurityException e){
			throw new ServiceException(e);
		}
	}

}
