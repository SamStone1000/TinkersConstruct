package slimeknights.tconstruct.library.smeltery;

import net.minecraft.item.Item;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import slimeknights.tconstruct.library.tools.Pattern;
/*
 * A class designed to represent a cast as a single object. Because I'm not that
 * interested in making a cast registry just for a bit of optimization, I have
 * to make this slightly janky wrapper class thing.
 */

public class CastType {
    private final Item item;
    private final Item part;
    private final int meta;
    private final int hashCode;

    public CastType(ItemStack itemStack) {
	this.item = itemStack.getItem();
	this.meta = itemStack.getMetadata();
	if (this.item instanceof Pattern) {
	    this.part = Pattern.getPartFromTag(itemStack);
	} else {
	    this.part = Items.AIR;
	}
	    // this object is intended solely to be key to a hashmap, so it's
	// probably faster to compute the hashcode now
	this.hashCode = ((this.meta * 31) + this.part.hashCode() * 31) + this.item.hashCode();
    }

    @Override
    public int hashCode() {
	return this.hashCode;
    }

    @Override
    public boolean equals(Object o) {
	if (o instanceof CastType) {
	    if (o != null) {
	    CastType other = (CastType)o;
	    return this.item == other.item && this.part == other.part && this.meta == other.meta;
	    } else {
		return false;
	    }
	} else {
	    return false;
	}
    }
}
