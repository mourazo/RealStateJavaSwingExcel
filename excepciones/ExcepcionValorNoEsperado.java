package excepciones;

public class ExcepcionValorNoEsperado extends Exception {

	private static final long serialVersionUID = 1L;

	private String receivedValue;
	private Class<?> expectedClass;

	public String getReceivedValue() {
		return receivedValue;
	}

	public Class<?> getExpectedClass() {
		return expectedClass;
	}

	public ExcepcionValorNoEsperado(Class<?> expectedClass, String receivedValue) {

		this.expectedClass = expectedClass;
		this.receivedValue = receivedValue;

	}

	
}


