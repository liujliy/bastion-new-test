package com.lj.bastion.test.ssh.server;

import org.apache.sshd.common.io.nio2.Nio2ServiceFactoryFactory;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.auth.hostbased.AcceptAllHostBasedAuthenticator;
import org.apache.sshd.server.auth.pubkey.AcceptAllPublickeyAuthenticator;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.session.ServerUserAuthServiceFactory;

import java.util.Arrays;

/**
 * @author liu.jiang
 * @date 2021/7/19 13:30
 */
public class MyServer {
    private SshServer sshServer;

    public SshServer getSshServer() {
        return sshServer;
    }

    public void setSshServer(SshServer sshServer) {
        this.sshServer = sshServer;
    }

    public void init() {
        sshServer = SshServer.setUpDefaultServer();

        sshServer.setPort(42020);

        // sshServer.setIoServiceFactoryFactory(new Nio2ServiceFactoryFactory());
        // sshServer.setNioWorkers(15);
        // 自定义密码校验
        sshServer.setPasswordAuthenticator(new MyPasswordAuthenticator());

        sshServer.setPublickeyAuthenticator(AcceptAllPublickeyAuthenticator.INSTANCE);

        sshServer.setHostBasedAuthenticator(AcceptAllHostBasedAuthenticator.INSTANCE);

        sshServer.setServiceFactories(Arrays.asList(new ServerUserAuthServiceFactory(),
                new MyServerConnectionServiceFactory()));

        sshServer.setKeyPairProvider(new SimpleGeneratorHostKeyProvider());
    }
}
