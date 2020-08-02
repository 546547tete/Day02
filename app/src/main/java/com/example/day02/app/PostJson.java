package com.example.day02.app;

import java.util.List;

public class PostJson {

    /**
     * status : 200
     * record : {"upgrade":false,"upgrade_version":0,"upgrade_url":""}
     * messages : {"succeed":["成功"]}
     */

    private int status;
    private RecordBean record;
    private MessagesBean messages;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public RecordBean getRecord() {
        return record;
    }

    public void setRecord(RecordBean record) {
        this.record = record;
    }

    public MessagesBean getMessages() {
        return messages;
    }

    public void setMessages(MessagesBean messages) {
        this.messages = messages;
    }


    public static class RecordBean {
        /**
         * upgrade : false
         * upgrade_version : 0
         * upgrade_url :
         */

        private boolean upgrade;
        private int upgrade_version;
        private String upgrade_url;

        public boolean isUpgrade() {
            return upgrade;
        }

        public void setUpgrade(boolean upgrade) {
            this.upgrade = upgrade;
        }

        public int getUpgrade_version() {
            return upgrade_version;
        }

        public void setUpgrade_version(int upgrade_version) {
            this.upgrade_version = upgrade_version;
        }

        public String getUpgrade_url() {
            return upgrade_url;
        }

        public void setUpgrade_url(String upgrade_url) {
            this.upgrade_url = upgrade_url;
        }

        @Override
        public String toString() {
            return "RecordBean{" +
                    "upgrade=" + upgrade +
                    ", upgrade_version=" + upgrade_version +
                    ", upgrade_url='" + upgrade_url + '\'' +
                    '}';
        }
    }

    public static class MessagesBean {
        private List<String> succeed;

        public List<String> getSucceed() {
            return succeed;
        }

        public void setSucceed(List<String> succeed) {
            this.succeed = succeed;
        }

        @Override
        public String toString() {
            return "MessagesBean{" +
                    "succeed=" + succeed +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PostJson{" +
                "status=" + status +
                ", record=" + record +
                ", messages=" + messages +
                '}';
    }
}
