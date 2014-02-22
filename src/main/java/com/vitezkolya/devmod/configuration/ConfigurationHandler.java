
package com.vitezkolya.devmod.configuration;

import java.io.File;

public class ConfigurationHandler {
	
	public static void init(String configPath) {
	
		BlockConfiguration.init(new File(configPath + "block.properties"));
		
	}
	
}
