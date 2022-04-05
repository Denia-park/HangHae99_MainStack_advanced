package com.sparta.springadvanced_hh99homework.model;

public enum UserRoleEnum {
    USER(Authority.USER),  // 사용자 권한
    STORE_OWNER(Authority.STORE_OWNER);  // 관리자 권한

    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String USER = "ROLE_USER"; //ROLE이 앞에 있어야함
        public static final String STORE_OWNER = "ROLE_STORE_OWNER"; //ROLE이 앞에 있어야함
    }
}
