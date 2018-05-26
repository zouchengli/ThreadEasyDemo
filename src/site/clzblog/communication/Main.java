package site.clzblog.communication;

/**
 * This is person class
 */
class Person {
    public String name;
    public String gender;
}

/**
 * This is producer class
 */
class Producer extends Thread {
    Person person;

    public Producer(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {
            synchronized (person) {
                if (count == 0) {
                    person.name = "Jack";
                    person.gender = "Male";
                } else {
                    person.name = "Lucy";
                    person.gender = "Female";
                }
                count = (count + 1) % 2;
            }
        }
    }
}

/**
 * This is consumer class
 */
class Consumer extends Thread {
    Person person;

    public Consumer(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (person) {
                System.out.println(person.name + "," + person.gender);
            }
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
        Producer producer = new Producer(person);
        Consumer consumer = new Consumer(person);
        producer.start();
        consumer.start();
    }
}
