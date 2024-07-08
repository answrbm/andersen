package lesson10.dao;

import lesson10.exception.FunctionNotAvailableException;
import lesson10.exception.UserNotFoundException;
import lesson10.model.Ticket;
import lesson10.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserDAOImpl userDAO;
    private final TicketDAOImpl ticketDAO;
    private final Environment env;

    @Autowired
    public UserService(UserDAOImpl userDAO, TicketDAOImpl ticketDAO, Environment env) {
        this.userDAO = userDAO;
        this.ticketDAO = ticketDAO;
        this.env = env;
    }

    public User createUser(User user) {
        return userDAO.save(user);
    }

    public User getUserById(Long userId) {
        return userDAO.findById(userId).orElseThrow(() -> new UserNotFoundException("User with such id not found"));
    }

    /*
        Here has been used method getTicketById(Long ticketId),
        so if no such ticket, exception will be thrown
     */
    @Transactional
    public User updateUserAndCreateTicketForUser(Long userId, User userToUpdate, Ticket ticketToCreate) {
        String value = env.getProperty("ticker-user-update");
        if(value != null && !value.equals("ON"))
            throw new FunctionNotAvailableException("method updateUserAndCreateTicketForUser() not available: " +
                    "ticker-user-update="+value);

        getUserById(userId);
        ticketToCreate.setUserId(userId);
        ticketDAO.save(ticketToCreate);
        return userDAO.updateUser(userId,userToUpdate);
    }

    public Long deleteUser(Long userId) {
        return userDAO.deleteById(userId);
    }
}
