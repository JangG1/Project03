package com.FT.app.test;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FriendwithLombok {
    private String id;
    private String name;
    private Void getId;
    private Void getName;
    private Void setId;
    private Void setName;

    public FriendwithLombok(String id, String name) 
    {
		this.id = id;
        this.name = name;
	}

}