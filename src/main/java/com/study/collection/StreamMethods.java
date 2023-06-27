package com.study.collection;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wenqianqian
 */
public class StreamMethods {
    public static void main(String[] args) {

        List<String> dataList = geneList();
        dataList.forEach( a ->
                System.out.println(a)
        );

    }

    /**
     * 根据指定条件生成List
     * @return
     */
    public static List<String> geneList(){
        List<DataDto> dataDtoList = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        map.put("2022-10","13.2");
        map.put("2022-11","15.2");
        map.put("2023-02","19.2");
        DataDto dataDto1 = new DataDto("13.2","2022-10");
        DataDto dataDto2 = new DataDto("15.2","2022-11");
        DataDto dataDto3 = new DataDto("19.2","2023-02");

        List<String> timeList = Arrays.asList("2022-09","2022-10","2022-11","2022-12","2023-01","2023-02","2023-03");
        List<String> dataList = timeList.stream().map( time ->
        {
            if (map.get(time) != null) {
                return map.get(time);
            }
            return null;
        }).collect(Collectors.toList());
        return dataList;
    }



}
