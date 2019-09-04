package domain.po;

import java.util.Objects;

public class Information {
    private int id;
    private String createTime;
    private String updateTime;
    private Byte delete;
    private String account;
    private String userName;
    private String passWord;
    private String email;
    private String salt;
    private Byte permission;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getDelete() {
        return delete;
    }

    public void setDelete(Byte delete) {
        this.delete = delete;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Byte getPermission() {
        return permission;
    }

    public void setPermission(Byte permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Information that = (Information) o;
        return id == that.id &&
                delete == that.delete &&
                permission == that.permission &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(account, that.account) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(passWord, that.passWord) &&
                Objects.equals(email, that.email) &&
                Objects.equals(salt, that.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, updateTime, delete, account, userName, passWord, email, salt, permission);
    }

    @Override
    public String toString() {
        return "Information{" +
                "id=" + id +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", delete=" + delete +
                ", account='" + account + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", email='" + email + '\'' +
                ", salt='" + salt + '\'' +
                ", permission=" + permission +
                '}';
    }
}
