package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public void add(User user) {
      Car car = user.getCar();
      if (car != null) {
         car.setUser(user);
         userDao.add(user);
         userDao.addCar(car);
      } else {
         userDao.add(user);
      }
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> getUserByCarModelAndSeries(int series, String model) {
      return userDao.getUsersByCarModelAndSeries(model, series);
   }
}

