package com.lj.bastion.test.ssh.server;

import com.lj.bastion.test.ssh.SessionSwitch;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.common.SshException;
import org.apache.sshd.common.util.buffer.Buffer;
import org.apache.sshd.server.session.AbstractServerSession;
import org.apache.sshd.server.session.ServerConnectionService;
import org.apache.sshd.server.session.ServerSession;

/**
 * @author liu.jiang
 * @date 2021/7/19 13:38
 */
public class MyServerConnectionService extends ServerConnectionService {
    protected MyServerConnectionService(AbstractServerSession s) throws SshException {
        super(s);
    }

    @Override
    public void process(int cmd, Buffer buffer) throws Exception {
        ServerSession serverSession = getServerSession();
        ClientSession clientSession = serverSession.getAttribute(SessionSwitch.CLIENT);
        if (clientSession == null) {
            return;
        }
        Buffer buf = clientSession.createBuffer((byte) cmd);
        buf.putRawBytes(buffer.array(), buffer.rpos(), buffer.available());
        clientSession.writePacket(buf);
    }
}
