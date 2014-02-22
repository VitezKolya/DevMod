
package com.vitezkolya.devmod.tile;

public interface IMultiBlock {
	
	/**
	 * Gets the master block's world coords
	 * 
	 * @return x,y,z coords
	 */
	public int[] getCoreTEWorldCoords();
	
	/**
	 * Gets the master block's local coords
	 * 
	 * @return x,y,z coords
	 */
	public int[] getCoreTELocalCoords();
	
	/**
	 * Return's isValid variable
	 * 
	 * @return
	 */
	public boolean isValid();
	
	/**
	 * Invalidates the multiblock structure.
	 */
	public void invalidateMultiblock();
	
	/**
	 * Checks surrounding blocks to multiblock pattern
	 * 
	 * @return If matches return true else false
	 */
	public boolean isValidStructure();
	
	/**
	 * 
	 */
	public void buildStructure();
	
	/**
	 * 
	 */
	public void revertStructure();
	
	/**
	 * 
	 */
	public void breakStructure();
	
}
