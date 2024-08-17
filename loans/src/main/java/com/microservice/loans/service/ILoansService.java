package com.microservice.loans.service;

import com.microservice.loans.dto.LoansDto;

public interface ILoansService {
    /**
     * @param mobileNumber - String object
     */
    void createLoan(String mobileNumber);

    /**
     * @param mobileNumber - String object
     * @return loansDto
     */
    LoansDto fetchLoan(String mobileNumber);

    /**
     * @param loansDto - LoansDto object
     * @return boolean
     */
    boolean updateLoan(LoansDto loansDto);

    /**
     * @param loansDto - LoansDto object
     * @return boolean
     */
    boolean deleteLoan(String mobileNumber);
}
