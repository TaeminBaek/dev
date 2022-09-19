package com.btem.dev.common.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class JsonResponse {
    private Map< String, Object> response = new HashMap<>();
    private boolean success = true;
    private String code = "PASS";
    private String message = "PASS";

    public JsonResponse setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public JsonResponse setCode(String code) {
        this.code = code;
        return this;
    }

    public JsonResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public JsonResponse addAttribute(String key, Object obj) {
        response.put(key, obj);
        return this;
    }

    public JsonResponse addMap(Map map) {
        response.putAll(map);
        return this;
    }

    public JsonResponse addAttributes(Object...obj) {
        for(int n = 0; n < obj.length; n++) response.put(String.valueOf(obj[n]), obj[++n]);
        return this;
    }

    public Map< String, Object> getResponseData() {
        addAttribute("success", success);
        addAttribute("code", code);
        addAttribute("message", message);
        return this.response;
    }

    public static JsonResponse newInstance() {return new JsonResponse();}

    public static Map asSuccess(){
        return JsonResponse.newInstance().setSuccess(true).getResponseData();
    }

    public static Map asSuccess(Object...obj){
        return JsonResponse.newInstance().setSuccess(true).addAttributes(obj).getResponseData();
    }

    public static Map asSuccess(Map map){
        return JsonResponse.newInstance().setSuccess(true).addMap(map).getResponseData();
    }

    public static Map asFailure(String message){
        return JsonResponse.newInstance().setSuccess(false).setMessage(message).getResponseData();
    }

    public static Map asFailure(Object...obj){
        return JsonResponse.newInstance().setSuccess(false).setCode("FAIL").setMessage("FAIL").addAttributes(obj).getResponseData();
    }

    public static Map asFailure(String code, String message){
        return JsonResponse.newInstance().setSuccess(false).setCode(code).setMessage(message).getResponseData();
    }

    public static Map asSuccessMsg(String code, String message) {
        return JsonResponse.newInstance().setSuccess(true).setCode(code).setMessage(message).getResponseData();
    }
}