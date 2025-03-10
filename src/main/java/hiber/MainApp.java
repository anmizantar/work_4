package hiber;
import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);
      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("BMW", 2023);
      User user1 = new User("User1", "Lastname1", "user1@mail.ru", car1);
      userService.add(user1);

      Car car2 = new Car("Renault", 2014);
      User user2 = new User("User2", "Lastname2", "user2@mail.ru", car2);
      userService.add(user2);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      // Получение всех пользователе
      //userService.listUsers(): вызов метода listUsers для получения всех пользователей из БД
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car Model = " + user.getCar().getModel());
         System.out.println("Car Series = " + user.getCar().getSeries());
         System.out.println();
      }


      User foundUser =
              userService.getUserByCarModelAndSeries("Model1", 1234);
      if (foundUser != null) {
         System.out.println("Found User: " + foundUser.getFirstName() + " " + foundUser.getLastName());
      } else {
         System.out.println("User not found.");
      }
      context.close();
   }
}

