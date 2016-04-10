
package eg.edu.guc.yugioh.exceptions;

public class UnknownCardTypeException extends UnexpectedFormatException{
	
	private String unknownType;
	
	public UnknownCardTypeException(String sf, int sl, String u){
		super(sf,sl);
		this.unknownType=u;
	}

	public String getUnknownType() {
		return unknownType;
	}

	public void setUnknownType(String unknownType) {
		this.unknownType = unknownType;
	}
	
}
