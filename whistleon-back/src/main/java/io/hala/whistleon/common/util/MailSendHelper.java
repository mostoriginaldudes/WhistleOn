package io.hala.whistleon.common.util;

import java.util.Random;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MailSendHelper {

  private final String subject = "이메일 인증 메일 발송 - Team WhistleOn";
  private final String text = " 안녕하세요 휘슬온입니다.\n 아래 인증번호를 입력해주세요.\n\n 인증번호 : ";

  private final JavaMailSender javaMailSender;

  public void sendMail(String to, String code) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject(subject);
    message.setText(text + code);
    javaMailSender.send(message);
  }

  public String makeRandomText() {
    Random random = new Random();
    StringBuilder randomText = new StringBuilder();
    IntStream.range(0, 7).forEach(i -> {
      if (random.nextBoolean()) {
        randomText.append((char) ((random.nextInt(26)) + 65));
      } else {
        randomText.append(random.nextInt(10));
      }
    });
    return randomText.toString();
  }
}
