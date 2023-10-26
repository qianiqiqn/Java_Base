package com.study.sockets;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author wenqianqian
 * @date 2023/9/4
 *
 * ws://127.0.0.1:9102/data-service/websocket/{sid}
 */
@Component
@Slf4j
@Service
@ServerEndpoint("/websocket/{sid}")
public class WebSocketServer {

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet  = new CopyOnWriteArraySet<>();
    /**
     * websocket是客户端和服务端之间建立了一个连接，建立完连接以后，
     * 会生成一个websocket对象，我们可以用这个对象来执行发送，接收等操作。
     * 但是这只是一个存在于客户端与服务器之间的链接，换句话说，系统只能识别到这个websocket连接是对应于哪个页面（浏览器），
     * 而这个页面在系统中是对应哪个用户（数据库中的用户，或者根本就没有对应任何用户，即未登录，只是一个游客），
     * 我们是无法从这个websocket对象中获取的。所以我们需要创建一个Map对象，用于将websocket对象和实际的user对象进行关联，
     * 这样为我们后续向特定的用户推送消息做铺垫
     */
    private static final Map<String, Session> sessionPool = new HashMap<>();

    /**
     * 接收sid
     */
    private String sid = "";

    /**
     * 连接建立成功时调用的方法
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("sid") String sid) {
        try {
            this.session = session;
            webSocketSet .add(this);
            this.sid = sid;
            log.info("【websocket消息】有新的连接，总数为: {}", webSocketSet .size());
        } catch (Exception e) {
            log.error("websocket 连接失败:{}",e.getMessage(),e);
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        try {
            webSocketSet .remove(this);
            log.info("【websocket消息】连接断开，总数为: {}", webSocketSet .size());
        } catch (Exception e) {
            log.error("websocket 关闭失败:{}",e.getMessage(),e);
        }
    }

    /**
     * 收到客户端消息后调用的方法
     * @ Param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message,@PathParam("sid") String sid) {
        log.info("【websocket消息】收到{}窗口的消息: {}", sid,message);

        // 给所有客户端发送消息
        sendAllMessage(message,sid);
    }


    /**
     * 处理用户活连接异常
     * @param session
     * @param t
     */
    @OnError
    public void OnError(Session session, Throwable t) {
        try {
            if (session.isOpen()) {
                session.close();
            }
            webSocketSet .remove(this);

        } catch (IOException e) {
            log.error("【websocket消息】连接[错误]断开，总数为: {}, 错误：{}", webSocketSet .size(), t.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 此为广播消息, 对连接的客户端都发送消息
     * @param message
     */
    public void sendAllMessage(String message,@PathParam("sid") String sid) {
        log.info("【websocket消息】广播{}消息:{}",sid,message);
        for (WebSocketServer webSocketServer : webSocketSet ) {
            try {
                // 这里设定只推送给这个sid的
                if (webSocketServer.session.isOpen() && webSocketServer.sid.equals(sid)) {
                    webSocketServer.session.getAsyncRemote().sendText(message);
                    log.info("【websocket消息】广播{}消息:{}",webSocketServer.session.getId(),message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 此为单点消息，对指定用户发送消息
     * @param userId 指定用户
     * @param message
     */
    public void sendOneMessage(String userId, String message) {
        Session session = sessionPool.get(userId);
        if (session != null && session.isOpen()) {
            try {
                log.info("【websocket消息】 单点消息:" + message);
                session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 此为单点消息(多人)，对指定用户群体发送消息
     * @param userIds
     * @param message
     */
    public void sendMoreMessage(String[] userIds, String message) {
        for (String userId : userIds) {
            Session session = sessionPool.get(userId);
            if (session != null && session.isOpen()) {
                try {
                    log.info("【websocket消息】 单点消息:" + message);
                    session.getAsyncRemote().sendText(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
