package javaBeans;

import exeptions.GeneralException;

public abstract class PropertiesA {

	//-------------------properties------------------
	
	private int id;
	
	//-------------------setter+getter------------------
	
	public int getId() {
		return id;
	}

	public void setId(int id) throws GeneralException {
		if (id<0) {
			throw new GeneralException("Id must be bigger than 0");
		}
		else{
			this.id=id;
		}
	}

	// -------------------constructor------------------
	
	public PropertiesA(int id) throws GeneralException {
		super();
		setId(id);
	}
	
	// -------------------methods-----------------
	
	@Override
	public String toString() {
		return "id=" + getId();
	}

	
	
	
}
