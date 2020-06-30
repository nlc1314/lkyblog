package com.hz.lkyblog.biz.cache;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.alicp.jetcache.anno.SerialPolicy;
import com.hz.lkyblog.dao.model.BlogUser;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class SessionCache {

    //session和用户基本信息的关系
    @CreateCache(expire = 1,
            timeUnit = TimeUnit.MINUTES,
            cacheType = CacheType.LOCAL,
            localLimit = 10000,
            serialPolicy = SerialPolicy.JAVA)
    private Cache<String, BlogUser> blogUserCache;

    //保存admin信息缓存
    public void saveAdminInfo(String adminId, BlogUser blogUser) {
        blogUserCache.put(adminId, blogUser);
    }

    //获取admin信息缓存
    public BlogUser getAdminInfo(String adminId) {
        return blogUserCache.get(adminId);
    }

    //删除admin信息缓存
    public void delAdminInfo(String adminId) {
        blogUserCache.remove(adminId);
    }

    @CreateCache(expire = 10,
            timeUnit = TimeUnit.MINUTES,
            cacheType = CacheType.LOCAL,
            localLimit = 10000,
            serialPolicy = SerialPolicy.JAVA)
    private Cache<String, String> sessionIdCache;


    //保存session信息缓存
    public void saveSession(String adminId, String sessionId) {
        sessionIdCache.put(adminId, sessionId);
    }

    //获取session信息缓存
    public String getSession(String mobile) {
        return sessionIdCache.get(mobile);
    }

    //删除session信息缓存
    public void delSession(String mobile) {
        sessionIdCache.remove(mobile);
    }
}
