package model.values;

import model.types.IType;
import model.types.IntType;

public class IntValue implements IValue{
    private int val;

    public IntValue(int val) {
        this.val = val;
    }

    public int getValue() {
        return this.val;
    }

    @Override
    public boolean equals(IValue value) {
        return value.getType() instanceof IntType && ((IntValue) value).getValue() == this.val;
    }

    @Override
    public IType getType() {
        return new IntType();
    }

    @Override
    public String toString() {
        return Integer.toString(this.val);
    }
}
