package com.example.petest.model;

import com.example.petest.api.ApiClient;

public class TacgiaRepository {
    public static TacgiaService getTacgiaService() {
        return ApiClient.getClient().create(TacgiaService.class);
    }
}
