package ar.edu.unlp.info.lifia.grupo1.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlp.info.lifia.grupo1.dto.UserDTO;
import ar.edu.unlp.info.lifia.grupo1.model.User;
import ar.edu.unlp.info.lifia.grupo1.model.UserRole;
import ar.edu.unlp.info.lifia.grupo1.repository.interfaces.UserDAO;
import ar.edu.unlp.info.lifia.grupo1.services.exceptions.DatabaseException;
import ar.edu.unlp.info.lifia.grupo1.services.exceptions.ExistingUserException;
import ar.edu.unlp.info.lifia.grupo1.services.exceptions.InvalidLoginException;
import ar.edu.unlp.info.lifia.grupo1.services.interfaces.IUserService;
import java.util.Collections;
import java.util.Comparator;

public class UserService implements IUserService{
	
	private UserDAO userDAO;
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Transactional
	public UserDTO registerUser(String name, String username, String password) throws ExistingUserException, DatabaseException {
		UserDTO newUserDTO = null;
		try{
                    User exampleToFind = new User(null, username, null, null, null, null, null); //null values are ignored in the search
                    List<User> usersFound = this.getUserDAO().findByExample(exampleToFind);
                    if (!usersFound.isEmpty()){
                        throw new ExistingUserException();
                    }
                    User newUser = new User(name, username, password, 0.0, 0, new HashSet<UserRole>(), false);
                    this.getUserDAO().save(newUser);
                    newUserDTO = new UserDTO(newUser);
		}catch(DataAccessException dataAccessException){
                    throw new DatabaseException();
		}
                
		return newUserDTO;
	}
	
	@Transactional
	public UserDTO loginUser(String username, String password) throws InvalidLoginException, DatabaseException {
		UserDTO userDTO = null;
		try{
                    User exampleToFind = new User(null, username, password, null, null, null, null); //null values are ignored in the search
                    List<User> usersFound = this.getUserDAO().findByExample(exampleToFind); 
                    if (usersFound.size() != 1){
                        throw new InvalidLoginException();
                    }
                    userDTO = new UserDTO(usersFound.get(0));
		}catch(DataAccessException dataAccessException) {
                    throw new DatabaseException();
		}
                
		return userDTO;
	}

	@Transactional
	public List<UserDTO> listOfUsersInvestmentData() throws DatabaseException {
		List<UserDTO> usersDTO = new ArrayList<UserDTO>();		
		try{
                    List<User> usersFound = this.getUserDAO().findAll();
                    for (User currentUser : usersFound){
                        usersDTO.add(new UserDTO(currentUser));
                    }
                    Collections.sort(usersDTO, new Comparator<UserDTO>() {
                        // Anonymous class implementation for sortir by the UserDTO.totalInvested property
                        public int compare(UserDTO firstUserDTO, UserDTO secondUserDTO) {
                            // Default, secondUserDTO is larger
                            int result = 1;
                            if(firstUserDTO.getTotalInvested() > secondUserDTO.getTotalInvested())
                                result = -1;
                            else
                                if(firstUserDTO.getTotalInvested() == secondUserDTO.getTotalInvested())
                                    result = 0;
                            
                            return result;                            
                        }
                    });
		}catch(DataAccessException dataAccessException){
                    throw new DatabaseException();
		}
		
		return usersDTO;
	}
	
	@Transactional
	public List<UserDTO> projectCreatorsRank() throws DatabaseException {
		List<UserDTO> usersDTO = new ArrayList<UserDTO>();
		try{
                    List<User> creatorsRank  = this.getUserDAO().findCreatorsRank();
                    for (User user : creatorsRank) {
                            usersDTO.add(new UserDTO(user));
                    }
		}catch(DataAccessException dataAccessException){
                    throw new DatabaseException();
		}
		
		return usersDTO;
	}
	
}
