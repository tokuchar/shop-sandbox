package com.oncors.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorityTest {

    @Test
    void testEquals() {
        Authority firstAuthority = Authority.builder()
                .authority(ShopAuthority.ADMIN).build();
        Authority secondAuthority = Authority.builder()
                .authority(ShopAuthority.ADMIN).build();

        assertEquals(firstAuthority, secondAuthority);
    }
}