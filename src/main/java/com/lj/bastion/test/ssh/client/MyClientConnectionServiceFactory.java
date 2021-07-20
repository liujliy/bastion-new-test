package com.lj.bastion.test.ssh.client;

import org.apache.sshd.client.session.AbstractClientSession;
import org.apache.sshd.client.session.ClientConnectionService;
import org.apache.sshd.client.session.ClientConnectionServiceFactory;
import org.apache.sshd.common.Service;
import org.apache.sshd.common.ServiceFactory;
import org.apache.sshd.common.forward.PortForwardingEventListener;
import org.apache.sshd.common.session.AbstractConnectionServiceFactory;
import org.apache.sshd.common.session.Session;
import org.apache.sshd.common.util.ValidateUtils;

import java.io.IOException;

/**
 * @author liu.jiang
 * @date 2021/7/19 13:45
 */
public class MyClientConnectionServiceFactory extends AbstractConnectionServiceFactory implements ServiceFactory {
    public static final ClientConnectionServiceFactory INSTANCE = new ClientConnectionServiceFactory() {
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

    public MyClientConnectionServiceFactory() {
    }

    public String getName() {
        return "ssh-connection";
    }

    public Service create(Session session) throws IOException {
        AbstractClientSession abstractSession = (AbstractClientSession) ValidateUtils.checkInstanceOf(session, AbstractClientSession.class, "Not a client session: %s", session);
        ClientConnectionService service = new MyClientConnectionService(abstractSession);
        service.addPortForwardingEventListenerManager(this);
        return service;
    }
}
