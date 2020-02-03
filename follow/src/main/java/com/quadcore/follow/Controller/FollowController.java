package com.quadcore.follow.Controller;

import com.quadcore.follow.Domain.Member;
import com.quadcore.follow.Repository.MemberRepository;
import com.quadcore.follow.Service.MemberService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController


public class FollowController {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;
    Logger logger = LoggerFactory.getLogger(FollowController.class);

    @PostMapping(path = "/follow/add")
    public Map<String, Object> follow(@RequestBody Map<String, String> m) {

        String member = m.get("username");
        String tweetMember = m.get("tweetUsername");
        logger.info(member + tweetMember);
        memberService.updateTweetMembers(member, tweetMember);
        Map<String, Object> map = new HashMap<>();
        map.put("errorCode", 10);
        return map;
    }


    @GetMapping(path="/follow/flist/{username}")
    public ArrayList<String> getall(@PathVariable("username") String member) {
        logger.info("member: " + member);
        return memberService.findMemberByUsername(member).getTweetMembers();
    }

}
