package entity;

import java.util.List;

public class UserVO {
    List<Career> careerList;
    List<Education> educationList;
    PersonInfo personInfo;
    String selfIntroduction;

    public UserVO() {}

    public UserVO(List<Career> careerList, List<Education> education, PersonInfo personInfo, String selfIntroduction) {
        this.careerList = careerList;
        this.educationList = education;
        this.personInfo = personInfo;
        this.selfIntroduction = selfIntroduction;
    }

    public List<Career> getCareerList() {
        return careerList;
    }

    public void setCareerList(List<Career> careerList) {
        this.careerList = careerList;
    }

    public List<Education> getEducationList() {
        return educationList;
    }

    public void setEducationList(List<Education> educationList) {
        this.educationList = educationList;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    public String getSelfIntroduction() {
        return selfIntroduction;
    }

    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
    }
}
