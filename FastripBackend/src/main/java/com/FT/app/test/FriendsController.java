package com.FT.app.test;

import java.util.ArrayList;
import java.util.List;

import com.FT.app.test.FriendwithLombok;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/twitter")
public class FriendsController {
    private List<FriendwithLombok> friends;
    
    public FriendsController() {
        friends = new ArrayList<>();
        friends.add(new FriendwithLombok("nicoll", "Stepane Nicoll"));
        friends.add(new FriendwithLombok("woojung", "Jeon Woojung"));
    }

    @GetMapping("/")
    public List<FriendwithLombok> list() {
        return friends;
    }

    @GetMapping("/{id}")
    public FriendwithLombok get(@PathVariable String id) {
        return friends.stream().filter(f -> id.equals(f.getId())).findAny().orElse(null);
    }

    @PostMapping("/post")
    public String PostTest(@RequestBody String msg) {
    	System.out.println(msg);
        return "post success!!!"+msg;
    }
}