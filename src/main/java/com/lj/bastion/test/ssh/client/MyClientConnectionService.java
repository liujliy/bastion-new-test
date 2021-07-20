package com.lj.bastion.test.ssh.client;

import com.lj.bastion.test.ssh.SessionSwitch;
import org.apache.sshd.client.session.AbstractClientSession;
import org.apache.sshd.client.session.ClientConnectionService;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.common.SshException;
import org.apache.sshd.common.util.buffer.Buffer;
import org.apache.sshd.server.session.ServerSession;

/**
 * @author liu.jiang
 * @date 2021/7/19 13:46
 */
public class MyClientConnectionService extends ClientConnectionService {
    public MyClientConnectionService(AbstractClientSession s) throws SshException {
        super(s);
    }

    @Override
    public void process(int cmd, Buffer buffer) throws Exception {
        ClientSession clientSession = getClientSession();
        ServerSession serverSession = clientSession.getAttribute(SessionSwitch.SERVER);
        if (serverSession == null) {
            return;
        }
        Buffer buf = clientSession.createBuffer((byte) cmd);
        buf.putRawBytes(buffer.array(), buffer.rpos(), buffer.available());
        serverSession.writePacket(buf);
    }
}
