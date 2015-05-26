package proglang.students.daphne;

import java.io.InputStream;
import java.net.URL;

public class DaphneProvider {
	
	private final URL url;
	
	public DaphneProvider(final URL url) {
		this.url = url;
	}
	
	public void login(String username, String password) {
		
	}
	
	public void logout() {
		
	}
	
	public InputStream getPage(String semester, String coursename) {
		return null;
	}
}
