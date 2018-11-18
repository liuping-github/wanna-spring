package org.code4everything.springbee.service;

import org.code4everything.springbee.domain.Income;
import org.code4everything.springbee.model.IncomeDTO;
import org.code4everything.springbee.model.IncomeMonthVO;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author pantao
 * @since 2018/9/20
 */
public interface IncomeService {

    Long getAssetBalance(String userId);

    ArrayList<Income> listIncome(String userId, String category, Date start, Date end);

    ArrayList<IncomeMonthVO> listMonth(String userId, String startMonth, String endMonth);

    Income updateIncome(String userId, String incomeId, IncomeDTO incomeDTO);

    void remove(String userId, String incomeId);

    Income saveIncome(String userId, IncomeDTO incomeDTO);
}
