package com.scc.model;

public record User(int ID, String Username, String Password, String Roles, String Status) {
    @Override
    public String toString(){
        return String.format("[%d] %s (%s) - Status: %s", ID, Username, Roles, Status);
    }
}
