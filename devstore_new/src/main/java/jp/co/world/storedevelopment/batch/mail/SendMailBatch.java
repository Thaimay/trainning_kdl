package jp.co.world.storedevelopment.batch.mail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;

import jp.co.world.storedevelopment.batch.dto.MailMessageDto;
import jp.co.world.storedevelopment.model.Mail;
import jp.co.world.storedevelopment.model.mapper.repository.MailRepository;

public class SendMailBatch {

    private static final String authId = "info.worldstoredevelopment";
    private static final String authPassword = "wsdwsdwsd";

    public static MailMessageDto setMailMessageDto(Mail mail) {
        MailMessageDto mailMessageDto = new MailMessageDto();

    	Properties properties = new Properties();

    	try(InputStream is = ClassLoader.getSystemResourceAsStream("mail.properties");
				InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
				BufferedReader reader = new BufferedReader(isr)) {

    		properties.load(is);
    		mailMessageDto.setEncode(properties.getProperty("encode"));
    		mailMessageDto.setMailTimeout(properties.getProperty("mail_timeout"));
    		mailMessageDto.setPort(properties.getProperty("server.port"));
            mailMessageDto.setSenderMailAddress(properties.getProperty("sender_mailaddress"));
            mailMessageDto.setSenderUserName(properties.getProperty("sender_userName"));
            mailMessageDto.setHost(properties.getProperty("server.host"));
            mailMessageDto.setAddress(mail.getMailAddress());
            mailMessageDto.setCcAddress(mail.getMailAddressCc());
            mailMessageDto.setSubject(mail.getSubject());
            mailMessageDto.setBody(mail.getBody());
            mailMessageDto.setAuth(Boolean.valueOf(properties.getProperty("auth")));
            return mailMessageDto;

    	} catch (IOException e) {
            e.printStackTrace();
            System.err.println(String.format("メール配信バッチの実行中にエラーが発生しました"));
            return mailMessageDto;
    	}


    }

    public static void main(String[] args) throws MessagingException {

    	try {
	        List<Mail> mailList = new MailRepository().getMailList();
	        if(!mailList.isEmpty()) {
	            for (Mail mail : mailList) {
	            	if(StringUtils.isEmpty(mail.getMailAddress()) && StringUtils.isEmpty(mail.getMailAddressCc())) {
	            		continue;
	            	}
	                MailMessageDto mailMessageDto = setMailMessageDto(mail);
	                boolean isSentMail = sendMail(mailMessageDto);
	                if (isSentMail) {
	                	System.out.println("Wsdbatch sent email to " + mail.getMailAddress());
	                    saveSentMail(mail);
	                }
	            }
	        }
    	} catch (Exception e) {
            e.printStackTrace();
            System.err.println(String.format("メール配信バッチの実行中にエラーが発生しました"));
    	}
    }

    public static boolean sendMail(MailMessageDto mailMessageDto) throws MessagingException {
        // メール送信フラグ
        boolean sendmailFlg = false;

        final Properties props = new Properties();

        // SMTPサーバーの設定。
        props.put("mail.smtp.from", mailMessageDto.getSenderMailAddress());
        props.put("mail.smtp.host", mailMessageDto.getHost());

        // SSL用にポート番号を変更。
        props.put("mail.smtp.port", mailMessageDto.getPort());
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", true);
        // タイムアウト設定
        props.put("mail.smtp.connectiontimeout", mailMessageDto.getMailTimeout());
        props.put("mail.smtp.timeout", mailMessageDto.getMailTimeout());

        // 認証
        /* smtp認証 対策 start */
        Session session;
        // props.put("mail.smtp.auth", mailMessageDto.isAuth());
        if (mailMessageDto.isAuth()) {
            props.put("mail.smtp.auth", "true");
            session = Session.getInstance(props, new myAuth());
        } else {
            session = Session.getInstance(props);
        }
        /* smtp認証 対策 end */
        session.setDebug(true);

        // ここからメッセージ内容の設定。上記で作成したsessionを引数に渡す。
        final MimeMessage message = new MimeMessage(session);

        try {
            // 送信元アドレス
            final Address addrFrom = new InternetAddress(mailMessageDto.getSenderMailAddress(), mailMessageDto.getSenderUserName(), mailMessageDto.getEncode());
            message.setFrom(addrFrom);
            int n = 0;

            // 送信先アドレス
            if(StringUtils.isNotEmpty(mailMessageDto.getAddress())) {
	            String[] addresses = mailMessageDto.getAddress().split(",");
	            InternetAddress[] addrToList = new InternetAddress[addresses.length];
	            for (String address : addresses) {
	            	addrToList[n] = new InternetAddress(address);
	            	n++;
	            }
	            message.addRecipients(Message.RecipientType.TO, addrToList);
            }

         // 送信先アドレス(CC)
            if(StringUtils.isNotEmpty(mailMessageDto.getCcAddress())) {
	            String[] ccAddresses = mailMessageDto.getCcAddress().split(",");
	            InternetAddress[] ccAddrToList = new InternetAddress[ccAddresses.length];
	            n = 0;
	            for (String address : ccAddresses) {
	            	ccAddrToList[n] = new InternetAddress(address);
	            	n++;
	            }
	            message.addRecipients(Message.RecipientType.CC, ccAddrToList);
            }

            // メールのSubject
            message.setSubject(mailMessageDto.getSubject(), mailMessageDto.getEncode());

            // メール本文。
            message.setText(mailMessageDto.getBody(), mailMessageDto.getEncode());

            // その他の付加情報。
            message.setSentDate(new Date());
        } catch (MessagingException e) {
        	e.printStackTrace();
            return false;
            //throw new MessagingException();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }

        // メール送信。
        try {
            Transport.send(message);
        } catch (AuthenticationFailedException e) {
            e.printStackTrace();
            return false;
            //throw new AuthenticationFailedException();

        } catch (MessagingException e) {
            // TODO ログ出力
            e.printStackTrace();
            return false;
            //throw e;
        }

        // 成功したのでメール送信フラグをtrueに設定する。
        sendmailFlg = true;
        return sendmailFlg;
    }

    /** メール送信情報を保存します */
    public static void saveSentMail(Mail mail) {
        mail.setIsSent(true);
        mail.setSentDatetime(LocalDateTime.now());
        mail.setUpdateAccountCode("batch");
        mail.setUpdateDatetime(LocalDateTime.now());
        mail.update();
    }

    /**
     * SMTP認証承認クラス
     *
     */
    static class myAuth extends Authenticator {
		@Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(authId, authPassword);
        }
    }

}
