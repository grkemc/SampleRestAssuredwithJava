package com.codeway.apiClass.model;

import java.util.HashMap;
import java.util.Map;

public class RequestMaps {

    public Map<String, Object> getUserListMap() {
        Map<String, Object> byGetParams = new HashMap<>();
        byGetParams.put("page", "1");
        return byGetParams;
    }
}
