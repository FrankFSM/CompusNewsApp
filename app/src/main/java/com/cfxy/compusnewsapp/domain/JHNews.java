package com.cfxy.compusnewsapp.domain;

import java.util.List;

/**
 * Created by ralap on 2017/5/8.
 */
public class JHNews {
    public int error_code;
    public String reason;

    public ResultData result;

    @Override
    public String toString() {
        return "JHNews{" +
                "error_code=" + error_code +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }

    public class ResultData{
        public String stat;
        public List<JHNewsData> data;

        @Override
        public String toString() {
            return "ResultData{" +
                    "stat='" + stat + '\'' +
                    ", data=" + data +
                    '}';
        }
    }


    public class JHNewsData{
        public String author_name;
        public String category;
        public String date;
        public String thumbnail_pic_s;
        public String thumbnail_pic_s02;
        public String thumbnail_pic_s03;
        public String title;
        public String uniquekey;
        public String url;

        @Override
        public String toString() {
            return "JHNewsData{" +
                    "author_name='" + author_name + '\'' +
                    ", category='" + category + '\'' +
                    ", date='" + date + '\'' +
                    ", thumbnail_pic_s02='" + thumbnail_pic_s02 + '\'' +
                    ", thumbnail_pic_s03='" + thumbnail_pic_s03 + '\'' +
                    ", title='" + title + '\'' +
                    ", uniquekey='" + uniquekey + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }
}
