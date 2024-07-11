package lesson8.dao;

import lesson8.model.UserImpl;

import java.sql.*;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    private final Connection conn;

    public UserDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public UserImpl save(UserImpl user) {
        try(PreparedStatement ps = conn.prepareStatement("INSERT INTO users(name,creation_date) VALUES(?,?)")) {
            ps.setString(1,user.getName());
            ps.setTimestamp(2, Timestamp.valueOf(user.getCreationDate()));
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public Optional<UserImpl> findById(Long userId) {
        try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            conn.commit();
            if(rs.next()) {
                UserImpl user = new UserImpl();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setCreationDate(rs.getTimestamp("creation_date").toLocalDateTime());
                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    @Override
    public Long deleteById(Long userId) {
        try(PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {
            ps.setLong(1,userId);
            ps.execute();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e.printStackTrace();
            }
        }
        return userId;
    }
}
