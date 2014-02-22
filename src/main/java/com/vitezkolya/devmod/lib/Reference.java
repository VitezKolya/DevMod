
package com.vitezkolya.devmod.lib;

public class Reference {
	
	/* Debug Mod On-Off */
	public static final boolean DEBUG_MODE = true;
	
	public static final String MOD_ID = "devmod";
	public static final String MOD_NAME = "Dev Mod";
	public static final String CHANNEL_NAME = MOD_ID;
	public static final String VERSION = "@VERSION@ (build @BUILD_NUMBER@)";
	public static final String FINGERPRINT = "@FINGERPRINT@";
	public static final int BLOCKID_PREFIX = 930;
	public static final int SHIFTED_ID_RANGE_CORRECTION = 256;
	public static final int ITEMID_PREFIX = 10000;
	
	public static final int SECOND_IN_TICKS = 20;
	
	public static final String SERVER_PROXY_CLASS = "com.vitezkolya." + MOD_ID
			+ ".proxy.CommonProxy";
	public static final String CLIENT_PROXY_CLASS = "com.vitezkolya." + MOD_ID
			+ ".proxy.ClientProxy";
}
