package jp.co.world.storedevelopment.utils;

final public class PathUtils {

	public static String packagePath() {
		return Thread.currentThread().getContextClassLoader().getResource("").getPath();
	}

	public static String packagePath(String dir) {
		return packagePath() + dir + "/";
	}
}
