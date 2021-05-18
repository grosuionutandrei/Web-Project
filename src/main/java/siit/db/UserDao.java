package siit.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import siit.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {
  @Autowired
  JdbcTemplate jdbcTemplate;


  public List<User> getUsers(){
   return jdbcTemplate.query("select * from Users",this::getUser);
  }

  private User getUser(ResultSet rs, int rowNum) throws SQLException {
      User user = new User();
      user.setName(rs.getString("name"));
      user.setPassword(rs.getString("password"));
      return user;
  }
  public void insertUser(User user){
      String updateUser= "Insert into users (name,password)values(?,?)";
      jdbcTemplate.update(updateUser,user.getName(),user.getPassword());
  }
  public void deleteUser(String name){
      String deleteUser= "delete from users where name=?";
      jdbcTemplate.update(deleteUser,name);
  }
//  public void insertrLogged(User user){
//      String insertLogged="insert into users (logged) where name=?";
//      jdbcTemplate.update(insertLogged,user.getName());
//  }
//  public  User selectLogged(User user){
//      String selectLogged = "select  from users where name=?";
//      return jdbcTemplate.queryForObject(selectLogged,this::getUser);
//  }
}
