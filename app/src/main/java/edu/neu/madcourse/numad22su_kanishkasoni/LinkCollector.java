package edu.neu.madcourse.numad22su_kanishkasoni;

public class LinkCollector {

    private final String name;

    @Override
    public String toString() {
        return "LinkCollector{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    private final String url;

    /**
     * Constructs a linkCollector object with the specified name and url.
     *
     * @param name - name to be given to the person.
     * @param url -  url to be added.
     */
    public LinkCollector(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
