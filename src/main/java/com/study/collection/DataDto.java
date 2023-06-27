package com.study.collection;

/**
 * @author wenqianqian
 */
public class DataDto {

    private String timeDim;

    private String data;

    public DataDto(String data, String timeDim) {
        this.data = data;
        this.timeDim = timeDim;
    }

    public String getTimeDim() {
        return timeDim;
    }

    public void setTimeDim(String timeDim) {
        this.timeDim = timeDim;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


}
