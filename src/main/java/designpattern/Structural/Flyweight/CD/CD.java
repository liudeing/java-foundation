package designpattern.Structural.Flyweight.CD;

/**
 * 唱片CD运用Flyweight模式，歌唱家是可共享的，曲目，唱片日期是不共享的
 * Created by liur on 17-4-30.
 */
public class CD {
    private String title;
    private int year;
    private Artist artist;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
