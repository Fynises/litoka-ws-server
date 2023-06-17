package net.litoka.litokawebsocketserver.dto;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.SetMultimap;
import net.litoka.litokawebsocketserver.enums.ConnectionType;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * data object to manage identifiers
 */
public class IdManager {

    // key: user id, value: set of sessionIds
    private final SetMultimap<UserKey, String> users;

    // key: sessionId, userId
    private final ConcurrentMap<String, UserKey> sessions;

    public IdManager() {
        this.users = Multimaps.synchronizedSetMultimap(HashMultimap.create());
        this.sessions = new ConcurrentHashMap<>();
    }

    public Set<String> getSessions(String userId, ConnectionType connType) {
        return users.get(new UserKey(userId, connType));
    }

    public void setSession(String userId, ConnectionType connType, String sessionId) {
        var userKey = new UserKey(userId, connType);
        users.put(userKey, sessionId);
        sessions.put(sessionId, userKey);
    }

    public void removeSession(String sessionId) {
        var sessionUser = sessions.get(sessionId);
        users.remove(sessionUser, sessionId);
        sessions.remove(sessionId);
    }




}
