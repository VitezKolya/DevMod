
package com.vitezkolya.devmod.tile;

import java.util.HashMap;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import com.vitezkolya.devmod.lib.Reference;

public class MBCore extends TileBasic implements IMultiBlock {
	
	/**
	 * The X and Y are switch places in this pattern. This is for making the pattern
	 * easier to read.
	 */
	public String[][][] pattern;
	public String coreTEID;
	public HashMap<String, ItemStack> blocksOfPattern = new HashMap<String, ItemStack>();
	protected ItemStack coreBlock;
	protected ItemStack ghostBlock;
	protected int rotation;
	protected int maxWidth;
	protected int maxDepth;
	protected int maxHeight;
	protected boolean isValid = false;
	
	public MBCore() {
	
	}
	
	public void setMaxdimensions() {
	
		maxWidth = pattern[0].length;
		maxDepth = pattern.length;
		maxHeight = pattern[0][0].length;
	}
	
	/**
	 * If multiblock structure is broken do you want it to break the whole multiblock?
	 * This will also include dropping all broken blocks.
	 */
	public boolean dropPatternBlocks = true;
	
	public void setPattern(String[][][] pattern, String[] blocksOfPatternString,
			ItemStack[] blocksOfPatternItemStack) throws Exception {
	
		if(blocksOfPatternString.length != blocksOfPatternItemStack.length) {
			
			throw new Exception();
		}
		
		this.pattern = pattern;
	}
	
	@Override
	public int[] getCoreTEWorldCoords() {
	
		return new int[] {
				xCoord, yCoord, zCoord
		};
	}
	
	public static String[][][] rotate(int rotation, String[][][] pattern) {
	
		rotation %= 4;
		
		if(rotation == 0) {
			return pattern;
		}
		
		String[][][] temp = new String[pattern.length][pattern[0].length][pattern[0][0].length];
		
		for(int y = 0; y < pattern.length; y++) {
			
			for(int x = 0; x < pattern[y].length; x++) {
				
				for(int z = 0; z < pattern[y][x].length; z++) {
					
					temp[y][pattern[y][x].length - 1 - z][x] = pattern[y][x][z];
				}
			}
		}
		
		return rotate(rotation - 1, temp);
	}
	
	/**
	 * If core is found returns array else null
	 * 
	 * @return y, x, z
	 */
	@Override
	public int[] getCoreTELocalCoords() {
	
		for(int y = 0; y < pattern.length; y++) {
			
			for(int x = 0; x < pattern[y].length; x++) {
				
				for(int z = 0; z < pattern[x][x].length; z++) {
					
					if(coreTEID.equals(pattern[y][x][z])) {
						
						return new int[] {
								x, y, z
						};
						
					}
				}
			}
		}
		
		return null;
	}
	
	@Override
	public void invalidateMultiblock() {
	
		if(!dropPatternBlocks) {
			
			revertStructure();
		} else {
			breakStructure();
		}
		
		isValid = false;
	}
	
	@Override
	public boolean isValid() {
	
		return isValid;
	}
	
	@Override
	public boolean isValidStructure() {
	
		int[] core = getCoreTELocalCoords();
		
		if(core == null) {
			return false;
		}
		
		// Searches the X coords for pattern
		rot: for(int r = 0; r < 4; r++) {
			
			System.out.println("Orientation: " + orientation.ordinal());
			System.out.println("Roatation: " + r);
			
			pattern = rotate(r, pattern);
			
			for(int x = (-core[0]); x <= (maxWidth - core[0]) - 1; x++) {
				
				// Searches the Z coords for pattern
				
				for(int z = (-core[2]); z <= (maxDepth - core[2]) - 1; z++) {
					
					// Searches the Y coords for pattern
					for(int y = (-core[1]); y <= (maxHeight - core[1]) - 1; y++) {
						
						int id = worldObj.getBlockId(x + xCoord, y + yCoord, z + zCoord);
						int meta = worldObj.getBlockMetadata(x + xCoord, y + yCoord, z
								+ zCoord);
						
						if(id == getPatternItem(x + core[0], y + core[1], z + core[2]).itemID
								&& meta == getPatternItem(x + core[0], y + core[1],
										z + core[2]).getItemDamage()) {
							System.out.println("Found Correct Block");
							continue;
						} else {
							System.out.println("Found Incorrect Block");
							continue rot;
						}
					}
				}
			}
			
			rotation = r;
			return true;
		}
		
		return false;
	}
	
	@Override
	public void buildStructure() {
	
		int[] core = getCoreTELocalCoords();
		
		pattern = rotate(rotation, pattern);
		
		for(int x = (-core[0]); x < (maxWidth - core[0]); x++) {
			
			for(int z = (-core[2]); z < (maxDepth - core[2]); z++) {
				
				for(int y = (-core[1]); y < (maxHeight - core[1]); y++) {
					
					int blockId = worldObj.getBlockId(x + xCoord, y + yCoord, z + zCoord);
					
					if(blockId == 0 || blockId == coreBlock.itemID) {
						continue;
					}
					
					worldObj.setBlock(x + xCoord, y + yCoord, z + zCoord,
							ghostBlock.itemID, ghostBlock.getItemDamage(), 0);
					worldObj.markBlockForUpdate(x + xCoord, y + yCoord, z + zCoord);
					
					MBGhost tileGhost = (MBGhost) worldObj.getBlockTileEntity(x + xCoord,
							y + yCoord, z + zCoord);
					
					if(tileGhost instanceof MBGhost && tileGhost != null) {
						
						tileGhost.setCore(this);
					}
				}
			}
		}
		
		System.out.println("Multiblock Structure is built");
		
		isValid = true;
	}
	
	@Override
	public void revertStructure() {
	
		int[] core = getCoreTELocalCoords();
		
		pattern = rotate(rotation, pattern);
		
		if(isValid) {
			
			for(int x = (-core[0]); x < (maxWidth - core[0]); x++) {
				
				for(int z = (-core[2]); z < (maxDepth - core[2]); z++) {
					
					for(int y = (-core[1]); y < (maxHeight - core[1]); y++) {
						
						int blockId = worldObj.getBlockId(x + xCoord, y + yCoord, z
								+ zCoord);
						
						if(blockId == 0) {
							continue;
						}
						
						TileEntity ghost = worldObj.getBlockTileEntity(x + xCoord, y
								+ yCoord, z + zCoord);
						
						if((ghost instanceof MBGhost)) {
							System.out.println("Former Block = id: "
									+ ((MBGhost) ghost).getFormerBlock().itemID
									+ " damage: "
									+ ((MBGhost) ghost).getFormerBlock().getItemDamage());
							worldObj.setBlock(x + xCoord, y + yCoord, z + zCoord,
									((MBGhost) ghost).getFormerBlock().itemID,
									((MBGhost) ghost).getFormerBlock().getItemDamage(),
									1 + 3);
							worldObj.markBlockForUpdate(x + xCoord, y + yCoord, z
									+ zCoord);
							System.out.println("Reverting block");
						}
					}
				}
			}
		}
		
		if(Reference.DEBUG_MODE) {
			System.out.println("Multiblock Structure is reverted");
		}
		isValid = false;
	}
	
	@Override
	public void breakStructure() {
	
		int[] core = getCoreTELocalCoords();
		
		if(isValid) {
			
			for(int x = (-core[0]); x < (maxWidth - core[0]); x++) {
				
				for(int z = (-core[2]); z < (maxDepth - core[2]); z++) {
					
					for(int y = (-core[1]); y < (maxHeight - core[1]); y++) {
						TileEntity ghost = worldObj.getBlockTileEntity(x + xCoord, y
								+ yCoord, z + zCoord);
						
						if(ghost instanceof MBGhost && ghost != null) {
							
							worldObj.destroyBlock(x + xCoord, y + yCoord, z + zCoord,
									true);
							worldObj.removeBlockTileEntity(x + xCoord, y + yCoord, z
									+ zCoord);
						}
					}
				}
			}
		}
		
		worldObj.destroyBlock(xCoord, yCoord, zCoord, true);
		worldObj.removeBlockTileEntity(xCoord, yCoord, zCoord);
		
		if(Reference.DEBUG_MODE) {
			
			System.out.println("Multiblock Structure is dismantled");
		}
		isValid = false;
	}
	
	/**
	 * Pattern Stuff
	 */
	
	/**
	 * Gets the ItemStack that the pattern string represents
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return ItemStack
	 */
	public ItemStack getPatternItem(int x, int y, int z) {
	
		return blocksOfPattern.get(pattern[y][x][z]);
	}
}
