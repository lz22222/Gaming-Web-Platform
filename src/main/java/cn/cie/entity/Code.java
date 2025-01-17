package cn.cie.entity;

/**
 * Game activation code
 */
public class Code {

    /**
     * not use
     */
    public static final Byte STAT_NOT_USED = 0;
    /**
     * already use
     */
    public static final Byte STAT_USED = 1;

    private Integer id;

    private Integer item;

    private Integer uid;

    private String code;

    private Byte stat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Byte getStat() {
        return stat;
    }

    public void setStat(Byte stat) {
        this.stat = stat;
    }
}