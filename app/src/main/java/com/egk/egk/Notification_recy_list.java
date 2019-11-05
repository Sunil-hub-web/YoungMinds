package com.egk.egk;

public class Notification_recy_list {
    String Notificationid;
    String NotificationDesc;

    public String getNotificationid() {
        return Notificationid;
    }

    public void setNotificationid(String notificationid) {
        Notificationid = notificationid;
    }

    public String getNotificationDesc() {
        return NotificationDesc;
    }

    public void setNotificationDesc(String notificationDesc) {
        NotificationDesc = notificationDesc;
    }

    public Notification_recy_list(String notificationid, String notificationDesc) {
        Notificationid = notificationid;
        NotificationDesc = notificationDesc;
    }
}



