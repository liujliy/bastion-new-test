package com.lj.bastion.test.ssh.server;

import com.lj.bastion.test.ssh.SessionSwitch;
import com.lj.bastion.test.ssh.client.MyClient;
import org.apache.sshd.server.auth.AsyncAuthException;
import org.apache.sshd.server.auth.password.PasswordAuthenticator;
import org.apache.sshd.server.auth.password.PasswordChangeRequiredException;
import org.apache.sshd.server.session.ServerSession;

import java.io.IOException;

/**
 * @author liu.jiang
 * @date 2021/7/19 13:32
 */
public class MyPasswordAuthenticator implements PasswordAuthenticator {
    @Override
    public boolean authenticate(String s, String s1, ServerSession serverSession) throws PasswordChangeRequiredException, AsyncAuthException {
        // 初始化SSH客户端
        MyClient client = new MyClient("172.16.82.151", 22, "root", "cFts2cm@HJKG");
        try {
            client.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        serverSession.setAttribute(SessionSwitch.CLIENT, client.getSession());
        client.getSession().setAttribute(SessionSwitch.SERVER, serverSession);
        return true;
    }
}
