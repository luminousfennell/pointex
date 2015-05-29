package proglang.students;

import java.io.IOException;

@FunctionalInterface
public interface IOSupplier<T> {
	T get() throws IOException;
}
