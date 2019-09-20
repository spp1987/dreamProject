package com.song.ex.javamm;

import java.util.HashSet;
import java.util.Set;

/**
 * com.song.ex.javamm
 *
 * @author by Song
 * @date 2019/9/10 13:30
 */
public class HashSetTest {

    public static void main(String[] args)throws Exception {
            class TestHashSet implements Runnable{
                // 实现Runnable 让该集合能被多个线程访问
                Set<Integer> set = new HashSet<Integer>();
                // 线程的执行就是插入5000个整数
                Object c ;
                public TestHashSet() {
                    c = this;
                }
                public void run() {
                    for (int i = 0; i < 5000; i++) {
                        synchronized (this) {
                            set.add(i);
                        }
                    }
                }
            }
            TestHashSet run2 = new TestHashSet();
            // 实例化两个线程
            Thread t6 = new Thread(run2);
            Thread t7 = new Thread(run2);
            // 启动两个线程sssSSSSsssssss：：：：：:;;;:::::>>>>>>>>>
            t6.start();

            t7.start();

            // 当前线程等待加入到调用线程后
            t6.join();
            t7.join();

            // 打印出集合的size
            System.out.println(run2.set.size());
            int i = 0 ;
            for (Integer itemp :
                    run2.set) {
                //System.err.println("...."+itemp);
                i++;
            }
            System.err.println(i);

    }
}
