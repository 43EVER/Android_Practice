package com.example.rubikscube_test.db;

import org.litepal.crud.LitePalSupport;

public class UserInformation extends LitePalSupport {
    private String Name;
    private String Id;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
