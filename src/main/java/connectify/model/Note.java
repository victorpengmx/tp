package connectify.model;

public class Note {
    private String content;

    public Note() {
    }

    public Note(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this
                || (obj instanceof Note
                && content.equals(((Note) obj).content));
    }

    @Override
    public int hashCode() {
        return this.content.hashCode();
    }
}
