package com.SySTomateAlgo.TomateAlgo.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Arrays;

public class UpdatePropertiesUtil {

    public static String[] getNullPropertyName(Object source){
        BeanWrapper src = new BeanWrapperImpl(source);
        return Arrays.stream(src.getPropertyDescriptors())
                .map(pd -> pd.getName())
                .filter(name -> src.getPropertyValue(name) == null)
                .toArray(String[]::new);
    }

    public static void copyNonNullProperties(Object src, Object target){
        String[] ignore = getNullPropertyName(src);
        BeanUtils.copyProperties(src,target,ignore);
    }
}
