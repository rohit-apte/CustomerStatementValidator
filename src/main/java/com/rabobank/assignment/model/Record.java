package com.rabobank.assignment.model;

import java.math.BigDecimal;

public class Record {
    private long reference;
    private String accountNo;
    private String description;
    private BigDecimal startBalance;
    private BigDecimal mutation;
    private BigDecimal endBalance;

    public Record(long reference, String accountNo, String description, BigDecimal startBalance, BigDecimal mutation, BigDecimal endBalance) {
        this.reference = reference;
        this.accountNo = accountNo;
        this.description = description;
        this.startBalance = startBalance;
        this.mutation = mutation;
        this.endBalance = endBalance;
    }

    public Record() {
    }

    public long getReference() {
        return reference;
    }

    public void setReference(long reference) {
        this.reference = reference;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(BigDecimal startBalance) {
        this.startBalance = startBalance;
    }

    public BigDecimal getMutation() {
        return mutation;
    }

    public void setMutation(BigDecimal mutation) {
        this.mutation = mutation;
    }

    public BigDecimal getEndBalance() {
        return endBalance;
    }

    public void setEndBalance(BigDecimal endBalance) {
        this.endBalance = endBalance;
    }

    @Override
    public String toString() {
        return "Record:: reference="+this.reference+" AccountNo=" + this.accountNo + " description=" + this.description + " Start Balance=" + this.startBalance + " Mutation="+this.mutation +
                " End Balance=" + this.endBalance;
    }
}
