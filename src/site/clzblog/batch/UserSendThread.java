package site.clzblog.batch;

import site.clzblog.entity.User;

import java.util.List;

/**
 * @Description
 * @Author chengli.zou
 * @CreateDate 2018-05-19 15:02
 **/
public class UserSendThread implements Runnable {

    private List<User> users;

    public UserSendThread(List<User> users) {
        this.users = users;
    }

    @Override
    public void run() {
        for (User user : users) {
            System.out.println(Thread.currentThread().getName() + "<-->" + Thread.currentThread().getState().name() + "," + user.toString());
        }
        System.out.println();
    }
}
