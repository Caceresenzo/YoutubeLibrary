package caceresenzo.libs.youtube.common;

public class InvalidKindException extends IllegalArgumentException {
	
	public InvalidKindException(Kindable supposedKind, String actualKind) {
		this(supposedKind.getItemKind(), actualKind);
	}
	
	public InvalidKindException(String supposedKind, String actualKind) {
		super(String.format("Kind \"%s\" can't be processed with kind \"%s\"", supposedKind, actualKind));
	}
	
}