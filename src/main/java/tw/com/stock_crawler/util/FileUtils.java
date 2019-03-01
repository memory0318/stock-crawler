package tw.com.stock_crawler.util;

import java.io.File;
import java.io.IOException;

public class FileUtils {

	public static File createTempDir(String prefix, String suffix) throws IOException {
		final File temp = File.createTempFile(prefix, suffix);

		if (!temp.delete()) {
			throw new IOException("Could not delete temp file: " + temp.getAbsolutePath());
		}

		if (!temp.mkdir()) {
			throw new IOException("Could not create temp directory: " + temp.getAbsolutePath());
		}

		return temp;
	}
}
