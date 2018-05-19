package site.clzblog.batch;

import site.clzblog.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author chengli.zou
 * @CreateDate 2018-05-19 14:49
 **/
public class BatchSms {
    public static void main(String[] args) {
        //1.初始化数据
        List<User> users = initData();

        //2.定义每个线程分批发送大小
        int userCount = 2;
        //3.计算每个线程需要分批跑的数据
        List<List<User>> lists = ListUtil.splitList(users, userCount);
        for (int i = 0; i < lists.size(); i++) {
            List<User> userList = lists.get(i);
            UserSendThread userSendThread = new UserSendThread(userList);
            Thread thread = new Thread(userSendThread, "Thread" + i);
            thread.start();
        }
        //4.分批发送
    }

    private static List<User> initData() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(new User("userId:" + i, "userName:" + i));
        }
        return users;
    }
}
