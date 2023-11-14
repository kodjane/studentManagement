package com.kadConsulting.studentManagement.excetpion;

import java.util.Objects;

/*
 * Created By
 * @author Aime D. Kodjane
 */
public final class StudentErrorResponse {
    private int status;
    private String message;
    private long timeStamp;

    public StudentErrorResponse() {
    }

    public StudentErrorResponse(int status,
                                String message,
                                long timeStamp
    ) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public int status() {
        return status;
    }

    public String message() {
        return message;
    }

    public long timeStamp() {
        return timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (StudentErrorResponse) obj;
        return this.status == that.status &&
                Objects.equals(this.message, that.message) &&
                this.timeStamp == that.timeStamp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message, timeStamp);
    }

    @Override
    public String toString() {
        return "StudentErrorResponse[" +
                "status=" + status + ", " +
                "message=" + message + ", " +
                "timeStamp=" + timeStamp + ']';
    }

}
