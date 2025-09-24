// Recursively lists all files in a directory
package nat471exe;
import java.io.File;

public class RecursivelyListFilesInDirectory {

	public static void main(String[] args) {
		File folder = new File("/home/user/Documents");
		listRecursive(folder, 0);
	}

	static void listRecursive(File folder, int depth) {
		if (!folder.exists())  {
			System.out.println("Not found:" + folder.getPath());
			return;
		}
		
		String indent = " ".repeat(depth);
		if (folder.isDirectory()) {
			System.out.println(indent + "[DIR] " + folder.getName());
			
			File[] items = folder.listFiles();
			
			if (items == null || items.length == 0) {
				System.out.print(indent + "(empty)");
				return;
			}
			
			for (File f : items) {
				listRecursive(f, depth + 1);
			}
		} else {
			System.out.println(indent + "File: " + folder.getName());
		}
	}
}
