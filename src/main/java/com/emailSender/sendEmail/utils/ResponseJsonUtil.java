package com.emailSender.sendEmail.utils;

import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class ResponseJsonUtil {

    private static final String STATUS = "status";
	/**
	 * Returns success JSON format
	 * 
	 * @return
	 *  {
            "status": "success"
        }
	 */
    public static Map<String, String> getSuccessResponseJson() {
        Map<String, String> responseBuilder = new HashMap<>();
        responseBuilder.put(STATUS,"success");
        return responseBuilder;
    }
    
	/**
	 * Returns success JSON format for given data
	 * 
	 * @param data - value for 'data' key
	 * @return
	 * {
            "status": "success",
            "data": {
                "content": 101
            }
        }
	 */
    public static Map<String, Object> getSuccessResponseJson(Object data) {
        Map<String, Object> responseBuilder = new HashMap<>();
        responseBuilder.put(STATUS,"success");
        responseBuilder.put("data", data);
        return responseBuilder;
    }

    /**
     *
     * @param apiError
     *  {
            "status": "BAD_REQUEST",
            "timestamp": "20-09-2017 08:13:53 AM",
            "message": "Request JSON found Empty",
            "debugMessage": "Json was not found Null, it's Should not be Empty! "
        }
     * @return
     *  {
            "error": {
                "status": "BAD_REQUEST",
                "timestamp": "20-09-2017 08:13:53 AM",
                "message": "Request JSON found Empty",
                "debugMessage": "Json was not found Null, it's Should not be Empty! "
            },
            "status": "failure"
        }
     */
    public static Map<String, Object> getFailedResponseJson(Object apiError) {
        Map<String, Object> responseBuilder = new HashMap<>();
        responseBuilder.put(STATUS, "failure");
        responseBuilder.put("error", apiError);
        return responseBuilder;
    }

}
