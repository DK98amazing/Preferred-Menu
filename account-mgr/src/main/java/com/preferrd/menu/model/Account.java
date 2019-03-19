package com.preferrd.menu.model;

import lombok.Data;

@Data public class Account implements Entity {
    private static final long serialVersionUID = 7713453669641194833L;
    private String accountId;
    private String userName;
    private String password;
    private String email;
    private String description;
    private String phone;
    private String cardId;
    private String realName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        return stringBuffer.append("[accountId:").append(accountId).append(",userName").append(userName)
            .append(",reallName").append(realName).append(",password").append(password).append(",email").append(email)
            .append(",description").append(description).append(",phone").append(phone).append(",cardId").append(cardId)
            .append("]").toString();
    }

}
