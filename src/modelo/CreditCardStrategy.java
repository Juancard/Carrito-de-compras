package taller7_pd_uml;


import java.util.Date;
public class CreditCardStrategy implements PaymentStrategy{
	private final String name;
	private final String cardNumber;
	private final String cvv;
	private final Date expires;

	public CreditCardPayment(String name, String cardNumber,String cvv, Date expires) {
		super();
		this.name = name;
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.expires = expires;
	}
	
	public String getName() {
		return name;
	}

	public String getCardNumber() {
		return cardNumber;
	}
	public String getCvv() {
		return cvv;
	}

	public Date getexpires() {
		return expires;
	}

	@Override
	public boolean pay(double amount) {
		return true; 
	}
}
