package com.techshop.web.busines.services;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.techshop.web.busines.mapper.UserMapper;
import com.techshop.web.model.dto.EmailDto;
import com.techshop.web.model.dto.UserDto;
import com.techshop.web.model.dto.UserNamePassword;
import com.techshop.web.model.entity.User;
import com.techshop.web.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.Properties;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private JavaMailSender javaMailSendet;

    public void save(UserDto userDto) {
        userRepository.save(UserMapper.USER_MAPPER.toEntity(
                UserDto.builder()
                        .name(userDto.getName())
                        .username(userDto.getUsername())
                        .email(userDto.getEmail())
                        .password(bcryptEncoder.encode(userDto.getPassword()))
                        .enable(userDto.getEnable())
                        .build()
        ));
    }

    public void updateUser(UserDto userDto){
        userRepository.updateByUserName(userDto.getUsername(),userDto.getName(),userDto.getEmail(), bcryptEncoder.encode(userDto.getPassword()));
    }

    public void updatePassword(UserNamePassword userNamePassword){
        userRepository.updatePasswordByUserName(userNamePassword.getUsername(), bcryptEncoder.encode(userNamePassword.getPassword()));
    }

    public Optional<User> findById(Integer id){
        return userRepository.findById(id);
    }

    public User findUserByUserName(String username){
        return userRepository.findByUsername(username);
    }
    public Boolean existsEmail(String email){
        User user = userRepository.findByEmail(email);
        boolean respuesta = false;
        if(user!=null){
            respuesta = true;
        }
        return respuesta;
    }
    public UserDto findUserByEmail(String email){
        return UserMapper.USER_MAPPER.toDto(userRepository.findByEmail(email));
    }

    public UserDto findUserDtoByUserName(String username){
        return UserMapper.USER_MAPPER.toDto(userRepository.findByUsername(username));
    }

    public void sendMailMail(String username){

        String newPassword=generedPassword();

        User user = userRepository.findByUsername(username);

        EmailDto emailDto = EmailDto.builder()
                .from("kf11.vargas@gmail.com")
                .to(user.getEmail())
                .body("Su contraseña nueva es: "+newPassword)
                .subject("Cambio de contraseña - TechnoShop")
                .build();


        SimpleMailMessage mail = new SimpleMailMessage();

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");

        String host = "smtp.gmail.com";
        int port = 587;
        String userName = "technoshop.proyecto";
        String password = "Umb123456789*";

        JavaMailSenderImpl sender = new JavaMailSenderImpl();

        sender.setJavaMailProperties(props);
        sender.setHost(host);
        sender.setPort(port);
        sender.setUsername(userName);
        sender.setPassword(password);

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            updatePassword(UserNamePassword.builder()
                    .username(username)
                    .password(newPassword)
                    .build());
            helper = new MimeMessageHelper(message, true);
            helper.setTo(emailDto.getTo());
            helper.setSubject(emailDto.getSubject());
            helper.setText(emailDto.getBody());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        sender.send(message);
    }

    public String generedPassword(){
        String theAlphaNumericS;
        StringBuilder builder;

        theAlphaNumericS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789";

        //create the StringBuffer
        builder = new StringBuilder(15);

        for (int m = 0; m < 15; m++) {

            // generate numeric
            int myindex
                    = (int)(theAlphaNumericS.length()
                    * Math.random());

            // add the characters
            builder.append(theAlphaNumericS
                    .charAt(myindex));
        }

        return builder.toString();
    }

    public UserDto createUserGmail(GoogleIdToken.Payload user){
        String password = generedPassword();
        UserDto userDto = UserDto.builder()
                .name(user.getSubject())
                .username(user.getEmail())
                .email(user.getEmail())
                .password(bcryptEncoder.encode(password))
                .enable("1")
                .build();
        userRepository.save(UserMapper.USER_MAPPER.toEntity(userDto));
        return userDto;
    }

}
