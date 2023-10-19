package com.techforb.unicomerbackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techforb.unicomerbackend.model.UserTransfer;
import com.techforb.unicomerbackend.model.enums.TransactionType;

@Service
public class BalanceService {

	public static Double getBalance(List<UserTransfer> userTransfers) {
		Double 
		
		positiveBalance = userTransfers.stream().filter(x -> validateTransacTionType(x))
				.mapToDouble(x -> x.getTrasnferAmount().doubleValue()).sum(),
		
		negativeBalance = userTransfers.stream().filter(x -> !validateTransacTionType(x))
				.mapToDouble(x -> x.getTrasnferAmount().doubleValue()).sum();
		
		return positiveBalance - negativeBalance;
	}

	private static boolean validateTransacTionType(UserTransfer userTransfer) {
		return userTransfer.getTransactionType().equals(TransactionType.DEPOSIT)
				|| userTransfer.getTransactionType().equals(TransactionType.TRANSFER_RECEIVED);
	}

}
