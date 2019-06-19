package com.song.ex.javamm;

import lombok.Data;

/**
 * com.song.ex.javamm
 *
 * @author by Song
 * @date 2019/6/6 17:37
 */
public class JavaMMEx {

    //JAVA引用传递
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("李三");
        person.setAge(18);
        charted(person);
        person.tell();
    }

    private static void charted(Person per2){
        per2.setAge(80);
    }

}


@Data
class Person {
    private String name;
    private int age;
    public void tell(){
        System.err.println("姓名："+name+";年龄:"+age);
    }
}