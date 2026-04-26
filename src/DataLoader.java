import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataLoader {

	public static Map<String, List<String>> loadData(String rootPath) throws IOException {
		Map<String, List<String>> data = new HashMap<String, List<String>>();
		Path rootDir = Paths.get(rootPath);

		try (
			DirectoryStream<Path> languageDirs = Files.newDirectoryStream(rootDir);
		){
			for (Path languageDir : languageDirs) {
				if (!Files.isDirectory(languageDir)) {
					continue;
				}

				String languageName = languageDir.getFileName().toString();
				List<String> texts = new ArrayList<String>();

				try (
					DirectoryStream<Path> files = Files.newDirectoryStream(languageDir, "*.txt")
				) {
					for  (Path file : files) {
						String content = Files.readString(file);
						texts.add(content);
					}
				}

				data.put(languageName, texts);
			}
		}
		return data;
	}
}
