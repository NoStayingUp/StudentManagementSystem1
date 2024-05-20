package com.jk.studentsystem;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static com.jk.studentsystem.StudentSystem.studentSystem;

public class APP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>();
        while(true){
            System.out.println("-------------欢迎来到焦凯学生管理系统----------------");
            System.out.println("请选择操作：1、登录  2、注册  3、忘记密码  4、退出");
            String n = sc.next();
            String account,password,password2,id,phoneNum,verCode,idNum;
            switch (n){
                case "1"-> logIn(users);
                case "2" -> register(users);
                case "3" -> change(users);
                case "4" -> exit();
                default -> System.out.println("请输入正确的选项！");
            }
        }
    }

    public static boolean isAccountExit(String account,ArrayList<User> users){
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            String id = u.getAccount();
            if(account.equals(id)){
                return true; //用户名已存在
            }
        }
        return false; //用户名不存在
    }
    public static int getIndex(String account, ArrayList<User> users){
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            String id = u.getAccount();
            if(account.equals(id)){
                return i;
            }
        }
        return -1; //用户名不存在
    }

    //注册功能
    private static void register(ArrayList<User> users) {
        Scanner sc = new Scanner(System.in);
        String password;
        String phoneNum;
        String account;
        String password2;
        String id;
        while (true) {
            System.out.println("请输入您的用户名：");
            account = sc.next();
            if(checkAccount(account, users)){
                break;
            }
        }
        while (true) {
            System.out.println("请输入您的密码：");
            password = sc.next();
            System.out.println("请再次输入您的密码：");
            password2 = sc.next();
            if(password.equals(password2)){
                break;
            }else {
                System.out.println("两次密码不一致，请重新输入！");
            }
        }
        while (true) {
            System.out.println("请输入您的身份证号：");
            id = sc.next();
            if(checkId(id)){
                break;
            }else {
                System.out.println("您输入的身份证号码格式有误，请重新输入！");
            }
        }
        while (true) {
            System.out.println("请输入您的电话号码：");
            phoneNum = sc.next();
            if(checkPNum(phoneNum)){
                break;
            }else{
                System.out.println("您输入的电话号码格式有误，请重新输入");
            }
        }
        User u = new User(account,password,id,phoneNum);
        users.add(u);
    }
    public static boolean checkAccount(String account, ArrayList<User> users){
        if(account.length() < 3 || account.length() > 15){
            System.out.println("用户名长度必须在3~15位之间，请重新输入");
            return false;
        }
        int num = 0;
        for (int i = 0; i < account.length(); i++) {
            char ch = account.charAt(i);
            if(ch < '9' && ch > '0'){
                num++;
            }
            if((ch > '9' || ch < '0') && (ch < 'a' || ch > 'z') && (ch < 'A' || ch > 'Z')){
                System.out.println("用户名只能是数字加字母的组合，不能包含其他字符");
                return false;
            }
        }
        if(num == account.length()){
            System.out.println("用户名不能是纯数字");
            return false;
        }
        if(isAccountExit(account,users)) {
            System.out.println("该用户名已经存在，请重新输入！");
            return false;
        }
        //到最后一定是满足条件的
        return true;
    }
    public static boolean checkId(String id){
        if(id.length() != 18){
            //System.out.println("您输入的身份证号码格式有误");
            return false;
        }
        if(id.charAt(0) == '0'){
            //System.out.println("您输入的身份证号码格式有误");
            return false;
        }
        for (int i = 0; i < id.length() - 1; i++) {
            if(id.charAt(i) < '0' || id.charAt(i) > '9'){
                //System.out.println("您输入的身份证号码格式有误");
                return false;
            }
        }
        char lastNum = id.charAt(id.length() - 1);
        if((lastNum < '0' || lastNum > '9') && lastNum != 'x' && lastNum != 'X'){
            //System.out.println("您输入的身份证号码格式有误");
            return false;
        }
        return true;
    }
    public static boolean checkPNum(String phoneNum){
        if(phoneNum.length() != 11){
            return false;
        }
        if(phoneNum.charAt(0) == '0'){
            return false;
        }
        for (int i = 0; i < phoneNum.length(); i++) {
            char ch = phoneNum.charAt(i);
            if(ch < '0' || ch > '9'){
                return false;
            }
        }
        return true;
    }

    //登录功能
    private static void logIn(ArrayList<User> users) {
        String verCode;
        String account;
        String password;
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您的用户名：");
        account = sc.next();
        if(!isAccountExit(account, users)){
            System.out.println("用户名未注册，请先注册");
            return;
        }
        System.out.println("请输入您的密码：");
        password = sc.next();
        String verificationCode = getVerCode();
        while (true) {
            System.out.println("请输入验证码" + verificationCode);
            verCode = sc.next();
            //if(verificationCode.equals(verCode)){
            //验证码是忽略大小写的比较
            if(verificationCode.equalsIgnoreCase(verCode)){
                break;
            }else {
                System.out.println("验证码错误，请重新输入");
            }
        }
        for(int i = 0; i < 3; i++){
            if(check(account,password, users)){
                System.out.println("登陆成功！");
                break;
            }else{
                if(i == 2){
                    System.out.println("您已经连续三次输入错误密码");
                    return;
                } else{
                    System.out.println("密码错误，请重新输入密码");
                    password = sc.next();
                }
            }
        }
        studentSystem();
    }
    public static String getVerCode(){
        String[] code = new String[26+26+10];
        for (int i = 0; i < 10; i++) {
            code[i] = "" + i;
        }
        //字符和数字加一起会自动转换为int
        for (int i = 10; i < 36; i++) {
            code[i] = (char)('a' + (i - 10)) + "";
        }
        for (int i = 36; i < 62; i++) {
            code[i] = (char)('A' + (i - 36)) + "";
        }
        Random r = new Random();
        StringBuilder VCode = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int index = r.nextInt(code.length);
            VCode.append(code[index]);
        }
        return VCode.toString();
    }
    public static boolean check(String account, String password, ArrayList<User> users){
        User u = users.get(0);
        for (int i = 0; i < users.size(); i++) {
            u = users.get(i);
            String id = u.getAccount();
            if(account.equals(id)){
                break;
            }
        }
        if(u.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    //忘记密码
    private static void change(ArrayList<User> users) {
        Scanner sc = new Scanner(System.in);
        String phoneNum;
        String password;
        String account;
        String idNum;
        System.out.println("请输入您的用户名：");
        account = sc.next();
        int index = getIndex(account, users);
        if(index == -1){
            System.out.println("用户名未注册，请先注册");
            return;
        }
        User u = users.get(index);
        System.out.println("请输入您的身份证号码和手机号码：");
        idNum = sc.next();
        phoneNum = sc.next();
        if(checkIdAndPhNum(idNum,phoneNum,u)){
            System.out.println("请输入您要修改的密码：");
            password = sc.next();
            u.setPassword(password);
            System.out.println("修改成功");
        }else {
            System.out.println("账号信息不匹配，修改失败");
        }
    }
    public static boolean checkIdAndPhNum(String idNum, String phoneNum, User u){
        String id = u.getId_Num();
        String phone = u.getPhoneNum();
        if(id.equals(idNum) && phone.equals(phoneNum)){
            return true;
        }
        return false;
    }


    public static void exit() {
        System.out.println("退出");
        //如果只有一个break，那么就只会跳出switch，不会跳出while
        //有两种方法，第一种与goto语句类似，break加一个标签
        //break loop;
        //第二种
        System.exit(0); //停止虚拟机运行
        //第三种
        //return;
    }
}

