public class SecretSantaObject {
    String name;
    String email;
    String bio;
    int index;

    public SecretSantaObject(String name, String email, String bio) {
        this.name = name;
        this.email = email;
        this.bio = bio;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getBio() {
        return bio;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}
