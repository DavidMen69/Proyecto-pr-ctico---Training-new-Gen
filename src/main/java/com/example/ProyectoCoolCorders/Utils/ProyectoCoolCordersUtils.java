package com.example.ProyectoCoolCorders.Utils;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ProyectoCoolCordersUtils {
    public static boolean validateQuery(String query){
        if (query == null || query.trim().isEmpty()){
            return true;

        }
        else{
            return false;
        }
    }
}
