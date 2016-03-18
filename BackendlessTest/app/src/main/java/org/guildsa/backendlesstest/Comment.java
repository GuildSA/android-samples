package org.guildsa.backendlesstest;

// Make sure to read up on the restrictions that apply to your data objects!
// https://backendless.com/documentation/data/android/data_data_object.htm

public class Comment {

    private String objectId;
    private String message;
    private String authorEmail;

    public Comment() {
    }

    public Comment(String message, String authorEmail) {
        this.message = message;
        this.authorEmail = authorEmail;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId( String objectId ) {
        this.objectId = objectId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }
}
