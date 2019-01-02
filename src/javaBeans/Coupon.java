package javaBeans;

import java.util.Date;

import exeptions.GeneralException;

public class Coupon extends PropertiesA{
	
	//-------------------properties------------------
	
	
	private int companyId;
	private Category category;
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private int amount;
	private double price;
	private String image;

//-------------------setter+getter------------------


	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) throws GeneralException {
		if (companyId<0) {
			throw new GeneralException("Company Id must be bigger than 0");
		}
		else{
			this.companyId=companyId;
		}
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) throws GeneralException {
		if (title.length()<4 || title.length()>40) {
			throw new GeneralException("Title must be 4-40 letters");
		}
		else{
			this.title=title;
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) throws GeneralException {
		if (description.length()<10 || description.length()>250) {
			throw new GeneralException("Title must be 10-250 letters");
		}
		else{
			this.description=description;
		}
	}
//check dates validations
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) throws GeneralException {
		if (amount<1) {
			throw new GeneralException("You must add at least 1 coupon");
		}
		else{ 
			this.amount = amount;
		}
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(double price) throws GeneralException {
		if (price<1) {
			throw new GeneralException("Coupon price must be more than 0");
		}
		else{ 
			this.price = price;
		}
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	// -------------------constructors------------------

	public Coupon(int id, int companyId, Category category, String title, String description, Date startDate,
			Date endDate, int amount, double price, String image) throws GeneralException {

		super(id);
		setCompanyId(companyId);
		setCategory(category);
		setTitle(title);
		setDescription(description);
		setStartDate(startDate);
		setEndDate(endDate);
		setAmount(amount);
		setPrice(price);
		setImage(image);
	}


	
	// -------------------methods-----------------
	
	@Override
	public String toString() {
		return "Customer [" +super.toString() + ", companyId=" + getCompanyId() + ", category=" + getCategory() + ", title=" + getTitle()
				+ ", description=" + getDescription() + ", startDate=" + getStartDate() + ", endDate=" + getEndDate() + ", amount="
				+ getAmount() + ", price=" + getPrice() + ", image=" + getImage() + "]";
	}

}
