package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        List<User> users = List.of(
                new User("User1", "Lastname1", "user1@mail.ru"),
                new User("User2", "Lastname2", "user2@mail.ru"),
                new User("User3", "Lastname3", "user3@mail.ru")
        );
        carService.add(new Car("Lada", 12, users.get(0)));
        carService.add(new Car("Audi", 12312, users.get(1)));
        carService.add(new Car("Ferari ", 351, users.get(2)));
        System.out.println(carService.get("Lada", 12));
        userService.listUsers().forEach(System.out::println);
        carService.listCars().forEach(System.out::println);
        System.out.println(carService.get("Audi", 12312));
        context.close();
    }
}
