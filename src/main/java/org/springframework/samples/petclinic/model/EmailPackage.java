package org.springframework.samples.petclinic.model;

// Model used to encapsulate the information needed to send an email to an owner on the website.
public class EmailPackage {
    private String senderEmail;
    private String receiverEmail;
    private String subject;
    private String message;

    public EmailPackage() {}

    public EmailPackage(String senderEmail, String receiverEmail, String subject, String message) {
        this.senderEmail = senderEmail;
        this.receiverEmail = receiverEmail;
        this.subject = subject;
        this.message = message;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
