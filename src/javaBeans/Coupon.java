package javaBeans;

import java.util.Date;

import exeptions.GeneralException;

/**
 * Coupon is a Class that creates the Coupon and Extends PropertiesA
 * 
 * @author Dan
 */
public class Coupon extends PropertiesA {

	// -------------------Properties------------------

	private int companyId;
	private Category category;
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private int amount;
	private double price;
	private String image;

	// -------------------Constructor------------------

	/**
	 * Constructor for the Coupon class that gets id, companyId, category, title,
	 * description, startDate, endDate, amount, price and a path to image
	 * 
	 * @param id          gets a coupon ID
	 * @param companyId   gets a coupon companyId
	 * @param category    gets a coupon category
	 * @param title       gets a coupon title
	 * @param description gets a coupon description
	 * @param startDate   gets a coupon startDate
	 * @param endDate     gets a coupon endDate
	 * @param amount      gets a coupon amount
	 * @param price       gets a coupon price
	 * @param image       gets a coupon image
	 * @throws GeneralException can throw GeneralException
	 */
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

	// -------------------setters+getters------------------

	/**
	 * getCompanyId() method that gets the coupon companyId
	 * 
	 * @return returns the coupon companyId
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * setCompanyId() Method that sets the coupon company ID if it is valid must be
	 * more than 0
	 * 
	 * @param companyId coupon company Id to set
	 * @throws GeneralException can throw GeneralException
	 */
	public void setCompanyId(int companyId) throws GeneralException {
		if (companyId < 0) {
			throw new GeneralException("Company Id must be bigger than 0");
		} else {
			this.companyId = companyId;
		}
	}

	/**
	 * getCategory() method that gets the coupon category
	 * 
	 * @return returns the coupon category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * setCategory() Method that sets the coupon category
	 *  
	 * @param category the category to set
	 * @throws GeneralException can throw GeneralException
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * getTitle() method that gets the coupon title
	 * 
	 * @return returns the coupon title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * setTitle() Method that sets the coupon title if it is valid must be between
	 * 4-40 letters
	 * 
	 * @param title coupon title to set
	 * @throws GeneralException can throw GeneralException
	 */
	public void setTitle(String title) throws GeneralException {
		if (title.length() < 4 || title.length() > 40) {
			throw new GeneralException("Title must be 4-40 letters");
		} else {
			this.title = title;
		}
	}

	/**
	 * getDescription() method that gets the coupon description
	 * 
	 * @return returns the coupon description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * setDescription() Method that sets the coupon Description if it is valid must
	 * be between 10-250 letters
	 * 
	 * @param description coupon description to set
	 * @throws GeneralException can throw GeneralException
	 */
	public void setDescription(String description) throws GeneralException {
		if (description.length() < 10 || description.length() > 250) {
			throw new GeneralException("Title must be 10-250 letters");
		} else {
			this.description = description;
		}
	}

	/**
	 * getStartDate() method that gets the coupon startDate
	 * 
	 * @return returns the coupon startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * setStartDate() Method that sets the coupon startDate
	 * 
	 * @param startDate coupon startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * getEndDate() method that gets the coupon endDate
	 * 
	 * @return returns the coupon endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * setEndDate() Method that sets the coupon endDate if it is valid must be after
	 * current date and after the coupon startDate
	 * 
	 * @param endDate coupon endDate to set
	 * @throws GeneralException can throw GeneralException
	 */
	public void setEndDate(Date endDate) throws GeneralException {
		Date thisDate = new Date();
		if (endDate.after(thisDate) && endDate.after(startDate)) {
			this.endDate = endDate;
		} else {
			throw new GeneralException("End date of coupon must be after start date and not before today!");
		}
	}

	/**
	 * getAmount() method that gets the coupon amount
	 * 
	 * @return returns the coupon amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * setAmount() Method that sets the coupon amount if it is valid must be at
	 * least 1
	 * 
	 * @param amount coupon amount to set
	 * @throws GeneralException can throw GeneralException
	 */
	public void setAmount(int amount) throws GeneralException {
		if (amount < 1) {
			throw new GeneralException("You must add at least 1 coupon");
		} else {
			this.amount = amount;
		}
	}

	/**
	 * getPrice() method that gets the coupon price
	 * 
	 * @return returns the coupon price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * setPrice() Method that sets the coupon price if it is valid must be more than
	 * 0
	 * 
	 * @param price coupon price to set
	 * @throws GeneralException can throw GeneralException
	 */
	public void setPrice(double price) throws GeneralException {
		if (price < 1) {
			throw new GeneralException("Coupon price must be more than 0");
		} else {
			this.price = price;
		}
	}

	/**
	 * getImage() method that gets the coupon image path
	 * 
	 * @return returns the coupon image path
	 */
	public String getImage() {
		return image;
	}

	/**
	 * setImage() Method that sets the coupon image path
	 * 
	 * @param image coupon image path to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	// -------------------Methods-----------------

	/**
	 * toString() Override the toString method to return the coupon attributes
	 * 
	 * @return returns a string of all coupon attributes
	 */
	@Override
	public String toString() {
		return "Coupon " + super.toString() + ", companyId=" + getCompanyId() + ", category=" + getCategory()
				+ ", title=" + getTitle() + ", description=" + getDescription() + ", startDate=" + getStartDate()
				+ ", endDate=" + getEndDate() + ", amount=" + getAmount() + ", price=" + getPrice() + ", image="
				+ getImage() + "\n";
	}

}
