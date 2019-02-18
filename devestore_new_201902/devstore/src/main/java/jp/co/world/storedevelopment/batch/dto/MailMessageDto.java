package jp.co.world.storedevelopment.batch.dto;

public class MailMessageDto {

	/**
	 * エンコード
	 */
	private String encode;
	
	/**
	 * セッションタイムアウト
	 */
	private String mailTimeout;

    /**
     * メールタイトル
     */
    private String subject;

    /**
     * メール本文
     */
    private String body;

    /**
     * ホスト情報
     */
    private String host;

    /**
     * ポート情報
     */
    private String port;

    /**
     * 送信するアドレス情報
     */
    private String address;
    
    /**
     * 送信するアドレス情報
     */
    private String ccAddress;

    /**
     * 認証
     */
    private boolean auth;

    /**
     * 送信元メールアドレス
     */
    private String senderMailAddress;

    /**
     * 送信元メールアドレスパスワード
     */
    private String senderPassword;

	/**
	 * 送信者名称
	 */
    private String senderUserName;

    public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public String getMailTimeout() {
		return mailTimeout;
	}

	public void setMailTimeout(String mailTimeout) {
		this.mailTimeout = mailTimeout;
	}

	/**
     * メール件名を取得します。
     * @return メール件名
     */
    public String getSubject() {
        return subject;
    }

    /**
     * メール件名を設定します。
     * @param subject メール件名
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * メール本文を取得します。
     * @return メール本文
     */
    public String getBody() {
        return body;
    }

    /**
     * メール本文を設定します。
     * @param body メール本文
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * ホスト情報を取得します。
     * @return ホスト情報
     */
    public String getHost() {
        return host;
    }

    /**
     * ホスト情報を設定します。
     * @param host ホスト情報
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * ポート情報を取得します。
     * @return ポート情報
     */
    public String getPort() {
        return port;
    }

    /**
     * ポート情報を設定します。
     * @param port ポート情報
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * 送信するアドレス情報を取得します。
     * @return 送信するアドレス情報
     */
    public String getAddress() {
        return address;
    }

    /**
     * 送信するアドレス情報を設定します。
     * @param address 送信するアドレス情報
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * 送信するアドレス情報を取得します。
     * @return 送信するアドレス情報
     */
    public String getCcAddress() {
        return ccAddress;
    }

    /**
     * 送信するアドレス情報を設定します。
     * @param address 送信するアドレス情報
     */
    public void setCcAddress(String ccAddress) {
        this.ccAddress = ccAddress;
    }

    /**
     * 認証を取得します。
     * @return 認証
     */
    public boolean isAuth() {
        return auth;
    }

    /**
     * 認証を設定します。
     * @param auth 認証
     */
    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    /**
     * 送信元メールアドレスを取得します。
     * @return 送信元メールアドレス
     */
    public String getSenderMailAddress() {
        return senderMailAddress;
    }

    /**
     * 送信元メールアドレスを設定します。
     * @param senderMailAddress 送信元メールアドレス
     */
    public void setSenderMailAddress(String senderMailAddress) {
        this.senderMailAddress = senderMailAddress;
    }

    /**
     * 送信元メールアドレスパスワードを取得します。
     * @return 送信元メールアドレスパスワード
     */
    public String getSenderPassword() {
        return senderPassword;
    }

    /**
     * 送信元メールアドレスパスワードを設定します。
     * @param senderPassword 送信元メールアドレスパスワード
     */
    public void setSenderPassword(String senderPassword) {
        this.senderPassword = senderPassword;
    }

	public String getSenderUserName() {
		return senderUserName;
	}

	public void setSenderUserName(String senderUserName) {
		this.senderUserName = senderUserName;
	}


}
