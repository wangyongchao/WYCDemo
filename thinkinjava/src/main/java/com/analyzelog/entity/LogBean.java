package com.analyzelog.entity;

import java.util.List;
import java.util.Objects;

public class LogBean {
    public String event_date;
    public String event_timestamp;//事件发生时间
    public String event_name;//事件名称
    public List<Params> event_params;
    public String user_id;
    public String event_server_timestamp_offset;
    public Device device;
    public Geo geo;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogBean logBean = (LogBean) o;
        return event_timestamp.equals(logBean.event_timestamp);
    }



    @Override
    public int hashCode() {
        return Objects.hash(event_timestamp);
    }



    public class Params {
        public String key;
        public ValueParams value;

        public class ValueParams {
            public String string_value;
//            public int int_value;
//            public float float_value;
//            public double double_value;
        }


    }

    public class Device {
        public String mobile_brand_name;
        public String mobile_model_name;
        public String mobile_marketing_name;
        public String mobile_os_hardware_model;
        public String operating_system;
        public String operating_system_version;
        public String vendor_id;
        public String advertising_id;
        public String language;
        public String time_zone_offset_seconds;
        public String browser;
        public String browser_version;

    }

    public class Geo {
        public String continent;
        public String country;
        public String region;
        public String city;

    }

    public class AppInfo {
        public String id;
        public String version;
        public String install_store;
        public String firebase_app_id;
        public String install_source;

    }


    @Override
    public String toString() {
        return super.toString();
    }
}
