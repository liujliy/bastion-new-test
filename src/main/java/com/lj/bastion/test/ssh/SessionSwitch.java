package com.lj.bastion.test.ssh;

import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.common.AttributeRepository;
import org.apache.sshd.server.session.ServerSession;

/**
 * @author liu.jiang
 * @date 2021/7/19 13:28
 */
public class SessionSwitch {
    public static final AttributeRepository.AttributeKey<ClientSession> CLIENT = new AttributeRepository.AttributeKey<>();
    public static final AttributeRepository.AttributeKey<ServerSession> SERVER = new AttributeRepository.AttributeKey<>();
}
