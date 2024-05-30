package main.java.com.ouibak.erp.domain.franchisee;

public class FranchiseeVO {
    private static int franchiseeId;
    private static String franchiseeName;
    private static String franchiseeLocation;
    private static String franchiseeBusinessNum;
    private static String franchiseeTel;
    private static int percetage;

    public static int getPercetage() {
        return percetage;
    }

    public static void setPercetage(int percetage) {
        FranchiseeVO.percetage = percetage;
    }

    public FranchiseeVO(String franchiseeName, String franchiseeLocation, String franchiseeBusinessNum, String franchiseeTel, int percetage) {
        this.franchiseeName = franchiseeName;
        this.franchiseeLocation = franchiseeLocation;
        this.franchiseeBusinessNum = franchiseeBusinessNum;
        this.franchiseeTel = franchiseeTel;
        this.percetage = percetage;
    }

    public static int getFranchiseeId() {
        return franchiseeId;
    }

    public static void setFranchiseeId(int franchiseeId) {
        FranchiseeVO.franchiseeId = franchiseeId;
    }

    public static String getFranchiseeName() {
        return franchiseeName;
    }

    public static void setFranchiseeName(String franchiseeName) {
        FranchiseeVO.franchiseeName = franchiseeName;
    }

    public static String getFranchiseeLocation() {
        return franchiseeLocation;
    }

    public static void setFranchiseeLocation(String franchiseeLocation) {
        FranchiseeVO.franchiseeLocation = franchiseeLocation;
    }

    public static String getFranchiseeBusinessNum() {
        return franchiseeBusinessNum;
    }

    public static void setFranchiseeBusinessNum(String franchiseeBusinessNum) {
        FranchiseeVO.franchiseeBusinessNum = franchiseeBusinessNum;
    }

    public static String getFranchiseeTel() {
        return franchiseeTel;
    }

    public static void setFranchiseeTel(String franchiseeTel) {
        FranchiseeVO.franchiseeTel = franchiseeTel;
    }
}
