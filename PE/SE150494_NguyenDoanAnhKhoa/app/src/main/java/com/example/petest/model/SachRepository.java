package com.example.petest.model;

import com.example.petest.api.ApiClient;

public class SachRepository {

    public static SachService getSachService() {
        return ApiClient.getClient().create(SachService.class);
    }
}
