package com.jk.studentsystem;

public class User {
    private String account;
    private String password;
    private String Id_Num;
    private String phoneNum;

    public User() {
    }

    public User(String account, String password, String Id_Num, String phoneNum) {
        this.account = account;
        this.password = password;
        this.Id_Num = Id_Num;
        this.phoneNum = phoneNum;
    }

    /**
     * 获取
     * @return account
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置
     * @param account
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     * @return Id_Num
     */
    public String getId_Num() {
        return Id_Num;
    }

    /**
     * 设置
     * @param Id_Num
     */
    public void setId_Num(String Id_Num) {
        this.Id_Num = Id_Num;
    }

    /**
     * 获取
     * @return phoneNum
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * 设置
     * @param phoneNum
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String toString() {
        return "User{account = " + account + ", password = " + password + ", Id_Num = " + Id_Num + ", phoneNum = " + phoneNum + "}";
    }
}
