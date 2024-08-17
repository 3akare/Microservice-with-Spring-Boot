package com.microservice.loans.service.impl;

import com.microservice.loans.constant.LoansConstants;
import com.microservice.loans.dto.LoansDto;
import com.microservice.loans.entity.Loans;
import com.microservice.loans.exception.LoanAlreadyExistsException;
import com.microservice.loans.exception.ResourceNotFound;
import com.microservice.loans.mapper.LoanMapper;
import com.microservice.loans.repository.LoansRepository;
import com.microservice.loans.service.ILoansService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements ILoansService {
    private final LoansRepository loansRepository;

    /**
     * @param mobileNumber - String object
     */
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> loans = loansRepository.findByMobileNumber(mobileNumber);
        if(loans.isPresent())
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber "+ mobileNumber);
        loansRepository.save(createNewLoan(mobileNumber));
    }

    /**
     * @param mobileNumber - String object
     * @return LoansDto
     */
    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFound("Loans", "Mobile Number", mobileNumber)
        );
        return LoanMapper.MapToLoanDto(loans, new LoansDto());

    }

    /**
     * @param loansDto - LoansDto object
     * @return boolean
     */
    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loans = loansRepository.findByMobileNumber(loansDto.getMobileNumber()).orElseThrow(
                () -> new ResourceNotFound("Loans", "Mobile Number", loansDto.getMobileNumber())
        );

        loansRepository.save(LoanMapper.MapToLoan(loans, loansDto));
        return true;
    }

    /**
     * @param mobileNumber - String object
     * @return boolean
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFound("Loans", "Mobile Number", mobileNumber)
        );

        loansRepository.delete(loans);
        return true;
    }

    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new loan details
     */
    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }
}
