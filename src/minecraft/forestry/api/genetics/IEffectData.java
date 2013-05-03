package forestry.api.genetics;

import forestry.api.core.INBTTagable;

public interface IEffectData extends INBTTagable {
	void setInteger(int index, int val);

	void setFloat(int index, float val);

	void setBoolean(int index, boolean val);

	int getInteger(int index);

	float getFloat(int index);

	boolean getBoolean(int index);
}
