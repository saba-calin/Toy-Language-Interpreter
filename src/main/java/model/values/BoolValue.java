package model.values;

import model.types.BoolType;
import model.types.IType;

public class BoolValue implements IValue {
    private boolean val;

    public BoolValue(boolean val) {
        this.val = val;
    }

    public boolean getValue() {
        return this.val;
    }

    @Override
    public boolean equals(IValue value) {
        return value.getType() instanceof BoolType && ((BoolValue) value).getValue() == this.val;
    }

    @Override
    public IType getType() {
        return new BoolType();
    }

    @Override
    public String toString() {
        return Boolean.toString(this.val);
    }
}
