public class Passenger {
    //    private String pClass;
//    /* 1 2 3 */
//
//    private String survived;
//    /* 0 1 */
//
//    private String name;
//
//    private String sex;
//    /* female
//male make it into bool if we can
//we only can have 2 after all that how god wanted it to be
//*/
//    private float age;
//
//    private int siblingsSpousesAboard;
//    /* [ 0 8 ] */
//    /* Sibsp - Number of Siblings/Spouses Aboard */
//
//    private int parentsChildrenAboard;
//    /* 0 - 9 */
//    /* Parch - Number of Parents/Children Aboard */
//
//    private String ticket;
//
//    private float fare;
//
//    private String cabin;
//
//    private String embarked;
//
//    /* S - C - Q */
//    /* Embarked -   Port of    Embarkation(C =Cherbourg;Q=Queenstown;S=Southampton) */
//
//    private String boat;
//
//    private int bodyNumber;
//
//    /* Body number (if did not survive and body was recovered) */
//
//    private String address;
//    /* constructor */
//
//
//    /* getter  */
//    public String getpClass() {
//        return pClass;
//    }
//
//    public String isSurvived() {
//        return survived;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String isSex() {
//        return sex;
//    }
//
//    public float getAge() {
//        return age;
//    }
//
//    public int getSiblingsSpousesAboard() {
//        return siblingsSpousesAboard;
//    }
//
//    public int getparentsChildrenAboard() {
//        return parentsChildrenAboard;
//    }
//
//    public String getTicket() {
//        return ticket;
//    }
//
//    public float getFare() {
//        return fare;
//    }
//
//    public String getCabin() {
//        return cabin;
//    }
//
//    public String getEmbarked() {
//        return embarked;
//    }
//
//    public String getBoat() {
//        return boat;
//    }
//
//    public int getBody() {
//        return bodyNumber;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    @Override
//    public String toString() {
//        return "Passenger{" +
//                "his Class was=" + pClass +
//                ", survived=" + survived +
//                ", name='" + name + '\'' +
//                ", sex=" + sex +
//                ", age=" + age +
//                ", siblingsSpousesAboard=" + siblingsSpousesAboard +
//                ", parentsChildrenAboard=" + parentsChildrenAboard +
//                ", ticket='" + ticket + '\'' +
//                ", fare=" + fare +
//                ", cabin='" + cabin + '\'' +
//                ", embarked='" + embarked + '\'' +
//                ", boat='" + boat + '\'' +
//                ", body=" + bodyNumber +
//                ", Address='" + address + '\'' +
//                '}';
//    }
    private int pclass;
    private int survived;
    private String name;
    private String sex;
    private float age;
    private String sibsp;
    private String parch;
    private String ticket;
    private float fare;
    private String cabin;
    private String embarked;
    private String boat;
    private String body;
    private String home_dest;

    public int getPclass() {
        return pclass;
    }

    public int getSurvived() {
        return survived;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public float getAge() {
        return age;
    }

    public String getSibsp() {
        return sibsp;
    }

    public String getParch() {
        return parch;
    }

    public String getTicket() {
        return ticket;
    }

    public float getFare() {
        return fare;
    }

    public String getCabin() {
        return cabin;
    }

    public String getEmbarked() {
        return embarked;
    }

    public String getBoat() {
        return boat;
    }

    public String getBody() {
        return body;
    }

    public String getHome_dest() {
        return home_dest;
    }
}
