package main.java.com.ouibak.erp.gui;

public class Cookie {
    private static int franchiseeIdx;

    public static int getFranchiseeIdx() {
        return franchiseeIdx;
    }

    public static void setFranchiseeIdx(int franchiseeIdx) {
        Cookie.franchiseeIdx = franchiseeIdx;
    }
}
