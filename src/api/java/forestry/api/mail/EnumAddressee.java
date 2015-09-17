/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 *
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.mail;

import java.util.Locale;

public enum EnumAddressee {
	PLAYER, TRADER;
	
	public static EnumAddressee fromString(String ident) {
		ident = ident.toLowerCase(Locale.ENGLISH);
		for(EnumAddressee addr : values()) {
			if(addr.toString().equals(ident))
				return addr;
		}
		
		return null;
	}

	public String toString() {
		return super.toString().toLowerCase(Locale.ENGLISH);
	}
}
