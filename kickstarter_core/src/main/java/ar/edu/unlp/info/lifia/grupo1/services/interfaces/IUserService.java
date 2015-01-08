package ar.edu.unlp.info.lifia.grupo1.services.interfaces;

import java.util.List;

import ar.edu.unlp.info.lifia.grupo1.dto.UserDTO;
import ar.edu.unlp.info.lifia.grupo1.services.exceptions.DatabaseException;
import ar.edu.unlp.info.lifia.grupo1.services.exceptions.ExistingUserException;
import ar.edu.unlp.info.lifia.grupo1.services.exceptions.InvalidLoginException;

public interface IUserService {

	/**
	 * Registers a new user into the system
	 * 
	 * @param name
	 * @param username
	 * @param password
	 * @return An object with the user's information
	 * @throws ExistingUserException
	 * @throws DatabaseException
	 */
	public UserDTO registerUser(String name, String username, String password) throws ExistingUserException, DatabaseException; 
	
	/**
	 * Validates a user within the system 
	 * 
	 * @param username
	 * @param password
	 * @return An object with the user's information
	 * @throws InvalidLoginException
	 * @throws DatabaseException
	 */
	public UserDTO loginUser(String username, String password) throws InvalidLoginException, DatabaseException;
	
	/**
	 * Retrieves a list of all users
	 * and calculates their total invested amount
	 * 
	 * @return The list of users
	 * @throws DatabaseException
	 */
	public List<UserDTO> listOfUsersInvestmentData() throws DatabaseException;
	
	/**
	 * Retrieves a list of users who had created at least one project,
	 * ordered by their success points, greater to lower
	 * 
	 * @return The list of users whit one or more projects in charge
	 * @throws DatabaseException
	 */
	public List<UserDTO> projectCreatorsRank() throws DatabaseException;
}
