package com.hobby.limitedsub.LimitedSub.logic;

import org.springframework.scheduling.annotation.Scheduled;

public interface SubChecker {
    public default void addUserToFollowList() {};
    public default void addUserToUnfollowList() {};
    @Scheduled(fixedRate = 1000)
    public String findFollowsToUnsub();
}
