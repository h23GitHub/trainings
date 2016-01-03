package by.epam.grodno.uladzimir_stsiatsko.my_dao.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import by.epam.grodno.uladzimir_stsiatsko.my_dao.dao.BillDao;
import by.epam.grodno.uladzimir_stsiatsko.my_dao.model.Bill;

@Repository
public class BillDaoImpl implements BillDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void insert(Bill bill) {
		jdbcTemplate.update(
				"INSERT INTO bill (account_id, trip_list_id, payment_value, is_paid, billing_number, from_block, to_block, creation_date, currency_of_payment) VALUES (?,?,?,?,?,?,?,?,?);",
				bill.getAccountId(), bill.getTripListId(), bill.getPaymentValue(), bill.isPaid(),
				bill.getBillingNumber(), bill.getFromBlock(), bill.getToBlock(), bill.getCreationDate(), bill.getCurrencyOfPayment());
	}

	@Override
	public List<Double> getPriceElements(Bill bill) {
		Object[] args = { bill.getTripListId(), bill.getFromBlock(), bill.getToBlock() };
		return jdbcTemplate.query(
				"SELECT km_price*km summ FROM search_view v WHERE trip_id = ? AND block BETWEEN ? AND ? ;", args,
				new SingleColumnRowMapper<Double>());
	}

	@Override
	public int getBillingNumber(String currencyType) {
		try {
			Object[] args = { currencyType };
			Integer billingNumber = jdbcTemplate.queryForObject(
					"SELECT billing_number FROM bank_detail WHERE currency_of_payment = ? ;", Integer.class, args);
			return billingNumber;
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}
	
	public void setPaid(int id, boolean isPaid){
		jdbcTemplate.update(String.format("UPDATE bill SET is_paid = %s WHERE id = %s ;", isPaid, id));
	}
}
