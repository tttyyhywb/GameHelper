package kevin.mygamehelper.data.utils;

/**
 * Created by Kevin on 2016/2/23.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class RecordItem {

    private int heroId;
    private float record;
    private String name;
    private boolean ifWin;

    public RecordItem(int heroId, boolean ifWin, String name, float record) {
        this.heroId = heroId;
        this.ifWin = ifWin;
        this.name = name;
        this.record = record;
    }

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    public boolean isIfWin() {
        return ifWin;
    }

    public void setIfWin(boolean ifWin) {
        this.ifWin = ifWin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRecord() {
        return record;
    }

    public void setRecord(float record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "RecordItem{" +
                "heroId=" + heroId +
                ", record=" + record +
                ", name='" + name + '\'' +
                ", ifWin=" + ifWin +
                '}';
    }
}
