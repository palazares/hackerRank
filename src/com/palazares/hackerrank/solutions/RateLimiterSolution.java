package com.palazares.hackerrank.solutions;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.*;

public class RateLimiterSolution {

    static final int MAX_REQUESTS_SEC = 100;

    private static class RateLimiter {
        final private Map<String, Queue<Long>> clients = new HashMap<>();

        public boolean isAlow(String clientId) {
            var timeStamp = System.currentTimeMillis();
            var client = clients.computeIfAbsent(clientId, x -> new ConcurrentLinkedQueue<>());
            if (client.size() < MAX_REQUESTS_SEC) {
                client.offer(timeStamp);
                //System.out.println(String.format("%s: true. %s", client.size(), timeStamp));
                return true;
            }
            while (client.peek() < timeStamp - 1000) {
                client.poll();
            }
            if (client.size() < MAX_REQUESTS_SEC) {
                client.offer(timeStamp);
                //System.out.println(String.format("%s: true. %s", client.size(), timeStamp));
                return true;
            }
            //System.out.println(String.format("%s: false. %s", client.size(), timeStamp));
            return false;
        }
    }

    public static void main(String[] args) {
        var rateLimiter = new RateLimiter();
        var executor = Executors.newScheduledThreadPool(10);

        //executor.scheduleAtFixedRate(() -> rateLimiter.isAlow("5"), 0, 5, TimeUnit.MILLISECONDS);
//        executor.scheduleAtFixedRate(() -> rateLimiter.isAlow("10"), 0, 10, TimeUnit.MILLISECONDS);
//        executor.scheduleAtFixedRate(() -> rateLimiter.isAlow("1"), 0, 1, TimeUnit.MILLISECONDS);
        executor.scheduleAtFixedRate(() -> rateLimiter.isAlow("20"), 0, 20, TimeUnit.MILLISECONDS);
    }
}
