package acctMgr.model;

import java.math.BigDecimal;

public class OverdrawException extends Exception{
	public OverdrawException(BigDecimal amnt){
		super("\n	Overdraw Amount by: " + amnt + "\n   	AMOUNT NOT WITHDRAWN \n");
	}
}
