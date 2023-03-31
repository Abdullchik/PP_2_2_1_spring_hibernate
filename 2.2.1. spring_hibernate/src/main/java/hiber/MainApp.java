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

        List<Car> carList = List.of(
                (new Car("Lada", 123)),
                (new Car("Ferrari ", 12)),
                (new Car("Audi", 12312))
        );
        carList.forEach(carService::add);
        userService.add(new User("User1", "Lastname1", "user1@mail.ru", carList.get(0)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", carList.get(1)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", carList.get(2)));
        userService.listUsers().forEach(System.out::println);
        carService.listCars().forEach(System.out::println);
        System.out.println(userService.getUserByCar("Lada", 123));
        context.close();
    }
}
