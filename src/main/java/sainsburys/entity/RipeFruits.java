package sainsburys.entity;

import java.util.ArrayList;
import java.util.List;

public class RipeFruits {
	private List<RipeFruit> ripeFruitProducts = new ArrayList<RipeFruit>();
	private double          unitPriceTotal    = 0;
	
	public List<RipeFruit> getRipeFruitProducts() {
		return ripeFruitProducts;
	}
	
	
	public void setRipeFruitProducts(final List<RipeFruit> results) {
		this.ripeFruitProducts = results;
	}
	
	
	public double calculateUnitPriceTotal() {
		for (RipeFruit ripeFruit : ripeFruitProducts) {
			unitPriceTotal += ripeFruit.getUnitPrice();
		}
		
		return unitPriceTotal;
	}
}
