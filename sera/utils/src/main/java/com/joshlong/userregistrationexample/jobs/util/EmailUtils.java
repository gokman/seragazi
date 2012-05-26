package com.joshlong.userregistrationexample.jobs.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.tools.generic.DateTool;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailUtils {

    private Logger log = Logger.getLogger(EmailUtils.class);
    private JavaMailSenderImpl mailSender;

    public JavaMailSenderImpl getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    public static void main(String a[]) throws Throwable {
        ApplicationContext context = new ClassPathXmlApplicationContext("utils.xml");
        EmailUtils utils = (EmailUtils) context.getBean("emailUtils");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user", "Josh Long");
        utils.sendEmailMessage("josh@joshlong.com", new String[]{"starbuxman@gmail.com"}, "My Subject, Yay", "Hello, world ${user}.", "<h1>Hello ${user}</h1>", params);
    }

    public String mergeTemplate(String template, Map<String, Object> macros) throws Throwable {

        if (StringUtils.isEmpty(template))
            return StringUtils.EMPTY;

        String answer = null;

        VelocityContext context = new VelocityContext();

        context.put("dateTool", new DateTool());

        for (String key : macros.keySet())
            context.put(key, macros.get(key));

        StringWriter writer = new StringWriter();

        Velocity.init();

        if (Velocity.evaluate(context, writer, "LOG", template)) {
            IOUtils.closeQuietly(writer);
            answer = writer.toString();
        }
        return answer;
    }

    public void sendEmailMessage(String from, String[] to, String subject, String textBody, String htmlBody, Map<String, Object> params) throws Throwable {

        MimeMessage msg = mailSender.createMimeMessage();

        msg.setFrom(new InternetAddress(from));
        msg.setSubject(subject);
        msg.setRecipients(Message.RecipientType.TO, getInternetAddresses(to));

        MimeMultipart content = new MimeMultipart("alternative");

        if (!StringUtils.isEmpty(textBody)) {
            MimeBodyPart text = new MimeBodyPart();
            text.setText(mergeTemplate(textBody, params));
            content.addBodyPart(text);
        }

        if (!StringUtils.isEmpty(htmlBody)) {
            MimeBodyPart html = new MimeBodyPart();
            html.setContent(mergeTemplate(htmlBody, params), "text/html");
            content.addBodyPart(html);
        }

        msg.setContent(content);
        msg.saveChanges();

        try {
            mailSender.send(msg);
        } catch (MailException ex) {
            log.info("Issue with sending out mail having body " + StringUtils.defaultString(textBody) + "; params are:" + params, ex);
        }
    }

    private InternetAddress[] getInternetAddresses(String... emails) throws Throwable {
        List<InternetAddress> addys = new ArrayList<InternetAddress>();
        for (String e : emails)
            addys.add(new InternetAddress(e));
        return addys.toArray(new InternetAddress[0]);
    }

}
