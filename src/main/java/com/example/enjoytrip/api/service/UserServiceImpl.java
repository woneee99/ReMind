package com.example.enjoytrip.api.service;

import com.example.enjoytrip.api.dao.UserDao;
import com.example.enjoytrip.api.dto.MailDto;
import com.example.enjoytrip.api.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;


    @Override
    @Transactional(readOnly = true)
    public UserDto getUser(String userId) {
        return userDao.findUser(userId);
    }

    @Override
    public int createMailAndChangePassword(String userEmail) {
        UserDto userDto = userDao.findEmailUser(userEmail);
        System.out.println("userDto = " + userDto);
        if( userDto == null) return -1;

        String str = getTempPassword();
        MailDto dto = new MailDto();
        dto.setAddress(userEmail);
        dto.setTitle("임시비밀번호 안내 이메일 입니다.");
        dto.setMessage("안녕하세요. 임시비밀번호 안내 관련 이메일 입니다." + " 회원님의 임시 비밀번호는 "
                + str + " 입니다." + "로그인 후에 비밀번호를 변경을 해주세요");

        userDto.setUserPassword(str);
        System.out.println("userDto = " + userDto);
        userDao.findEmailUpdate(userDto);
        mailSend(dto);
        return 1;
    }

    @Override
    public void mailSend(MailDto mailDTO) {
        System.out.println("전송 완료!");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDTO.getAddress());
        message.setSubject(mailDTO.getTitle());
        message.setText(mailDTO.getMessage());
        message.setFrom("tripssafy@gmail.com");
        message.setReplyTo("tripssafy@gmail.com");
        System.out.println("message"+message);
        mailSender.send(message);
    }

    @Override
    @Transactional
    public int register(UserDto userDto) {
        userDto.setUserId(userDto.getUserEmail());
        if(userDto.getUserPassword() != null) {
            userDto.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
        }
        return userDao.register(userDto);
    }


    @Override
    @Transactional
    public UserDto updateInfo(UserDto userDto) {
        userDto.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
        userDao.updateInfo(userDto);
        userDto.setUpdatedAt(LocalDate.now().toString());
        return userDto;
    }


    @Override
    @Transactional
    public int withdraw(int userId) {
        return userDao.withdraw(userId);
    }

    @Override
    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
        String str = "";

        // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }
}
