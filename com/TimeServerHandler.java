package com;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.util.Date;

/**
 * Created by hjc on 16-7-17.
 */
public class TimeServerHandler extends IoHandlerAdapter {
    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("连接建立，会话创建完成");
        System.out.println("地址："+session.getRemoteAddress());
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("空闲"+session.getIdleCount(status));
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String str = message.toString();
        if(str.trim().equalsIgnoreCase("quit")){
            session.close(true);
            return;
        }
        System.out.println(str);
        Date date = new Date();
        session.write(date.toString());
        System.out.println("Message written...");
    }
}
