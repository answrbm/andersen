package lesson8.dao;

import lesson8.model.UserImpl;

import java.sql.*;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    @Override
    public UserImpl save(UserImpl user) {
        try(Connection conn = lesson8.Connection.connect();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users(name,creation_date) VALUES(?,?)")) {
            ps.setString(1,user.getName());
            ps.setTimestamp(2, Timestamp.valueOf(user.getCreationDate()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Optional<UserImpl> findById(Long userId) {
        try(Connection conn = lesson8.Connection.connect();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                UserImpl user = new UserImpl();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setCreationDate(rs.getTimestamp("creation_date").toLocalDateTime());
                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Long deleteById(Long userId) {
        try(Connection conn = lesson8.Connection.connect();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {
            ps.setLong(1,userId);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }
}
