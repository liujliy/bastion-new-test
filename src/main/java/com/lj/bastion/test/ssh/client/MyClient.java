package com.lj.bastion.test.ssh.client;

import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.client.session.ClientUserAuthServiceFactory;
import org.apache.sshd.client.session.SessionFactory;
import org.apache.sshd.common.io.nio2.Nio2ServiceFactoryFactory;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author liu.jiang
 * @date 2021/7/19 13:41
 */
public class MyClient {
    private String host;

    private Integer port;

    private String user;

    private String password;

    private SshClient client;

    private ClientSession session;

    public MyClient(String host, Integer port, String user, String password) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SshClient getClient() {
        return client;
    }

    public void setClient(SshClient client) {
        this.client = client;
    }

    public ClientSession getSession() {
        return session;
    }

    public void setSession(ClientSession session) {
        this.session = session;
    }

    public void init() throws IOException {
        client = SshClient.setUpDefaultClient();

        client.setSessionFactory(new SessionFactory(client));
        client.setServiceFactories(Arrays.asList(new ClientUserAuthServiceFactory(),
                new MyClientConnectionServiceFactory()));

        // client.setIoServiceFactoryFactory(new Nio2ServiceFactoryFactory());
        // client.setNioWorkers(15);

        client.start();

        session = client.connect(user, host, port).verify().getClientSession();
        session.addPasswordIdentity(password);
        session.auth().verify();
    }

    public void close() throws IOException {
        session.close();
        client.stop();
    }
}
