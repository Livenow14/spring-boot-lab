package com.livenow.springcore.di;

import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    public String sayGump() {
        return "Gump";
    }
}
