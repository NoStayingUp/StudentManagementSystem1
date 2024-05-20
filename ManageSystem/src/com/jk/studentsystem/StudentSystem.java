package com.jk.studentsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentSystem {
    public static void studentSystem() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        loop : while (true) {
            System.out.println("-------------欢迎来到焦凯学生管理系统----------------");
            System.out.println("1：添加学生");
            System.out.println("2：删除学生");
            System.out.println("3：修改学生");
            System.out.println("4：查询学生");
            System.out.println("5：退出");
            System.out.println("请输入您的选择：");

            //n = sc.nextInt();
            //这里用String作为选项类型更方便，因为如果按错了不会报错
            String n = sc.next();
            switch (n){
                case "1" -> addStudent(students);
                case "2" -> deleteStudent(students);
                case "3" -> updateStudent(students);
                case "4" -> queryStudent(students);
                case "5" -> exit();
                default -> System.out.println("请输入正确的选择！");
            }
        }
    }

    //添加学生
    public static void addStudent(ArrayList<Student> students) {
        Scanner sc = new Scanner(System.in);
        String id = null;
        while (true) {
            System.out.println("请输入学生的ID：");
            id = sc.next();
            if(!isExit(id,students)){
                break;
            } else {
                System.out.println("这个ID重复了，换一个试试吧~");
            }
        }
        System.out.println("请输入学生的姓名");
        String name = sc.next();
        System.out.println("请输入学生的年龄：");
        int age = sc.nextInt();
        System.out.println("请输入学生的家庭住址：");
        String address = sc.next();

        Student s = new Student(id, name, age, address);
        students.add(s);

        System.out.println("添加成功！");
    }

    //删除学生
    public static void deleteStudent(ArrayList<Student> students) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除学生的ID：");
        String id = sc.next();
        //查询id在集合中的索引
        int index = getIndex(id, students);
        //对index进行判断
        //如果大于等于0，那么表示存在，直接删除
        //如果小于0，表示不存在，结束方法，回到主菜单
        if(index >= 0){
            students.remove(index);
            System.out.println("id为" + id + "的学生删除成功");
        }
        else{
            System.out.println("id不存在，删除失败");
            //return;
        }
    }
    //修改学生
    public static void updateStudent(ArrayList<Student> students) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您要修改的学生ID：");
        String id = sc.next();
        int index = getIndex(id, students);
        if(index == -1){
            System.out.println("ID为" + id + "的学生不存在，请重新输入");
            return;
        }
        //代码如果执行到了这里就表示id是一定存在的
        Student s = students.get(index);
        System.out.println("请输入您要修改的学生姓名");
        String newName = sc.next();
        s.setName(newName);

        System.out.println("请输入您要修改的学生年龄");
        int newAge = sc.nextInt();
        s.setAge(newAge);

        System.out.println("请输入您要修改的学生家庭住址");
        String newAddress = sc.next();
        s.setAddress(newAddress);

        System.out.println("学生信息修改成功");
    }
    //查询学生
    public static void queryStudent(ArrayList<Student> students) {
        if(students.size() == 0){
            System.out.println("当前无学生信息，请添加后再查询");
            return;
        }
        //输出表头信息
        System.out.println("id\t\t姓名\t年龄\t家庭住址\t");
        for (int i = 0; i < students.size(); i++) {
            //用一个Student变量代替students.get(i)，不然太长了
            Student s = students.get(i);
            System.out.println(s.getId() + "\t" + s.getName() + "\t"
                                + s.getAge() + "\t" + s.getAddress());
        }
    }
    //退出系统
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
    //判断ID唯一性
    public static boolean isExit(String id, ArrayList<Student> students) {
        /*for (Student s : students) {
            if (s.getId().equals(id)) {
                return true;
            }
        }
        return false;*/
        return getIndex(id, students) >= 0;
    }
    //找到对应id在集合中的索引
    public static int getIndex(String id, ArrayList<Student> students) {
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            String Id = s.getId();
            if(Id.equals(id)){
                return i;
            }
        }
        return -1;
    }
}
