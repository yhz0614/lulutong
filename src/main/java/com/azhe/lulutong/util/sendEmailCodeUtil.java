package com.azhe.lulutong.util;

import cn.hutool.core.util.RandomUtil;
import com.sun.mail.util.MailSSLSocketFactory;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author Azhe
 *
 * 邮箱验证码发送工具
 */

public class sendEmailCodeUtil {
    private static final String emailSender = "yhz18972165008@163.com";

    public static HashMap<String, String> sendEmail (String userMailUrl){
        Properties prop = new Properties();
        // 开启debug调试，以便在控制台查看
        prop.setProperty("mail.debug", "true");
        // 设置邮件服务器主机名
        prop.setProperty("mail.host", "smtp.163.com");
        // 发送服务器需要身份验证
        prop.setProperty("mail.smtp.auth", "true");
        // 发送邮件协议名称
        prop.setProperty("mail.transport.protocol", "smtp");
        // 开启SSL加密，否则会失败
        try {
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put("mail.smtp.ssl.enable", "true");
            prop.put("mail.smtp.ssl.socketFactory", sf);
            // 随机生成6位验证码
            String code = RandomUtil.randomStringUpper(6);
            // 以map方式存储
            HashMap<String, String> codeMap = new HashMap<>();
            codeMap.put(userMailUrl, code);

            // 创建session
            Session session = Session.getInstance(prop);
            // 通过session得到transport对象
            Transport ts = session.getTransport();
            // 连接邮件服务器：邮箱类型，帐号，POP3/SMTP协议授权码 163使用：smtp.163.com，qq使用：smtp.qq.com
            ts.connect("smtp.163.com", emailSender, "ENRQRNASWXWNLJYR");
            // 创建邮件
            Message message = writeMail(session, userMailUrl, code);
            // 发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
            return codeMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static MimeMessage writeMail(Session session, String userMailUrl, String code) throws MessagingException {
        //TODO Azhe 2023/9/13: 验证码发送功能

        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress(emailSender));
        // 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(userMailUrl));
        // 邮件的标题
        message.setSubject("路路通平台");
        // 邮件的文本内容
        message.setContent("您正在注册路路通平台，验证码为：" + code + "，如非本人操作，请忽略！请勿回复此邮箱。", "text/html;charset=UTF-8");
        // 返回创建好的邮件对象
        return message;

    }

}
