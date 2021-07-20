package com.lj.bastion.test.ssh.server;

import org.apache.sshd.common.Service;
import org.apache.sshd.common.ServiceFactory;
import org.apache.sshd.common.forward.PortForwardingEventListener;
import org.apache.sshd.common.session.AbstractConnectionServiceFactory;
import org.apache.sshd.common.session.Session;
import org.apache.sshd.common.util.ValidateUtils;
import org.apache.sshd.server.session.AbstractServerSession;
import org.apache.sshd.server.session.ServerConnectionService;
import org.apache.sshd.server.session.ServerConnectionServiceFactory;

import java.io.IOException;

/**
 * @author liu.jiang
 * @date 2021/7/19 13:34
 */
public class MyServerConnectionServiceFactory extends AbstractConnectionServiceFactory implements ServiceFactory {
    public static final ServerConnectionServiceFactory INSTANCE = new ServerConnectionServiceFactory() {
        public void addPortForwardingEventListener(PortForwardingEventListener listener) {
            throw new UnsupportedOperationException("addPortForwardingListener(" + listener + ") N/A on default instance");
        }

        public void removePortForwardingEventListener(PortForwardingEventListener listener) {
            throw new UnsupportedOperationException("removePortForwardingEventListener(" + listener + ") N/A on default instance");
        }

        public PortForwardingEventListener getPortForwardingEventListenerProxy() {
            return PortForwardingEventListener.EMPTY;
        }
    };

    public MyServerConnectionServiceFactory() {
    }

    public String getName() {
        return "ssh-connection";
    }

    public Service create(Session session) throws IOException {
        AbstractServerSession abstractSession = (AbstractServerSession) ValidateUtils.checkInstanceOf(session, AbstractServerSession.class, "Not a server session: %s", session);
        ServerConnectionService service = new MyServerConnectionService(abstractSession);
        service.addPortForwardingEventListenerManager(this);
        return service;
    }
}
