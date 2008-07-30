
package net.sourceforge.filebot;


import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.List;
import java.util.regex.Pattern;

import net.sourceforge.tuned.FileUtil;


public class FileBotUtil {
	
	/**
	 * Invalid characters in filenames: \, /, :, *, ?, ", <, >, |, \r and \n
	 */
	public static final String INVALID_CHARACTERS = "\\/:*?\"<>|\r\n";
	public static final Pattern INVALID_CHARACTERS_PATTERN = Pattern.compile(String.format("[%s]+", Pattern.quote(INVALID_CHARACTERS)));
	
	
	/**
	 * Strip filename of invalid characters
	 * 
	 * @param filename original filename
	 * @return valid filename stripped of invalid characters
	 */
	public static String validateFileName(String filename) {
		// strip invalid characters from filename
		return INVALID_CHARACTERS_PATTERN.matcher(filename).replaceAll("");
	}
	

	public static boolean isInvalidFileName(String filename) {
		return INVALID_CHARACTERS_PATTERN.matcher(filename).find();
	}
	
	private static final String[] TORRENT_FILE_EXTENSIONS = { "torrent" };
	private static final String[] SFV_FILE_EXTENSIONS = { "sfv" };
	private static final String[] LIST_FILE_EXTENSIONS = { "txt", "list", "" };
	private static final String[] SUBTITLE_FILE_EXTENSIONS = { "srt", "sub", "ssa", "smi" };
	
	
	public static boolean containsOnlyFolders(List<File> files) {
		for (File file : files) {
			if (!file.isDirectory())
				return false;
		}
		
		return true;
	}
	

	public static boolean containsOnlyTorrentFiles(List<File> files) {
		return containsOnly(files, TORRENT_FILE_EXTENSIONS);
	}
	

	public static boolean containsOnlySfvFiles(List<File> files) {
		return containsOnly(files, SFV_FILE_EXTENSIONS);
	}
	

	public static boolean containsOnlyListFiles(List<File> files) {
		return containsOnly(files, LIST_FILE_EXTENSIONS);
	}
	

	public static boolean containsOnly(List<File> files, String... extensions) {
		for (File file : files) {
			if (!FileUtil.hasExtension(file, extensions))
				return false;
		}
		
		return true;
	}
	
	public static final FileFilter FOLDERS_ONLY = new FileFilter() {
		
		@Override
		public boolean accept(File file) {
			return file.isDirectory();
		}
		
	};
	
	public static final FileFilter FILES_ONLY = new FileFilter() {
		
		@Override
		public boolean accept(File file) {
			return file.isFile();
		}
		
	};
	
	public static final FilenameFilter SUBTITLES_ONLY = new FilenameFilter() {
		
		@Override
		public boolean accept(File dir, String name) {
			return FileUtil.hasExtension(name, SUBTITLE_FILE_EXTENSIONS);
		}
		
	};
	
	
	private FileBotUtil() {
		// hide constructor
	}
	
}
