package sainsburys.entity;

import com.google.gson.annotations.SerializedName;

public class RipeFruit {
	private final String title;
	private final String size;

	@SerializedName("unit_price")
	private final double unitPrice;
	private final String description;

	public RipeFruit(final String title,
			final String size,
			final double unitPrice,
			final String description) {
		this.title       = title;
		this.size        = size;
		this.unitPrice   = unitPrice;
		this.description = description;
	}
	
	
	public String getTitle() {
		return title;
	}


	public String getSize() {
		return size;
	}

	
	public double getUnitPrice() {
		return unitPrice;
	}


	public String getDescription() {
		return description;
	}
}
