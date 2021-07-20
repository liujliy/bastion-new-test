package com.lj.bastion.test;

import com.lj.bastion.test.ssh.server.MyServer;
import org.apache.sshd.server.SshServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) throws IOException {
        // 初始化SSH服务器
        MyServer server = new MyServer();
        server.init();
        SshServer sshServer = server.getSshServer();
        sshServer.start();

        SpringApplication.run(TestApplication.class, args);

        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
