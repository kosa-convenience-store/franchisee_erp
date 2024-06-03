package main.java.com.ouibak.erp.domain.franchisee.dto;

public class FranchiseeInfoDto {
    private String franchiseeName;
    private String franchiseeAddress;
    private String franchiseeBusinessNum;
    private String franchiseeTel;

    public FranchiseeInfoDto(String franchiseeName, String franchiseeAddress, String franchiseeBusinessNum, String franchiseeTel) {
        this.franchiseeName = franchiseeName;
        this.franchiseeAddress = franchiseeAddress;
        this.franchiseeBusinessNum = franchiseeBusinessNum;
        this.franchiseeTel = franchiseeTel;
    }

    public String getFranchiseeName() {
        return franchiseeName;
    }

    public String getFranchiseeAddress() {
        return franchiseeAddress;
    }

    public String getFranchiseeBusinessNum() {
        return franchiseeBusinessNum;
    }

    public String getFranchiseeTel() {
        return franchiseeTel;
    }
}
