package edu.wit.hbasemanager.mail.receiver;

import com.rabbitmq.client.Channel;
import edu.wit.hbasemanager.model.MailMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Slf4j
@Component
@RabbitListener(queues="direct.mail")
public class MailReceiver {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @RabbitHandler
    public void directHandlerManualAck(MailMessage mailMessage, Message message, Channel channel) {
        //  如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            sendSimpleMsg(mailMessage.getMessage(), mailMessage.getTargetMail(),mailMessage.getSubject());
            // 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            try {
                // 处理失败,重新压入MQ
                channel.basicRecover();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void sendSimpleMsg(String msg, String receiver,String subject) {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom(sender);
            mail.setTo(receiver);
            mail.setSubject(subject);
            mail.setText(msg);
            mailSender.send(mail);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
