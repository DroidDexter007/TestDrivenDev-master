package com.cisco.unittesting.login;

/**
 * Created by pchangul on 12/12/2016.
 */

class LoginAPI {

    boolean authenticate(String username, String password) {

        if(username.equals("param") && password.equals("12345")) {
            return true;
        } else {
            return false;
        }

    }
}
