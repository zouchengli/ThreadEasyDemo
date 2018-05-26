package site.clzblog.thread.communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This is person class
 */
class Person {
    public String name;
    public String gender;
    public Boolean flag = false;
    Lock lock = new ReentrantLock();
}

/**
 * This is producer class
 */
class Producer extends Thread {
    Person person;
    Condition condition;

    public Producer(Person person, Condition condition) {
        this.person = person;
        this.condition = condition;
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {
            /*synchronized (person) {*/
            person.lock.lock();
            try {
                if (person.flag) {
                    //person.wait();
                    condition.await();

                }
                if (count == 0) {
                    person.name = "Jack";
                    person.gender = "Male";
                } else {
                    person.name = "Lucy";
                    person.gender = "Female";
                }
                count = (count + 1) % 2;
                person.flag = true;
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                person.lock.unlock();
            }
                /*person.notify();
            }*/
        }
    }
}

/**
 * This is consumer class
 */
class Consumer extends Thread {
    Person person;
    Condition condition;

    public Consumer(Person person, Condition condition) {
        this.person = person;
        this.condition = condition;
    }

    @Override
    public void run() {
        while (true) {
            person.lock.lock();
            /*synchronized (person) {*/
            try {
                if (!person.flag) {
                    //person.wait();
                    condition.await();
                }
                System.out.println(person.name + "," + person.gender);
                person.flag = false;
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                person.lock.unlock();
            }
               /* person.notify();
            }*/
        }
    }
}

/**
 * @Description Simulation producer and consumer thread
 * @Author chengli.zou
 * @CreateDate 2018-05-26 16:44
 **/
public class Main {
    public static void main(String[] args) {
        Person person = new Person();
        Condition condition = person.lock.newCondition();
        Producer producer = new Producer(person, condition);
        Consumer consumer = new Consumer(person, condition);
        producer.start();
        consumer.start();
    }
}
