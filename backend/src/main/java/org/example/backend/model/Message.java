package org.example.backend.model;

import java.util.Objects;

public class Message {

    private String content;
    private String buttonName;

    public Message() {}

    public Message(String content, String buttonName) {
        this.content = content;
        this.buttonName = buttonName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(content, message.content) &&
                Objects.equals(buttonName, message.buttonName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, buttonName);
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", buttonName='" + buttonName + '\'' +
                '}';
    }
}
