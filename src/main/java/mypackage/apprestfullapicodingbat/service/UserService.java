package mypackage.apprestfullapicodingbat.service;

import mypackage.apprestfullapicodingbat.entity.User;
import mypackage.apprestfullapicodingbat.payload.UserDto;
import mypackage.apprestfullapicodingbat.payload.template.Result;
import mypackage.apprestfullapicodingbat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * ADD USER
     * @param userDto
     * @return Result
     */
    public Result add(UserDto userDto){
        boolean username = userRepository.existsByUsername(userDto.getUsername());
        if (username)
            return new Result("Bunday foydalanuvchi mavjud",false);
        User user=new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return new Result("Muvaffaqiyatli saqlandi",true);
    }

    /**
     * GET ALL USERS
     * @return List<User>
     */
    public List<User> getAll(){
        return userRepository.findAll();
    }

    /**
     * GET USER BY ID
     * @param id
     * @return User
     */
    public User getById(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent())
            return optionalUser.get();
        return null;
    }


    /**
     * EDIT USER
     * @param id
     * @param userDto
     * @return Result
     */
    public Result edit(Integer id, UserDto userDto){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new Result("Bunday foydalanuvchi mavjud emas",false);
        boolean idNot = userRepository.existsByUsernameAndIdNot(userDto.getUsername(), id);
        if (idNot)
            return new Result("Ushbu foydalanuvchi avval ro`yxatdan o`tgan", false);
        User user= optionalUser.get();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return new Result("Muvaffaqiyatli tahrilandi",true);
    }

    /**
     * DELETE USER
     * @param id
     * @return Result
     */
    public Result delete(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent())
            return new Result("Bunday foydalanuvchi mavjud emas",false);
        userRepository.deleteById(id);
        return new Result("Muvaffaqiyatli o`chirildi", true);
    }



}
