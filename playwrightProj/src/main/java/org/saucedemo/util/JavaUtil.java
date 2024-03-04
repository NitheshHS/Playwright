package org.saucedemo.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class JavaUtil {
    public List<Double> convertStringToDouble(List<String> list){
        return list.stream()
                .map(txt->Double.parseDouble(txt.replace("$","")))
                .toList();
    }
    public static String getSystemDate(){
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd_mm_yyyy_hh_MM_ss");
        return dateFormat.format(new Date());
    }
}
