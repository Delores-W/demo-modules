package com.delores.ws;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author William
 * @date 2022/4/22 2:14 PM
 * @description
 */
@Slf4j
@ServerEndpoint(value="/webSocketTest/{userId}")
public class WebSocket {

    private static int onlineCount = 0;
    private static Map<String, WebSocket> clients = new ConcurrentHashMap<>();
    private Session session;
    private String userId;

    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) {
        this.userId = userId;
        this.session = session;
        addOnlineCount();
        clients.put(userId, this);
        log.info("new connection {}", userId);
    }

    @OnClose
    public void onClose() {
        clients.remove(userId);
        log.info("connection: **** closed");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("accept user message {}", message);
    }

    @OnError
    public void OnError(Session session, Throwable error) {
        log.info("error....");
        error.printStackTrace();
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
    private static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }

    private static synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }

    public static synchronized Map<String, WebSocket> getClients() {
        return clients;
    }

}
