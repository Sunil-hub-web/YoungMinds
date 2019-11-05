package com.egk.egk;

public class Transaction_list_item {

    String id, transaction_type, package_id, package_duration, transaction_amount, transaction_id, transaction_datetime, transaction_status;


    public Transaction_list_item(String id, String transaction_type, String package_id, String package_duration, String transaction_amount, String transaction_id, String transaction_datetime, String transaction_status) {
        this.id = id;
        this.transaction_type = transaction_type;
        this.package_id = package_id;
        this.package_duration = package_duration;
        this.transaction_amount = transaction_amount;
        this.transaction_id = transaction_id;
        this.transaction_datetime = transaction_datetime;
        this.transaction_status = transaction_status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getPackage_id() {
        return package_id;
    }

    public void setPackage_id(String package_id) {
        this.package_id = package_id;
    }

    public String getPackage_duration() {
        return package_duration;
    }

    public void setPackage_duration(String package_duration) {
        this.package_duration = package_duration;
    }

    public String getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(String transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getTransaction_datetime() {
        return transaction_datetime;
    }

    public void setTransaction_datetime(String transaction_datetime) {
        this.transaction_datetime = transaction_datetime;
    }

    public String getTransaction_status() {
        return transaction_status;
    }

    public void setTransaction_status(String transaction_status) {
        this.transaction_status = transaction_status;
    }
}
