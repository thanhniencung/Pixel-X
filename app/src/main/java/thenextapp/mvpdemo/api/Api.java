package thenextapp.mvpdemo.api;

import thenextapp.mvpdemo.model.User;

/**
 * Created by Sandy on 12/25/15.
 */
public class Api {
    public static boolean requestCheckLogin(User user) {

        if (user.getEmail().equals("vinova@gmail.com") && user.getPass().equals("123456")) {
            return true;
        }

        return false;
    }
}
