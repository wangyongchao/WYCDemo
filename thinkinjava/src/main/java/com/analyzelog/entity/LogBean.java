package com.analyzelog.entity;

public class LogBean {
    private String userid;
    private String pageid;
    private String sessid;
    private String clits;
    private String appid;
    private String devid;
    private String clientid;
    private Data data;
    private String originalJsonStr;

    public String getOriginalJsonStr() {
        return originalJsonStr;
    }

    public void setOriginalJsonStr(String originalJsonStr) {
        this.originalJsonStr = originalJsonStr;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPageid() {
        return pageid;
    }

    public void setPageid(String pageid) {
        this.pageid = pageid;
    }

    public String getSessid() {
        return sessid;
    }

    public void setSessid(String sessid) {
        this.sessid = sessid;
    }

    public String getClits() {
        return clits;
    }

    public void setClits(String clits) {
        this.clits = clits;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getDevid() {
        return devid;
    }

    public void setDevid(String devid) {
        this.devid = devid;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {
        private String version;
        private String systemVersion;
        private String date;//日期
        private String devicename;
        private String usinglogindex;
        private String eventid;
        private String creattime;
        private String liveid;//场次id
        private String teacherId;
        private String attachment;
        private String prepagename;
        private String liveeventid;//直播中的eventid 都是 live_debug_message,别的日志应该都是tag
        private String tag;
        private String enterTime;
        private String logindex;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getSystemVersion() {
            return systemVersion;
        }

        public void setSystemVersion(String systemVersion) {
            this.systemVersion = systemVersion;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDevicename() {
            return devicename;
        }

        public void setDevicename(String devicename) {
            this.devicename = devicename;
        }

        public String getUsinglogindex() {
            return usinglogindex;
        }

        public void setUsinglogindex(String usinglogindex) {
            this.usinglogindex = usinglogindex;
        }

        public String getEventid() {
            return eventid;
        }

        public void setEventid(String eventid) {
            this.eventid = eventid;
        }

        public String getCreattime() {
            return creattime;
        }

        public void setCreattime(String creattime) {
            this.creattime = creattime;
        }

        public String getLiveid() {
            return liveid;
        }

        public void setLiveid(String liveid) {
            this.liveid = liveid;
        }

        public String getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(String teacherId) {
            this.teacherId = teacherId;
        }

        public String getAttachment() {
            return attachment;
        }

        public void setAttachment(String attachment) {
            this.attachment = attachment;
        }

        public String getPrepagename() {
            return prepagename;
        }

        public void setPrepagename(String prepagename) {
            this.prepagename = prepagename;
        }

        public String getLiveeventid() {
            return liveeventid;
        }

        public void setLiveeventid(String liveeventid) {
            this.liveeventid = liveeventid;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getEnterTime() {
            return enterTime;
        }

        public void setEnterTime(String enterTime) {
            this.enterTime = enterTime;
        }

        public String getLogindex() {
            return logindex;
        }

        public void setLogindex(String logindex) {
            this.logindex = logindex;
        }
    }

    @Override
    public String toString() {
        String s = "";
        if (data != null) {
            s = "[date:" + data.getDate() + ",userid:" + userid + ",eventid:" + data.getEventid()
                    + ",tag:" + data.getTag() + "]";
        }
        return s;
    }
}
