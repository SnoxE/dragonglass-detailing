package dgbackend.service;

import dgbackend.database.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    public static final String EMAIL_ADDRESS = "dragonglassdetailing@gmail.com";
    public static final String COMPANY_NAME = "Dragonglass Detailing";
    public static final String REGISTER_SUBJECT = "Witaj w Dragonglass Detailing";
    public static final String RESERVATION_CONFIRMATION_SUBJECT = "Twoja rezerwacja została przyjęta";


    public void sendRegisterConfirmationEmail(UserDto userDto) {

        String emailBody = String.format(
                """
                Witaj %s %s,

                Potwierdzenie rejestracji w %s.

                Dziękujemy za rejestrację na naszej stronie.

                Pozdrawiamy,
                Zespół %s""",
                userDto.firstName(),
                userDto.lastName(),
                COMPANY_NAME,
                COMPANY_NAME
        );

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(String.format("%s <%s>", COMPANY_NAME, EMAIL_ADDRESS));
        message.setTo(userDto.email());
        message.setSubject(REGISTER_SUBJECT);
        message.setText(emailBody);
        emailSender.send(message);
    }
}
