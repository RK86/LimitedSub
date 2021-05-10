package com.hobby.limitedsub.LimitedSub.logic;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@EnableScheduling
@Component
public class DatabaseSubChecker implements SubChecker {
    private int subCounter;

    public void addUserToFollowList() {};
    public void addUserToUnfollowList() {};
    @Scheduled(fixedRate = 50000)
    public String findFollowsToUnsub() {
        subCounter = subCounter + 1;
        return "Found subs:" + String.valueOf(subCounter);
    }
}
