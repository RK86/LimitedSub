package com.hobby.limitedsub.LimitedSub.logic;

import com.github.redouane59.RelationType;
import com.github.redouane59.twitter.TwitterClient;
import com.github.redouane59.twitter.dto.user.User;
import com.github.redouane59.twitter.dto.user.UserV2;
import com.github.redouane59.twitter.signature.TwitterCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TwitterSubController {

    private static TwitterClient twitterClient;


    private SubChecker subChecker;

    public TwitterSubController() {
                twitterClient = new TwitterClient(TwitterCredentials.builder()
                .apiKey("Tcg6GaFBnqcwOAPXosVWn2XgF")
                .apiSecretKey("O6SzwVmXWY7FKwdL8tZ7YvIlvdyWOn28HSb1uGOKbQwk84NXFD")
                .accessToken("705592992-OMmVUI1ZS4QOwOGcXiXh1vMqzgaocPv2HY6EADfe")
                .accessTokenSecret("QjJ0IBvk8vgxObZDsQnHQyt4pnbsElKOv8OgsfU5ArjQy")
                .build());
    }

    @Autowired
    public void setSubChecker(SubChecker theSubChecker) {
        this.subChecker = theSubChecker;
    }

    @RequestMapping("/isFollowed/{userName}")
    @ResponseBody
    public boolean isFollowed(@PathVariable String userName) {
        // error handling
        if(userName != null) {
            UserV2 checkedUser = twitterClient.getUserFromUserName(userName);
            RelationType relationshipType = twitterClient.getRelationType(twitterClient.getUserIdFromAccessToken(), checkedUser.getId());
            return (relationshipType.equals(RelationType.FRIENDS) || relationshipType.equals(RelationType.FOLLOWING)) ? true : false;
        } else {
            // error handling
            return false;
        }
    }

    @RequestMapping("/follow/{userName}")
    @ResponseBody
    public String subscribeToUser(@PathVariable String userName) {
        // error handling
        if(userName != null) {
            UserV2 userToFollow = twitterClient.getUserFromUserName(userName);
            // if user exits
            return twitterClient.follow(userToFollow.getId()).toString();
        }
        else {
            return "";
        }
    }

    @RequestMapping("/unfollow/{userName}")
    @ResponseBody
    public String  unsubscribeUser(@PathVariable String userName) {
        // error handling
        if(userName != null) {
            UserV2 userToUnfollow = twitterClient.getUserFromUserName(userName);
            // if it exits
            return twitterClient.unfollow(userToUnfollow.getId()).toString();
        }
        else {
            return "";
        }
    }
}
